package com.example.sensible.ui.recipe

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import com.example.sensible.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensible.ui.components.AnimatedCircle
import com.example.sensible.ui.theme.SensibleTheme

private val zoomPic = mutableStateOf(false)

@Composable
fun FoodItem(){
    val image: Painter = painterResource(id = R.drawable.cola)
    Column() {
        Text("Cola")
        Row() {
            Box(contentAlignment = Alignment.Center){
                AnimatedCircle(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(8.dp),
                    proportions = listOf(0.25f, 0.5f, 0.25f),
                    colors = listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.secondary, MaterialTheme.colors.secondaryVariant)
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
        Zoom(image)
    }
        
}

@Composable
fun Zoom(image: Painter){
    Box(modifier = Modifier.fillMaxWidth()
        .padding(8.dp),
        contentAlignment = Alignment.TopCenter){
        Image(painter = image,contentDescription = "", modifier = Modifier
            .scale(0.8f)
            .background(Color.White)
            .border(BorderStroke(4.dp, MaterialTheme.colors.primary))
            .clickable { zoomPic.value = false })
    }
}

@Preview
@Composable
fun FoodItemPreview(){
    SensibleTheme() {
        FoodItem()
    }
}