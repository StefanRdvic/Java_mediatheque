package service;

import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/feed")
public class FeedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if(user != null){
            if(user.isBibliothecaire()){
                resp.sendRedirect("./biblio");
            }else {
                req.getRequestDispatcher("WEB-INF/vue/feed.jsp").forward(req, resp);
            }
        }else{
            resp.sendRedirect("./login");
        }
    }

}
