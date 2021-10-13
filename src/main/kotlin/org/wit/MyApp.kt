package org.wit

import org.wit.styles.Styles
import org.wit.view.allImagesView
import tornadofx.App

class MyApp: App(allImagesView::class, Styles::class) {
    override fun stop() {
        super.stop()
        fileSystem().save()
    }
}