package org.wit

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.wit.model.ImageData
import java.io.*


val userHome = System.getProperty("user.home")!!
val caraDirectory = "$userHome/Cara"
val imageDirectory = "$caraDirectory/images"
val dataDirectory = "$caraDirectory/data"

private var fileSystem: FileSystem? = null;

fun fileSystem(): FileSystem {
    if(fileSystem == null) {
        fileSystem = FileSystem()
        fileSystem!!.sync()
    }

    return fileSystem!!;
}

class FileSystem {
    var imageDataArray = ArrayList<ImageData>()

    init { // called after empty constructor
        val caraDirectoryFile = File(caraDirectory)
        if(!caraDirectoryFile.exists()) {
            caraDirectoryFile.mkdir()
        }

        val imageDirectoryFile = File(imageDirectory)
        if(!imageDirectoryFile.exists()) {
            imageDirectoryFile.mkdir()
        }

        val dataDirectoryFile = File(dataDirectory)
        if(!dataDirectoryFile.exists()) {
            dataDirectoryFile.mkdir()
        }
    }

    public fun sync() {
        if(File("$dataDirectory/index.json").exists()) {
            val indexedImages = loadFromIndex()
            val imageNameOnDisk = readImageNames()
            imageDataArray = merge(indexedImages, imageNameOnDisk)
        } else {
            createIndex()
            val imageNameOnDisk = readImageNames()
            imageDataArray = buildImageData(imageNameOnDisk)
        }

        save()
    }

    public fun findById(id: Int): ImageData? {
        var imageData: ImageData? = null;
        for(item in imageDataArray) {
            if(item.id == id) {
                imageData = item
                break
            }
        }

        return imageData
    }

    public fun rename(id: Int, to: String) {
        val imageData = findById(id) ?: return
        File("$imageDirectory/${imageData.name}")
            .renameTo(File("$imageDirectory/$to"))
        imageData.name = to
        save()
    }

    public fun save() {
        val writer = JsonWriter(OutputStreamWriter(
            FileOutputStream("$dataDirectory/index.json", false),
            "UTF-8"))
        writer.setIndent("  ")
        writer.beginArray()
        val gson = Gson()
        for (imageData in imageDataArray) {
            gson.toJson(imageData, ImageData::class.java, writer)
        }
        writer.endArray()
        writer.close()
    }

    private fun createIndex() {
        File("$dataDirectory/index.json").createNewFile()

        val writer = JsonWriter(OutputStreamWriter(
            FileOutputStream("$dataDirectory/index.json", false),
            "UTF-8"))
        writer.setIndent("  ")
        writer.beginArray()
        writer.endArray()
        writer.close()
    }

    // returns list of imageData from index.json
    private fun loadFromIndex(): ArrayList<ImageData> {
        val reader = JsonReader(InputStreamReader(
            FileInputStream("$dataDirectory/index.json"),
            "UTF-8"))

        val imageDataArray = ArrayList<ImageData>()
        val gson = Gson()
        reader.beginArray()

        while (reader.hasNext()) {
            val imageData: ImageData = gson.fromJson(reader, ImageData::class.java)
            imageDataArray.add(imageData)
        }

        reader.endArray()
        reader.close()
        return imageDataArray
    }

    // returns a list of strings of the names of images
    private fun readImageNames(): ArrayList<String> {
        var imageNames = File(imageDirectory).list()!!
        val arrayList = ArrayList<String>()
        for(name in imageNames) {
            if(name != null)
                arrayList.add(name)
        }

        return arrayList
    }

    // merges index with image names
    private fun merge(imageDataArray: ArrayList<ImageData>, imageNameArray: ArrayList<String>): ArrayList<ImageData> {
        // record max id
        // remove image data that is not on disk

        var maxId = -1
        for(imageData in imageDataArray) {
            if(imageData.id > maxId) maxId = imageData.id
        }

        maxId++

        val toBeSaved = ArrayList<ImageData>()
        for(name in imageNameArray) {
            var isIndexed = false
            for(i in 0 until imageDataArray.size) {
                if(name == imageDataArray[i].name) {
                    toBeSaved.add(imageDataArray[i])
                    isIndexed = true
                }
            }

            if(!isIndexed) {
                toBeSaved.add(ImageData(name, maxId++))
            }
        }

        val toBeRemoved = ArrayList<ImageData>()
        for(imageData in imageDataArray) {
            if(!toBeSaved.contains(imageData)) toBeRemoved.add(imageData)
        }

        for(toRemove in toBeRemoved) {
            imageDataArray.remove(toRemove)
        }

        return toBeSaved
    }

    private fun buildImageData(imageNames: ArrayList<String>): ArrayList<ImageData> {
        val imageDataArray = ArrayList<ImageData>()
        for (i in 0 until imageNames.size) {
            imageDataArray.add(ImageData(imageNames[i], i))
        }

        return imageDataArray;
    }
}