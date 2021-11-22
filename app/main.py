import os
import yaml
from http import HTTPStatus
from shutil import copyfile
from os.path import isfile, join
from json import loads
from app import app
from flask import request, jsonify, render_template
from werkzeug.utils import secure_filename
from errors import DependencyNotFound, NoLabelForButton, InvalidYamlFormat


def check_dirs():
    """
    Check if app directories exist. Create if necessary
    """
    for app_dir in {app.config["UPLOAD_FOLDER"], app.config["CURRENT_TEMPLATE_DIR"]}:
        if not os.path.exists(app_dir):
            os.makedirs(app_dir)


def get_file_path(tmpl_id):
    return join(app.config["UPLOAD_FOLDER"], f"{tmpl_id}.yaml")


def allowed_file_ext(filename):
    """
    Check if file extension is one of ALLOWED_EXTENSIONS
    """
    return (
        "." in filename
        and filename.rsplit(".", 1)[1].lower() in app.config["ALLOWED_EXTENSIONS"]
    )


def validate_rec(rec):
    """
    Check fields in template record
    """
    if "id" not in rec:
        raise InvalidYamlFormat(f'No "id" field in {rec}')
    if "link" in rec and "label" not in rec:
        raise InvalidYamlFormat(
            f"Links without labels are not allowed. Error occurred in {rec}"
        )


def load_yaml_from_file(path):
    """
    Load yaml file as python object
    """
    if not os.path.isfile(path):
        raise FileNotFoundError
    with open(path) as f:
        return yaml.safe_load(f)


def process_template(tmpl_id):
    """
    Load template from the file. Validate contents. Rewrite installed template file
    """
    path = get_file_path(tmpl_id)
    template = load_yaml_from_file(path)
    if not isinstance(template, list):
        raise InvalidYamlFormat
    for el in template:
        validate_rec(el)
    ids = [rec["id"] for rec in template]
    for el in template:
        try:
            if el["depends"] not in ids:
                raise DependencyNotFound(
                    f"Dependency form {el} is not presented in template"
                )
        except KeyError:
            pass
    copyfile(path, join(app.config["CURRENT_TEMPLATE_FILE"]))


@app.route("/api/v1/templates", methods=["POST"])
def upload_template():
    if "file" not in request.files:
        resp = jsonify({"message": "No file part in the request"})
        resp.status_code = HTTPStatus.BAD_REQUEST
        return resp
    file = request.files["file"]
    if file.filename == "":
        resp = jsonify({"message": "No file selected for uploading"})
        resp.status_code = HTTPStatus.BAD_REQUEST
        return resp
    if file and allowed_file_ext(file.filename):
        if "data" in request.form:
            filename = (
                str(loads(request.form["data"]).get("tmpl_id", "")).lower() + ".yaml"
            )
        else:
            filename = secure_filename(
                file.filename.rsplit(".", 1)[0].lower() + ".yaml"
            )
        file.save(join(app.config["UPLOAD_FOLDER"], filename))
        resp = jsonify(
            {
                "message": f'Template successfully uploaded. tmpl_id={filename.rsplit(".", 1)[0].lower()}'
            }
        )
        resp.status_code = HTTPStatus.CREATED
        return resp
    else:
        resp = jsonify(
            {"message": f'Allowed file types are {app.config["ALLOWED_EXTENSIONS"]}'}
        )
        resp.status_code = HTTPStatus.BAD_REQUEST
        return resp


@app.route("/api/v1/templates", methods=["GET"])
def list_templates():
    files = [
        f
        for f in os.listdir(app.config["UPLOAD_FOLDER"])
        if isfile(join(app.config["UPLOAD_FOLDER"], f))
    ]
    files_with_params = []
    for f in files:
        files_with_params.append(f.rsplit(".", 1)[0].lower())
    resp = jsonify({"templates": files_with_params})
    return resp


@app.route("/api/v1/templates/<string:tmpl_id>", methods=["DELETE"])
def delete_template(tmpl_id):
    if tmpl_id != "":
        path = get_file_path(tmpl_id)
        if os.path.isfile(path):
            os.remove(path)
            resp = jsonify(
                {"message": f"Template with tmpl_id={tmpl_id} successfully deleted!"}
            )
        else:
            resp = jsonify({"message": f"No template with tmpl_id={tmpl_id} found!"})
            resp.status_code = HTTPStatus.NOT_FOUND
    else:
        resp = jsonify({"message": "No tmpl_id in request!"})
        resp.status_code = HTTPStatus.BAD_REQUEST
    return resp


@app.route("/api/v1/templates/<string:tmpl_id>/install", methods=["POST"])
def install_template(tmpl_id):
    if tmpl_id != "":
        try:
            process_template(tmpl_id)
            resp = jsonify(
                {"message": f"Template with tmpl_id={tmpl_id} successfully installed!"}
            )
        except (DependencyNotFound, NoLabelForButton, InvalidYamlFormat) as e:
            resp = jsonify({"message": e.message})
            resp.status_code = HTTPStatus.BAD_REQUEST
        except FileNotFoundError:
            resp = jsonify({"message": f"No template with tmpl_id={tmpl_id} found!"})
            resp.status_code = HTTPStatus.NOT_FOUND
    else:
        resp = jsonify({"message": "No tmpl_id in request!"})
        resp.status_code = HTTPStatus.BAD_REQUEST
    return resp


@app.route("/")
def home():
    try:
        template = load_yaml_from_file(join(app.config["CURRENT_TEMPLATE_FILE"]))
    except FileNotFoundError:
        template = []
    return render_template("main.html", buttons=template)


if __name__ == "__main__":
    check_dirs()
    app.run(host="0.0.0.0", port=5000)
