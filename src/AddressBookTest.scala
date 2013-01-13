object AddressBookTest {
  def assert(cmd: String, expected: String) = try {
    printf("Command: %s", cmd)

    val response = AddressBook.handleInput(cmd)
    scala.Predef.assert(response == expected, response)

    println(" ...success!")
  }
  catch {
    case e:AssertionError => {
      println(" ...fail")
      printf("\tExpected: \"%s\"\n", expected)
      printf("\tGot: \"%s\"\n", e.getMessage())
    }
  }

  def main(args: Array[String]) {
    assert("add Spider Jerusalem spidey@theword.com", "Entry added: Spider Jerusalem <spidey@theword.com>")
    assert("list", "Spider Jerusalem\t<spidey@theword.com>")

    assert("add Mitchell Royce mitch@theword.com", "Entry added: Mitchell Royce <mitch@theword.com>")
    assert("list", "Mitchell Royce\t<mitch@theword.com>\nSpider Jerusalem\t<spidey@theword.com>")

    assert("del Spider Jerusalem", "Entry removed: Spider Jerusalem <spidey@theword.com>")
    assert("list", "Mitchell Royce\t<mitch@theword.com>")
  }
}
