package servlet;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class FilterServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    private FilterServlet filterServlet;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        filterServlet = new FilterServlet();
    }

    @Test
    public void testAuthenticatedRequest() throws Exception{
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());

        filterServlet.doFilter(request, response, filterChain);

        verify(requestDispatcher, never()).forward(request, response);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testUnauthenticatedRequestWhenUserNull() throws Exception{
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getRequestDispatcher("auth")).thenReturn(requestDispatcher);

        filterServlet.doFilter(request, response, filterChain);

        verify(requestDispatcher).forward(request, response);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    public void testUnauthenticatedRequestWhenSessionNull() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        when(request.getRequestDispatcher("auth")).thenReturn(requestDispatcher);

        filterServlet.doFilter(request, response, filterChain);

        verify(requestDispatcher).forward(request, response);
        verify(filterChain, never()).doFilter(request, response);
    }


}
