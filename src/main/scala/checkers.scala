import java.util.NoSuchElementException

class checkers {
    //shape of the game board
    var checkersBoard : Array[Array[String]] = Array(Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_")
    )

  //list of the black pieces
  val blackPieces : List[block]= List(new block("b", 0, 1),
                                      new block("b", 0, 3),
                                      new block("b", 0, 5),
                                      new block("b", 0, 7),
                                      new block("b", 1, 0),
                                      new block("b", 1, 2),
                                      new block("b", 1, 4),
                                      new block("b", 1, 6),
                                      new block("b", 2, 1),
                                      new block("b", 2, 3),
                                      new block("b", 2, 7),
                                      new block("b", 2, 5)
  )

  //list of the white pieces
  val whitePieces : List[block]= List(new block("w", 5, 0),
                                      new block("w", 5, 2),
                                      new block("w", 5, 4),
                                      new block("w", 5, 6),
                                      new block("w", 6, 1),
                                      new block("w", 6, 3),
                                      new block("w", 6, 5),
                                      new block("w", 6, 7),
                                      new block("w", 7, 0),
                                      new block("w", 7, 2),
                                      new block("w", 7, 4),
                                      new block("w", 7, 6)
  )

  //set the pieces on the board
  def setBoard(): Unit = {
    //set all white pieces on board
    whitePieces.foreach(piece => checkersBoard(piece.position._1)(piece.position._2) = piece.color)

    //set all black pieces on board
    blackPieces.foreach(piece => checkersBoard(piece.position._1)(piece.position._2) = piece.color)
  }

  //check if the input is valid
  def validateInput(input: String): Boolean= {
    //check if the input in the availabe range
    var valid : Boolean = input.matches("[a-hA-H][1-8][a-hA-H][1-8]")
    valid
  }

  //turn string input into int co-ordinations
  def translateInput(input : String): ((Int, Int), (Int, Int)) = {
    //if the letters are case small -97, case capital - 65
    var col1: Int = if(input.matches("[a-h]..."))input(0).toInt - 97
                    else input(0).toInt - 65

    var col2: Int = if(input.matches("[a-h]..."))input(2).toInt - 97
                    else input(2).toInt - 65

    //return tuple of the dimension pairs
    ((input(1).toInt - 49, col1), (input(3).toInt - 49, col2))
    }

  def validateMove(start: (Int, Int), end : (Int, Int), turn: Int) : Boolean = {
    println(s"turn is: $turn")
    //get the piece in the start position
    var piece: block = try {
      if (turn == 1) blackPieces.filter(_.position == start).head
      else whitePieces.filter(_.position == start).head
    }
    catch {
      //in case of empty square end the function --> no valid move
      case a: NoSuchElementException => println("you chose empty square"); return false
    }

    //check if there is any pieces in the dist. position
    var dist : List[block] = blackPieces.filter(_.position == end).concat(whitePieces.filter(_.position == end))

    //get horizontal distance for the chosen move
    var x_space : Int = end._2 - start._2

    //get vertical distance for the chosen move
    var y_space : Int = end._1 - start._1

    //boolean determine which player
    var player1 : Boolean = if(turn == 1) true
                            else false

    //check if the  piece is a king piece
    var special : Boolean = if(piece.special) true
                            else false
    //general black piece move (go to the next row and check that dist. square is empty)
    var blackMove : Boolean = player1 && y_space == 1 && dist.isEmpty
    //general white piece move(go to the next row and check that dist. square is empty)
    var whiteMove : Boolean = !player1 && y_space == -1 && dist.isEmpty

    //black piece attack movement constant condition
    var blackAttack : Boolean = player1 && y_space == 2 && dist.isEmpty
    //white piece attack movement constant condition
    var whiteAttack : Boolean = !player1 &&(x_space == 2 || x_space == -2) && y_space == -2

    //special attack constant condition
    var specialBlackAttack: Boolean = player1 && y_space == -2 && dist.isEmpty
    var specialWhiteAttack: Boolean = !player1 && y_space == 2 && dist.isEmpty

    var valid : Boolean = {

      /*one step moves*/
      if (blackMove && !whitePieces.exists(_.position == (start._1+1, start._2+1)) && x_space == -1) true //black to left
      else if(blackMove && !whitePieces.exists(_.position == (start._1+1, start._2-1)) && x_space == 1)true //black to right
      else if(whiteMove && !blackPieces.exists(_.position == (start._1-1, start._2+1)) && x_space == -1) true //white to left
      else if(whiteMove && !blackPieces.exists(_.position == (start._1-1, start._2-1))  && x_space == 1) true //white to right

      /*two level steps*/
      else if(blackAttack && whitePieces.exists(_.position == (start._1+1, start._2-1))  && x_space == -2) true
      else if(blackAttack && whitePieces.exists(_.position == (start._1+1, start._2+1))  && x_space == 2) true
      else if(whiteAttack && blackPieces.exists(_.position == (start._1-1, start._2-1))  && x_space == -2) true
      else if(whiteAttack && blackPieces.exists(_.position == (start._1-1, start._2+1))  && x_space == 2) true

      /*special moves*/
      else if(specialBlackAttack && whitePieces.exists(_.position == (start._1-1, start._2-1)) && x_space == -2) true
      else if(specialBlackAttack && whitePieces.exists(_.position == (start._1-1, start._2+1)) && x_space == 2) true
      else if(specialWhiteAttack && blackPieces.exists(_.position == (start._1+1, start._2-1)) && x_space == -2) true
      else if(specialWhiteAttack && blackPieces.exists(_.position == (start._1+1, start._2+1)) && x_space == 2) true

      //invalid move
      else false
    }

    valid
  }

  def movePiece(start: (Int, Int),end:(Int, Int), player:Int): Unit = {
    /*get the piece to move*/
    println(s"the player now is $player and the piece is found ${blackPieces.exists(_.position == start).toString}")
    var piece:block = if(player == 1) blackPieces.filter(_.position == start).head
                      else whitePieces.filter(_.position == start).head

    /*change piece position*/
    piece.position = end

    /*show the move on the board*/
    checkersBoard(start._1)(start._2) = "_"
    checkersBoard(end._1)(end._2) = piece.color

    //in case it was attack move
    if(math.abs(end._1 - start._1) == 2)
    {
      //empty the attacked square
      checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2) = "_"
      //remove any piece in the square and put it out of the board
      whitePieces.filter(_.position == ((start._1 + end._1) /2, (start._2 + end._2) /2)).foreach(_.position = (-1, -1))
      whitePieces.filter(_.position == ((start._1 + end._1) /2, (start._2 + end._2) /2)).foreach(_.position = (-1, -1))
    }

    /*check if there any king pieces after each move*/
    if(player == 1 && end._1 == 7)piece.special = true
    else if(player == 2 && end._1 == 0) piece.special = true
  }
}
