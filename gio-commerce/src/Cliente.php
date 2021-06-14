<?php

namespace gio;

class Cliente {
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
        $sql = "INSERT INTO `bezeroa`(`cif`, `izena`, `telefonoa`) VALUES (:cif,:nombre,:telefono)";

        $resultado = $this->cn->prepare($sql);

        $_array = array(
            ":cif" => $_params['cif'],
            ":nombre" => $_params['nombre'],
            ":telefono" => $_params['telefono']
        );

        if($resultado->execute($_array)) return $this->cn->lastInsertId();

        return false;
    }



}    