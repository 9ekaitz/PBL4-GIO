<?php 

  session_start();
  require 'funciones.php';

  if(isset($_GET['id']))
  {
    $id = $_GET['id'];
    require 'vendor/autoload.php';
    $caja = new gio\Caja;
    $resultado = $caja->mostrarCajaPorIdTipo($id);

    if(!$resultado) header('Location: index.php');

    if(isset($_SESSION['cesta'])){ 
    
      if(array_key_exists($id,$_SESSION['cesta'])) {
          actualizarCaja($id);
      } else agregarCaja($resultado,$id,1);
    } else agregarCaja($resultado,$id,1);
  }

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

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad </th>
                <th>Total</th>
                <th></th>
            </tr>
        </thead>
        <tbody> 
            <?php 
                if(isset($_SESSION['cesta']) && !empty($_SESSION['cesta'])) {
                    foreach($_SESSION['cesta'] as $incide => $value) {
                        $total = $value['precio'] * $value['cantidad'];
            ?>
                <tr>
                  <form action="actualizar_cesta.php" method="post">
                      <td></td>
                      <td><?php print $value['descripcion']?></td>
                      <td><?php print $value['precio']?></td>
                      <td>
                        <input type="hidden" name="id"  value="<?php print $value['id'].$value['tipo']?>"/>
                        <input type="text" name="cantidad" class="form-control u-size-100" value=" <?php print $value['cantidad']?>"/>
                      </td>
                      <td><?php print $total ?> €</td>
                      <td>
                        <button type="submit" class="btn btn-success btn-xs"><span 	class="glyphicon glyphicon-refresh"></span></button>
                        <a href="eliminar_cesta.php?id=<?php print $value['id'].$value['tipo'] ?>" class="btn btn-danger btn-xs"><span 	class="glyphicon glyphicon-trash"></span></a>
                      </td>
                  </form>

                </tr>


            <?php
                    }
                } else {
            ?>
                <tr>
                    <td colspan="6"> No hay productos</td>
                </tr>

            <?php
                }
            ?>
        </tbody>
        <tfoot>
                <tr>
                  <td colspan="4" class="text-right"> Total</td>
                  <td> <?php print calcularTotal();?> €</td>
                  <td></td>
                </tr>
        </tfoot>
    </table>

    <hr>
    <?php 
      if(isset($_SESSION['cesta']) && !empty($_SESSION['cesta'])) {
    ?>

    <div class="row">
        <div class="pull-left">
          <a href="index.php" class="btn btn-info">Seguir comprando</a>
        </div>
        <div class="pull-right">
          <a href="tramitar_cesta.php" class="btn btn-success">Tramitar pedido</a>
        </div>
    </div>

    <?php } ?>


    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

  </body>
</html>
