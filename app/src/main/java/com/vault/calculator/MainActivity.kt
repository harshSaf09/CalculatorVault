package com.vault.calculator

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.vault.calculator.ui.CalculatorApp
import com.vault.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                var imageUri by remember { mutableStateOf<Uri?>(null) }

                // Check if the activity was launched with an ACTION_SEND intent
                if (intent?.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
                    imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM)
                }
                if (imageUri == null) {
                    val navController = rememberNavController()
                    CalculatorApp(navController)
                } else {
                    Surface {
                        SharedImageScreen(imageUri, this)
                    }
                }
            }
        }
    }
}

@Composable
fun SharedImageScreen(imageUri: Uri?, context: Context) {
    if (imageUri != null) {
        val imageBitmap: Bitmap? = remember(imageUri) {
            try {
                val inputStream =
                    context.contentResolver.openInputStream(imageUri)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        if (imageBitmap != null) {
            Image(
                imageBitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
            )
        }
    } else {
        // Display a placeholder or message if no image is shared
        // For example,
        Text("No image shared yet")
    }
}

