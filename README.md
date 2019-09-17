# Spring boot starter data graphql

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

## Documentation

Spring boot starter for [java-data-graphql](https://github.com/tcaselli/java-data-graphql).  
See [spring-data-graphql-demo](https://github.com/tcaselli/spring-data-graphql-demo) for an example of usage.

### Configuration properties

When creating a GQLExecutor, you will have to provide a ```com.daikit.graphql.config.GQLSchemaConfig```. The default implementation takes these properties default values. You can override these properties in your ```application.properties``` file.

```properties
spring.data.graphql.query-type-name=QueryType
spring.data.graphql.mutation-type-name=MutationType
spring.data.graphql.input-type-name-suffix=InputType
spring.data.graphql.output-type-name-suffix=OutputType
spring.data.graphql.query-get-list-output-type-name-suffix=LoadResult
spring.data.graphql.output-delete-result-type-name-prefix=DeleteResult
spring.data.graphql.query-get-list-prefix=getAll
spring.data.graphql.query-get-by-id-prefix=get
spring.data.graphql.mutation-save-prefix=save
spring.data.graphql.mutation-delete-prefix=delete
spring.data.graphql.attribute-id-suffix=Id
spring.data.graphql.attribute-plural-suffix=s
spring.data.graphql.attribute-id-plural-suffix=Ids
spring.data.graphql.mutation-attribute-input-data-name=data
spring.data.graphql.query-get-list-attribute-output-data-name=data
spring.data.graphql.query-get-list-filter-attribute-order-by-direction-default-value=ASC
spring.data.graphql.mutation-delete-result-attribute-id=id
spring.data.graphql.mutation-delete-result-attribute-typename=typename
spring.data.graphql.query-get-list-filter-attribute-name=filter
spring.data.graphql.query-get-list-filter-attribute-operator-type-name-prefix=FilterOperator
spring.data.graphql.query-get-list-filter-attribute-operator-name=operator
spring.data.graphql.query-get-list-filter-attribute-value-name=value
spring.data.graphql.query-get-list-filter-attribute-order-by-name=orderBy
spring.data.graphql.query-get-list-filter-attribute-order-by-field-name=field
spring.data.graphql.query-get-list-filter-attribute-order-by-direction-name=direction
spring.data.graphql.query-get-list-filter-attribute-name=paging
spring.data.graphql.query-get-list-filter-attribute-total-length-name=totalLength
spring.data.graphql.query-get-list-filter-attribute-offset-name=offset
spring.data.graphql.query-get-list-filter-attribute-limit-name=limit
spring.data.graphql.query-get-list-filter-attribute-limit-default-value=25
spring.data.graphql.query-get-list-filter-entity-type-name-suffix=Filter
spring.data.graphql.concrete-embedded-extending-type-name-prefix=type
spring.data.graphql.attribute-id-name=id
```

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