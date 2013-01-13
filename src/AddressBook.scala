import collection.mutable

object AddressBook {
  val entries = mutable.HashMap[String, String]()

  def list(input: Array[String]): String = {
    entries.map {
      entry =>
        Array(entry._1, "\t<", entry._2, ">").mkString
    }.mkString("\n")
  }

  def add(input: Array[String]): String = {
    val name = input.dropRight(1).mkString(" ")
    val email = input.last
    entries.put(name, email)

    "Entry added: %s <%s>".format(name, email)
  }

  def del(input: Array[String]): String = {
    val key = input.mkString(" ")

    val value = entries.remove(key)
    if (value != None) {
      "Entry removed: %s <%s>".format(key, value.get)
    }
    else {
      "Entry not found"
    }
  }

  def exit(input: Array[String]): String = {
    "" + System.exit(0)
  }

  def echo(input: Array[String]): String = {
    "Could not parse command: " + input.mkString(" ")
  }

  def handleInput(input: String): String = {
    val tokens = input.split(" ")

    val command = tokens.head match {
      case "list" => list _
      case "add" => add _
      case "del" => del _
      case "^D" => exit _
      case _ => echo _
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