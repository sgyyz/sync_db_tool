sync_db_tool
=========================
# Background
Since we need to sync data between different environment databases, so this tool will help us to do this work by case. It will support migrate whole tables data or by identifier.

# Environment
- gradle 2.14+ (if you are using a jar version, this tool is not necessary)
- jdk 1.8.0+

# Preconditions
- Tables are between different environments should have same schema(Local, QA or DEV). If local table schema is out of date, please run the liquidbase script to update them.
- You should have your own credentials for all environment. (Recommend: using read-only credentials on QA or Dev)

# Start project
We’ve already shared this project on github, so you can clone it there.
* Clone project , you can use the follow command line：
`git clone https://github.com/lijun003/sync_db_tool.git`

* Configuration modification, you should modify, 
**application.properties:**
```
 # remote datasource properties                spring.datasource.url=jdbc:mysql://localhost:3306/ptv_working?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# local datasource properties
spring.datasource.secondary.url=jdbc:mysql://localhost:3306/ptv_working_local?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.secondary.username=root
spring.datasource.secondary.password=123456
spring.datasource.secondary.driver-class-name=com.mysql.cj.jdbc.Driver
```
**gradle.properties:**
```
flywayUrl = jdbc:mysql://localhost:3306/ptv_working_local?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false
flywayUser = root
flywayPassword = 123456
flywayDriver = com.mysql.cj.jdbc.Driver
baselineOnMigrate= true
```
* Build cloned application, you can use the follow command lines:
```
cd sync_data_tool
./gradlew cleanIdea idea
./gradlew clean build
```
* 2 ways to start this project
`.gradlew bootRun`
Or
`java -jar build/libs/sync_db_tool-0.1.0.jar`

* Then it is time to sync data we need. Because we don not have UI. We use swagger ui instead.
`http://localhost:8081/data-sync/swagger-ui.html`



