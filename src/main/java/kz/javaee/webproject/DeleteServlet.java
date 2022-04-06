package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.javaee.db.DBManager;
import kz.javaee.db.Item;

import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Item item = DBManager.getItem(id);

        if (item != null) {

            if (DBManager.deleteItem(item)) {
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/edit?id=" + id +"&error");
            }

        } else {
            response.sendRedirect("/");
        }
    }
}
