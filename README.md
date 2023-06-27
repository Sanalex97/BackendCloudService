  <h1 align="center">Сервис для облачного хранилища файлов</h1> 
  <h3 align="center">
    Дипломный проект на курсе Нетологии по Java-разработке</h3>

<!-- ABOUT THE PROJECT -->
## О проекте
Сервис предоставляет REST-интерфейс для загрузки файлов и вывода списка уже загруженных файлов пользователя.
Все запросы к сервису проходят авторизацию. Сервсис подключается к заранее подготовленному веб-приложению (FRONT).

Функционал сервиса:
- вывод списка файлов;
- добавление файла;
- удаление файла;
- авторизация.

## Используемые инструменты и технологии

<div>
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/a4df725b-20e4-46c0-9642-2df27af30e4b" title="java" alt="java" width="50" height="50"/>&nbsp
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/d734ea82-b14e-4088-83b8-663a90308a41" title="spring" alt="spring" width="50" height="50"/>&nbsp
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/29623d20-16f7-42e6-972e-6256e0c10546" title="maven" alt="maven" width="50" height="50"/>&nbsp
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/4bced588-3a15-4822-9f32-ed63c965afce" title="docker" alt="docker" width="60" height="50"/>&nbsp
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/08f82f97-faa5-435c-a47c-532fc103ca30" title="mySQL" alt="mySQL" width="50" height="50"/>&nbsp
  <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/f3e1acd2-1593-4105-8f5e-d5e4e0a5726e" title="JUnit" alt="JUnit" width="70" height="50"/>&nbsp
</div>

## Как запустить и использовать проект
### Запуск BACKEND
1. Скачайте <a href="https://github.com/Sanalex97/BackendCloudService.git"><strong>репозиторий</strong></a>.
2. Запустите сервис.
3. По умолчанию в базу данных добавлены 3 пользователя, которые могут пройти авторизацию:
   1. login: ```user1``` password: ```12345678```
   2. login: ```user2``` password: ```87654321```
   3. login: ```user3``` password: ```12348765```
4. Для добавления новых пользователей необходимо в классе ```BackendCloudServiceApplication``` в методе ```run(String... args)``` ввести данные новых пользователей.
  
### Запуск FRONT
1. Установите nodejs (версия не ниже 19.7.0) на компьютер, следуя <a href="https://nodejs.org/ru/download/current/"><strong>инструкции</strong></a>. 
2. Скачайте <a href="https://github.com/netology-code/jd-homeworks/blob/master/diploma/netology-diplom-frontend"><strong>FRONT</strong></a> (JavaScript). 
3. Перейдите в папку FRONT приложения и все команды для запуска выполняйте из неё. 
4. Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (```npm install```, ```npm run serve```). 
5. Далее нужно задать url для вызова backend-сервиса.
   
   5.1 В файле  ```.env``` FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: ```VUE_APP_BASE_URL=http://localhost:8080 ```.
   
   -  Нужно указать корневой url вашего backend, к нему frontend будет добавлять все пути согласно спецификации. 
   -  Для  ```VUE_APP_BASE_URL=http://localhost:8080 ``` при выполнении логина frontend вызовет  ```http://localhost:8080/login ```.

   5.2 Запустите FRONT снова: ```npm run serve```.
   
   5.3 Изменённый  ```url``` сохранится для следующих запусков.
 
 6. По умолчанию FRONT запускается на порту 8080 и доступен по url в браузере  ```http://localhost:8080```.
    - Если порт 8080 занят, FRONT займёт следующий доступный ( ```8081```). После выполнения  ```npm run serve``` в терминале вы увидите, на каком порту он запустился.
 
 ### Использование проекта
 1. Перейдите на страницу авторизации (по умочанию:```http://localhost:8081/login```).
 2. Введите логин и пароль пользователя, который может пройти авторизацию.
 3. Для загрузки файла в облачное хранилище необходимо нажать кнопку "```добавить```" и выбрать нужный файл.
 4. После загрузки файла появится возможность с помощью иконок:
 
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/58667adb-f21d-43f0-903d-269127412230" width="20" height="20" /> - ```изменить имя файла```.
  
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/dd840968-64ba-4cec-80e7-069bbc7c9226" width="20" height="20" /> - ```скачать файл```.
  
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/0b077121-eb54-443d-9c33-3556cd68a0a9" width="20" height="20" /> - ```удалить файл```.
  
