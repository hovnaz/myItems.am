package server.servlet.auth;

import core.model.User;
import server.manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/register")
public class RegisterServlet extends HttpServlet {
    UserManager userManager = new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/auth/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userManager.getUserByEmail(email);

        if (user != null) {
            req.setAttribute("message", "user with this exact email already exists");
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        } else {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");

            userManager.addUser(
                    User.builder()
                            .name(name)
                            .surname(surname)
                            .email(email)
                            .password(password)
                            .build());

            resp.sendRedirect("/auth/login");
        }
    }
}
