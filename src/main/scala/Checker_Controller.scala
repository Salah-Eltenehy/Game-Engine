import java.util.NoSuchElementException

class Checker_Controller {
    //shape of the game board
    var checkersBoard : Array[Array[String]] = Array(Array("_","b","_", "b", "_", "b" ,"_", "b"),
                                                     Array("b","_","b", "_", "b", "_" ,"b", "_"),
                                                     Array("_","b","_", "b", "_", "b" ,"_", "b"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("_","_","_", "_", "_", "_" ,"_", "_"),
                                                     Array("w","_","w", "_", "w", "_" ,"w", "_"),
                                                     Array("_","w","_", "w", "_", "w" ,"_", "w"),
                                                     Array("w","_","w", "_", "w", "_" ,"w", "_")
    )

    var availableBlackAttack: Boolean = false //check if there any available black attack moves
    var availableWhiteAttack: Boolean = false //check fi there any available white attack moves

    //print the board
    checkersBoard.foreach{x => x.foreach(print); println()}

  def validateMove(start: (Int, Int), end : (Int, Int), turn: Int) : Boolean = {
    println(s"now turn is: ${turn}")

    var piece: String = checkersBoard(start._1)(start._2) //get the piece the player choose

    //check the input is valid square for each player
    if((turn == 1 && (!piece.equals("b") && !piece.equals("sb"))) || (turn != 1 && (!piece.equals("w") && !piece.equals("sw"))) ) {
      println("you choose empty square or non valid piece"); return false} //end the move with invalid if the player choose wrong square

    //if destination is not empty then the move is not valid
    if(!checkersBoard(end._1)(end._2).equals("_")) {
      println("you chose un-empty square to move to, invalid move"); return false}

    //get horizontal distance for the chosen move
    val x_space : Int = end._2 - start._2
    println(s"x space is: ${x_space}")

    //get vertical distance for the chosen move
    val y_space : Int = end._1 - start._1
    println(s"y space is: ${y_space}")

    //boolean determine which player
    var player1 : Boolean = if(turn == 1) true
                            else false

    println(s"is it player 1: ${player1.toString}")
    println(s"is it player 2: ${(!player1).toString}")

    //check if the  piece is a king piece
    var special : Boolean = if(checkersBoard(start._1)(start._2).equals("sw") || checkersBoard(start._1)(start._2).equals("sb")) true
                            else false
    println(s"this piece is special: $special")

    //general black piece move (go to the next row and check that dist. square is empty)
    var blackMove : Boolean = player1 && y_space == 1 && !availableBlackAttack

    //general white piece move(go to the next row and check that dist. square is empty)
    var whiteMove : Boolean = !player1 && y_space == -1 && !availableWhiteAttack
    println(s"this move is small black move ${blackMove.toString} and is small white move ${whiteMove.toString}")

    //black piece attack movement constant condition
    var blackAttack : Boolean = player1 && y_space == 2 && (x_space == 2 || x_space == -2)

    //white piece attack movement constant condition
    var whiteAttack : Boolean = !player1 && y_space == -2 && (x_space == 2 || x_space == -2)
    println(s"this is black attack move: ${blackAttack.toString}, and white attack move: ${whiteAttack.toString}")

    //special attack constant condition
    var specialBlackAttack: Boolean = player1 && y_space == -2 && (x_space == 2 || x_space == -2)
    var specialWhiteAttack: Boolean = !player1 && y_space == 2 && (x_space == 2 || x_space == -2)

    println(s"this is going to be special move for black piece: ${specialBlackAttack.toString}, and it's special white piece: ${specialWhiteAttack}")

    var valid : Boolean = {

      /*one step moves*/
      if (blackMove && (x_space == -1 || x_space == 1)) true //black to left
      else if(whiteMove && (x_space == -1 || x_space == 1  )) true //white to left

      /*two level steps*/
      else if(blackAttack && checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("w") || checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("sw")) true
      else if(whiteAttack && checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("b") || checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("sb")) true

      /*special moves*/
      else if(specialBlackAttack && checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("w") || checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("sw") ) true
      else if(specialWhiteAttack && checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("b") || checkersBoard((start._1 + end._1) / 2)((start._2 + end._2) / 2).equals("sb") ) true

      //invalid move
      else false
    }

    println(s"now the move is valid: ${valid.toString}")
    valid
  }

  def movePiece(start: (Int, Int),end:(Int, Int), player:Int): Unit = {

    checkersBoard(end._1)(end._2) = checkersBoard(start._1)(start._2)
    checkersBoard(start._1)(start._2) = "_"


    //in case it was attack move
    if(math.abs(end._1 - start._1) == 2)
    {
      //empty the attacked square
      if(checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2).equals("w") || checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2).equals("sw")) availableBlackAttack = false
      if(checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2).equals("b") || checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2).equals("sb")) availableWhiteAttack = false
      checkersBoard((start._1 + end._1) /2)((start._2 + end._2) /2) = "_"

    }

    /*check if there any king pieces after each move*/
    if(player == 1 && end._1 == 7) {checkersBoard(end._1)(end._2) = "sb"}
    else if(player == 2 && end._1 == 0) {checkersBoard(end._1)(end._2)= "sw"}

    checkersBoard.foreach{x => x.foreach(print); println()}

    availableWhiteAttack = if(player ==1 && checkersBoard(end._1)(end._2).equals("b") && end._1 < 7 && end._1 > 0 && end._2 < 7 && end._2 > 0){
      if((checkersBoard(end._1 + 1)(end._2 + 1).equals("w") && checkersBoard(end._1 - 1)(end._2 - 1).equals("_")) ||
         (checkersBoard(end._1 + 1)(end._2 - 1).equals("w") && checkersBoard(end._1 - 1)(end._2 + 1).equals("_")) ||
         (checkersBoard(end._1 - 1)(end._2 + 1).equals("sw") && checkersBoard(end._1 + 1)(end._2 - 1).equals("_")) ||
         (checkersBoard(end._1 - 1)(end._2 - 1).equals("sw") && checkersBoard(end._1 + 1)(end._2 + 1).equals("_"))) true
      else false
    } else false

    availableBlackAttack = if(player !=1 && checkersBoard(end._1)(end._2).equals("w") && end._1 < 7 && end._1 > 0 && end._2 < 7 && end._2 > 0){
      if((checkersBoard(end._1 - 1)(end._2 + 1).equals("b") && checkersBoard(end._1 + 1)(end._2 - 1).equals("_")) ||
         (checkersBoard(end._1 - 1)(end._2 - 1).equals("b") && checkersBoard(end._1 + 1)(end._2 + 1).equals("_")) ||
         (checkersBoard(end._1 + 1)(end._2 + 1).equals("sb") && checkersBoard(end._1 - 1)(end._2 - 1).equals("_")) ||
         (checkersBoard(end._1 + 1)(end._2 - 1).equals("sb") && checkersBoard(end._1 - 1)(end._2 + 1).equals("_"))) true
      else false
    } else false

  }
}




