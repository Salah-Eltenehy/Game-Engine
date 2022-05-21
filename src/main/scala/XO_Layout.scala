import scalafx.Includes._
import scalafx.scene._
import scalafx.application.JFXApp
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.event.ActionEvent
import scalafx.scene.paint._

class XO_Layout {

  //def xo_drawer(){
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "XO"
        val root = new Group
        scene = new Scene(root, 330, 350, Color.DarkGray) {
          /*var board = Array(Rectangle(0, 0, 100, 100),  Rectangle(100, 0, 200, 100),     Rectangle(200, 0, 300, 100)
                           ,Rectangle(0, 100, 100, 200), Rectangle(100, 100, 200, 200),   Rectangle(200, 100, 300, 200)
                           ,Rectangle(0, 200, 100, 300), Rectangle(100, 200, 200, 300),  Rectangle(200, 200, 300, 300))*/
          val rect_a3 = Rectangle(0, 0, 100, 100) //.setStroke(Color.Green)
          rect_a3.setStroke(Color.Green)
          rect_a3.setStrokeWidth(5)
          val rect_b3 = Rectangle(100, 0, 200, 100)
          rect_b3.setStroke(Color.Green)
          rect_b3.setStrokeWidth(5)
          val rect_c3 = Rectangle(200, 0, 100, 100)
          rect_c3.setStroke(Color.Green)
          rect_c3.setStrokeWidth(5)
          val rect_a2 = Rectangle(0, 100, 100, 100)
          rect_a2.setStroke(Color.Green)
          rect_a2.setStrokeWidth(5)
          val rect_b2 = Rectangle(100, 100, 200, 100)
          rect_b2.setStroke(Color.Green)
          rect_b2.setStrokeWidth(5)
          val rect_c2 = Rectangle(200, 100, 100, 100)
          rect_c2.setStroke(Color.Green)
          rect_c2.setStrokeWidth(5)
          val rect_a1 = Rectangle(0, 200, 100, 100)
          rect_a1.setStroke(Color.Green)
          rect_a1.setStrokeWidth(5)
          val rect_b1 = Rectangle(100, 200, 200, 100)
          rect_b1.setStroke(Color.Green)
          rect_b1.setStrokeWidth(5)
          val rect_c1 = Rectangle(200, 200, 100, 100)
          rect_c1.setStroke(Color.Green)
          rect_c1.setStrokeWidth(5)
          val run_button = new Button("RUN")
          run_button.setLayoutX(220)
          run_button.setLayoutY(320)

          var col_names = new Label("            a                             b                             c")

          col_names.setLayoutX(10)
          col_names.setLayoutY(300)

          var row3_name = new Label("3")
          row3_name.setLayoutX(310)
          row3_name.setLayoutY(45)
          var row2_name = new Label("2")
          row2_name.setLayoutX(310)
          row2_name.setLayoutY(145)
          var row1_name = new Label("1")
          row1_name.setLayoutX(310)
          row1_name.setLayoutY(245)

          val text_field = new TextField()
          text_field.setLayoutX(20)
          text_field.setLayoutY(320)

          content = List(rect_a3, rect_b3, rect_c3,
            rect_a2, rect_b2, rect_c2,
            rect_a1, rect_b1, rect_c1,
            run_button, text_field,
            col_names, row1_name, row2_name, row3_name)
        }
      }
    }
  //}
}

object XO_Layout {
  var xo = new XO_Layout
  xo.app.main(Array(""))
  def main(args:Array[String]) : Unit = {
    println("hello world")
  }
}
