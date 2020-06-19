package servlet;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import service.UserService;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.getService().getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/allUsers.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getPathInfo()) {
            case "/add":
                addUser(req);
                break;
            case "/update":
                updateUser(req);
                break;
            case "/delete":
                deleteUser(req);
                break;
        }
        resp.sendRedirect("/users");
    }

    private void addUser(HttpServletRequest req) throws IOException {
        User user = new User(
                req.getParameter("name"),
                req.getParameter("surname"),
                Integer.parseInt(req.getParameter("age"))
        );
        UserService.getService().addUser(user);
    }

    private void updateUser(HttpServletRequest req) {


        User user = new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("surname"),
                Integer.parseInt(req.getParameter("age"))
        );
        UserService.getService().updateUser(user);
    }

    private void deleteUser(HttpServletRequest req) {
        UserService.getService()
                .deleteUserById(Integer.parseInt(req.getParameter("id")));
    }
}
