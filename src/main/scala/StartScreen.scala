import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.paint.ImagePattern
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint._
import scalafx.scene.shape.Rectangle


class StartScreen{
  //var label = new Label()

  def get_choice () : String = {
    var choice : String = ""
    var app = new JFXApp {
      var startButton = new Button("Start Screen")

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
            var obj = new ChessBoard
            stage.title = choice
            stage.setWidth(610)
            stage.setHeight(550)
            startButton.setLayoutX(515)
            startButton.setLayoutY(130)
            //        label.setLayoutX(515)
            //      label.setLayoutY(150)
            content = List(obj.get_choice(), startButton)
          })
          chess_rectangle.setX(30)
          chess_rectangle.setY(50)

          var checker_rectangle =  Rectangle(100, 100)
          var checker_img: Image = new Image(curDir+"\\imgs\\checkers.PNG")
          checker_rectangle.setFill(new ImagePattern(checker_img))
          checker_rectangle.setOnMouseClicked((e) => {
            choice = "checker"
            stage.title = choice
            var obj = new CheckerBoard
            stage.setWidth(610)
            stage.setHeight(550)
            startButton.setLayoutX(515)
            startButton.setLayoutY(50)

            content = List(obj.initialize_checker(), startButton)
          })
          checker_rectangle.setX(250)
          checker_rectangle.setY(50)

          var connect4_rectangle =  Rectangle(100, 100)
          var connect4_img: Image = new Image(curDir+"\\imgs\\connect-four.PNG")
          connect4_rectangle.setFill(new ImagePattern(connect4_img))
          connect4_rectangle.setOnMouseClicked((e) => {
            choice = "connect4"
            stage.title = choice
            stage.setWidth(490)
            stage.setHeight(400)
            var connect4 =new Connect4Board
            var connect4_rectangle =  Rectangle(392, 360)
            var connect4_img: Image = new Image(curDir+"\\imgs\\connect4_Board.PNG")
            connect4_rectangle.setFill(new ImagePattern(connect4_img))
            startButton.setLayoutX(400)
            startButton.setLayoutY(50)

            content=List(connect4_rectangle,connect4.initialize_Connect4(), startButton)
          })
          connect4_rectangle.setX(30)
          connect4_rectangle.setY(250)

          var XO_rectangle =  Rectangle(100, 100)
          var XO_img: Image = new Image(curDir+"\\imgs\\XO.PNG")
          XO_rectangle.setFill(new ImagePattern(XO_img))
          XO_rectangle.setOnMouseClicked((e) => {
            choice = "XO"
            var obj = new XOBoard
            stage.title = "Tic Tac Toe"
            stage.setWidth(400)
            stage.setHeight(349)
            startButton.setLayoutX(310)
            startButton.setLayoutY(50)
            content = List(obj.initialize_XO(), startButton)
          })
          XO_rectangle.setX(250)
          XO_rectangle.setY(250)
          startButton.setOnAction(e => {
            Root.player = 1
            stage.setWidth(380)
            stage.setHeight(400)
            content = List (back_ground_rectangle, chess_rectangle, connect4_rectangle, XO_rectangle, checker_rectangle )
          })
          ///////
          //
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