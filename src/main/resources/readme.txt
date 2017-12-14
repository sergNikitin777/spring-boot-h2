Инструкция.

1. Настроить бд h2
При необходимости изменить \spring-boot-h2\src\main\resources\application.properties

    spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    spring.datasource.username=sa
    spring.datasource.password=

2. Выполнить скрипты в бд из файла spring-boot-h2\src\main\resources\ddl.sql

4. Выполнить  mvn package скопировать spring-boot-h2\target\spring-boot-h2-0.0.1-SNAPSHOT.war и задеплоить в томкат.

5. Добавление клиента http://localhost:8080/addclient POST запрос : Body raw (application/json)
    {"firstName":"Name"}

6. Добавление телефона клинта http://localhost:8080/addphone/1 POST запрос : Body raw (application/json)
    {"phoneNumber":"+41 44 668 18 00"}

7. Редактирование телефона клинта http://localhost:8080/editphone POST запрос : Body raw (application/json)
    {"id":1,"clientId":1,"phoneNumber":"8-913-919-91-92"}

8. Список клиентов http://localhost:8080/client GET

9. Информация по кокретному клиенту http://localhost:8080/clients?id=2 GET