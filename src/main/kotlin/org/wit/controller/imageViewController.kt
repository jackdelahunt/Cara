package org.wit.controller

import org.wit.fileSystem
import tornadofx.Controller

class ImageViewController: Controller() {
    fun rename(id: Int, to: String) {
        fileSystem().rename(id, to)
    }
}