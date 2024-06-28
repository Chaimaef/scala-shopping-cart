package com.example

import org.scalatest.funsuite.AnyFunSuite

class CartManagerTest extends AnyFunSuite{
  val cheeriosItem: Item = new Item("Cheerios", 8.43)
  val taxPercentage = 0.125

  test("An Invalid item name should throw an Exception") {
    val cart = new Cart()
    assertThrows[InvalidItemException] {
      CartManager.addToCart("invalid", 2, cart)
    }
  }

  test("A valid item should be added to the cart"){
    val cart = new Cart()
    val updatedCart = CartManager.addToCart("cheerios", 2, cart)
    val addedItem = updatedCart.cartItems.head
    assert(addedItem._1.name == cheeriosItem.name)
    assert(addedItem._1.price == cheeriosItem.price)
  }

  test("getCartSubtotal should return the correct subtotal"){
    val cart = new Cart(Seq((cheeriosItem,2)))
    val total = CartManager.getCartSubtotal(cart)
    assert(total == CartManager.getRoundTotal(cheeriosItem.price * 2))
  }

  test("getCartTaxPayable should return the correct tax payable"){
    val cart = new Cart(Seq((cheeriosItem,2)))
    val total = CartManager.getCartTaxPayable(cart)
    assert(total == CartManager.getRoundTotal(cheeriosItem.price * taxPercentage * 2))
  }

  test("getCartTotal should return the correct total"){
    val cart = new Cart(Seq((cheeriosItem,2)))
    val total = CartManager.getCartTotal(cart)
    assert(total == CartManager.getRoundTotal(cheeriosItem.price * 2 * (1 + taxPercentage)))
  }
}


