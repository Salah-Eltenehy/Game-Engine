import ChessGUI.Drawer
import Root.{Game_Engine, checkersController}
import javafx.scene.paint.ImagePattern
import scalafx.scene.image.Image
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class CheckerBoard {
  var number_of_clickes: Int = 0
  var first_click: String = ""
  var second_click: String = ""

  var board = new Checker_Controller
  var gridPane= new GridPane
  def initialize_checker(): GridPane = {
    var i = 0
    var j = 0
    var white: Boolean = true
    while(i < 8)
    {
      while (j < 8)
      {
        var rec2 = Rectangle(64, 64)
        var rec = Rectangle(64, 64)
        if(white)
          {
            rec2.fill = Color.White
          }
        else
          rec2.fill = Color.Gray
        white = !white
        rec = setImages(rec, j, i, white)
        gridPane.add(rec2, i, j)
        gridPane.add(rec, i, j)
        j = j+1
      }
      white = !white
      j = 0
      i = i+1
    }
    gridPane.setOnMouseClicked((e) => {
      var raw: Int=  Math.floor(e.getSceneY/64).toInt
      var col: Int = Math.floor(e.getSceneX/64).toInt
      println(s"raw: $raw col: $col")
      if (number_of_clickes == 1)
      {
        number_of_clickes = 0
        second_click = raw + " "+ col
        var obj = new Drawer
        obj.state = first_click + " " + second_click
        var result = Game_Engine(obj.drawer, checkersController,board);
        if(!(result.size>1)) return gridPane
        var i : Int=0
        var j : Int=0
        while(i < 8) {
          while (j < 8) {
            var curDir :String =System.getProperty("user.dir")
            if(!result(i)(j).equals("_")){
              var rectangle = Rectangle(64, 64)
              var img = new Image(curDir+"\\imgs\\"+ result(i)(j)+".png")
              rectangle.setFill(new ImagePattern(img))
              gridPane.add(rectangle, j, i)
            }else
              {
                if((i+j)%2==0) {
                  var rectangle = Rectangle(64, 64)
                  rectangle.fill = Color.White
                  gridPane.add(rectangle, j, i)
                }else{
                  var rectangle = Rectangle(64, 64)
                  rectangle.fill = Color.Gray
                  gridPane.add(rectangle, j, i)
                }
              }
            j += 1
          }
          j = 0
          i += 1
        }
      }
      else
      {
        first_click = raw + " " + col
        number_of_clickes = 1
      }
    })
    gridPane
  }
  def setImages(rectangle: Rectangle, raw: Int, col: Int, color: Boolean): Rectangle = {
    var curDir :String =System.getProperty("user.dir")
    if (raw == 0 || raw == 2)
      {
        if (col%2== 1)
        {
          var img = new Image(curDir+"\\imgs\\b.png")
          rectangle.setFill(new ImagePattern(img))
        }
        else {
          if (!color)
            rectangle.fill = Color.White
          else
            rectangle.fill = Color.Gray
        }
      }
    else if(raw == 1)
      {
        if (col%2== 0)
        {
          var img = new Image(curDir+"\\imgs\\b.png")
          rectangle.setFill(new ImagePattern(img))
        }
        else {
          if (!color)
            rectangle.fill = Color.White
          else
            rectangle.fill = Color.Gray
        }
      }
    else if(raw == 5 || raw ==7)
    {
      if (col%2== 0)
      {
        var img = new Image(curDir+"\\imgs\\w.png")
        rectangle.setFill(new ImagePattern(img))
      }
      else {
        if (!color)
          rectangle.fill = Color.White
        else
          rectangle.fill = Color.Gray
      }
    }
    else if(raw == 6)
      {
        if (col%2== 1)
        {
          var img = new Image(curDir+"\\imgs\\w.png")
          rectangle.setFill(new ImagePattern(img))
        }
        else {
          if (!color)
            rectangle.fill = Color.White
          else
            rectangle.fill = Color.Gray
        }
      }
    else {
      if (!color)
        rectangle.fill = Color.White
      else
        rectangle.fill = Color.Gray
    }
    rectangle
  }

}
