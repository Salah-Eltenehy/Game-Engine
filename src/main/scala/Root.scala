import ChessGUI.TestGame

import scala.io.StdIn.readLine

object Root {
  println("hello in root")
  //var board : Any


  def xo_controller(player_turn:Int, input : String, boardn : Any) : Array[Array[String]] = {
    val board = boardn.asInstanceOf[XO_Controller]

    /*
    val i = input.substring(1,2).toInt - 1
    var j : Int = 0
    var char = input.substring(0,1) match {
      case "a" => j=0
      case "b" => j=1
      case "c" => j=2
      case _ => j= -1
    }*/

    val i = input.substring(0,1).toInt
    val j = input.substring(2,3).toInt

    println("i is " + i + " j is " + j)

    if (j <0 || j>2 || i <0 || i>2 || input.length > 3) {println("in valid") ;return Array(Array());}
    if (board.xo_board(i)(j) != "_"){println("in valid") ;return Array(Array());}
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
    board.xo_board
  }

  def connect4_controller(player_turn:Int, input : String, boardn : Any) : Array[Array[String]] = {
    val board = boardn.asInstanceOf[Connect4_Controller]

    var col : Int = input.toInt
    println("col is " + col)

    var no_place = true
    var row_no = 0
    while (no_place && row_no < 6) {
      if (board.connect_board(row_no)(col) == "_"){
        no_place = false
      }else {
        row_no +=1
      }
    }

    if (no_place) {println("invalid"); return Array(Array())}
    board.make_play(col,player_turn)

    board.connect_board
  }


  def checkersController(player_turn:Int, input : String, boardn : Any):Array[Array[String]] = {
    val board = boardn.asInstanceOf[Checker_Controller]

    val row1 = input.substring(0,1).toInt
    val column1 = input.substring(2,3).toInt
    val row2 =  input.substring(4,5).toInt
    val column2 =input.substring(6,7).toInt

    if(board.validateMove((row1,column1),(row2, column2) ,player_turn))
      board.movePiece((row1, column1),(row2, column2), player_turn )
    else
      return Array(Array(""))
    var c = 0;
    var h=0;
    var k=0;
    while (c!=64) {
      print(board.checkersBoard(h)(k))
      c+=1
      k+=1
      if (c%8 == 0) {
        h+=1
        k=0
        println()
      }
    }
    board.checkersBoard
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
  private var player = 1
  def Game_Engine (drawer : (String) => String , controller : (Int, String, Any) => Array[Array[String]], board : Any) : Array[Array[String]] = {

      val getter = new Input
      var x = drawer("");
      val valid = controller(player, getter.get_input(x), board)
    if(valid.size >1) {
      if (player == 1) player = 2
      else player = 1
    }

    valid
  }


/*
    def game_logic (input_from_drawer : String): String = {
      val getter = new Input
      val x = drawer("null");
      val valid = controller(player, getter.get_input(input_from_drawer), board)
      if (player == 1) player = 2
      else player = 1
      valid;
      /*
      if (valid.equals("")) {
        x = drawer(valid)
      }else {
        x = drawer(valid);
        if (player == 1) player = 2
        else player = 1
      }
      }
       */
    }*/
    /*
    val getter = new Input
    var x = drawer("null");
    var runing = true
    while (runing) {
     val valid = controller(player, getter.get_input(x), board)
     if (valid.equals("")) {
       x = drawer(valid)
     }else {
       x = drawer(valid);
       if (player == 1) player = 2
       else player = 1
     }
      if (x == "end") runing = false
    }*/

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
      case "XO" => {
        var obj = new TestGame
        board = new XO_Controller; Game_Engine(obj.xo_drawer, xo_controller, board);
      }
      case "connect4" => {
        board = new Connect4_Controller; Game_Engine(connect4_drawer, connect4_controller, board);
      }
      case "chess" => {
        println("asda")
      }
      //case _ => board = new XO
      //todo list the other cases
    }
  }



  def main(args: Array[String]): Unit = {
    println("Hello world!")
    start()
  }
  //def Game_Engine

}
