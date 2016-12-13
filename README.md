# sync_db_tool

### Reload IntelliJ IDEA project
`./gradlew cleanIdea idea`

### Run clean and build tasks
`./gradlew clean build`, will auto create database

### Run unit test
`./gradlew test`, it depends on `build` task.

### Run integration test
`./gradlew integrationTest` or `./gradlew iT`, it depends on `build` task.

### Run build exclude integration test
`./gradlew build -x integrationTest` or `./gradlew build -x iT`

### Start Application
`./gradlew bootRun`

### Run the JAR file
`java -jar build/libs/sync_db_tool-0.1.0.jar`

### Swagger UI link
`http://localhost:8081/data-sync/swagger-ui.html`


### Git提交规范：

[卡号][提交作者&Pair作者] - comment here

Note: [提交作者]和comment之间需要有 空格+minus+空格，comment内容不要出现minus。

**Example:** `[M001][justin&jason] - add the order domain object`

