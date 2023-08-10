<%@ page import="entity.Question" %>
<%@ page import="java.util.Map" %>
<%@ page import="entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .quiz-container {
            text-align: left;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 21px;
        }

        .ending {
            display: block;
            margin: 5px 0;
            padding: 10px 20px;
            background-color: transparent;
            border: none;
            color: black;
            font-style: italic;
            font-size: 18px;
            cursor: pointer;
        }

        .endings {
            text-align: left;
        }

        .questions {
            text-indent: 20px;
        }
    </style>
</head>
<body>
<div class="quiz-container">
    <div class="questions">
        <%for (String question : ((Question) session.getAttribute("question")).getQuestions()) { %>
        <% if (question != null) { %>
        <p><%= question%>
        </p>
        <% } %>
        <% } %>
    </div>

    <div class="endings">
        <% Map<Integer, Integer> endings = ((User) session.getAttribute("user")).getEndings(); %>
        <% if (endings != null) { %>
        <%Integer number = 0; %>
        <% for (Integer key : endings.keySet()) { %>
        <% number += endings.get(key); %>
        <% } %>
        <span class="ending">Вы проши книгу-квест: <%=number%> раз</span>
        <span class="ending">Открыли концовок <%=endings.keySet().size()%> из 23</span>
        <% } %>
    </div>

    <div style="text-align: center; margin-top: 20px;">
        <button onclick="restart()">Пройти заново</button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function restart() {
        $.ajax({
            url: '/end',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            success: function (response) {
                window.location.href = "/start";
            }
        });
    }
</script>
</body>
</html>