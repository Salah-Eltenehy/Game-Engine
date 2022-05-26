class XO_Controller {
  var xo_board : Array[Array[String]] = Array(Array("_","_","_")
                                            , Array("_","_","_")
                                            , Array("_","_","_"))

  def make_play(i:Int, j:Int, player:Int) : Unit = {
    println("in make play")
    if (player == 1) this.xo_board(i)(j) = "X"
    else this.xo_board(i)(j) = "O"
  }
}
