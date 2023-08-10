<%@ page import="entity.Question" %>
<%@ page import="java.util.Map" %>
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
            width: 70%;
        }
        .answers {
            text-decoration: none;
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
        .answer {
            text-align: left;
        }
        .questions{
            text-indent: 20px;
        }
    </style>
</head>
<body>
<div class="quiz-container">
    <div class="questions">
    <%for (String question : ((Question) session.getAttribute("question")).getQuestions()) { %>
        <% if(question != null) { %>
            <p><%= question%></p>
            <% } %>
    <% } %>
    </div>

    <div class="answer">
        <ul>
        <% for (Map.Entry<Integer, String> entry : ((Question) session.getAttribute("question")).getAnswers().entrySet()) { %>
            <% if (entry.getKey() == 0) { %>
                <li><a href="/end?answer=<%=entry.getKey()%>" class="answers"> <%=entry.getValue()%> </a></li>
            <% } else { %>
            <li><a href="javascript:void(0);" onclick="restartWithParameter(<%= entry.getKey() %>)" class="answers"> <%=entry.getValue()%> </a></li>
            <% } %>
        <% } %>
        </ul>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function restartWithParameter(parameterValue) {
        $.ajax({
            url: '/start',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            data: {
                answer: parameterValue
            },
            success: function () {
                location.reload();
            }
        });
    }
</script>
</body>
</html>