object Hello {
  def main(args: Array[String]) {
    val add = (x: Int, y: Int) => x + y

    println(add(2, 3))

    val add2 = add(2, _:Int)

    println(add2(3))

    def realPower(base: Int, power: Int): Int = {
      if (power == 0) 1
      else if (power == 1) base
      else base * realPower(base, power - 1)
    }

    println(realPower(2, 3))

    def pow(base: Int)(power: Int) = {
      realPower(base, power)
    }

    println(pow(2)(3))

    val square = realPower(_: Int, 2)

    println(square(7))
  }
}
