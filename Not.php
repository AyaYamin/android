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


  //$sql="INSERT into aya (name) VALUES ('aya')";
  //$result = mysqli_query($conn,$sql);

 // push();

  mysqli_close($conn);
  ?>