package server.servlet.account.item;

import core.model.Item;
import core.model.User;
import server.manager.ItemManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "account/item/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ItemAddServlet extends HttpServlet {
    private final Random rand = new Random();
    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/account/item/add.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("cat_id"));
        Part pictureUrl = req.getPart("pic_url");
        String file = null;
        if (pictureUrl.getSize() != 0) {
            long nanoTime = System.nanoTime();
            file = nanoTime + "_" + rand.nextInt();
        }
        itemManager.addItem(Item.builder()
                .title(title)
                .price(price)
                .categoryId(categoryId)
                .picUrl(file)
                .userId(user.getId())
                .build());
        req.setAttribute("itemSuccessfullyAdded", "item successfully added");
    }
}
