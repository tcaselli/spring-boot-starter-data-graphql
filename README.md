# Spring boot starter data graphql

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

## Documentation

Spring boot starter for [java-data-graphql](https://github.com/tcaselli/java-data-graphql).  
See [spring-data-graphql-demo](https://github.com/tcaselli/spring-data-graphql-demo) for an example of usage.

## Where can I get the latest release?

You can check latest version and pull it from the [central Maven repositories](https://mvnrepository.com/artifact/com.daikit/spring-boot-starter-data-graphql):

With maven

```xml
<dependency>
    <groupId>com.daikit</groupId>
    <artifactId>spring-boot-starter-data-graphql</artifactId>
    <version>x.x</version>
</dependency>
```

Or with gradle 

```gradle
compile group: 'com.daikit', name: 'spring-boot-starter-data-graphql', version: 'x.x'
```

## Contributing

We accept Pull Requests via GitHub. There are some guidelines which will make applying PRs easier for us:
+ No spaces :) Please use tabs for indentation.
+ Respect the code style.
+ Create minimal diffs - disable on save actions like reformat source code or organize imports. If you feel the source code should be reformatted create a separate PR for this change.
+ Provide JUnit tests for your changes and make sure your changes don't break any existing tests by running ```mvn clean test```.

## License

This code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0).