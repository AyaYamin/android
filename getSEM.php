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
      $sql = "SELECT classid FROM student WHERE  id='$id'";
      $result = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($result);
      
      if ($count > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
           $classid=$row['classid'];
         //  echo $classid;
        }
    }


    $s = "SELECT semester FROM exam WHERE  classid='$classid'";
    $result1 = mysqli_query($conn,$s);
    $count1 = mysqli_num_rows($result1);
    if ($count1 > 0) {
        while ($row1 = mysqli_fetch_assoc($result1)) {
          echo $row1['semester'];
          break;
        }

    }
mysqli_close($conn);
?>





