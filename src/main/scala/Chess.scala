
class Chess {
  var chess_board :Array[Array[Char]]= Array(Array('R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'),
                                              Array('P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
                                              Array('-', '.', '-', '.', '-', '.', '-', '.'),
                                              Array('.', '-', '.', '-', '.', '-', '.', '-'),
                                              Array('-', '.', '-', '.', '-', '.', '-', '.'),
                                              Array('.', '-', '.', '-', '.', '-', '.', '-'),
                                              Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
                                              Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))

  var validPromote = false
  var last_in0 : Int
  var last_in1 : Int
  var last_in2 : Int
  var last_in3 : Int
  //get square color from its index in the array
  def getColor(row: Int, col: Int): Char = if ((row + col) % 2 == 1) 'W'
                  else 'B'

  //change the turn Hypothetically if W which means white we change it to B which means black and vise versa
  def nextTurn(turn: Char): Char = if (turn == 'W') 'B'
  else 'W'
  //get square color from its index in the array
  def isEmpty(row:Int,col:Int) : Boolean ={
    this.chess_board(row)(col) == '.' || this.chess_board(row)(col) == '-'
  }

  //if the letter is lowercase we know its white other wise its black//if the letter is lowercase we know its white other wise its black
  def getSide(piece: Char): Char = {
    if ('a' <= piece && piece <= 'z') return 'W'
    'B'
  }
  //we check if the input of the promotion is right so we can call our doPromotion function
  def isValidPromotionInput(row : Int , col : Int ,toRow :Int, toCol :Int):Boolean =
  {
    val piece:Char =this.chess_board(row)(col)
    if(getLower(piece) == 'p' &&( (getSide(piece)=='W' && toRow==0)||getSide(piece)=='B' && toRow==7))
      {
        return true;
        //ask engine which piece to promote
      }
      return false
  }
  def PromotionValidated(row : Int , col : Int ,toRow :Int, toCol :Int,promote : Char ):Unit ={
    doPromotion(row,col,toRow,toCol,promote)
  }
  //match case for promotion pieces
  def promotionPiece(promotion : Char) :Char =promotion match {
    case 'r' => 'R'
    case 'n' => 'N'
    case 'b' => 'B'
    case 'q' => 'Q'
  }
  //we check if the isValidPromotionInput true then we can safely do our promotion
  def doPromotion( row:Int, col:Int,toRow:Int, toCol:Int, promote:Char) :Unit =
  {
    val piece : Char =this.chess_board(row)(col)
    var promotion:Char =promotionPiece(promote)

    if(getSide(piece)=='W')
      promotion=getLower(promotion)
    this.chess_board(row)(col)=promotion
  }

  //void function take 4 arguments from coordinate and to coordinate
  //and simply move our piece as we already made sure it’s a valid move
  def move (row:Int, col:Int,toRow:Int, toCol:Int) :Unit ={
   // isValidPromotionInput(row, col, toRow, toCol)

    this.chess_board(toRow)(toCol) = this.chess_board(row)(col)
    this.chess_board(row)(col) = if (getColor(row, col) == 'W') '.'
      else '-'
  }

  //check if the pawn hasn't moved yet
  def notMovedPawn(row: Int, col: Int, turn: Char): Boolean = (turn == 'W' && row == 6) || (turn == 'B' && row == 1)

  //we get lower case if its upper case if not we get it as it's//we get lower case if its upper case if not we get it as it's
  def getLower(c: Char): Char = if (c >= 'a' && c <= 'z') c
                                  else  (c - 'A' + 'a').toChar

  def checkPawnMove(row:Int,col:Int,toRow:Int, toCol:Int,turn:Char):Boolean={
    val sign= turn match {
      case 'W' => -1
      case 'B' => 1
    }
    // one forward move
    var one :Boolean =toRow==row+1*sign&&toCol==col&& isEmpty(toRow,toCol)
    var two :Boolean =toRow==row+1*sign && Math.abs(toCol-col)==1 && !isEmpty(toRow,toCol) &&getSide(this.chess_board(toRow)(toCol)) != getSide(this.chess_board(row)(col))
    var three :Boolean = toRow==row+2*sign && toCol==col&&notMovedPawn(row,col,turn) && isEmpty(toRow,toCol) && isEmpty(toRow-sign,toCol)
     one||  //or going to kill an enemy
       two|| //or two moves first move
      three
  }
  //we check if it move on same column or same row
  def checkRookMove(row:Int,col:Int,toRow:Int, toCol:Int):Boolean= {
    if(toRow!=row&&toCol!=col)
      return false
    val dx : Array[Int]= Array(1,-1,0,0)
    val dy : Array[Int]= Array(0,0,1,-1)
    var j :Int=0
    var i :Int=1
    while (j<4){
      while(i<=8)
        {
         if(row+i*dx(j)==toRow && col+i*dy(j)==toCol) {
            return true
         }
          if( !(row+i*dx(j) <=7 && row+i*dx(j) >=0 && col+i*dy(j)<=7&& col+i*dy(j)>=0)
            || (!isEmpty(row+i*dx(j),col+i*dy(j))))
            i=9 // to break
          i=i+1
        }
      i=1
      j=j+1
    }
    false
  }

  //we check if it move on different row and different col with same difference between each other
  def checkBishopMove(row:Int,col:Int,toRow:Int, toCol:Int):Boolean= {
    if(Math.abs(toRow-row) != Math.abs(toCol-col))
      return false
    val dx : Array[Int]= Array(1,1,-1,-1)
    val dy : Array[Int]= Array(1,-1,1,-1)
    var j :Int=0
    var i :Int=1
    while (j<4){
      while(i<=8)
      {
        if(row+i*dx(j)==toRow && col+i*dy(j)==toCol)
          return true
        if(!(row+i*dx(j) <=7 && row+i*dx(j) >=0 && col+i*dy(j)<=7&&col+i*dy(j)>=0) ||
          (!isEmpty(row+i*dx(j),col+i*dy(j))))
          i=9 // to break
        i=i+1
      }
      i=1
      j=j+1
    }
    false
  }
  //we see if it move 2 forward or 2 backward and one right or one left
  //or we see if it move 2 right or 2 left and one left or one right
  def checkKnightMove(row:Int,col:Int,toRow:Int, toCol:Int):Boolean= {
    (Math.abs(toRow-row)==2 && Math.abs(toCol-col)==1) || (Math.abs(toRow-row)==1 && Math.abs(toCol-col)==2)
  }
  //we use our function for rook and bishop to check the queen movement as it is a combination of both
  def checkQueenMove(row:Int,col:Int,toRow:Int,toCol:Int) :Boolean =
  {
    checkBishopMove(row, col, toRow, toCol)||checkRookMove(row, col, toRow, toCol)
  }

  //we see if the king want to move any direction for one square any direction
  def checkKingMove(row :Int,col :Int,toRow:Int ,toCol:Int):Boolean ={
    Math.abs(toRow-row)<=1 && Math.abs(toCol-col)<=1
  }
  //apply check validation on each piece accordingly
  def checkValidPiece(piece : Char ,row :Int,col:Int,toRow:Int,toCol:Int,turn:Char) :Boolean =piece match {
    case 'p' =>
      checkPawnMove( row, col, toRow, toCol, turn)
    case 'k' =>
      checkKingMove( row, col, toRow, toCol)
    case 'n' =>
      checkKnightMove( row, col, toRow, toCol)
    case 'b' =>
      checkBishopMove( row, col, toRow, toCol)
    case 'r' =>
      checkRookMove( row, col, toRow, toCol)
    case 'q' =>
      checkQueenMove( row, col, toRow, toCol)
    case _ =>
      false
  }
  //we check if the move is valid (legal)
  //if we are catching empty index then it is not valid
  //then we check the possible move for this piece using check functions
  //in case of ischeck we put mainturn=0 as we don't want to do the stuff in this condition in that case
  //when the user input mainturn =2 and from program will be 1 or 0
  def isValid(row :Int , col :Int ,toRow :Int,toCol :Int,turn:Char,mainTurn:Int) : Boolean ={

    if (isEmpty(row, col)) return false

    val piece:Char=this.chess_board(row)(col)
    val toCell:Char=this.chess_board(toRow)(toCol)

    if ((getSide(piece) != turn) || (!isEmpty( toRow, toCol) && (getSide(toCell) == turn))) return false

    //check for check king
    if(mainTurn>=1)
      {
        //apply the move hypothetically and check for check king
        getColor(row,col) match {
          case 'W' =>  this.chess_board(row)(col) = '-'
          case 'B' =>  this.chess_board(row)(col) = '.'
        }
        this.chess_board(toRow)(toCol) =piece
        val flag :Boolean= isCheck(turn)
        this.chess_board(toRow)(toCol)=toCell
        this.chess_board(row)(col)=piece
        if(flag)
          return false
      }
    checkValidPiece(getLower(piece),row, col, toRow, toCol, turn)
  }

  //first we search for the king place
  //then we see all possible move that threaten the king
  def isCheck(turn :Char) : Boolean ={
    //index where the king is
    var rowK:Int =0
    var colK:Int =0

    var i:Int=0
    var j:Int=0
    //find the king
    while (i<8){
      while (j<8){
        if(!isEmpty(i,j) && getLower(this.chess_board(i)(j))=='k' && getSide(this.chess_board(i)(j))==turn){
          rowK=i
          colK=j
          i=9 //to break
          j=9
        }
        j=j+1
      }
      j=0
      i=i+1
    }
    i=0
    var rowX:Int =0
    var colX:Int =0
    var flag :Boolean=false
    //check all possible move from any piece to the king
    while (colX <= 7){
      while (rowX <= 7){
        flag=flag | isValid (rowX,colX,rowK,colK,nextTurn(turn), 0)
        rowX=rowX+1
      }
      rowX=0
      colX=colX+1
    }
    colX=0
    flag
  }
  //we see all the possible moves for all the pieces on the grid if no move are possible then we return true
  //no move are there
  //if commented out the lines in comment will show possible moves
  def cantMove(turn:Char):Boolean={
    //printf("Valid Moves:\n");
    var i:Int=0
    var j:Int=0
    var k:Int=0
    var l:Int=0

    var flag :Boolean=true
    while (i<8){
      while (j<8){
        while (k<8){
          while (l<8){
            val currentF=isValid(i,j,k,l,turn,1)
//            if(currentF)
//                printf("%d %d %d %d\n", i,j,k,l)
            flag= flag & !currentF
            l=l+1
          }
          l=0
          k=k+1
        }
        k=0
        j=j+1
      }
      j=0
      i=i+1
    }
    i=0
    flag
  }

  def someOneWon(turn :Char): Unit ={
      if(cantMove(turn)){
        if (isCheck( turn))
          printf("Checkmate!\n%s wins!", if (turn == 'W') "Black" else "White")
        else printf("Stalemate!\nThe game is tied!")
      }
  }
}
