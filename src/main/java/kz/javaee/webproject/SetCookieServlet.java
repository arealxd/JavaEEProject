package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "setcookie", value = "/setcookie")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieValue = request.getParameter("cookie_value");

        Cookie cookie = new Cookie("my_cookie_name", cookieValue);
        cookie.setMaxAge(3600 * 24 * 365);
        response.addCookie(cookie);

        response.sendRedirect("/cookies");
    }
}