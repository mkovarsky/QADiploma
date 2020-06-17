## Запуск приложения

1. Запустить контейнеры с симулятором банковских сервисов, двумя СУБД и двумя СУТ на разных портах (обращающимся каждый к своей БД) из корневой директории проекта командой: 
   ``` 
   docker-compose up
   ``` 
1. Запустить симулятор банковских сервисов командой:
   ``` 
   npm start
   ``` 
1. Запустить приложение командой   
   для MySQL:
   ```
   java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar
   ```
   для Postgres:
   ```
   java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar artifacts/aqa-shop.jar
   ```
1. Запустить тесты командой:
    для MySQL:
    ```
    gradlew clean test allureReport -Ddb.url=jdbc:mysql://192.168.99.100:3306/app
    ```
   для Postgres:
   ```
   gradlew clean test allureReport -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app
   ```
1. Для просмотра отчета запустить команду:
    ```
    gradlew allureServe
    ```
   
В пунктах 3 и 4 IP адрес может отличаться от указанного. Если приложение не запускается, следует заменить 192.168.99.100 на:
* *localhost* в случае использования нативного Docker
* *IP адрес*, выдаваемый командой `docker-machine ip` в случае использования Docker Toolbox