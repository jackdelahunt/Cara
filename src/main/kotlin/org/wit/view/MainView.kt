package org.wit.view

import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import org.wit.*
import tornadofx.*
import tornadofx.Stylesheet.Companion.button
import tornadofx.Stylesheet.Companion.label
import java.io.File

class MainView : View("Hello TornadoFX") {

    val controller: MainController by inject()

    override val root = hbox {
        anchorpane {
            prefWidth = 740.0
            prefHeight = 540.0

            vbox {
                prefWidth = parent.prefWidth(-1.0)
                prefHeight = parent.prefHeight(-1.0)

                listmenu(theme = "blue") {
                    prefWidth = parent.prefWidth(-1.0)
                    orientation = Orientation.HORIZONTAL

                    item(text = "All Images") {
                        activeItem = this
                    }
                    item(text = "Groups")
                    item(text = "Settings")
                }

                scrollpane {
                    vgrow = Priority.ALWAYS

                    flowpane {
                        for (imageData in controller.filesystem.imageDataArray) {
                            this += imageIcon(imageData)
                        }
                    }
                }

                listmenu(theme = "blue") {
                    prefWidth = parent.prefWidth(-1.0)
                    orientation = Orientation.HORIZONTAL

                    item{}
                }
            }
        }
    }
}

fun imageIcon(imageData: ImageData): HBox {
    val hbox = HBox()
    val button = Button()
    button += ImageView(File("${imageDirectory}/${imageData.name}").toURI().toString())
    hbox += button
    hbox += Label(imageData.name)
    return hbox
}

class MainController: Controller() {
    var filesystem = FileSystem()

    init {
        filesystem.sync()
    }
}
