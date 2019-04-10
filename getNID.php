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
  $sql="SELECT id FROM student WHERE part_id='$id'";
  $result = mysqli_query($conn,$sql);
  $count = mysqli_num_rows($result);
  if ($count > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
      echo $row['id'];
     // echo $row['id'];
    }
  }
  mysqli_close($conn);
  ?>