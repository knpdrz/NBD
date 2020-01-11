

/*2.	Zmodyfikuj klasę Maybe z ćwiczenia 9 tak, by implementowała metody map i
flatMap. Zaprezentuj ich działanie.*/
abstract class Maybe[+A] {
  def getValue: A
  def isEmpty: Boolean

  def getOrElse[X >: A](x: X): X ={
    if(isEmpty) return x
    getValue
  }

  def map[B](f: A => B): Maybe[B] = {
    if (isEmpty) return No()
    Yes(f(getValue))
  }

  def flatMap[B](f: A => Maybe[B]): Maybe[B] = {
    if (isEmpty) return No()
    f(getValue)
  }
}

case class No() extends Maybe[Nothing] {
  override def getValue: Nothing = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
}

case class Yes[A](value: A) extends Maybe[A] {
  override def getValue: A = value
  override def isEmpty: Boolean = false
}

object c10 {
  def main(args: Array[String]): Unit = {
    /*1.	(0.5 pkt) Zaimplementuj przy użyciu for generator kolejnych par (a,b) takich,
    że a jest liczbą naturalną a b jest jej dzielnikiem. Zadbaj o to, by generator był leniwie ewaluowany.
    Przy użyciu metod take i next wypisz w kilku kolejnych wywołaniach 20 pierwszych elementów ciągu.*/

    val gen = for (a <- LazyList.from(1); b <- 1 to a if a % b == 0) yield (a, b)
    val it = gen.iterator

    println("1")
    for (_ <- 1 to 20) {
      print(it.next() + " ")
    }
    println()

    println("2")
    def giveMePositiveYes(a: Int): Maybe[Double] = if(a<0) No() else Yes(a)

    val yes: Maybe[Int] = Yes(7)
    val no: Maybe[Int] = No()
    println("map yes " + yes.map(_*9))
    println("map no " + no.map(_*9))
    println("fmap yes " + yes.flatMap(giveMePositiveYes))
    println("fmap yes " + no.flatMap(giveMePositiveYes))
  }
}