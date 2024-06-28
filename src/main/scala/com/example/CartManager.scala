package com.example

object CartManager {

  def addToCart(itemName : String, quantity : Int, cart : Cart) : Cart = {
    val result = ItemClient.loadItem(itemName)
    result match {
      case Some(item) => new Cart(cart.cartItems :+ (item, quantity))
      case None => throw InvalidItemException()
    }
  }

  def getCartSubtotal(cart: Cart) : Double = {
    val cartSubtotal = cart.cartItems.map{ case (item : Item, quantity : Int) =>
      item.price * quantity
    }.sum
    getRoundTotal(cartSubtotal)
  }

  def getCartTaxPayable(cart : Cart): Double = {
    val taxPercentage = 0.125
    val cartTaxPayable = cart.cartItems.map{case (item : Item, quantity : Int) =>
      item.price * quantity * taxPercentage
    }.sum
    getRoundTotal(cartTaxPayable)
  }

  def getCartTotal(cart: Cart): Double = {
    getCartSubtotal(cart) + getCartTaxPayable(cart)
  }

  def getRoundTotal(total : Double) : Double = {
    BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}

case class InvalidItemException() extends Exception
