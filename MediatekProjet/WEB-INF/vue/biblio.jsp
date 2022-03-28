<%@ page import="mediatek2022.Utilisateur" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mediatek2022 - login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
<section class="section">

    <div class="columns">
        <div class="column is-one-quarter">
            <div class="box">
                <div class="content">
                    <h2>Bienvenue
                        <code><%=((Utilisateur) session.getAttribute("user")).name()%></code>
                    </h2>
                    <form method="post" action="./logout">
                        <button class="button is-primary">Deconnection</button>
                    </form> 
                </div>
                 
            </div>
            
        </div>

        <div class="column">

            <div class="columns is-centered">
                <div class="column">
                    <div class="box">
                        <div class="content">
                            <h1>Ajouter un Livre</h1>
                        </div>
                        <form method="post" action="./ajout">
                            <div class="columns">

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Titre</label>
                                        <div class="control">
                                            <input class="input" placeholder="Titre du livre" name="titre" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Auteur</label>
                                        <div class="control">
                                            <input class="input" placeholder="Auteur" name="auteur" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Genre</label>
                                        <div class="control">
                                            <input class="input" placeholder="Genre" name="genre" required>
                                        </div>
                                    </div>
                                </div>
                            
                            </div>
                        
                            <button class="button is-primary" name="type" value="0">Ajouter</button>
                        </form>
                    </div>
    
                </div>
            </div>

            <div class="columns is-centered">
                <div class="column">
                    <div class="box">
                        <div class="content">
                            <h1>Ajouter un Cd</h1>
                        </div>
                        <form method="post" action="./ajout">
                            <div class="columns">

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Titre</label>
                                        <div class="control">
                                            <input class="input" placeholder="Titre du Cd" name="titre" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Artiste</label>
                                        <div class="control">
                                            <input class="input" placeholder="Artiste" name="artiste" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Style</label>
                                        <div class="control">
                                            <input class="input" placeholder="Style" name="style" required>
                                        </div>
                                    </div>
                                </div>
                            
                            </div>
                        
                            <button class="button is-primary" name="type" value="1">Ajouter</button>
                        </form>
                    </div>
    
                </div>
            </div>

            <div class="columns is-centered">
                <div class="column">
                    <div class="box">
                        <div class="content">
                            <h1>Ajouter un Dvd</h1>
                        </div>
                        <form method="post" action="./ajout">
                            <div class="columns">

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Titre</label>
                                        <div class="control">
                                            <input class="input" placeholder="Titre du Dvd" name="titre" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="column">
                                    <div class="field">
                                        <label class="label">Realisateur</label>
                                        <div class="control">
                                            <input class="input" placeholder="Realisateur" name="realisateur" required>
                                        </div>
                                    </div>
                                </div>
                
                            </div>
                        
                            <button class="button is-primary" name="type" value="2">Ajouter</button>
                        </form>
                    </div>
    
                </div>
            </div>

        </div>

    </div>

    
</section>

</body>
</html>