<?php


/*
$link = mysqli_connect("localhost", "root", "", "project_new");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}else{
    echo 'connected Successfully';
}*/




  $db = new mysqli("localhost", "root", "", "project_new");
  if (!$db) die("database connection error");
  
  $servername = "localhost";
  $username = "root";
  $password = "";
  
  
  $conn = new mysqli($servername, $username, $password, "project_new");
  
  if ($conn->connect_error) {
      die("Connection failed: " . $conn->connect_error);
  } else {
      echo "Connected successfully";
  }

   if($_SERVER["REQUEST_METHOD"] == "POST") {
	   
	  $id=$_GET['id'];
	  
	  $pass=$_GET['pass'];
	  
      $sql = "SELECT parn_pass FROM student WHERE part_id='$id'";
      $result = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($result);
      
      if ($count > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            echo $row['parn_pass'] . "\n";
            if ($row['parn_pass'] == ($pass)) {
                  /// header("location: parent.html");
                  return true;
            } 
			else {
                echo 'error';
            }
        }
    }
}
mysqli_close($conn);
?>





