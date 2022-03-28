package service;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if(user != null){
            resp.sendRedirect(user.isBibliothecaire() ? "./biblio" : "./feed");
        } else {
            req.getRequestDispatcher("WEB-INF/vue/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user == null){
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            user = Mediatheque.getInstance().getUser(login, password);
            if(user == null){
                req.setAttribute("err", "incorrect credentials");
                req.getRequestDispatcher("WEB-INF/vue/login.jsp").forward(req, resp);
            }else {
                session.setAttribute("user", user);
                resp.sendRedirect(user.isBibliothecaire() ? "./biblio" : "./feed");
            }
        }else {
            resp.sendRedirect(user.isBibliothecaire() ? "./biblio" : "./feed");
        }
    }

}
