# spring-boot-starter-maintenance ![Build Status](https://github.com/viascom/spring-boot-starter-maintenance/actions/workflows/build.yml/badge.svg)

spring-boot-starter-maintenance is a maintenance mode library and auto-configuration for spring boot web and security projects.

With spring-boot-starter-maintenance anyone can easily enforce best practices during maintenance work on their applications. For example, every HTTP client will receive a response with status code 503 - Service Unavailable during maintenance. On top of that, the library provides an injection point for clean-up tasks and detailed statistics. Everything is customizable and extendable programmed in mind.

---

### Requirements

spring-boot-starter-maintenance depends on the following two dependencies and will not start without them present.

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-security

---

### Download

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

[spring-boot-starter-maintenance jar downloads](https://maven-badges.herokuapp.com/maven-central/io.viascom.devutils/spring-boot-starter-maintenance) are available from Maven Central.

---

### Getting Started

Open your implementation of the WebSecurityConfigurerAdapter (f.e. named WebSecurityConfig) and add the following three parts:

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
.exceptionHandling().accessDeniedHandler(new DefaultMaintenanceAccessDeniedHandler(maintenance))
```

---
### Maintenance properties

All properties can be accessed under the property `maintenance`.

| Name    | Description                                                               | Default Value |
|---------|---------------------------------------------------------------------------|---------------|
| enabled | Enable maintenance mode.                                                  | false         |
| roles   | Roles to be allowed the access to the app during a maintenance.           |               |
| clean   | Automatically execute all MaintenanceCleaners at stop of the Maintenance. | false         |
| alert   | Automatically execute all MaintenanceAlerts at start of the Maintenance.  | false         |
| events  | Publish spring events for all actions.                                    | false         |
| stats   | Enable persisting of maintenances and stats aggregation.                  | false         |

---

### Versioning 🔖 [![GitHub release](https://img.shields.io/github/release/viascom/spring-boot-starter-maintenance/all?logo=GitHub)](https://github.com/viascom/spring-boot-starter-maintenance/releases/latest)

This project is developed by [Viascom](https://github.com/viascom) using the [Semantic Versioning specification](https://semver.org). For the versions available, see the [releases on this repository](https://github.com/viascom/spring-boot-starter-maintenance/releases).

---

### Change log 📝

See the [CHANGELOG](CHANGELOG.md) file for details.

---

### Authors 🖥️

* **Nikola Stanković** - *Initial work* - [botscripter](https://github.com/botscripter)
* **Patrick Bösch** - *Initial work* - [itsmefox](https://github.com/itsmefox)

See also the list of [contributors](https://github.com/viascom/spring-boot-starter-maintenance/contributors) who participated in this project. 💕

---

### License

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
