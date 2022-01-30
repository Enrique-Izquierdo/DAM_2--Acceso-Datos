<?php 

// Preparem els valors obtinguts mitjançant ajax per a guardar-los en la base de dades
// restcountries (MySql), allotjada en http://localhost:81/AE7_T4_REST/
if(isset($_POST["nombreComun"])){

    $nombreComun = $_POST["nombreComun"];
    $nombreOficial = $_POST["nombreOficial"];
    $capital = $_POST["capital"];
    $poblacion = $_POST["poblacion"];
    $region = $_POST["region"];
    $subregion = $_POST["subregion"];
    $superficie = $_POST["superficie"];
    $bandera = $_POST["imagenBandera"]; 
    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $dbname = "restcountries";

    $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);

    if (!$conexion) {
        echo "Error en la conexion a MySQL: " . mysqli_connect_error(); 
        exit();
    }
    $sql = "INSERT INTO paisos (nomComu, nomOficial, capital, poblacio, regio, subregio, superficie, bandera)
                 VALUES ('".addslashes($nombreComun)."', '".addslashes($nombreOficial)."', '".addslashes($capital)."',
                  '".addslashes($poblacion)."', '".addslashes($region)."',  '".addslashes($subregion)."', 
                  '".addslashes($superficie)."', '".addslashes($bandera)."')";
    if (mysqli_query($conexion, $sql)) {
        echo "Registro insertado correctamente.";
    } else {
        echo "Error " . mysqli_error($conexion);
    }

} else {
    echo "error";
}

?>
