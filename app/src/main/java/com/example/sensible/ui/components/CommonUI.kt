package com.example.sensible.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.sensible.R
import com.example.sensible.ui.theme.SensibleTheme
@Composable
fun SensibleActionButton(
    onClick: () -> Unit
){
    FloatingActionButton(
        onClick = onClick,
        content = {
            Icon(
                painterResource(R.drawable.ic_launcher_add),
                contentDescription = null,
            )
        },
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    )
}


@Composable
fun FoodListItem(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {},
    title: String,
    image: Painter,
    calories: Double,
    amount: Long,
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth().clickable { onItemClick }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            // Set image size to 40 dp
                            .size(56.dp)
                            // Clip image to be shaped as a circle
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                            .background(MaterialTheme.colors.surface),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(Modifier.align(Alignment.CenterVertically)) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.subtitle2,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 2,
                            modifier = Modifier.fillMaxWidth(0.7F),
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "$amount g",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.alpha(0.7F),
                        )
                    }
                }
            }
            Text(
                text = "$calories kcal",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>,
    color: Color = MaterialTheme.colors.surface,
    modifier: Modifier = Modifier,
    onScanClick: () -> Unit ) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body1,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
            else {
                IconButton(
                    onClick = onScanClick
                ) {
                    Icon(
                        painterResource(R.drawable.ic_launcher_barcode),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(32.dp), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            /*
            textColor = MaterialTheme.colors.onPrimary,
            cursorColor = MaterialTheme.colors.onPrimary,
            leadingIconColor = MaterialTheme.colors.onPrimary,
            trailingIconColor = MaterialTheme.colors.onPrimary,

            focusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.75F),
            unfocusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.5F),
             */
            disabledIndicatorColor = Transparent,
            backgroundColor = color,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SensibleTheme {
        SearchBar(textState, color = MaterialTheme.colors.primary) {}
    }
}

@Preview
@Composable
fun FoodRowPreview(){
    SensibleTheme(darkTheme = false){
        FoodListItem(title = "peanuts", image = painterResource(R.drawable.cola), calories = 100.0, amount = 1)
    }
}