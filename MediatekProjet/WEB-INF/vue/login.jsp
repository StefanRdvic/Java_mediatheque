<%@ page contentType="text/html; charset=UTF-8" %>
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
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-half">
                    <form method="post" action="./login" class="box">
                        <div class="field">
                            <label class="label">Login</label>
                            <div class="control">
                                <input class="input" placeholder="e.g Stefan" name="login" required>
                            </div>
                        </div>

                        <div class="field">
                            <label class="label">Mot de passe</label>
                            <div class="control">
                                <input class="input" type="password" placeholder="********" name="password" required>
                            </div>
                        </div>

                        <button class="button is-primary">Connection</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>