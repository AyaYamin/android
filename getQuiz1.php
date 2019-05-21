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
  
      $idd=$_GET['id'];
      /*
      $sql = "SELECT id FROM student WHERE part_id='$id'";
      $result = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($result);
      $idd="";
      if ($count > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
          $idd=$row['id'];
        //  echo $idd;

        }
    }
*/
$sub=$_GET['subject'];
        $s="SELECT point FROM grades WHERE id='$idd' and type='Daily' and subject='$sub'";
      $result1 = mysqli_query($conn,$s);
        $count1 = mysqli_num_rows($result1);
        if ($count1 > 0) {
        while ($row1 = mysqli_fetch_assoc($result1)) {
            $idd1=$row1['point'];
            echo $idd1;
        }
    }
    
mysqli_close($conn);
?>





