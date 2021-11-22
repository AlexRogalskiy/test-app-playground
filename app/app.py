from flask import Flask

UPLOAD_FOLDER = "app-files/"
CURRENT_TEMPLATE_DIR = "app-files/current/"
CURRENT_TEMPLATE_FILE_NAME = "template.yaml"
ALLOWED_EXTENSIONS = {"yaml", "yml"}

app = Flask(__name__)
app.secret_key = "XT\\\xcdRs\xf4\xb4N\xca\xd7/6\xda\nI\x86\x87\xca@H\xba\xc8="
app.config["UPLOAD_FOLDER"] = UPLOAD_FOLDER
app.config["CURRENT_TEMPLATE_DIR"] = CURRENT_TEMPLATE_DIR
app.config["CURRENT_TEMPLATE_FILE"] = CURRENT_TEMPLATE_DIR + CURRENT_TEMPLATE_FILE_NAME
app.config["MAX_CONTENT_LENGTH"] = 128 * 1024
app.config["ALLOWED_EXTENSIONS"] = ALLOWED_EXTENSIONS
