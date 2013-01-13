import collection.mutable

object AddressBook {

  val entries = mutable.HashMap[String, String]()

  def list(input: Array[String]): String = {
    entries.map { entry =>
      Array(entry._1, "\t<", entry._2, ">").mkString
    }.mkString("\n")
  }

  def add(input: Array[String]): String = {
    val name = input.dropRight(1).mkString(" ")
    val email = input.last
    entries.put(name, email)

    "Entry added: " + name + " <" + email + ">"
  }

  def echo(input: Array[String]): String = {
    "Could not parse command: " + input.mkString(" ")
  }

  def handleInput {
    print("> ")

    for (ln <- io.Source.stdin.getLines) {
      val tokens = ln.split(" ")

      val command = tokens.head match {
        case "list" => list _
        case "add" => add _
        case _ => echo _
      }

      val output = command(tokens.drop(1))
      println(output)
      print("> ")
    }

  }

  def main(args: Array[String]) {
    entries += ("Spider Jerusalem" -> "spidey@theword.com")
    entries += ("Mitchell Royce" -> "mitch@theword.com")

    handleInput
  }
}