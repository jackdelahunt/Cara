package org.wit.view

import javafx.geometry.Orientation
import javafx.scene.layout.Priority
import org.wit.fileSystem
import tornadofx.*

class ImageGroupView : View("Hello TornadoFX") {

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

                    item(text = "Images") {
                        whenSelected {
                            replaceWith(AllImagesView())
                        }
                    }
                    item(text = "Settings")
                }

                button("Create group") {
                    setOnAction {
                        find<CreateGroupView>().openModal()
                    }
                }

                scrollpane {
                    vgrow = Priority.ALWAYS
                    flowpane {
                        for (imageGroup in fileSystem().imageGroupArray) {
                            add(GroupPreview(imageGroup))
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
