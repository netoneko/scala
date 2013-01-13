object AddressBook {
  case class Command(name: String, apply: (Array[String]) => String);

  val commands = Array(
    Command("echo", { input: Array[String] =>
      input.mkString(" ")
    })
  )

  def main(args: Array[String]) {
    println(commands.head.apply _)

    print("> ")

    for (ln <- io.Source.stdin.getLines) {
      val result = ln match {

        case _ => commands.head.apply(ln.split(" "))
      }

      println(result)
      print("> ")
    }
  }
}