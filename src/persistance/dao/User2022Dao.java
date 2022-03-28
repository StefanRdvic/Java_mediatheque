package persistance.dao;

import mediatek2022.Utilisateur;
import persistance.Dao;
import persistance.GestionBD;
import persistance.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User2022Dao implements Dao<Utilisateur> {

    @Override
    public Utilisateur get(Object... args) {
        String login = (String) args[0];
        String password = (String) args[1];

        String query = "select * from utilisateur where login = ? and password = ?";

        User user = null;
        Connection cn = GestionBD.getInstance().getConnection();
        try{
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();

            if(!res.next()) return null;

            user = new User(
                res.getString("login"),
                res.getBoolean("isBibliothecaire"),
                res.getInt("idUtilisateur")
            );

            ps.close();
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<Utilisateur> getAll() {
        // hors scope projet
        return null;
    }

    @Override
    public void create(Object... args) {
        // hors scope projet
    }

    @Override
    public void update(Object... args) {
        // hors scope projet
    }

    @Override
    public void delete(int id) {
        // hors scope projet
    }
}
