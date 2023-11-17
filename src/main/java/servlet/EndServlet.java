package servlet;

import entity.Question;
import entity.User;
import service.LogicService;
import service.LogicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/end")
public class EndServlet extends HttpServlet {
    private LogicService logicService = LogicServiceImpl.getLogicService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String answer = request.getParameter("answer");
        Question question = (Question) session.getAttribute("question");
        logicService.end(user, question);
        session.setAttribute("user", user);
        session.setAttribute("question", logicService.getQuestion(Integer.parseInt(answer)));
        request.getRequestDispatcher("/WEB-INF/end.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(false).setAttribute("question", null);
        response.sendRedirect("/start");
    }
}
