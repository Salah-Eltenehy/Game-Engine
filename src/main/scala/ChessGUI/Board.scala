package ChessGUI

import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class Board {
  var number_of_clickes: Int = 0
  var first_click: String = ""
  var second_click: String = ""
  var gridPane= new GridPane
  def get_choice () : GridPane = {
    var setImages: SetImages = new SetImages()
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
  //                test(rec)
                  gridPane.setOnMouseClicked((e) => {
                    var raw: Int=  Math.floor(e.getSceneY/64).toInt
                    var col: Int = Math.floor(e.getSceneX/64).toInt
                    println(s"raw: $raw col: $col number of clicks: $number_of_clickes")
                    if (number_of_clickes == 1)
                      {
                        number_of_clickes = 0
                        second_click = raw + " " + col
                        test()
                      }
                    else
                      {
                        first_click = raw + " " + col
                        number_of_clickes = 1
                      }
                      //number_of_clickes = (number_of_clickes+1)%2
                  })
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
    //println(Integer.parseInt("5.5") + "                      hhhhhhhhhhhhhhhh")
    gridPane.add(rectangle1, Integer.parseInt(t1(1)), Integer.parseInt(t1(0)))
    var t2 = second_click.split(" ")
    //println(t1(0) + "    " + t1(1) + "    " + t2(0) + "   "  + t2(1))
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