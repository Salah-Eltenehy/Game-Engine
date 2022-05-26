import ChessGUI.TestGame
import Root.{Game_Engine, connect4_controller, xo_controller}
import javafx.scene.paint.ImagePattern
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
class Connect4 {

    def initialize_Connect4(): GridPane =
    {
      var board = new Connect4_Controller
      var grid_pane = new GridPane()
      var i: Int = 0
      var j: Int = 0
      while(i < 7) {
        while (j < 6) {
          var rectangle = Rectangle(56.5, 60)
//          rectangle.setStroke(Color.Green)
//          rectangle.setStrokeWidth(3)
          rectangle.fill = Color(0,0,0,0)
          grid_pane.add(rectangle, i, j)

          j += 1
        }
        j = 0
        i += 1
      }
      grid_pane.setOnMouseClicked((e) => {
        var col: Int = Math.floor(e.getSceneX/57).toInt
        var obj = new TestGame
        obj.state = col+""
        var result = Game_Engine(obj.xo_drawer, connect4_controller, board);
        if(!(result.size>1)) return grid_pane
        var i : Int=0
        var j : Int=0
        while(i < 6) {
          while (j <7) {
            if(!result(i)(j).equals("_")){
              var rectangle = Rectangle(50, 50)
              var curDir :String =System.getProperty("user.dir")
              var img = new Image(curDir+"\\imgs\\"+ result(i)(j)+".PNG")
              rectangle.setFill(new ImagePattern(img))
              grid_pane.add(rectangle,j,5-i)
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
