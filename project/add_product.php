<?php

function product($id, $name, $price, $img) {
    $element = "
        <form action=\"\" method=\"POST\" class=\"box\">
          <img style\"width: 93%;\" src=\"$img\" class=\"image\" alt=\"\">
          <h3 class=\"name\">$name</h3>
          <input type=\"hidden\" name=\"product_id\" value=\"$id\">
          <div class=\"flex\">
             <p class=\"price\"><i class=\"fas fa-dollar\"></i>$price</p>
             <input type=\"number\" name=\"qty\" required min=\"1\" value=\"1\" max=\"99\" maxlength=\"2\" class=\"qty\">
          </div>
          <input type=\"submit\" name=\"add_to_cart\" value=\"add to cart\" class=\"btn addtocart\">
       </form>";
    echo $element;
}
?>