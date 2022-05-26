class Connect4_Controller {
  var connect_board : Array[Array[String]] = Array(Array("_","_","_","_","_","_","_")
                                                 , Array("_","_","_","_","_","_","_")
                                                 , Array("_","_","_","_","_","_","_")
                                                 , Array("_","_","_","_","_","_","_")
                                                 , Array("_","_","_","_","_","_","_")
                                                 , Array("_","_","_","_","_","_","_"))

  def make_play(col:Int, player:Int) : Unit = {
    println("in make play")
    var not_placed = true
    var c = 0
    while(not_placed) {
      if (connect_board(c)(col) == "_") {
        if (player == 1) connect_board(c)(col) = "R"
        else connect_board(c)(col) = "Y"
        not_placed = false
      }else {
        c+=1
      }
    }
  }
}
