object AddressBook {
  val entries = collection.mutable.Map[String, String]()

  def handleInput(line: String): String = {
    val tokens = line.split(" ")
    val input = tokens.drop(1)

    tokens.head match {
      case "list" => {
        entries.map(x => "%s\t<%s>".format(x._1, x._2)).mkString("\n")
      }
      case "add" => {
        val name = input.dropRight(1).mkString(" ")
        val email = input.last
        entries += (name -> email)
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
        System.exit(0).toString
      }
      case _ => {
        "Could not parse command: " + input.mkString(" ")
      }
    }
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