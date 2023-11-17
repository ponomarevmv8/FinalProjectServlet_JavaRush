package servlet;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import service.AuthService;
import service.AuthServiceImpl;

import static org.mockito.Mockito.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
public class AuthServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HttpSession session;

    @Mock
    private AuthService authService;

    private AuthServlet authServlet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authServlet = new AuthServlet();
    }




    @Test
    public void testDoGet() throws Exception{
        when(request.getRequestDispatcher("/WEB-INF/auth.jsp")).thenReturn(dispatcher);
        authServlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        try(MockedStatic<AuthServiceImpl> getAuthService = mockStatic(AuthServiceImpl.class)) {
            when(request.getParameter("username")).thenReturn("Max");
            getAuthService.when(AuthServiceImpl::getAuthService).thenReturn(authService);
            User user = new User();
            when(authService.login("Max")).thenReturn(user);
            when(request.getSession()).thenReturn(session);

            authServlet.doPost(request, response);

            verify(authService).login("Max");
            verify(session).setAttribute("user", user);
            verify(response).sendRedirect("/start");
        }
    }
}
