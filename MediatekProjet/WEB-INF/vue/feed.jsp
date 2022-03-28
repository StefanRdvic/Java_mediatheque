<%@ page import="mediatek2022.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>

<%!

    String getType(int type){
        String strType;
        switch(type){
            case 0: strType = "Livre"; break;
            case 1: strType = "Cd"; break;
            case 2: strType = "Dvd"; break;
            default: throw new RuntimeException("Type nom pris en charge dans cette jsp");
        }
        return strType;
    }

    String getDocuments(int type){
        List<Document> documents = Mediatheque.getInstance().tousLesDocumentsDisponibles();
        String strType = getType(type);

        StringBuilder sb = new StringBuilder();

        for(Document document : documents){
            String[] split = document.toString().split(";");
            if(document.disponible() && Integer.parseInt(split[0]) == type){

                sb.append("<tr>\n").append("<th>").append(strType).append("</th>\n");    // type du document en header

                // commence a 1 pour ne pas avoir l'attribut 0, 1, 2 (le type, l'emprunteur, idDoc)
                for(int i = 3; i < split.length; i++)
                    sb.append("<td>").append(split[i]).append("</td>\n");   // ajout des attributs dans la table

                // ajout du bouton pour emprunter
                sb.append("<td>\n")
                        .append("<form method=\"post\" action=\"./emprunt\">\n")
                        .append("<button class=\"button is-primary\" name=\"id\" value=\"")
                        .append(split[2]) // idDoc en value de post
                        .append("\">Emprunter</button>\n")
                        .append("</form>\n")
                        .append("</td>\n")
                        .append("</tr>");
            }
        }
        return sb.toString();
    }

    String getUsersDocuments(Utilisateur user){
        List<Document> documents = Mediatheque.getInstance().tousLesDocumentsDisponibles();
        StringBuilder sb = new StringBuilder();

        for(Document document : documents){
            String[] split = document.toString().split(";");
            if(split[1].equals(user.name())){ //on verifie si le document est emprunter par l'utilisateur
                sb.append("<tr>\n");

                String type = getType(Integer.parseInt(split[0]));
                sb.append("<th>").append(type).append("</th>\n");   // le type de document

                sb.append("<td>").append(split[3]).append("</td>\n"); //le titre du document

                sb.append("<td>\n")
                        .append("<form method=\"post\" action=\"./retour\">\n")
                        .append("<button class=\"button is-primary\" name=\"id\" value=\"")
                        .append(split[2]) // idDoc en value de post
                        .append("\">Retourner</button>\n")
                        .append("</form>\n")
                        .append("</td>\n")
                        .append("</tr>");
            }
        }

        return sb.toString();
    }
%>

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
            <div class="column">

                <div class="columns is-centered">
                    <div class="column">
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
                </div>

                <div class="columns is-centered">
                    <div class="column">
                        <div class="box">

                            <div class="content">
                                <h2>Vos Document</h2>
                            </div>
                            <div class="columns is-centered">
                                <table class="table is-fullwidth is-hoverable is-striped">
                                    <thead>
                                    <tr>
                                        <th>Type</th>
                                        <th>Titre</th>
                                        <th>Retourner</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <%=
                                            getUsersDocuments((Utilisateur) session.getAttribute("user"))
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            
            <div class="column is-four-fifths-desktop">
            
                <div class="columns is-centered">
                    <div class="column">
                        <div class="box">

                            <div class="content">
                                <h2>Nos livres</h2>
                            </div>
                            <div class="columns is-centered">
                                <table class="table is-fullwidth is-hoverable is-striped">
                                    <thead>
                                    <tr>
                                        <th>Type</th>
                                        <th>Titre</th>
                                        <th>Auteur</th>
                                        <th>Genre</th>
                                        <th>Disponible</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <%=
                                            getDocuments(0)
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="columns is-centered">
                    <div class="column">
                        <div class="box">

                            <div class="content">
                                <h2>Nos Cd's</h2>
                            </div>
                            <div class="columns is-centered">
                                <table class="table is-fullwidth is-hoverable is-striped">
                                    <thead>
                                    <tr>
                                        <th>Type</th>
                                        <th>Titre</th>
                                        <th>Artiste</th>
                                        <th>Style</th>
                                        <th>Disponible</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <%=
                                            getDocuments(1)
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="columns is-centered">
                    <div class="column">
                        <div class="box">

                            <div class="content">
                                <h2>Nos Dvd's</h2>
                            </div>
                            <div class="columns is-centered">
                                <table class="table is-fullwidth is-hoverable is-striped">
                                    <thead>
                                    <tr>
                                        <th>Type</th>
                                        <th>Titre</th>
                                        <th>Realisateur</th>
                                        <th>Disponible</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                        <%=
                                            getDocuments(2)
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>

</section>

</body>
</html>