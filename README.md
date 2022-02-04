## Запуск тестов
* Склонировать репозиторий ```git clone```
* Запустить контейнеры с Node.js, MySql, PostgreSQL командой ```docker-compose up``` (необходим установленный Docker)
* Запуск приложения:
     * для запуска под MySQL использовать команду ```java -Durl="jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar```
     * для запуска под PostgreSQL использовать команду ```java -Durl="jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar```
* Запуск тестов:
     * для запуска под MySQL использовать команду ``` ./gradlew clean test -Durl="jdbc:mysql://localhost:3306/app"```
     * для запуска под PostgreSQL использовать команду ``` ./gradlew clean test -Durl="jdbc:postgresql://localhost:5432/app"```
* Запуск Allure для формирования отчетов командой ``` ./gradlew allureServe```
