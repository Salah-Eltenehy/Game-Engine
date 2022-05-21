import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight}

class Test extends Application{
  override def start(stage: Stage): Unit = {
    var text = new Label("Hi")
    text.setFont((Font.font(null, FontWeight.Bold, 15)))
    text.setTextFill(Color.RED)
    text.setScaleX(150)
    text.setScaleY(50)
    var event = new EventHandler[MouseEvent] {
      override def handle(t: MouseEvent): Unit = {
        println("Done!!!")
      }
    }
    text.addEventFilter(MouseEvent.MOUSE_CLICKED, event)

    var scene = new Scene((text), 600, 600)
    scene.setFill(Color.Lavender)
    stage.setTitle("Hi")
    stage.setScene(scene)
    stage.show()
  }
}
