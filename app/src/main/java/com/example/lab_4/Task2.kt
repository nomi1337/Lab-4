package com.example.lab_4
import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab_4.ui.theme.Lab4Theme
import java.io.File
import java.io.FileOutputStream

class Task2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingE(this)
        }
    }

    fun Greeting(context: Context) {
        val fileName = "example.txt" // Corrected variable name
        val fileContents = "Hello, World" // Corrected typo in the message

        val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE) // Added Context qualifier to MODE_PRIVATE
        outputStream.write(fileContents.toByteArray())
        outputStream.close()
    }

    fun GreetingE(context: Context) {
        val fileName = "example2.txt"
        val fileContents = "Hello, World"

        if (isExternalStorageWritable()) {
            val directory = File(Environment.getExternalStorageDirectory().absolutePath + "/MyAppFolder/")
            directory.mkdirs()
            val file = File(directory, fileName)

            try {
                val fos = FileOutputStream(file)
                fos.write(fileContents.toByteArray())
                fos.close()
                // File successfully written to external storage
            } catch (e: Exception) {
                e.printStackTrace()
                // Error writing to file
            }
        } else {
            // External storage not available
        }
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun GreetingPreview() {
//        Greeting(this)
        GreetingE(this)
    }
}