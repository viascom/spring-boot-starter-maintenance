# spring-boot-starter-maintenance

![Build Status](https://github.com/viascom/spring-boot-starter-maintenance/actions/workflows/build.yml/badge.svg)
[![License: Unlicense](https://img.shields.io/badge/license-Unlicense-blue.svg)](http://unlicense.org/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.viascom.devutils/spring-boot-starter-maintenance/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.viascom.devutils/spring-boot-starter-maintenance/)

**spring-boot-starter-maintenance** is an extensible auto-configuration library for spring boot web and security
projects supporting Java and Kotlin applications to block access to your application during maintenances and still
provide a secure open door for your maintainers.

With spring-boot-starter-maintenance anyone can easily make use of best practices during maintenance work on their
applications. In fact, every HTTP client will receive a response with status code 503 - Service Unavailable during
maintenance. This library provides additionally injection points for alert- and clean-up tasks.

spring-boot-starter-maintenance is ready to use out of the box for most common setups. Even for uncommon applications
and technologies, it should be simple to implement the necessary interfaces to connect a library/framework/etc. to it.

## Requirements

JDK >= 1.8

spring-boot-starter-maintenance depends on the following two dependencies and will not start without them present.

- org.springframework.boot:spring-boot-starter-web *(required)*
- org.springframework.boot:spring-boot-starter-security *(required)*

## Download

Gradle:

```gradle
dependencies {
  implementation 'io.viascom.devutils:spring-boot-starter-maintenance:0.0.1'
}
```

Maven:

```xml
<dependency>
  <groupId>io.viascom.devutils</groupId>
  <artifactId>spring-boot-starter-maintenance</artifactId>
  <version>0.0.1</version>
</dependency>
```

[spring-boot-starter-maintenance jar downloads](https://maven-badges.herokuapp.com/maven-central/io.viascom.devutils/spring-boot-starter-maintenance)
are available from Maven Central.

## Getting Started

Open your implementation of the WebSecurityConfigurerAdapter (f.e. named WebSecurityConfig) and add the following three
parts:

**Step 1:** Autowire maintenance

```java
@Autowired
private Maintenance maintenance;
```

**Step 2:** Add a request matcher

```java
.authorizeRequests()
.requestMatchers(new DefaultMaintenanceRequestMatcher(maintenance)).denyAll()
```

**Step 3:** Add a access denie handler

```java
.exceptionHandling()
.accessDeniedHandler(new DefaultMaintenanceAccessDeniedHandler(maintenance))
```

## Configuration Properties

### Basic configuration example

```yaml
maintenance:
  enabled: true
  roles:
    - ADMIN
```

### Full configuration example

```yaml
maintenance:
  enabled: true
  roles:
    - MAINTAINER
    - ADMIN
  alert: true
  clean: true
  retry-after: 30
  events: true
```

### Maintenance properties

All properties can be accessed under the property `maintenance`.

| Name        | Description                                                                                                                      | Default Value |
|-------------|----------------------------------------------------------------------------------------------------------------------------------|---------------|
| enabled     | Enable to instantiate the maintenance mode as active.                                                                            | false         |
| roles       | Roles to be allowed the access to the API during a maintenance. This property is case-sensitive.                                 | *none*        |
| alert       | Enable to run all classes implementing the MaintenanceAlert interface during the start maintenance process.                      | false         |
| clean       | Enable to run all classes implementing the MaintenanceCleaner interface during the stop maintenance process.                     | false         |
| retry-after | Default value for the "Retry-After" response HTTP header in seconds, which is used in the DefaultMaintenanceAccessDeniedHandler. | 60            |
| events      | Enable to publish spring events for maintenance events.                                                                          | false         |

## Versioning 🔖 [![GitHub release](https://img.shields.io/github/release/viascom/spring-boot-starter-maintenance/all?logo=GitHub)](https://github.com/viascom/spring-boot-starter-maintenance/releases/latest)

This project is developed by [Viascom](https://github.com/viascom) using
the [Semantic Versioning specification](https://semver.org). For the versions available, see
the [releases on this repository](https://github.com/viascom/spring-boot-starter-maintenance/releases).

## Change log 📝

See the [CHANGELOG](CHANGELOG.md) file for details.

## Authors 🖥️

* **Nikola Stanković** - *Initial work* - [botscripter](https://github.com/botscripter)
* **Patrick Bösch** - *Initial work* - [itsmefox](https://github.com/itsmefox)

See also the list of [contributors](https://github.com/viascom/spring-boot-starter-maintenance/contributors) who
participated in this project. 💕

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) file.

🙏 If you like spring-boot-starter-maintenance you can show support by starring ⭐ this repository.

## License

spring-boot-starter-maintenance is released under the [Unlicense](LICENSE).

```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org/>
```
