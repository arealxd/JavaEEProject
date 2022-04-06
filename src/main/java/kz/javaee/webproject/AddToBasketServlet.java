package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addtobasket", value = "/addtobasket")
public class AddToBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basketValue = request.getParameter("basket_value");

        ArrayList<String> basket = (ArrayList<String>) request.getSession().getAttribute("basket");

        if (basket == null){
            basket = new ArrayList<>();
        }

        basket.add(basketValue);

        request.getSession().setAttribute("basket", basket);
        response.sendRedirect("/sessions");

    }
}
