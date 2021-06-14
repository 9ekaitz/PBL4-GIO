<?php

session_start();

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    require 'funciones.php';
    require 'vendor/autoload.php';

    if(isset($_SESSION['cesta']) && !empty($_SESSION['cesta']))
    {
        // CLIENTE
        $cliente = new gio\Cliente;

        $_params = array(
            'cif' => $_POST['cif'],
            'nombre' => $_POST['nombre'],
            'telefono' => $_POST['telefono']
        );

        $clienteId = $cliente->registrar($_params);


        // PEDIDO 

        $pedido = new gio\Pedido;

        $_params = array(
            'fecha' => date('Y-m-d'),
            'direccion' => $_POST['direccion'],
            'clienteId' =>  $clienteId
        );


        $pedidoId = $pedido->registrar($_params);

        // DETALLE

        foreach($_SESSION['cesta'] as $indice => $value) {
            $_params = array(
                "tipoId" => $value['id'],
                "materialId" => $value['tipo'],
                "pedidoId" => $pedidoId,
                "precio" => $value['precio'],
                "cantidad" => $value['cantidad']
            );

            $pedido->registrarDetalle($_params);
        }
        $_SESSION['cesta'] = array();
        header('Location: gracias.php');
    }

}