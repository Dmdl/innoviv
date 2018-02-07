# innoviv

### To generate JOOQ meta data

```
mvn -P generate clean verify -DDATABASE_PROPERTIES=local.db -Dspring.profiles.active=dev
```

### To run the jar file

```$xslt
java -jar -Dspring.profiles.active=local mas-0.0.1-SNAPSHOT.jar
```