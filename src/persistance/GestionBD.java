package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionBD {


    /* Login pour la BD hebergé sur un serveur par Julien Comoli (Dut APP) avec mariaDB et PHPmyAdmin*/
    private static final String URL = "jdbc:mariadb://enthousiasme.io:22221/stefan";
    private static final String USER = "stefan";
    private static final String PW = "73xs4hADZ";

    private static GestionBD instance;
    private static Connection conn;

    /**
     * Singleton permettant d'avoir une unique connection a la base de donnée
     * permet de gagner du temps.
     * Bonus: les singleton avec bloc static sont thread safe :)
     */
    static {
        try {
            instance = new GestionBD();
            conn = DriverManager.getConnection(URL, USER, PW);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public GestionBD(){ }

    public static GestionBD getInstance() {
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }

}
