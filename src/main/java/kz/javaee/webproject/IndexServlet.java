package kz.javaee.webproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.javaee.db.DBManager;
import kz.javaee.db.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "index", value = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Item> items = DBManager.getItems();

        PrintWriter out = response.getWriter();

        out.println("<h1 style = 'color: orange'>ITEMS LIST</h1>");

        for (int i = 0; i < items.size(); i++) {
            out.println("<h3>" + items.get(i).getId() + " " + items.get(i).getName() + " " + items.get(i).getAmount() + " " + items.get(i).getPrice() + " KZT</h3>");
        }

        out.println("<br><a href='index.jsp'>ADD NEW ITEM</a>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
