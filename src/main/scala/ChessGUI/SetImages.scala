package ChessGUI

import javafx.scene.paint.ImagePattern
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class SetImages {
  def set(raw: Int, col: Int, rectangle: Rectangle, color: Boolean): Rectangle =
  {
    var curDir :String =System.getProperty("user.dir")
    var path: String = curDir.concat("\\imgs");
    if (raw == 0)
      {
        path = path + "\\black\\"+col+".png"
        var img: Image = new Image(path)
        rectangle.setFill(new ImagePattern(img))
      }
    else if(raw == 1)
      {
        path = path + "\\black\\"+"soldier.png"
        var img: Image = new Image(path)
        rectangle.setFill(new ImagePattern(img))
      }
    else if(raw == 6) {
      path = path + "\\white\\"+"soldier.png"
      var img: Image = new Image(path)
      rectangle.setFill(new ImagePattern(img))
    }
    else if(raw == 7) {
      path = path + "\\white\\"+col+".png"
      var img: Image = new Image(path)
      rectangle.setFill(new ImagePattern(img))
    } else {
      if(color)
        rectangle.fill = Color.Wheat
      else
        rectangle.fill = Color.Lavender
    }
    rectangle
  }

}
