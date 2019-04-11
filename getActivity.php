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
      $sql = "SELECT id FROM student WHERE part_id='$id'";
      $result = mysqli_query($conn,$sql);
      $count = mysqli_num_rows($result);
      $idd="";
      if ($count > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
          $idd=$row['id'];
          //echo $idd;

        }
    }

    
        $s="SELECT type,points FROM grades_activity WHERE id='$idd'";
        $result1 = mysqli_query($conn,$s);
        $count1 = mysqli_num_rows($result1);
        $myArray=array();
        if ($count1 > 0) {
        while ($row1 = mysqli_fetch_assoc($result1)) {
          //  $idd1=$row1['point'];
           $myArray[]=$row1;
            
        }
       // echo $myArray;
    }

   //  for($i=0;$i<count($myArray);$i++){
        // echo $myArray[$i]."<br>";
    // }


     $type=array();
     foreach ($myArray as $result) {
      //echo $result['type'];
      $type[]=$result['type'];
    }


    
    $point=array();
    foreach ($myArray as $result) {
     //echo $result['type'];
     $point[]=$result['points'];
   }

   // foreach ($point as $result) {
    //    echo $result."<br>";
       // $type[]=$result['type'];
   // }


$n=count($point)-1;
//echo $n;
   for($i=0;$i<count($type);$i++){
  //  echo ' 

   //   ';
        echo $type[$i];
       // echo str_repeat("&nbsp;", 10);
       echo '      ';
        echo $point[$i]."points";
        echo '   

          ';  
        
       
       
        //echo "<br>";
   }


mysqli_close($conn);
?>





