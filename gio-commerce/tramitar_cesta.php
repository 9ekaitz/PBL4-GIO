<?php 

session_start();
require 'funciones.php';
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>GIO Commcerce</title>

    <!-- CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/estilos.css">
  </head>

  <body>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.php">GIO Commerce </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav pull-right">
            <li>
              <a href="cesta.php" class="btn">Cesta<span class="badge"><?php print cantidadCajas() ?></span></a>
            </li> 
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container" id="main">
        <div class="main-form">
            <div class="row">
                <div class="col-md-12">
                    <fieldset>
                        <legend>Completar datos</legend>
                            <form action="finalizar_pedido.php" method="post">
                                <div class="form-group">
                                    <label for="">CIF</label>
                                    <input type="text" class="form-control" name="cif" placeholder="H123456789" maxlength="10" required/>
                                </div>
                                <div class="form-group">
                                    <label for="">Nombre</label>
                                    <input type="text" class="form-control" name="nombre" placeholder="Gio" required/>
                                </div>
                                <div class="form-group">
                                    <label for="">Dirección</label>
                                    <input type="text" class="form-control" name="direccion" placeholder="Calle MU, Nº 1" required/>
                                </div>
                                <div class="form-group">
                                    <label for="">Teléfono</label>
                                    <input type="tel" class="form-control" name="telefono" placeholder="601234567" pattern="[0-9]{9}" required/>
                                </div>
                                <button class="btn btn-primary btn-block">Tramitar</button> 
                            </form>                                                       
                    </fieldset>
                </div>
            </div>
        </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

  </body>
</html>
