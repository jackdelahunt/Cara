package org.wit.model

import com.google.gson.stream.JsonWriter

class ImageData constructor(var name: String, val id: Int) {
    fun toJson(writer: JsonWriter) {
        writer.beginObject()
        writer.name("name").value(name)
        writer.name("id").value(id)
        writer.endObject()
    }

    override fun toString(): String {
        return "($id) $name"
    }
}