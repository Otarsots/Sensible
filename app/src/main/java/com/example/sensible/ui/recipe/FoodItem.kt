package com.example.sensible.ui.recipe

import android.Manifest
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sensible.ui.components.AnimatedCircle
import com.example.sensible.ui.components.scanning
import com.example.sensible.ui.theme.SensibleTheme
import com.example.sensible.util.getProductData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

private val zoomPic = mutableStateOf(false)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FoodItem(){
    var productName by rememberSaveable{ mutableStateOf("") }
    val image = rememberAsyncImagePainter("https://de.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.full.jpg")
    val imageZoomed = rememberAsyncImagePainter("https://de.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.full.jpg")
    val coroutineScope = rememberCoroutineScope()
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val getProductOnClick: () -> Unit = {
        coroutineScope.launch {
            val result = getProductData(737628064502)
                productName = result?.productName.toString()
        }
    }
    Column() {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(topStartPercent = 40, topEndPercent = 40)
                )
        ) {
            Row() {
                Text(text=productName,
                    modifier = Modifier.fillMaxWidth(0.9F),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.h6,
                )
                Button(onClick =
                {cameraPermissionState.launchPermissionRequest()
                        scanning = true}, shape = CircleShape) {

                }
            }


        }

        Row() {
            Box(contentAlignment = Alignment.Center){
                AnimatedCircle(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(8.dp),
                    proportions = listOf(0.25f, 0.5f, 0.25f),
                    colors = listOf(MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.secondary,
                        MaterialTheme.colors.secondaryVariant)
                )
                Image(painter = image,contentDescription = "", modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .clickable { zoomPic.value = true })
            }
            
            Column(Modifier.padding(8.dp)) {
                Text("Protein: 2000",color = MaterialTheme.colors.primaryVariant)
                Text("Carbohydrates: 2000",color = MaterialTheme.colors.secondary)
                Text("Fats: 2000",color = MaterialTheme.colors.secondaryVariant)
            }
        } 
    }
    if(zoomPic.value) {
        Zoom(imageZoomed)
    }
        
}

@Composable
fun Zoom(image: Painter) {

    Box(Modifier.padding(20.dp).fillMaxHeight(0.8F), contentAlignment = Alignment.Center) {
        Image(painter = image, contentDescription = "", modifier = Modifier
            .clip(shape = RoundedCornerShape(percent = 10))
            .border(
                BorderStroke(4.dp, MaterialTheme.colors.primaryVariant),
                shape = RoundedCornerShape(percent = 10)
            )
            .clickable { zoomPic.value = false },
            contentScale = ContentScale.Fit)
    }
}

@Preview
@Composable
fun FoodItemPreview(){
    SensibleTheme() {
        FoodItem()
    }
}