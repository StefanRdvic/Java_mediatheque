package persistance.dao;

import mediatek2022.Document;
import persistance.Dao;
import persistance.GestionBD;
import persistance.model.Doc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Doc2022Dao implements Dao<Document> {

    @Override
    public Document get(Object... args) {
        int id = (int) args[0];

        String query = "select * from document where idDoc = ?";

        Document doc = null;
        Connection cn = GestionBD.getInstance().getConnection();
        try{
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();

            if(!res.next()) return null;

            doc = fabriqueUtil(res);

            ps.close();
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    public List<Document> getAll() {
        String query = "select * from document";
        List<Document> documents = new LinkedList<>();

        Connection cn = GestionBD.getInstance().getConnection();
        try{
            ResultSet res = cn.createStatement().executeQuery(query);

            while(res.next()){
                documents.add(fabriqueUtil(res));
            }

            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documents;
    }

    @Override
    public void create(Object... args) {
        int type = (int) args[0];

        String queryLivre = "insert into document (typeDoc, titre, auteur, genre) values (?, ?, ?, ?)";
        String queryCd = "insert into document (typeDoc, titre, artiste, style) values (?, ?, ?, ?)";
        String queryDvd = "insert into document (typeDoc, titre, realisateur) values (?, ?, ?)";

        Connection cn = GestionBD.getInstance().getConnection();
        try {
            PreparedStatement ps;
            switch (type){
                case 0:
                    ps = cn.prepareStatement(queryLivre);
                    ps.setInt(1, type);
                    ps.setString(2, (String) args[1]);
                    ps.setString(3, (String) args[2]);
                    ps.setString(4, (String) args[3]); break;
                case 1:
                    ps = cn.prepareStatement(queryCd);
                    ps.setInt(1, type);
                    ps.setString(2, (String) args[1]);
                    ps.setString(3, (String) args[2]);
                    ps.setString(4, (String) args[3]); break;
                case 2:
                    ps = cn.prepareStatement(queryDvd);
                    ps.setInt(1, type);
                    ps.setString(2, (String) args[1]);
                    ps.setString(3, (String) args[2]); break;
                default: throw new RuntimeException("create : type non pris en charge");
            }

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object... args) {
        String emprunteur = (String) args[0];
        int id = (int) args[1];

        String query = "update document set emprunteur = ? where idDoc = ?";
        Connection cn = GestionBD.getInstance().getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, emprunteur);
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        //hors scope projet
    }

    private Document fabriqueUtil(ResultSet res) throws SQLException {
        int type = res.getInt("typeDoc");
        int idDoc = res.getInt("idDoc");
        String titre = res.getString("titre");

        String emprunteur = res.getString("emprunteur");
        if(emprunteur == null) emprunteur = "personne";

        switch (type){
            case 0: return new Doc(type, emprunteur, idDoc, titre,
                    res.getString("auteur"),
                    res.getString("genre")
            );

            case 1: return new Doc(type, emprunteur, idDoc, titre,
                    res.getString("artiste"),
                    res.getString("style")
            );

            case 2: return new Doc(type, emprunteur, idDoc, titre,
                    res.getString("realisateur")
            );
            default: throw new RuntimeException("Type non pris en charge");
        }
    }

}
