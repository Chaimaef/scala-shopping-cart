package com.example

import org.scalatest.funsuite.AnyFunSuite

class ItemClientTest extends AnyFunSuite{

  test("The items should be loaded correctly"){
    val cheeriosItem = new Item("Cheerios", 8.43)
    val loadedCheeriosItem: Option[Item] = ItemClient.loadItem("cheerios")
    assert(loadedCheeriosItem.map(_.name).getOrElse("") == cheeriosItem.name)
    assert(loadedCheeriosItem.map(_.price).getOrElse(-1) == cheeriosItem.price)
  }

  test("The result of loading an item which doesn't' exist should be None "){
    assert(ItemClient.loadItem("krave").isEmpty)
  }

}
