package com.example

import sttp.client3._


object ItemClient {
  def loadItem(name : String): Option[Item] = {
      val url = "https://raw.githubusercontent.com/mattjanks16/shopping-cart-test-data/main/" + name + ".json"
      val backend = HttpURLConnectionBackend()
      val request = basicRequest.get(uri"$url")
      val response = request.send(backend)

      response.body match {
        case Left(_) => None
        case Right(a) => {
          try {
            val itemValues = ujson.read(a)
            Some(new Item(itemValues("title").str, itemValues("price").num))
          } catch {
            case _: Exception => None
          }
        }
      }
  }
}
