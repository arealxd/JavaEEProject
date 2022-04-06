package kz.javaee.webproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.javaee.db.DBManager;
import kz.javaee.db.Users;

import java.io.IOException;

@WebServlet(name = "toregister", value = "/toregister")
public class ToRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");

        String redirect = "/register?passworderror";

        if (password.equals(rePassword)) {
            redirect = "/register?emailerror";

            Users user = DBManager.getUser(email);
            if (user == null) {

                Users newUser = new Users(null, email, password, fullName);

                if (DBManager.addUser(newUser)) {
                    redirect = "/register?success";
                }
            }
        }

        response.sendRedirect(redirect);

    }
}
