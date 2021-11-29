# Test Application Playground

> Original app repository - [https://github.com/dgusakov/test_app](https://github.com/dgusakov/test_app)

## Table of contents

* [Test Application Playground](#test-application-playground)
  * [<em>Table of contents</em>](#table-of-contents)
  * [<em>Summary</em>](#summary)
  * [<em>How to install</em>](#how-to-install)
  * [<em>How to build</em>](#how-to-build)
    * [Test Application build](#test-application-build)
    * [Test Suites build](#test-suites-build)
  * [<em>How to run</em>](#how-to-run)
    * [Local run](#local-run)
    * [Docker run](#docker-run)
  * [<em>Issues</em>](#issues)
  * [<em>Authors</em>](#authors)
  * [<em>Licensing</em>](#licensing)
  * [<em>Links</em>](#links)

## Summary

The project consists of the following submodules:

* [**App**](https://github.com/AlexRogalskiy/test-app-playground/blob/master/app) `app`
  - The REST API application under test.
* [**Test App**](https://github.com/AlexRogalskiy/test-app-playground/blob/master/test-app) `test-app`
  - The application with test suites.

## How to install

```bash
git clone https://github.com/AlexRogalskiy/test-app-playground
cd test-app-playground
```

## How to build

### Test Application build

```bash
cd app
make docker-build
```

### Test Suites build

```bash
cd test-app
make build
make docker-build
```

## How to run

### Local run

To make the application running locally the following steps should be executed:

#### Running ***Test Application***

```bash
cd app
make docker-run
```

#### Running ***Test Suites***

```bash
cd test-app
make local-chrome-run
```

or

```bash
cd test-app
make local-firefox-run
```

### Docker run

Pull chrome/firefox browser images:

```bash
docker pull selenoid/vnc:chrome_58.0
docker pull dumbdumbych/selenium_vnc_chrome_arm64:91.0.b

docker pull selenoid/vnc:firefox_53.0
```

To make the applications running in docker containers run the following command:

```bash
make docker-up
```

To stop docker containers:

```bash
make docker-down
```

To view docker containers logs (in live mode):

```bash
make docker-logs
```

## Issues

There are several issues that may be arisen during docker run:

- org.openqa.selenium.SessionNotCreatedException: Could not start a new session. Response code 500. Message:
  wait: `http://172.17.0.2:4444/` does not respond in 1m0s

The problem is closely connected with drivers availability with version matching as well as browser containers support
for several architectures (`amd64`/`arm64`).

## Authors

***Test App Playground***  is maintained by the following GitHub team-members:

[![Author](https://img.shields.io/badge/author-AlexRogalskiy-FB8F0A)](https://github.com/AlexRogalskiy)

with community support please contact with us if you have some question or proposition.

## Licensing

***Test App Playground*** is distributed under MIT license.

The detailed information is presented at 
[license agreement](https://github.com/AlexRogalskiy/test-app-playground/blob/master/LICENSE.txt)

## Links

- [Selenoid Docs](https://aerokube.com/selenoid/latest/)
- [Selenide Docs](https://selenide.org)
- [Cucumber Docs](https://cucumber.io/)
