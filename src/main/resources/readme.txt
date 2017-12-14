Инструкция.

1. Настроить бд h2
При необходимости изменить \spring-boot-h2\src\main\resources\application.properties

    spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    spring.datasource.username=sa
    spring.datasource.password=

2. Выполнить скрипты в бд из файла spring-boot-h2\src\main\resources\ddl.sql

4. Выполнить  mvn package скопировать spring-boot-h2\target\spring-boot-h2-0.0.1-SNAPSHOT.war и задеплоить в томкат.