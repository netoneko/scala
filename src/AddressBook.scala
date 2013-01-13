import collection.mutable

object AddressBook {
  val entries = mutable.HashMap[String, String]()

  def handleInput(line: String): String = {
    val tokens = line.split(" ")
    var input = tokens.drop(1)

    val command = tokens.head match {
      case "list" => {
        entries.map {
          entry =>
            Array(entry._1, "\t<", entry._2, ">").mkString
        }.mkString("\n")
      }
      case "add" => {
        val name = input.dropRight(1).mkString(" ")
        val email = input.last
        entries.put(name, email)
        "Entry added: %s <%s>".format(name, email)
      }
      case "del" => {
        val key = input.mkString(" ")
        val value = entries.remove(key)
        if (value != None)
          "Entry removed: %s <%s>".format(key, value.get)
        else
          "Entry not found"
      }
      case "^D" => {
        "" + System.exit(0)
      }
      case _ => {
        "Could not parse command: " + input.mkString(" ")
      }
    }

    command
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