package com.nmp160423174.uts_anmp.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper(val context: Context) {
    val folderName = "myfolder"
    val fileName = "myhabit.txt"

    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if(!dir.exists()) {
            dir.mkdirs()
        }
        return File(dir, fileName)
    }

    fun writeToFile(data:String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use {
                it.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromFile():String {
        return try {
            val file = getFile()

            if (!file.exists()) {
                writeToFile("[]")
                return "[]"
            }

            file.bufferedReader().useLines {
                it.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace().toString()
        }
    }

    fun deleteFile(): Boolean {
        return getFile().delete()
    }

    fun getFilePath(): String {
        return getFile().absolutePath
    }
}