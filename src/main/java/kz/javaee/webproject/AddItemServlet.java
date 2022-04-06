package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.javaee.db.Countries;
import kz.javaee.db.DBManager;
import kz.javaee.db.Item;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addItem", value = "/addItem")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Countries> countries = DBManager.getCountries();
        request.setAttribute("contries", countries);
        request.getRequestDispatcher("/addItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        Long countryId = Long.parseLong(request.getParameter("manufacturer_id"));

        Countries cnt = DBManager.getCountry(countryId);
        if (cnt != null) {
            Item item = new Item(null, name, price, amount, cnt);
            DBManager.addItem(item);
            response.sendRedirect("/addItem?success");
        } else {
            response.sendRedirect("/addItem?error");
        }
    }
}
