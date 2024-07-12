## **Установить приложения**

1. IntelliJ IDEA 2024.1.1 (Community Edition)
2. Docker Desktop
3. Браузер: Chrome Версия 126.0.6478.127 (Официальная сборка), (64 бит)

**Убедитесь, что порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны;**

## **Процедура запуска тестов**

1. Запускаем Docker Desktop
2. Запускаем IDEA
3. В терминале IDEA набираем docker compose down
4. Ждем удаления 3 контейнеров и набираем docker compose up
5. Ждем запуска контейнеров node-app, mysql, postgres и во 2-ом терминале для
   запуска джарника набираем java -jar artifacts/aqa-shop.jar -port=8080
6. В 3-ем терминале запускаем тесты командой ./gradlew clean test --info
7. Для генерации отчетов на Allure после прохождения тестов набираем в терминале ./gradlew allureserve
8. По умолчанию в файле application.properties указано подключение к MySQL://localhost:3306/app.
9. Для замены СУБД на PostgreSQL необходимо заменить строку 3 в файле application.properties на
   spring.datasource.url=jdbc:postgresql://localhost:5432/app и затем также запусить тесты согласно
   пунктам 3-6
10. Для отключения джарника/остановки контейнеров нажать ctrl+C