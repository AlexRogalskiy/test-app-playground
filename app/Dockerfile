FROM python:3
LABEL maintainers="Dmitry Gusakov <gdv@arenadata.io>"

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

CMD [ "python", "./main.py" ]