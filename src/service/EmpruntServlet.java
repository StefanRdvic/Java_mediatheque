package service;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/emprunt")
public class EmpruntServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user != null && !user.isBibliothecaire()){
            String id = req.getParameter("id");
            Document doc = Mediatheque.getInstance().getDocument(Integer.parseInt(id));

            try {
                doc.emprunt(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect("./feed");
        }else {
            resp.sendRedirect("./login");
        }
    }
}
