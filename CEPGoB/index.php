<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>.::CEPGoB.:: - Busca de dados de CEP on-line!</title>
    </head>
    <body>
        <?php
        header('Content-Type: text/html; charset=UTF-8');
        include('correios.class.php');
        if (isset($_GET['cep'])) {
            die(json_encode(Correios::cep($_GET['cep'])));
        } elseif (isset($_GET['codigo_rastreio'])) {
            die(json_encode(Correios::rastreio($_GET['codigo_rastreio'])));
        } else {
            die('informe parametro GET cep ou codigo_rastreio');
        }
        ?>
    </body>
</html>
