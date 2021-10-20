package org.wit.model

import com.google.gson.stream.JsonWriter

class ImageData constructor(var name: String, val id: Int) {
    override fun toString(): String {
        return "($id) $name"
    }
}