<header class="header" style="background-color: rgba(27,27,27,27);">

   <section class="flex">
      <a href="view_products.php" class="logo" style="font-size: 45px"><i class="fab fa-apple" style="font-size: 50px"></i> Apple Store</a>

      <nav class="navbar">
         <a href="view_products.php">Product</a>
         <a href="orders.php">My order</a>
         <?php
            $count_cart_items = $conn->prepare("SELECT * FROM `cart` WHERE user_id = ?");
            $count_cart_items->execute([$user_id]);
            $total_cart_items = $count_cart_items->rowCount();
         ?>
         <a href="shopping_cart.php" class="cart-btn">My cart<span><?= $total_cart_items; ?></span></a>
      </nav>

      <div id="menu-btn" class="fas fa-bars"></div>
   </section>

</header>