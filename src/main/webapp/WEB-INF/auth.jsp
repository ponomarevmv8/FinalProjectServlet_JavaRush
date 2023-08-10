<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }

        .login-container {
            text-align: center;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .input-container {
            position: relative;
            margin-bottom: 20px;
        }

        input[type="text"] {
            width: 300px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="text"]:focus::placeholder {
            color: transparent;
        }

        .play-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            width: 300px;
        }

        .play-button:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        .play-button:active {
            transform: scale(0.95);
        }
        .welcome-text {
            font-size: 24px;
            margin: 10px;
            padding-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('username').addEventListener('focus', function() {
                this.placeholder = '';
            });

            document.getElementById('username').addEventListener('blur', function() {
                if (this.value === '') {
                    this.placeholder = 'Введите ваше имя';
                }
            });
        });
    </script>
</head>
<body>
<div class="login-container">
    <h1 class="welcome-text">Добро пожаловать!</h1>
    <div class="welcome-text">
        <p>Спасибо что зашли поиграть в книгу-квест</p>
        <p>Вас ждет недолгое но увлекательное путешествие в мир Гарри Поттера, где вы сможете повлиять на историю этого мира</p>
        <p>Книга имеет множество концовок от каноничных до абсурдных</p>
        <p>Введите ваше имя и начните увлекательное путешесвие в мир Гарри Поттера</p>
    </div>
    <form action="auth" method="post">
        <div class="input-container">
            <input type="text" id="username" name="username" placeholder="Введите ваше имя">
        </div>
        <button type="submit" class="play-button">Играть</button>
    </form>
    <div class="input-container">
        <p>P.S. Спасибо автору за его <a href="https://quest-book.ru/online/view/game5296"> текст</a></p>
    </div>
</div>
</body>
</html>

