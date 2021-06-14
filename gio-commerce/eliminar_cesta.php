<?php

session_start();

if(!isset($_GET['id'])) header('Location: cesta.php');

if(isset($_SESSION['cesta'])){
    $id = $_GET['id'];
    unset($_SESSION['cesta'][$id]);
    header('Location: cesta.php');
} else {
    header('Location: index.php');
}

     
    
