import scala.io.StdIn.readLine

object Root {
  println("hello in root")
  //var board : Any

  def xo_controller(player_turn:Int, input : String, boardn : Any) : Boolean = {
    val board = boardn.asInstanceOf[XO]

    val i = input.substring(1,2).toInt - 1
    var j : Int = 0
    var char = input.substring(0,1) match {
      case "a" => j=0
      case "b" => j=1
      case "c" => j=2
      case _ => j= -1
    }

    println("i is " + i + " j is " + j)
    //println("board(i)(j) is "+ board.xo_board(i)(j))

    if (j == -1 || i <0 || i>2 || input.length > 2) {println("in valid") ;return false;}
    if (board.xo_board(i)(j) != "_"){println("in valid") ;return false;}
    board.make_play(i,j,player_turn)
    var c = 0;
    var h=0;
    var k=0;
    while (c!=9) {
      print(board.xo_board(h)(k))
      c+=1
      k+=1
      if (c%3 == 0) {
        h+=1
        k=0
        println()
      }
    }
    return true
  }

  def connect4_controller(player_turn:Int, input : String, boardn : Any) : Boolean = {
    val board = boardn.asInstanceOf[Connect4]

    var col : Int = 0
    var char = input.substring(0,1) match {
      case "a" => col=0
      case "b" => col=1
      case "c" => col=2
      case "d" => col=3
      case "e" => col=4
      case "f" => col=5
      case "g" => col=6
      case _ => col= -1
    }

    println("col is " + col)
    //println("board(i)(j) is "+ board.xo_board(i)(j))

    if (col == -1 || input.length > 1) {println("invalid") ;return false;}
    var no_place = true
    var row_no = 0
    while (no_place && row_no < 6) {
      if (board.connect_board(row_no)(col) == "_"){
        no_place = false
      }else {
        row_no +=1
      }
    }

    if (no_place) {println("invalid"); return false;}
    board.make_play(col,player_turn)
    var c = 0;
    var h=0;
    var k=0;
    while (c!=42) {
      print(board.connect_board(h)(k))
      c+=1
      k+=1
      if (c%7 == 0) {
        h+=1
        k=0
        println()
      }
    }
    //println(board.xo_board)
    return true
  }

  //var x = readLine()
  //var getter = new Input

  /*
  xo_controller(1, getter.get_input(x))
  x = readLine()
  xo_controller(2, getter.get_input(x))
  x = readLine()
  xo_controller(2, getter.get_input(x))
  x = readLine()
  xo_controller(1, getter.get_input(x))
  x = readLine()
  xo_controller(1, getter.get_input(x))
  x = readLine()
  xo_controller(2, getter.get_input(x))*/

  def Game_Engine (drawer : (String) => String , controller : (Int, String, Any) => Boolean, board : Any) : Unit = {
    var player = 1

    val getter = new Input
    var x = drawer("null");
    var runing = true
    while (runing) {
     val valid = controller(player, getter.get_input(x), board)
     if (!valid) {
       x = drawer("not_valid")
     }else {
       x = drawer(getter.get_input(x))
       if (player == 1) player = 2
       else player = 1
     }
      if (x == "end") runing = false
    }
  }

  def xo_drawer(state:String) : String = {
    val input = readLine()
    input
  }

  def connect4_drawer(state:String) : String = {
    val input = readLine()
    input
  }

  def start () {
    val starter = new StartScreen
    var board: Any = null

    var choice = starter.get_choice() match {
      case "xo" => {
        board = new XO; Game_Engine(xo_drawer, xo_controller, board);
      }
      case "connect4" => {
        board = new Connect4; Game_Engine(connect4_drawer, connect4_controller, board);
      }
      //case _ => board = new XO
      //todo list the other cases
    }
  }

  start()

  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
  //def Game_Engine

}
