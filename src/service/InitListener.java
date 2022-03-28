package service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Lancement du projet MEDIATEK");
        try {
            Class.forName("persistance.MediathequeData");
            Class.forName("org.mariadb.jdbc.Driver");
            Class.forName("persistance.GestionBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Les librairies ont bien été chargées");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) { }

}
