import javafx.scene.paint.ImagePattern
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint._
import scalafx.scene.shape.Rectangle


class StartScreen {
  def get_choice () : String = {
    var choice : String = ""
    var app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "Start Screen :)"
        val root = new Group
        scene = new Scene(root, 380, 400, Color.Black) {
          var grid_pane = new GridPane

          var chess_rectangle =  Rectangle(100, 100)
          var chess_img: Image = new Image("chess.PNG")
          chess_rectangle.setFill(new ImagePattern(chess_img))
          chess_rectangle.setOnMouseClicked((e) => {
            choice = "chess"
            stage.close()
          })
          chess_rectangle.setX(30)
          chess_rectangle.setY(50)

          var checker_rectangle =  Rectangle(100, 100)
          var checker_img: Image = new Image("checkers.PNG")
          checker_rectangle.setFill(new ImagePattern(checker_img))
          checker_rectangle.setOnMouseClicked((e) => {
            choice = "checker"
            stage.close()
          })
          checker_rectangle.setX(250)
          checker_rectangle.setY(50)

          var connect4_rectangle =  Rectangle(100, 100)
          var connect4_img: Image = new Image("connect-four.PNG")
          connect4_rectangle.setFill(new ImagePattern(connect4_img))
          connect4_rectangle.setOnMouseClicked((e) => {
            choice = "connect4"
            stage.close()
          })
          connect4_rectangle.setX(30)
          connect4_rectangle.setY(250)

          var XO_rectangle =  Rectangle(100, 100)
          var XO_img: Image = new Image("XO.PNG")
          XO_rectangle.setFill(new ImagePattern(XO_img))
          XO_rectangle.setOnMouseClicked((e) => {
            choice = "XO"
            stage.close()
          })
          XO_rectangle.setX(250)
          XO_rectangle.setY(250)

          var back_ground_rectangle =  Rectangle(380, 400)
          var back_ground_img: Image = new Image("background-img.PNG")
          back_ground_rectangle.setFill(new ImagePattern(back_ground_img))
          content = List (back_ground_rectangle, chess_rectangle, connect4_rectangle, XO_rectangle, checker_rectangle )
        }
      }
    }
    app.main(Array(""))
    choice
  }
}