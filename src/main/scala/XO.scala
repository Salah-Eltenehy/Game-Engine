import ChessGUI.Drawer
import Root.{Game_Engine, xo_controller}
import javafx.scene.paint.ImagePattern
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class XOBoard {
  def initialize_XO(): GridPane =
  {
    var board = new XO_Controller
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

      var obj = new Drawer
      obj.state = raw + " " + col
      var result = Game_Engine(obj.drawer, xo_controller, board);
      if(!(result.size>1)) return grid_pane
      var i : Int=0
      var j : Int=0
      while(i < 3) {
        while (j < 3) {
          if(!result(i)(j).equals("_")){
            var curDir :String =System.getProperty("user.dir")
            var rectangle = Rectangle(100, 100)
            var img = new Image(curDir+"\\imgs\\"+ result(i)(j)+".PNG")
            rectangle.setFill(new ImagePattern(img))
            grid_pane.add(rectangle, j, i)
          }
          j += 1
        }
        j = 0
        i += 1
      }
    })
    grid_pane
  }

}
