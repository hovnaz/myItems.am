package server.servlet.account.item;

import server.manager.ItemManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/account/item/remove")
public class ItemRemoveServlet extends HttpServlet {
    ItemManager itemManager = new ItemManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        itemManager.removeItemById(itemId);
        resp.sendRedirect("account/item/list");
    }
}
