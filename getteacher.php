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
  
	  $subject=$_GET['subject'];
      $sql = "SELECT fname,mname,lname FROM teacher WHERE subject='$subject'";
      $result = mysqli_query($conn,$sql);
    $count = mysqli_num_rows($result);
    $row = mysqli_fetch_assoc($result);
     echo $row['fname'];
          echo $row['mname'];
          echo $row['lname'];
      if ($count > 0) {
        //while ($row = mysqli_fetch_assoc($result)) {
         
        }
   // }


mysqli_close($conn);
?>





