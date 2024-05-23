## Работу выполнил кандидат: Тарасов Д.В.

## Test cases TODO App - https://docs.google.com/spreadsheets/d/1c9q97c_XA2UqujoxBZ3nVG80vFkAVNLYU4zj2gloir8/edit?usp=sharing

## Bug report TODO App - https://docs.google.com/spreadsheets/d/1c9q97c_XA2UqujoxBZ3nVG80vFkAVNLYU4zj2gloir8/edit#gid=1024993401

## JS-Schema - https://docs.google.com/spreadsheets/d/1c9q97c_XA2UqujoxBZ3nVG80vFkAVNLYU4zj2gloir8/edit#gid=1351548332



### 1. Выполнено задание по тестированию API приложения TODO для HTTP-методов POST, GET, PUT, DELETE.

    Для каждого теста были создан Тест-Кейс (Test cases TODO App).
    При тестировании приложения был выявлен баг, подробнее описано в документе: Баг-Репорт (Bug report TODO App).

    Перед каждым тестом производится проверка существования задач в БД. 
    Если задачи существуют - производится их удаление, что гарантирует выполнение тестов в "чистой среде".

    После выполнения каждого теста производится удаление всех тестовых данных из БД.

### 2. Для запуска программы тестирования приложения TODO необходимо:
        1. Создать Properties: "credentials.properties" 
            по следующему пути: src/test/resources/credentials.properties

        2. Прописать в "credentials.properties":
            username=*****
            password=*****

            где необходимо указать значение (заместо *****) для : username
                                                                    password