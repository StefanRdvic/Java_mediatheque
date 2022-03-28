package persistance.model;

import mediatek2022.*;

import java.util.Objects;

public class User implements Utilisateur {

    private final String name;
    private final boolean isBibliothecaire;
    private final Object[] data;

    /**
     * Representation d'un utilisateur de la table utilisateur en DB
     * @param name le nom de l'utilisateur
     * @param isBibliothecaire l'utilisateur est bibliothecaire
     * @param data les informations de l'utilisateur
     */
    public User(String name, boolean isBibliothecaire, Object... data) {
        this.name = name;
        this.data = data;
        this.isBibliothecaire = isBibliothecaire;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isBibliothecaire() {
        return this.isBibliothecaire;
    }

    @Override
    public Object[] data() {
        return this.data;
    }
}
