import ChessGUI.TestGame

import scala.io.StdIn.readLine

object Root {
  println("hello in root")

  def xo_controller(player_turn:Int, input : String, boardn : Any) : Array[Array[String]] = {
    val board = boardn.asInstanceOf[XO_Controller]

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

  def chess_controller(player_turn:Int, input : String, boardn : Any) : Array[Array[String]] = {
    var promotion :Array[Char] = Array(' ')
    if(input.length>8)
       promotion = input.substring(8, 9).toCharArray
    println("Promotion: " + promotion(0))
    val board = boardn.asInstanceOf[Chess]
    var col : Int = 0
    var in: Array[Int] = new Array[Int](4)
    in(0) = input.substring(0, 1).toInt
    in(1) = input.substring(2, 3).toInt
    in(2) = input.substring(4, 5).toInt
    in(3) = input.substring(6, 7).toInt
    var i = 0
    var ans_board: Array[Array[String]] = Array.ofDim[String](8, 8)
    i = 0
    var turn_c: Char = 'W'
    if(player_turn == 2) {
      turn_c = 'B'
    }
    board.someOneWon(turn_c)
    if(board.isValid(in(0), in(1), in(2), in(3), turn_c, 2))
    {
      if(board.isValidPromotionInput(in(0),in(1),in(2),in(3))){
        //function to get promoted piece in lower case
        if(promotion(0)=='q' || promotion(0) =='r' || promotion(0) == 'n' || promotion(0) =='b')
          board.PromotionValidated(in(0) , in(1), in(2), in(3), promotion(0))
        else
          return Array(Array(""))
      }
      board.move(in(0), in(1), in(2), in(3))

    }
    else
    {
      return Array(Array(""))
    }
    while( i != 8)
    {
      var j = 0
      while(j != 8)
      {
        ans_board(i)(j) = board.chess_board(i)(j) + ""
        j=j+1
      }
      i=i+1
    }
    var c = 0;
    var h=0;
    var k=0;
    while (c!=64) {
      print(ans_board(h)(k))
      c+=1
      k+=1
      if (c%8 == 0) {
        h+=1
        k=0
        println()
      }
    }
    ans_board
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

  def xo_drawer(state:String) : String = {
    val input = readLine()
    input
  }

  def connect4_drawer(state:String) : String = {
    val input = readLine()
    input
  }

  def chess_drawer(state: String) : String ={
    val input = readLine()
    input
  }

  def start () {
    val starter = new StartScreen
    var board: Any = null
    var choice = starter.get_choice() match {
      case "XO" => {
        var obj = new TestGame
        board = new XO_Controller
      }
      case "connect4" => {
        board = new Connect4_Controller
      }
      case "chess" => {
        board = new Chess
      }
      //case _ => board = new XO
      //todo list the other cases
    }
  }



  def main(args: Array[String]): Unit = {
    println("Hello world!")
    start()
  }

}
