package persistance;

import java.util.List;
import mediatek2022.*;
import persistance.dao.Doc2022Dao;
import persistance.dao.User2022Dao;

/**
 * classe mono-instance  dont l'unique instance est connue de la médiatheque
 * via une auto-déclaration dans son bloc static
 * @author Jean-François Brette 01/01/2018
 */
public class MediathequeData implements PersistentMediatheque {

	/**
	 * DAO : data access object
	 * thread safe car les sgbd sont thread safe
	 */
	private static final Dao<Document> DOC_DAO;
	private static final Dao<Utilisateur> USER_DAO;

	static {
		DOC_DAO = new Doc2022Dao();
		USER_DAO = new User2022Dao();
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
	}

	/*	renvoie la liste de tous les documents disponibles de la médiathèque */
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		return DOC_DAO.getAll();
	}

	/**
	* va récupérer le User dans la BD et le renvoie
	* si pas trouvé, renvoie null
	* */
	@Override
	public Utilisateur getUser(String login, String password) {
		return USER_DAO.get(login, password);
	}

	/**
	 * va récupérer le document de numéro numDocument dans la BD
	 * et le renvoie
	 * si pas trouvé, renvoie null
	 */
	@Override
	public Document getDocument(int numDocument) {
		return DOC_DAO.get(numDocument);
	}

	/**
	 * Ajoute un document dans la DB
	 * args[0] -> le titre
	 * args[1] -> l'auteur
	 * etc... variable suivant le type de document
	 */
	@Override
	public void ajoutDocument(int type, Object... args) {
		// new Doc2022Dao().create(type, args); marche pas

		Object[] argsCopy = new Object[args.length + 1];

		argsCopy[0] = type;
		System.arraycopy(args, 0, argsCopy, 1, args.length);

		DOC_DAO.create(argsCopy);
	}

}
