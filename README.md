Алгоритм развертывания проекта

Вначале создаем базу
скрипт БД в корне проекта script.sql

Cклонировать проект
git clone git@github.com:RealSorcerer/converter.git

Собрать и запустить
cd converter && mvn clean install && cd target/ && java -jar cconverter-1.0-SNAPSHOT.jar

Результат проверять по адресу http://localhost:8090/

