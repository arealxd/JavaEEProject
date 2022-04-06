package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "cookies", value = "/cookies")
public class CookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();

        String myCookieValue = "No Cookie Data";

        if (cookies != null) {
            for (Cookie c : cookies){
                if (c.getName().equals("my_cookie_name")){
                    myCookieValue = c.getValue();
                    break;
                }
            }
        }

        request.setAttribute("cookieValue", myCookieValue);
        request.getRequestDispatcher("/cookies.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
