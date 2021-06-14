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

    <title>GIO Commerce</title>

    <!--  CSS -->
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

      <div class="row">
        <?php 
          require 'vendor/autoload.php';
          $caja = new gio\Caja;
          $detalleCaja = $caja->mostrarCajas();

          $cantidad = count($detalleCaja);

          if($cantidad > 0) {
            for($i = 0; $i < $cantidad; $i++) {
              $item = $detalleCaja[$i];
        ?>

       <div class="col-md-3">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="text-center">
                  <?php
                    print $item['deskribapena'];
                  ?>
                </h4>
              </div>
              <div class="panel-body">
                <?php 
                  $foto = 'upload/'.$item['argazkia'];
                  if(file_exists($foto)){
                  ?>
                  <img src="<?php print $foto; ?>"  class="img-responsive"/> 
                <?php } else {?>
                  <img src="assets/imagenes/not-found-image.jpg"  class="img-responsive"/> 
                <?php } ?>

              </div>

              <div class="panel-footer">
                  <a href="cesta.php?id=<?php print $item['motaId'].$item['lehengaiaId'] ; ?>" class="btn btn-success btn-block"> <!-- Revisar el ID que devuelve, se convina ID + LEHENGAIAID-->
                    <span class="glyphicon glyphicon-plus"></span> 
                    Añadir
                  </a>
              </div>

            </div>
       </div>

        <?php 
          }
        }else{?>
            
        <?php }?>

      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

  </body>
</html>
