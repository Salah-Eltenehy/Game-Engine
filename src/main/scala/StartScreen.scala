import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.paint.ImagePattern
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint._
import scalafx.scene.shape.Rectangle


class StartScreen{
  def get_choice () : String = {
    var choice : String = ""
    var app = new JFXApp {


      stage = new JFXApp.PrimaryStage {
        val root = new Group
        scene = new Scene(root, 380, 400, Color.Black) {

          var grid_pane = new GridPane
          var curDir :String =System.getProperty("user.dir")
          var chess_rectangle =  Rectangle(100, 100)
          var chess_img: Image = new Image(curDir+"\\imgs\\chess.PNG")
          chess_rectangle.setFill(new ImagePattern(chess_img))
          chess_rectangle.setOnMouseClicked((e) => {
            var alert = new Alert(AlertType.NONE)
            alert.setTitle("Promotion")
            alert.setAlertType(AlertType.INFORMATION)
            alert.setContentText("For Promotion\nQueen: q  Bishop: b\nRook: r  Knight: n\nNote:Case sensitive ")
            alert.show()
            choice = "chess"
            var obj = new Board
            stage.setWidth(610)
            stage.setHeight(550)
            content = List(obj.get_choice())
          })
          chess_rectangle.setX(30)
          chess_rectangle.setY(50)

          var checker_rectangle =  Rectangle(100, 100)
          var checker_img: Image = new Image(curDir+"\\imgs\\checkers.PNG")
          checker_rectangle.setFill(new ImagePattern(checker_img))
          checker_rectangle.setOnMouseClicked((e) => {
            choice = "checker"
            var obj = new Checker
            stage.setWidth(525)
            stage.setHeight(550)
            content = obj.initialize_checker()
          })
          checker_rectangle.setX(250)
          checker_rectangle.setY(50)

          var connect4_rectangle =  Rectangle(100, 100)
          var connect4_img: Image = new Image(curDir+"\\imgs\\connect-four.PNG")
          connect4_rectangle.setFill(new ImagePattern(connect4_img))
          connect4_rectangle.setOnMouseClicked((e) => {
            choice = "connect4"
            stage.setWidth(407)
            stage.setHeight(400)
            var connect4 =new Connect4
            var connect4_rectangle =  Rectangle(392, 360)
            var connect4_img: Image = new Image(curDir+"\\imgs\\connect4_Board.PNG")
            connect4_rectangle.setFill(new ImagePattern(connect4_img))
            content=List(connect4_rectangle,connect4.initialize_Connect4())
          })
          connect4_rectangle.setX(30)
          connect4_rectangle.setY(250)

          var XO_rectangle =  Rectangle(100, 100)
          var XO_img: Image = new Image(curDir+"\\imgs\\XO.PNG")
          XO_rectangle.setFill(new ImagePattern(XO_img))
          XO_rectangle.setOnMouseClicked((e) => {
            choice = "XO"
            var obj = new XO
            stage.title = "Tic Tac Toe"
            stage.setWidth(325)
            stage.setHeight(349)
            content = obj.initialize_XO()
          })
          XO_rectangle.setX(250)
          XO_rectangle.setY(250)

          var back_ground_rectangle =  Rectangle(380, 400)
          var back_ground_img: Image = new Image(curDir+"\\imgs\\background-img.PNG")
          back_ground_rectangle.setFill(new ImagePattern(back_ground_img))
          content = List (back_ground_rectangle, chess_rectangle, connect4_rectangle, XO_rectangle, checker_rectangle )
        }
        resizable = false
      }
    }
    app.main(Array(""))
    choice
  }
}