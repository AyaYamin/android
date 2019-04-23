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
      
      $sql = "SELECT msg FROM msg WHERE  id='$id'";
      $result = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($result);
        $myArray=array();
      if ($count > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
           $myArray[]=$row;
        }
    }


   

  $sub=array();
  foreach ($myArray  as $key ) {
     $sub[]=$key['msg'];
     $sub[]=",";
  }

  foreach (  $myArray  as $key ) {
    //echo $key['msg'];
   
 }

 //print_r($sub);


  for($i=0;$i<count($sub);$i++){
     echo $sub[$i];
     // echo " ";
  }
mysqli_close($conn);
?>





