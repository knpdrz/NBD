object c1 {

  // NBD Ćwiczenia 1 – Scala
  // W każdym wypadku rozwiązanie powinno zawierać oczywiście instrukcje pozwalające wypisać wyniki itp.
  // 1.	Stwórz 7 elementową listę zawierającą nazwy dni tygodnia. Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  val dni = List("pon", "wt", "sr", "czw", "pt", "sob", "nie")

  // a.	Pętli for
  def concatList(list: List[String]): String = {
    var result = ""
    for(elem <- list.init){
      result = result + elem + ","
    }
    result + list.last
  }

  // b.	Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
  def concatListWithFilter(list: List[String]): String = {
    var result = ""
    for(elem <- list.init if elem.startsWith("p")){
      result = result + elem + ", "
    }
    if(list.last.startsWith("p")){
      result = result + list.last
    }
    result
  }

  // c.	Pętli while
  // 2.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  // a.	Funkcji rekurencyjnej
  // b.	Funkcji rekurencyjnej wypisując elementy listy od końca
  // 3.	Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1
  // 4.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  // a.	Metody foldl
  // b.	Metody foldr
  // c.	Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”
  // 5.	Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm mapowania kolekcji.
  // 6.	Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
  // 7.	Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
  // 8.	Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).  Wykorzystaj rekurencję.
  // 9.	Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę, w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
  // 10.	Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.

  def main(args: Array[String]): Unit = {
    println("1a = " + concatList(dni))
    println("1b = " + concatListWithFilter(dni))

  }
}
