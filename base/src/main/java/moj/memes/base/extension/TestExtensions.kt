package moj.memes.base.extension

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileReader

fun readFileAsString(file: Any, filePath: String): String {
    val classLoader = file.javaClass.classLoader
    val path = classLoader.getResource(filePath).path

    val jsonString = StringBuilder()
    val bufferedReader = BufferedReader(FileReader(path))
    var line: String? = bufferedReader.readLine()
    while (line != null) {
        jsonString.append(line.trim())
        line = bufferedReader.readLine()
    }

    return jsonString.toString()
}

fun <T> Any.jsonToObject(
        clazz: Class<T>,
        filePath: String,
        gson: Gson = Gson()
): T {
    val json = readFileAsString(this, filePath)
    return gson.fromJson(json, clazz)
}