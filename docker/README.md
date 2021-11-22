## Description

Test App Playground deployment consists of the following docker containers:

- `app` (rest api service)
- `test-app` (test scenarios)

## How to use

First make shell script executable:

```shell
$ chmod +x ./docker-compose.sh
```

To start docker containers:

```shell
$ ./docker-compose.sh up
```

To stop docker containers:

```shell
$ ./docker-compose.sh down
```

To view docker containers logs:

```shell
$ ./docker-compose.sh logs
```
