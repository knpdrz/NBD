object c1 {

  // NBD Ćwiczenia 1 – Scala
  // W każdym wypadku rozwiązanie powinno zawierać oczywiście instrukcje pozwalające wypisać wyniki itp.
  // 1.	Stwórz 7 elementową listę zawierającą nazwy dni tygodnia. Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  val dni = List("pon", "wt", "sr", "czw", "pt", "sob", "nie")

  // a.	Pętli for
  def concatList(list: List[String]): String = {
    var result = ""
    for (elem <- list.init) {
      result = result + elem + ","
    }
    result + list.last
  }

  // b.	Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
  def concatListWithFilter(list: List[String]): String = concatList(list.filter(_.startsWith("p")))

  // c.	Pętli while
  def concatListWithWhile(list: List[String]): String = {
    var result = ""
    var cutList = list
    while (cutList.length > 1) {
      result = result + cutList.head + ","
      cutList = cutList.tail
    }
    result = result + cutList.head
    result
  }

  // 2.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  // a.	Funkcji rekurencyjnej
  def concatListReku(list: List[String]): String = {
    if (list.isEmpty)
      return ""
    if (list.length == 1)
      list.head
    else
      list.head + "," + concatListReku(list.tail)
  }

  // b.	Funkcji rekurencyjnej wypisując elementy listy od końca
  def concatListRekuBackwards(list: List[String]): String = {
    if (list.isEmpty)
      return ""
    if (list.length == 1)
      list.head
    else
      concatListRekuBackwards(list.tail) + "," + list.head
  }

  // 3.	Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1
  def concatListRekuTail(list: List[String], result: String): String = {
    if (list.isEmpty)
      return ""
    if (list.length == 1)
      result + list.head
    else
      concatListRekuTail(list.tail, result + list.head + ",")
  }

  // 4.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  // a.	Metody foldl
  def concatListWithFoldL(list: List[String]): String = {
    list.tail.foldLeft(list.head)(_ + "," + _)
  }

  // b.	Metody foldr
  def concatListWithFoldR(list: List[String]): String = {
    list.slice(0, list.length - 1).foldRight(list.last)(_ + "," + _)
  }

  // c.	Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”
  def concatListWithFoldLAndFilter(list: List[String]): String = {
    concatListWithFoldL(list.filter(_.startsWith("p")))
  }


  // 5.	Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm mapowania kolekcji.
  def mapProducts(): String = {
    val discount = (k: String, v: Int) => (k, v * 0.9)
    val products = Map("egg" -> 1, "cake" -> 5000)
    val discountProducts = products.map(discount.tupled)
    discountProducts.toString()
  }

  // 6.	Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
  def printTripple(triple: (String, Int, Any)): Unit = {
    println("6)  \ttriple = " + triple._1 + " " + triple._2 + " " + triple._3)
  }

  // 7.	Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
  def optionExhibitionLottery(betValue: Double, guess: Int): Double = {
    val lotteryTickets = Map(17 -> 100.0, 99 -> 12.5, 32 -> 4.99)

    val ticketValueOption = lotteryTickets.get(guess): Option[Double]
    ticketValueOption match {
      case Some(ticketValue) => ticketValue * betValue
      case None => 0.0
    }
  }

  // 8.	Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).  Wykorzystaj rekurencję.
  // 9.	Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę, w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
  // 10.	Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.

  def main(args: Array[String]): Unit = {
    println("1a) \t" + concatList(dni))
    println("1b) \t" + concatListWithFilter(dni))
    println("1c) \t" + concatListWithWhile(dni))
    println("2a) \t" + concatListReku(dni))
    println("2b) \t" + concatListRekuBackwards(dni))
    println("3)  \t" + concatListRekuTail(dni, ""))
    println("4a) \t" + concatListWithFoldL(dni))
    println("4b) \t" + concatListWithFoldR(dni))
    println("4c) \t" + concatListWithFoldLAndFilter(dni))

    println("5)  \t" + mapProducts())

    printTripple(Tuple3("Mooo", 66, 0.6))
    println("7)  \t" + optionExhibitionLottery(betValue = 1000, guess = 99))
    println("    \t" + optionExhibitionLottery(betValue = 20000, guess = 1))
  }
}
