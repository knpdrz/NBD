object c2 {
  //  NBD Ćwiczenia 2 – Scala
  //  W każdym wypadku rozwiązanie powinno zawierać oczywiście instrukcje pozwalające wypisać wyniki itp.
  //  1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String.
  // Dla stringów odpowiadających nazwom dni tygodnia funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów „Nie ma takiego dnia”.
  def nauczycielDni(text: String): String = {
    text match {
      case "poniedzialek" | "wtorek" | "sroda" | "czwartek" | "piatek" => "Praca"
      case "sobota" | "niedziela" => "Weekend"
      case _ => "Nie ma takiego dnia"
    }
  }

  //  2.	Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz własnością stanKonta - własność ma być tylko do odczytu.
  // Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi, ustawiający początkowy stan konta na 0.
  class KontoBankowe() {
    private var _stanKonta = 0.0

    def stanKonta: Double = _stanKonta

    def this(stanKonta: Double) {
      this()
      _stanKonta = stanKonta
    }

    def wplata(wartosc: Double): Unit = {
      _stanKonta = _stanKonta + wartosc
    }

    def wyplata(wartosc: Double): Unit = {
      val nowyStanKonta = _stanKonta - wartosc
      if (nowyStanKonta < 0) {
        println("brak wystarczajacej liczby srodkow do wykonania tej akcji")
      } else {
        _stanKonta = nowyStanKonta
      }
    }
  }

  //  3.	Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy.
  // Zdefiniuj funkcję, która przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie danej osoby.
  // Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.
  case class Person(var imie: String, var nazwisko: String)

  def przywitanie(person: Person): String = {
    person match {
      case Person("Sans", "Comic") => "oh hi Sans!"
      case Person("Jose", "Morales") => "buenos dias senior Jose"
      case _ => "dzienbobry"
    }
  }

  //  4.	Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
  // Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.
  def applier(value: Int, fun: Int => Int): Int = {
    fun(fun(fun(value)))
  }

  def square(x: Int): Int = x * x

  //  5.	Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik.
  // Osoba powinna mieć własności read only: imie, nazwisko, podatek.
  // Pracownik powinien mieć własność pensja (z getterem i seterem).
  // Student i Pracownik powinni przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji.
  // Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek zwraca 10% pensji.
  // Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla każdego z nich.
  // Stwórz obiekty z traitami Student i Pracownik, pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
  abstract class Osoba(val imie: String, val nazwisko: String){
    def podatek : Double
  }

  trait Pracownik extends Osoba{
    var pensja: Double = 0.0
    override def podatek: Double = pensja*0.2
  }

  trait Student extends Osoba{
    override def podatek: Double = 0.0
  }

  trait Nauczyciel extends Pracownik{
    override def podatek: Double = pensja*0.1
  }

  def main(args: Array[String]): Unit = {
    println("1) poniedzialek: " + nauczycielDni("poniedzialek") + ", sfhs:" + nauczycielDni("sfhs") + ", niedziela: " + nauczycielDni("niedziela"))

    println("2)")
    val kontoBankowe = new KontoBankowe(999)
    println("poczatkowy stan konta " + kontoBankowe.stanKonta)
    kontoBankowe.wplata(1000.0)
    println("stan konta po wplacie 1000: " + kontoBankowe.stanKonta)
    kontoBankowe.wyplata(1600)
    println("stan konta po wyplacie 1600: " + kontoBankowe.stanKonta)

    println("3)")
    val jose = Person("Jose", "Morales")
    val filip = Person("Filip", "Filip")
    val sans = Person("Sans", "Comic")

    println("przywitanie Jose: " + przywitanie(jose))
    println("przywitanie Sansa: " + przywitanie(sans))
    println("przywitanie Filipa: " + przywitanie(filip))

    println("4)")
    println("wynik funkcji square zaaplikowany trzy razy do liczby 2: " + applier(2, square))

    println("5)")
    val student = new Osoba("A", "Student") with Student
    val nauczyciel = new Osoba("B", "Nauczyciel") with Nauczyciel
    val pracownik = new Osoba("C", "Pracownik") with Pracownik

    println("podatek studenta wynosi: " + student.podatek)

    nauczyciel.pensja = 9000.0
    println("podatek nauczyciela z pensją: "+ nauczyciel.pensja + " wynosi: " + nauczyciel.podatek)

    pracownik.pensja = 9000.0
    println("podatek pracownika z pensją: "+ pracownik.pensja + " wynosi: " + pracownik.podatek)


    val studentPracownik = new Osoba("D", "StudentPracownik") with Student with Pracownik
    studentPracownik.pensja = 1000.0
    val pracownikStudent = new Osoba("E", "PracownikStudent") with Pracownik with Student
    print("podatek studenta oraz pracownika")
    println(" z pensją: "+ studentPracownik.pensja + " wynosi: " + studentPracownik.podatek)
    pracownikStudent.pensja = 10000.0
    print("podatek pracownika oraz studenta")
    println(" z pensją: "+ pracownikStudent.pensja + " wynosi: " + pracownikStudent.podatek)
  }
}
