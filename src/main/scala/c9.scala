//przyjmuję, że R oznacza liczby rzeczywiste

/*1.	(0.3 pkt) Zaimplementuj klasę Container parametryzowaną typem A. Konstruktor klasy powinien przyjmować pojedynczą prywatną wartość wskazanego typu.
Klasa powinna implementować następujące metody:
  a.	getContent zwracającą przechowywaną wartość
  b.	applyFunction przyjmującą funkcję typu A=>R i zwracającą wynik działania funkcji na zawartości kontenera*/
class Container[A](value: A){
  private val _value: A = value
  def getContent:A = _value
  def applyFunction(fun: A => Double) = fun(_value)
}

/*2.	 (0.2 pkt) Zaimplementuj trait Maybe parametryzowany typem A i dwie dziedziczące z niego klasy – klasę No rozszerzającą Maybe[Nothing] i
klasę Yes parametryzowaną typem A i przechowującą pojedynczy obiekt wskazanego typu. Stwórz obiekty obu klas.
Sprawdź, czy oba są podtypem Maybe[_] (do sprawdzenia tego służy metoda isInstanceOf parametryzowana sprawdzanym typem)
3.	(0.2 pkt) Rozszerz rozwiązanie zadania 3 o funkcję applyFunction typu A=>R i zwracającą:
  a.	dla No: No
b.	dla Yes: Yes(f(zawartość yes))
4.	(0.3 pkt) rozszerz rozwiązanie zadania 3 o funkcję getOrElse parametryzowaną typem i zwracającą:
  a.	Dla No – parametr getOrElse
  b.	Dla Yes – zawartość Yes*/

trait Maybe[A]{
  def applyFunction(fun: A => Double): Maybe[_]
  def getOrElse[X>:A](x: X): X
}

case class No() extends Maybe[Nothing]{
  override def applyFunction(fun: Nothing => Double) = No()
  override def getOrElse[X >: Nothing](x: X): X = x
}

case class Yes[B](value: B) extends Maybe[B]{
  private val _value = value
  override def applyFunction(fun: B => Double) = Yes(fun(_value))
  override def getOrElse[X >: B](x: X): X = _value
}


object c9 {
  def main(args: Array[String]): Unit ={
    val c = new Container[Int](-7)
    println("1a " + c.getContent)
    println("1b " + c.applyFunction(_*1000))

    val yes = Yes(5)
    val no = No()
    println("2 yes instance of maybe? " + yes.isInstanceOf[Maybe[_]])
    println("2 no instance of maybe? " + no.isInstanceOf[Maybe[_]])

    def f(n: Nothing) = 5
    println("3 " + yes.applyFunction(_+73))
    println("3 " + no.applyFunction(f))

    println("4 " + yes.getOrElse(42))
    println("4 " + no.getOrElse(42))
  }
}