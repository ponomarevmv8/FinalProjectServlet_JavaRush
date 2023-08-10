package servlet;

import entity.Question;
import service.LogicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class LogicServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("question") == null) {
            Question question = LogicService.getLogicService().getQuestion(1);
            session.setAttribute("question", question);
        }
        request.getRequestDispatcher("/WEB-INF/start.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String answer = request.getParameter("answer");
        System.out.println("Получен ответ: " + answer);
        Question question = LogicService.getLogicService().getQuestion(Integer.parseInt(answer));
        request.getSession(false).setAttribute("question", question);
    }
}
