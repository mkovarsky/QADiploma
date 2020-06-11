## Запуск приложения

1. Запустить контейнеры с симулятором банковских сервисов, двумя СУБД и двумя СУТ на разных портах (обращающимся каждый к своей БД) из корневой директории проекта командой: 
      ``` 
     docker-compose up
      ``` 
2. Запустить симулятор банковских сервисов командой:
      ``` 
      npm start
      ``` 
3. Запустить приложение командой 
* для mysql   
   ```
   java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=user -P:jdbc.password=pass
     ```
* для postgres 
    ```
   java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql://192.168.99.100:5432/app -P:jdbc.user=user -P:jdbc.password=pass
    ```