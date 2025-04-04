Бэйджи всяких проверок

[![Actions Status](https://github.com/Dangerwind/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Dangerwind/java-project-72/actions)
[![Java CI](https://github.com/Dangerwind/java-project-72/actions/workflows/build.yml/badge.svg)](https://github.com/Dangerwind/java-project-72/actions/workflows/build.yml)
[![Maintainability](https://qlty.sh/badges/047169aa-2f0f-4aeb-aab5-a7d36de972c2/maintainability.svg)](https://qlty.sh/gh/Dangerwind/projects/java-project-72)
[![Code Coverage](https://qlty.sh/badges/047169aa-2f0f-4aeb-aab5-a7d36de972c2/test_coverage.svg)](https://qlty.sh/gh/Dangerwind/projects/java-project-72)

_новый test coverage из ветки qlty работает плохо, то отображается, то нет_
## Учебный проект по созданию сайта проверки на SEO пригодность
Программа запрашивает адрес сайта, далее вытаскивает по этому 
адресу title, h1, description и сохраняет в базу данных. Проект демонстрирует умение работать с Javaline и c SQL запросами. Умение создавать простые html страницы (шаблоны) 
с использованием bootstrap. Умение писать тесты, в том числе с использованием Mockito.

Демонстрация работы сайта доступна по [ссылке](https://java-project-72-qx5q.onrender.com)

Склонируйте и запустите программу:
```
git clone https://github.com/Dangerwind/java-project-72.git
make build
make run
````
Откройте в браузере страницу `http://localhost:7070/`

<hr>

На главной странице введите адрес проверяемого сайта и нажмите **Отправить на проверку**.
![](https://github.com/Dangerwind/java-project-72/blob/main/img/01-mainpage.png)

<hr>

В появившимся окне найдите строчку с введенной ранее ссылкой на сайт и нажмите на нее.
![](https://github.com/Dangerwind/java-project-72/blob/main/img/02-allsites.png)

<hr>

В новом окне нажмите **Запустить проверку**
![](https://github.com/Dangerwind/java-project-72/blob/main/img/03-checkpage.png)

<hr>

На странице появится информация о коде ответа от проверяемого сайта, заголовок страницу, заголовок H1, описание и дата проверки
![](https://github.com/Dangerwind/java-project-72/blob/main/img/04-checked.png)
Нажмите **Главная** чтобы ввести новый адрес проверяемого сайта.
