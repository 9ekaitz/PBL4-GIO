<?php

namespace gio;

class Caja {
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

    public function mostrarCajas()
    {
        $sql = "SELECT * FROM kutxa_mota";

        $resultado = $this->cn->prepare($sql);

       if($resultado->execute()) return $resultado->fetchAll();

        return false;
    }

    public function mostrarCajaPorId($id)
    {
        $sql = "SELECT * FROM `kutxa_mota` WHERE `motaId` =:id";
        $_array = array(
            ":id" => $id
        );

        $resultado = $this->cn->prepare($sql);
        if($resultado->execute($_array)) return $resultado->fetch();

        return false;
    }


    public function mostrarCajaPorIdTipo($pk)
    {
        $tipo  = substr($pk,-2);
        $id = str_replace($tipo,'', $pk);

        $sql = "SELECT * FROM `kutxa_mota` WHERE `motaId` =:id AND `lehengaiaId` =:tipo";
       
        $_array = array(
            ":id" => $id,
            ":tipo" => $tipo
        );

        $resultado = $this->cn->prepare($sql);

        if($resultado->execute($_array)) return $resultado->fetch();

        return false;
    }


}