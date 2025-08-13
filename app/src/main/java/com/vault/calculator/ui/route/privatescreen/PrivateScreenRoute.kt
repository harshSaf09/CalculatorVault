package com.vault.calculator.ui.route.privatescreen

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.decodeBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vault.calculator.util.loadVaultImages
import com.vault.calculator.util.moveImageToVault
import java.lang.Exception
import java.security.cert.Extension

@Composable
fun PrivateScreenRoute(
    navController: NavHostController,
    viewModel: PrivateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var images by remember { mutableStateOf(loadVaultImages(context)) }

    val pickImagesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        uris.forEach { uri ->
            moveImageToVault(context, uri) // or saveEncryptedImage(context, uri)
        }
        images = loadVaultImages(context)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { pickImagesLauncher.launch("image/*") }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(images) { file ->
                println("FILE NAME - $file and Ext - ${file.extension}")
                if (file.extension == "jpg") {
                    val bitmap = try {
                        BitmapFactory.decodeFile(file.absolutePath)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                    if (bitmap != null) {
                        val imageBitmap = try {
                            bitmap.asImageBitmap()
                        } catch (e: kotlin.Exception) {
                            e.printStackTrace()
                            null
                        }
                        if (imageBitmap != null) {
                            Image(
                                bitmap = imageBitmap,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(100.dp)
                                    .clickable {
                                        // Open full screen view if needed
                                    }
                            )
                        }
                    }

                }
//                Box(
//                    Modifier
//                        .size(100.dp, 100.dp)
//                        .border(1.dp, Color.Red)
//                )
            }
        }
    }
}

