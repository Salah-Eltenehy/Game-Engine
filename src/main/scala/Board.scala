import ChessGUI.TestGame
import Root.{Game_Engine, chess_controller}
import javafx.geometry.Insets
import javafx.scene.layout.{Background, BackgroundFill, CornerRadii}
import javafx.scene.paint.ImagePattern
import scalafx.scene.control.{Label, TextArea}
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class Board {
  var board = new Chess
  var number_of_clickes: Int = 0
  var first_click: String = ""
  var second_click: String = ""
  var gridPane= new GridPane

  var text = new TextArea()
  def get_choice () : GridPane = {
    var label = new Label(("Promotion"))
    text.setMaxWidth(95)
    text.setMaxHeight(30)
    //text.setText("h")
    gridPane.setBackground(new Background((new BackgroundFill(Color.WhiteSmoke, CornerRadii.EMPTY, Insets.EMPTY))))
    gridPane.add(label, 8, 0)
    gridPane.add(text, 8, 1)
          var i = 0
          var j = 0
          var white: Boolean = false
          while(i < 8)
            {
              while (j < 8)
                {
                  var rec = Rectangle(64, 64)
                  var rec2 = Rectangle(64, 64)
                  if(white)
                    rec2.fill = Color.Wheat
                  else
                    rec2.fill = Color.Lavender
                  var curDir :String =System.getProperty("user.dir")
                  if (j == 0 || j == 1 )
                    {
                      var img = new Image(curDir+"\\imgs\\black\\"+board.chess_board(j)(i)+".png")
                      rec.setFill(new ImagePattern((img)))
                    }else if(j == 6 || j == 7){
                    var img = new Image(curDir+"\\imgs\\white\\"+board.chess_board(j)(i)+".png")
                    rec.setFill(new ImagePattern((img)))
                  }
                  else if (board.chess_board(j)(i)=='.') {
                    rec.fill =Color.Wheat
                  }
                  else if(board.chess_board(j)(i)=='-') {
                    rec.fill = Color.Lavender
                  }
                  white = !white
                  rec.setOnMouseClicked((e) => {

                  })
                  GridPane.setColumnIndex(rec, j)
                  GridPane.setRowIndex(rec, i)
                  gridPane.add(rec2, i, j)
                  gridPane.add(rec, i, j)
                  j = j+1
                }
              white = !white
              j = 0
              i = i+1
            }

    gridPane.setOnMouseClicked((e) => {
      var raw: Int=  Math.floor(e.getSceneY/64).toInt
      var col: Int = Math.floor(e.getSceneX/64).toInt
      println(s"raw: $raw col: $col")
      if (number_of_clickes == 1)
      {
        number_of_clickes = 0
        second_click = raw + " "+ col
        var obj = new TestGame
        obj.state = first_click + " " + second_click + " " + text.getText
        var result = Game_Engine(obj.xo_drawer, chess_controller,board);
        if(!(result.size>1)) return gridPane
        var i : Int=0
        var j : Int=0
        while(i < 8) {
          while (j < 8) {
            var curDir :String =System.getProperty("user.dir")
            var rectangle = Rectangle(64, 64)
            var backRectange= Rectangle(64,64)
            var t = Rectangle(64, 64)
            if ((i+j)%2 == 1)
              t.fill = Color.Wheat
            else
              t.fill = Color.Lavender
            if(result(i)(j).charAt(0)=='.'){
              backRectange.fill =Color.Wheat
              rectangle.fill = Color.Wheat
              gridPane.add(backRectange,j,i)
            }else if( result(i)(j).charAt(0)=='-'){
              backRectange.fill = Color.Lavender
              rectangle.fill = Color.Lavender
              gridPane.add(backRectange,j,i)
            }
            else if (result(i)(j).charAt(0).isLower) {
              var img = new Image(curDir + "\\imgs\\white\\" + result(i)(j) + ".png")
              rectangle.setFill(new ImagePattern(img))
              gridPane.add(t, j, i)
              gridPane.add(rectangle, j, i)
            } else if (!result(i)(j).charAt(0).isLower) {
              var img = new Image(curDir + "\\imgs\\black\\" + result(i)(j) + ".png")
              rectangle.setFill(new ImagePattern(img))
              gridPane.add(t, j, i)
              gridPane.add(rectangle, j, i)
            }
            j += 1
          }
          j = 0
          i += 1
        }
      }
      else
      {
        first_click = raw + " " + col
        number_of_clickes = 1
      }
    })
    gridPane
  }



  def test(): Unit =
  {
    /**the next code just for  testing*/
    var rectangle1 = Rectangle(64, 64)
    var rectangle2 = Rectangle(64, 64)
    rectangle1.setFill(Color.Red)
    rectangle2.setFill(Color.Black)
    /*send coordinates to game_engine here
    */
    var t1 = first_click.split(" ")
    gridPane.add(rectangle1, Integer.parseInt(t1(1)), Integer.parseInt(t1(0)))
    var t2 = second_click.split(" ")
    gridPane.add(rectangle2, Integer.parseInt(t2(1)), Integer.parseInt(t2(0)))
    first_click = ""
    second_click = ""
  }
  def get_Input(first: String, second:String): Array[Int] =
  {
    var arr1: Array[String] = first.split(" ")
    var t: Array[Int] = Array(0, 1, 0, 2)
    t
  }
}