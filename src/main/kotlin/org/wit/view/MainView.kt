package org.wit.view

import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
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
                        for (imageData in fileSystem().imageDataArray) {
                            this += ImageIconView(imageData)
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

class ImageIconView constructor(imageData: ImageData): View() {
    override val root = hbox {
        imageview(File("${imageDirectory}/${imageData.name}").toURI().toString())
        borderpane {
            prefWidth = 410.0
            top = textfield(imageData.name) {
                hgrow = Priority.ALWAYS
                useMaxWidth = true
                alignment = Pos.CENTER
            }
            center = button("rename") {
                hgrow = Priority.ALWAYS
                useMaxWidth = true
            }
        }
    }
}

class MainController: Controller() {
}
