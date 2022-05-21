package ChessGUI

import scalafx.application.JFXApp
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.{Group, Scene}

class Board {
  var number_of_clickes: Int = 0
  var first_click: String = ""
  var second_click: String = ""
  def get_choice () : Unit = {
    var setImages: SetImages = new SetImages()
    var gridPane= new GridPane
    var app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "Player 1"
        val root = new Group
        scene = new Scene(root, 512, 512, Color.White) {
          var i = 0
          var j = 0
          var white: Boolean = true
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
                  rec = setImages.set(j, i, rec, white)
                  white = !white
                  rec.setOnMouseClicked((e) => {
                    if(number_of_clickes == 1)
                      {
                        second_click = rec.getId
                        var array = get_Input(first_click, second_click)
                        var rectangle1 = Rectangle(64, 64)
                        var rectangle2 = Rectangle(64, 64)
                        rectangle1.setFill(Color.Black)
                        rectangle2.setFill(Color.Black)
                        gridPane.add(rectangle1, array(0), array(1))
                        gridPane.add(rectangle2, array(2), array(3))
                      }
                    else
                      {
                        first_click = rec.getId
                      }
                    number_of_clickes = (number_of_clickes+1)%2
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
          resizable = false
          content = List(gridPane)
        }
      }
    }
    app.main(Array(""))
  }

  def get_Input(first: String, second:String): Array[Int] =
  {
    var t: Array[Int] = Array(0, 1, 0, 2)
    t
  }
}