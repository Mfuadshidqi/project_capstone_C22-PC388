package com.fuad.mywasteappchanneling.utils

import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

object FileUtil {
    /** Load TF Lite model from asset file.  */
    @Throws(IOException::class)

    fun loadModelFile(assetManager: AssetManager, modelPath: String?): MappedByteBuffer {
        assetManager.openFd(modelPath!!).use { fileDescriptor ->
            FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->
                val fileChannel = inputStream.channel
                val startOffset = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                return fileChannel.map(
                    FileChannel.MapMode.READ_ONLY,
                    startOffset,
                    declaredLength
                )
            }
        }
    }

//    /** Load candidates from asset file.  */
//    @RequiresApi(Build.VERSION_CODES.N)
//    @Throws(IOException::class)
//    fun loadMovieList(
//        assetManager: AssetManager, candidateListPath: String
//    ): Collection<Food> {
//        val content = loadFileContent(assetManager, candidateListPath)
//        val gson = Gson()
//        val type = object : TypeToken<Collection<Food?>?>() {}.type
//        return gson.fromJson<Collection<Food>>(content, type)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    @Throws(IOException::class)
//    fun loadGenreList(assetManager: AssetManager, genreListPath: String): List<String> {
//        val content = loadFileContent(assetManager, genreListPath)
//        val lines = content.split(System.lineSeparator().toRegex()).toTypedArray()
//        return Arrays.asList(*lines)
//    }
//
//    /** Load config from asset file.  */
//    @RequiresApi(Build.VERSION_CODES.N)
//    @Throws(IOException::class)
//    fun loadConfig(assetManager: AssetManager, configPath: String): Config {
//        val content = loadFileContent(assetManager, configPath)
//        val gson = Gson()
//        val type = object : TypeToken<Config?>() {}.type
//        return gson.fromJson(content, type)
//    }
//
//    /** Load file content from asset file.  */
//    @RequiresApi(Build.VERSION_CODES.N)
//    @Throws(IOException::class)
//    private fun loadFileContent(assetManager: AssetManager, path: String): String {
//        assetManager.open(path).use { ins ->
//            BufferedReader(
//                InputStreamReader(
//                    ins,
//                    StandardCharsets.UTF_8
//                )
//            ).use { reader ->
//                return reader.lines()
//                    .collect(Collectors.joining(System.lineSeparator()))
//            }
//        }
//    }
}