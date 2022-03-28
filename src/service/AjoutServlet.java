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

@WebServlet(urlPatterns = "/ajout")
public class AjoutServlet extends HttpServlet{


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if(user != null){
            if(!user.isBibliothecaire()){
                resp.sendRedirect("./feed");
            }else{
                int type = Integer.parseInt(req.getParameter("type"));
                String titre = req.getParameter("titre");

                switch (type){
                    case 0:
                        Mediatheque.getInstance().ajoutDocument(type, titre, req.getParameter("auteur"),
                                req.getParameter("genre")); break;
                    case 1:
                        Mediatheque.getInstance().ajoutDocument(type, titre, req.getParameter("artiste"),
                                req.getParameter("style")); break;
                    case 2: Mediatheque.getInstance().ajoutDocument(type, titre,
                            req.getParameter("realisateur")); break;
                    default: throw new RuntimeException("Type non pris en charge");
                }
                resp.sendRedirect("./biblio");
            }
        }else {
            resp.sendRedirect("./login");
        }
    }
}
