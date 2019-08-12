# Crocodile

[![gradle-version](https://img.shields.io/badge/gradle-5.5.1-brightgreen)](https://img.shields.io/badge/gradle-5.5.1-brightgreen)

Deletes tweets using their status id's given in file. 

### Usage
clone project and set required properties in crocodile.properties file, and then execute: 

```groovy
gradle run
```

#### crocodile.properties

```properties
interaction-count=7
source-type=0
status-id-list-file=path/to/statusFile
rate-limit-check-in-deleted-status-count=100
```

#### crocodile.auth
```properties
#Twitter API Auth keys and options
consumer-key =
consumer-secret =
access-token = 
access-token-secret =
```


#### example status-id-file

```text
1
2
3
4
5
```