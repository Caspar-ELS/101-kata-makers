## Service status checker

This is a simple service status checker that shows the service instance is up or down.

## Requirements:

- [ ] Show all BOM running microservices in DEV

## Example
```
Name: orcx, State: running
Name: trdr, State: running
Name: rercv3, State: running
```


## Useful information

Most of the information of a microservice comes from EC2 instance `tags` (you can refer to Ec2Service.java)

| Tag               | Description                        |
|-------------------|------------------------------------|
| `Role`            | Microservice abbv. (ORCX / RERCv3) |
| `Environment`     | environment (dev / sit)            |
| `MicroService` / `SubProduct` | Full microservice name (revenue-recognition-status-router-v3)          |



