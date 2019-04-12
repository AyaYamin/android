<?php


$db = new mysqli("localhost", "root", "", "project_new");
if (!$db) die("database connection error");

$servername = "localhost";
$username = "root";
$password = "";


$conn = new mysqli($servername, $username, $password, "project_new");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}



$id=$_GET['id'];
$new=$_GET['new'];
//$sql="SELECT parn_pass FROM student WHERE part_id='$id'";
$sql="UPDATE student set  password='$new' WHERE id='$id'";
$result = mysqli_query($conn,$sql);
//$count = mysqli_num_rows($result);


if($result){
    echo "true";
}

mysqli_close($conn);
?>