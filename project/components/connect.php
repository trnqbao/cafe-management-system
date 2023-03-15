<?php

   $db_name = 'mysql:host=localhost;dbname=shopping';
   $db_user_name = 'root';
   $db_user_pass = '';

   $conn = new PDO($db_name, $db_user_name, $db_user_pass);

   function create_unique_id(){
      $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
      $charactersLength = strlen($characters);
      $randomString = '';
      for ($i = 0; $i < 20; $i++) {
          $randomString .= $characters[mt_rand(0, $charactersLength - 1)];
      }
      return $randomString;
    }

   // get product from the database
    // public function getData(){
    //     $sql = "SELECT * FROM 'products'";

    //     $result = mysqli_query($this->conn, $sql);

    //     if(mysqli_num_rows($result) > 0){
    //         return $result;
    //     }
    // }


?>