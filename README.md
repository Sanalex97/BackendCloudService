  <!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>
  
  <h3 align="center">Сервис для облачного хранилища</h3>

  <p align="center">
    An awesome README template to jumpstart your projects!
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## О проекте

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Сервис предоставляет REST-интерфейс для загрузки файлов и вывода списка уже загруженных файлов пользователя.

Все запросы к сервису проходят авторизацию. Сервсис подключается к заранее подготовленному веб-приложению (FRONT).

Функционал сервиса:
* вывод списка файлов;
* добавление файла;
* удаление файла;
* авторизация.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Используемые технологии

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.

* [![Next][Next.js]][Next-url]
* [![React][React.js]][React-url]
* [![Vue][Vue.js]][Vue-url]
* [![Angular][Angular.io]][Angular-url]
* [![Svelte][Svelte.dev]][Svelte-url]
* [![Laravel][Laravel.com]][Laravel-url]
* [![Bootstrap][Bootstrap.com]][Bootstrap-url]
* [![JQuery][JQuery.com]][JQuery-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
### Как запустить и использовать проект
#### Запуск BACKEND
1. Скачайте <a href="https://nodejs.org/ru/download/current/"><strong>репозиторий</strong></a>
2. Запустите сервис
3. По умолчанию в базу данных добавлены 3 пользователя, которые могут пройти авторизацию:
   1. login: ```user1``` password: ```12345678```
   2. login: ```user2``` password: ```87654321```
   3. login: ```user3``` password: ```12348765```
4. Для добавления новых пользователей необходимо в классе ```BackendCloudServiceApplication``` в методе ```run(String... args)``` ввести данные новых пользователей.
  
#### Запуск FRONT
1. Установите nodejs (версия не ниже 19.7.0) на компьютер, следуя <a href="https://nodejs.org/ru/download/current/"><strong>инструкции</strong></a>. 
2. Скачайте <a href="https://github.com/netology-code/jd-homeworks/blob/master/diploma/netology-diplom-frontend"><strong>FRONT</strong></a> (JavaScript). 
3. Перейдите в папку FRONT приложения и все команды для запуска выполняйте из неё. 
4. Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (```npm install```, ```npm run serve```). 
5. Далее нужно задать url для вызова backend-сервиса.
   
   5.1 В файле  ```.env``` FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: ```VUE_APP_BASE_URL=http://localhost:8080 ```.
   
   -  Нужно указать корневой url вашего backend, к нему frontend будет добавлять все пути согласно спецификации 
   -  Для  ```VUE_APP_BASE_URL=http://localhost:8080 ``` при выполнении логина frontend вызовет  ```http://localhost:8080/login ```

   5.2 Запустите FRONT снова: ```npm run serve```.
   
   5.3 Изменённый  ```url``` сохранится для следующих запусков.
 
 6. По умолчанию FRONT запускается на порту 8080 и доступен по url в браузере  ```http://localhost:8080```.
    - Если порт 8080 занят, FRONT займёт следующий доступный ( ```8081```). После выполнения  ```npm run serve``` в терминале вы увидите, на каком порту он запустился.
 
 #### Использование проекта
 1. Перейдите на страницу авторизации (по умочанию:```http://localhost:8081/login```).
 2. Введите логин и пароль пользователя, который может пройти авторизацию.
 3. Для загрузки файла в облачное хранилище необходимо нажать кнопку "```добавить```" и выбрать нужный файл.
 4. После загрузки файла появится возможность с помощью иконок:
 
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/58667adb-f21d-43f0-903d-269127412230" width="20" height="20" /> - ```изменить имя файла```.
  
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/dd840968-64ba-4cec-80e7-069bbc7c9226" width="20" height="20" /> - ```скачать файл```.
  
    <img src="https://github.com/Sanalex97/BackendCloudService/assets/72032908/0b077121-eb54-443d-9c33-3556cd68a0a9" width="20" height="20" /> - ```удалить файл```.
  


  




<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Add Changelog
- [x] Add back to top links
- [ ] Add Additional Templates w/ Examples
- [ ] Add "components" document to easily copy & paste sections of the readme
- [ ] Multi-language Support
    - [ ] Chinese
    - [ ] Spanish

See the [open issues](https://github.com/othneildrew/Best-README-Template/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Your Name - [@your_twitter](https://twitter.com/your_username) - email@example.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 


