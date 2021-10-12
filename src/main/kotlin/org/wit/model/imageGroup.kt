package org.wit.model

class ImageGroup constructor(var name: String){
    val ids = ArrayList<Int>()

    fun addImageData(imageData: ImageData) {
        for (id: Int in ids) {
            if(id == imageData.id) return
        }

        ids.add(imageData.id)
    }
}