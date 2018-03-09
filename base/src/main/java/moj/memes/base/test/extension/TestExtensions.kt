package moj.memes.base.test.extension

import com.squareup.moshi.Moshi
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
        c: Class<T>,
        filePath: String,
        moshi: Moshi
): T {
    val json = readFileAsString(this, filePath)
    val jsonAdapter = moshi.adapter(c)
    return jsonAdapter.fromJson(json) ?: c.newInstance()
}
