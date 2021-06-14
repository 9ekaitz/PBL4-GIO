<?php 

    function agregarCaja($resultado, $id, $cantidad)
    {
        $_SESSION['cesta'][$id] = array(
            'id' => $resultado['motaId'],
            'tipo' => $resultado['lehengaiaId'],
            'descripcion' => $resultado['deskribapena'],
            'precio' => $resultado['prezioa'],
            'foto' => $resultado['argazkia'],
            'cantidad' => $cantidad
        );    
    }


    function actualizarCaja($id, $cantidad = FALSE)
    {
        if($cantidad)
        {
            $_SESSION['cesta'][$id]['cantidad']  = $cantidad;
        }
        else {
            $_SESSION['cesta'][$id]['cantidad']+=1;
        }
      
    }


    function calcularTotal()
    {
      
        $total = 0;

        if(isset($_SESSION['cesta']))
        {
            foreach($_SESSION['cesta'] as $incide => $value)
            {
                $total += $value['precio'] * $value['cantidad'];
            }
        }
        return $total;
    }


    function  cantidadCajas()
    {
              
        $cantidad = 0;

        if(isset($_SESSION['cesta']))
        {
            foreach($_SESSION['cesta'] as $incide => $value)
            {
               $cantidad++;
            }
        }
        return $cantidad;
    }
