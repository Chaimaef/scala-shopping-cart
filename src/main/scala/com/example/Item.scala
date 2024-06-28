package com.example

class Item(val name : String, val price : Double) {
  override def toString: String = name + ", " + price
}
