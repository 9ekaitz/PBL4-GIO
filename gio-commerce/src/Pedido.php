<?php

namespace gio;

class Pedido {
    private $config;
    private $cn = null;


    public function __construct()
    {
        $this->config = parse_ini_file(__DIR__.'/../config.ini');

        $this->cn = new \PDO($this->config['dns'], $this->config['usuario'], $this->config['clave'], 
        array(
                \PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8' 
            )
        );
    }

    public function registrar($_params) {
        $sql = "INSERT INTO `eskaera_onartzeko`(`sortze_data`, `helbidea`, `bezeroa`) VALUES (:fecha,:direccion,:clienteId)";

        $resultado = $this->cn->prepare($sql);

        $_array = array(
            ":fecha" => $_params['fecha'],
            ":direccion" => $_params['direccion'],
            ":clienteId" => $_params['clienteId']
        );

        if($resultado->execute($_array)) return $this->cn->lastInsertId();

        return false;
    }


    
    public function registrarDetalle($_params) {
        $sql = "INSERT INTO `lerroak_onartzeko`(`motaId`, `lehengaiaId`, `eskaeraId`, `pezioa`, `kopurua`) VALUES (:tipoId,:materialId,:pedidoId, :precio, :cantidad)";

        $resultado = $this->cn->prepare($sql);

        $_array = array(
            ":tipoId" => $_params['tipoId'],
            ":materialId" => $_params['materialId'],
            ":pedidoId" => $_params['pedidoId'],
            ":precio" => $_params['precio'],
            ":cantidad" => $_params['cantidad']
        );

        if($resultado->execute($_array)) return true;

        return false;
    }


}    