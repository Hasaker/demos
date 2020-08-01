package fp_in_scala.data_structure

/**
 * @Project scala
 * @Author Ravooo
 * @Since 2020/8/1 14:37
 * @Description List
 */
sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

    def sum(list: List[Int]): Int = list match {
        case Nil => 0
        case Cons(x, xs) => x + sum(xs)
    }

    def product(list: List[Double]): Double = list match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(x, xs) => x * product(xs)
    }

    def apply[A](list: A*): List[A] = {
        if (list.isEmpty) Nil
        else Cons(list.head, apply(list.tail: _*))
    }

    /**
     * practice 3.2
     * @param list
     * @tparam A
     * @return
     */
    def tail[A](list: List[A]): List[A] = list match {
        case Nil => sys.error("Empty list")
        case Cons(_, tail) => tail
    }

    /**
     * practice 3.3
     * @param list
     * @param element
     * @tparam A
     * @return
     */
    def setHead[A](list: List[A], element: A): List[A] = list match {
        case Nil => sys.error("Empty list")
        case Cons(_, tail) => Cons(element, tail)
    }

    /**
     * practice 3.4
     * @param list
     * @param index
     * @tparam A
     * @return
     */
    @scala.annotation.tailrec
    def drop[A](list: List[A], index: Int): List[A] = {
        if (index < 0) list
        else list match {
            case Nil => Nil
            case Cons(_, tail) => drop(tail, index - 1)
        }
    }

    /**
     * practice 3.5
     * @param list
     * @param func
     * @tparam A
     * @return
     */
    @scala.annotation.tailrec
    def dropWhile[A](list: List[A], func: A => Boolean): List[A] = list match {
        case Cons(head, tail) if func(head) => dropWhile(tail, func)
        case _ => list
    }

    /**
     * practice 3.6
     * @param list
     * @tparam A
     * @return
     */
    def init[A](list: List[A]): List[A] = list match {
        case Nil => sys.error("Empty list")
        case Cons(_, Nil) => Nil
        case Cons(head, tail) => Cons(head, init(tail))
    }

    def init2[A](list: List[A]): List[A] = {
        import collection.mutable.ListBuffer
        val buf = new ListBuffer[A]

        @scala.annotation.tailrec
        def go(cur: List[A]): List[A] = cur match {
            case Nil => sys.error("Empty list")
            case Cons(_, Nil) => List(buf.toList: _*)
            case Cons(head, tail) => buf += head; go(tail)
        }

        go(list)
    }




    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3, 4, 5)
        val x = list match {
            case Cons(x, Cons(2, Cons(4, _))) => x
            case Nil => 42
            case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
            case Cons(head, tail) => head + sum(tail)
            case _ => 101
        }
        println(x)

        val tailed = tail(list)
        println(tailed)

        val headChanged = setHead(list, 100)
        println(headChanged)

        val draped = drop(list, 2)
        println(draped)

        val dropWhiled = dropWhile(list, (element: Int) => element % 2 == 0)
        println(dropWhiled)

        val inited = init(list)
        val inited2 = init2(list)
        println(inited, inited2)
    }
}