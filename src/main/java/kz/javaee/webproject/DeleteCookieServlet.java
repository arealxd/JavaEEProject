package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "deletecookie", value = "/deletecookie")
public class DeleteCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie c : cookies){
                if (c.getName().equals("my_cookie_name")){
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
        }

        response.sendRedirect("/cookies");

    }
}
