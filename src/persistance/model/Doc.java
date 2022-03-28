package persistance.model;

import mediatek2022.*;
import persistance.Dao;
import persistance.dao.Doc2022Dao;

public class Doc implements Document {

    private final int type;
    private final Dao<Document> DOC_DAO;

    private Object[] args;

    /**
     * Object representant la table document en base de donné
     * ordre des arguments à respecter:
     * - args[0] -> emprunteur
     * - args[1] -> idDoc
     * - args[...] -> attribut restant
     * @param type le type de document
     * @param args les arguments du documents
     */
    public Doc(int type, Object... args) {
        this.type = type;
        this.args = args;

        this.DOC_DAO = new Doc2022Dao();
    }

    @Override
    public boolean disponible() {
        return ((String) this.args[0]).equals("personne");
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        synchronized (this){
            if(!disponible()) throw new Exception("Document déjà emprunté");

            this.args[0] = u.name();
            this.DOC_DAO.update(this.args[0], this.args[1]);
        }
    }

    @Override
    public void retour() {
        synchronized (this){
            if(!disponible()){
                this.args[0] = "personne";
                this.DOC_DAO.update(null, this.args[1]); //emprunteur null en BD
            }
        }
    }

    /* squelette: type;emprunteur;idDoc;args[2]; ... ; args[n] */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(type);

        for(Object arg : this.args){
            sb.append(";").append(arg);
        }

        return sb.toString();
    }
}
