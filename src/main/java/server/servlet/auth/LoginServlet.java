package server.servlet.auth;

import core.model.User;
import server.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/login")
public class LoginServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/auth/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        final String WarningMessage = "email or password is not current";
        User user = userManager.getUserByAndPassword(email, password);
        if (user == null){
            req.setAttribute("message", WarningMessage);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        else{
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/");
        }
    }
}
