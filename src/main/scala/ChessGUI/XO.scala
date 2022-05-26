package ChessGUI

import javafx.scene.paint.ImagePattern
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class XO {
  var player: Int = 1
  def initialize_XO(): GridPane =
  {
    var grid_pane = new GridPane()
    var i: Int = 0
    var j: Int = 0
    while(i < 3) {
      while (j < 3) {
        var rectangle = Rectangle(100, 100)
        rectangle.setStroke(Color.Green)
        rectangle.setStrokeWidth(3)
        grid_pane.add(rectangle, i, j)
        j += 1
      }
      j = 0
      i += 1
    }
    grid_pane.setOnMouseClicked((e) => {
      var raw: Int=  Math.floor(e.getSceneY/100).toInt
      var col: Int = Math.floor(e.getSceneX/100).toInt
      var rectangle = Rectangle(100, 100)
      var obj = new TestGame
      obj.state = raw + " " + col
      var curDir :String =System.getProperty("user.dir")
      println(obj.res + "     jjjjjjjjjjjjjj")
      var img = new Image(curDir+"\\imgs\\"+ obj.res+".PNG")
      rectangle.setFill(new ImagePattern(img))
      grid_pane.add(rectangle, col, raw)
      /*rectangle.setStroke(Color.Green)
      rectangle.setStrokeWidth(3)
      var curDir :String =System.getProperty("user.dir")
      if (player == 1)
        {
          var img = new Image(curDir+"\\imgs\\X1.PNG")
          rectangle.setFill(new ImagePattern(img))
          player = 2
        }
      else {
        var img = new Image(curDir+"\\imgs\\O.jpeg")
        rectangle.setFill(new ImagePattern(img))
        player = 1
      }
      grid_pane.add(rectangle, col, raw)*/
    })
    grid_pane
  }

}
