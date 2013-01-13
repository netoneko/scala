import collection.mutable

object AddressBook {
  val entries = mutable.HashMap[String, String]()

  def handleInput(input: String): String = {
    val tokens = input.split(" ")

    val command = tokens.head match {
      case "list" => {
        input: (Array[String]) =>
          entries.map {
            entry =>
              Array(entry._1, "\t<", entry._2, ">").mkString
          }.mkString("\n")
      }
      case "add" => {
        input: (Array[String]) =>
          val name = input.dropRight(1).mkString(" ")
          val email = input.last
          entries.put(name, email)
          "Entry added: %s <%s>".format(name, email)
      }
      case "del" => {
        input: (Array[String]) =>
          val key = input.mkString(" ")
          val value = entries.remove(key)
          if (value != None)
            "Entry removed: %s <%s>".format(key, value.get)
          else
            "Entry not found"
      }
      case "^D" => {
        input: (Array[String]) => "" + System.exit(0)
      }
      case _ => {
        input: (Array[String]) => "Could not parse command: " + input.mkString(" ")
      }
    }

    command(tokens.drop(1))
  }

  def main(args: Array[String]) {
    print("> ")
    for (line <- io.Source.stdin.getLines) {
      val output = handleInput(line)
      println(output)
      print("> ")
    }
  }
}