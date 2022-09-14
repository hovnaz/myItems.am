package server.servlet;


import java.sql.Connection;
import core.db.DBConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class UserServlet extends HttpServlet {
//    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
        System.out.println(connection);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
