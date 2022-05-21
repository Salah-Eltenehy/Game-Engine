import scalafx.Includes._
import scalafx.scene._
import scalafx.application.JFXApp
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.event.ActionEvent
import scalafx.scene.paint._
import scalafx.scene.layout.GridPane

class XO_GUI {
  var firstentered = true
  def xo_drawer(state : String, player_Turn : Int) : String = {
    var input:String = ""

    var end = false

    var gridpane = new GridPane
    var app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "XO"
        val root = new Group
        scene = new Scene(root, 330, 370, Color.DarkGray) {
          var i=0
          var j=0
          while(i<3){
            while(j<3) {
              var rec = Rectangle(i * 100, j * 100, 100, 100)
              rec.setStroke(Color.Green)
              rec.setStrokeWidth(3)
              //GridPane.setColumnIndex("")
              gridpane.add(rec, i, j)
              j += 1
            }
            j=0
            i+=1
          }
          resizable= true
          val run_button = new Button("RUN")
          run_button.setLayoutX(220)
          run_button.setLayoutY(330)

          var col_names = new Label("            a                             b                             c")

          col_names.setLayoutX(10)
          col_names.setLayoutY(310)

          var row3_name = new Label("3")
          row3_name.setLayoutX(315)
          row3_name.setLayoutY(45)
          var row2_name = new Label("2")
          row2_name.setLayoutX(315)
          row2_name.setLayoutY(145)
          var row1_name = new Label("1")
          row1_name.setLayoutX(315)
          row1_name.setLayoutY(245)

          val text_field = new TextField()
          text_field.setLayoutX(20)
          text_field.setLayoutY(330)
          text_field.promptText = "Please enter your place"

          run_button.onAction = (e:ActionEvent) => {
            input = text_field.text.apply()
            println("this is your input "+ input)
            stage.hide()
            firstentered = false
            end = true
          }


          text_field.onAction = (e:ActionEvent) => {
            input = text_field.text.apply()
            println("this is your input "+ input)
            stage.hide()
            firstentered = false
            end = true
          }

          content = List(gridpane,run_button, text_field,
            col_names, row1_name, row2_name, row3_name)


          /*var arr : Array[Array[String]];
          println(arr(0)(1))*/
        }
      }
    }
    //app.delayedInit(x)
    if (firstentered) app.main(Array(""))
    else app.stage.show()
    gridpane
    println("i reaced the end")
    input

  }
}


object XO_GUI {
  def main(args:Array[String]) : Unit = {
    var xo = new XO_GUI
    xo.xo_drawer("null",1)
    var xo2 = new XO_GUI
    xo2.xo_drawer("fofo", 2)
    println("hello world")
  }
}


