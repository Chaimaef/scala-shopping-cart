package com.example

import cats.effect.IOApp
import cats.effect.IO

object Main extends IOApp.Simple {

  def run: IO[Unit] = {
    // An example case on how to initialize a cart, add items to it and get the total price
    val cart = new Cart()
    val updatedCart = CartManager.addToCart("cheerios", 2, cart)
    IO.println(CartManager.getCartTotal(updatedCart))
  }

}
