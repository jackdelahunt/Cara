package org.wit.view

import javafx.geometry.Orientation
import javafx.scene.layout.Priority
import org.wit.*
import tornadofx.*

class AllImagesView : View("Hello TornadoFX") {

    override val root = hbox {
        anchorpane {
            prefWidth = 1080.0
            prefHeight = 720.0

            vbox {
                prefWidth = parent.prefWidth(-1.0)
                prefHeight = parent.prefHeight(-1.0)

                listmenu(theme = "blue") {
                    prefWidth = parent.prefWidth(-1.0)
                    orientation = Orientation.HORIZONTAL

                    item(text = "Groups") {
                        whenSelected {
                            replaceWith(ImageGroupView())
                        }
                    }
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
