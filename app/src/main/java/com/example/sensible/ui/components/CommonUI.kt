package com.example.sensible.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
                painterResource(R.drawable.ic_action_add),
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
    onItemClick: () -> Unit = {},
    trailing: @Composable (() -> Unit)? = null,
    title: String,
    image: String? = null,
    calories: Double,
    amount: Long? = null,
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(72.dp).fillMaxWidth().clickable { onItemClick.invoke() }.padding(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (horizontalArrangement = Arrangement.SpaceBetween){
                Box(modifier = Modifier.padding(8.dp)) {
                    if(image!= null){
                        AsyncImage(
                            model = image,
                            contentDescription = "",
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(48.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                                .background(MaterialTheme.colors.surface),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 2,
                            modifier = Modifier.fillMaxWidth(0.6F),
                            overflow = TextOverflow.Ellipsis
                        )
                        if (amount != null){
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
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle2
            )
            if (trailing != null) {
                Box(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp)
                        .requiredSize(44.dp)
                ) { trailing() }
            }
        }
    }
}

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface),
        cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
        decorationBox = { innerTextField ->
            Surface(
                modifier = Modifier.height(60.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(30.dp)
            ) {
                Row(
                    Modifier.padding(start = 24.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, null)
                    Spacer(Modifier.width(12.dp))
                    Box(
                        Modifier
                            .padding(vertical = 16.dp)
                            .weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                stringResource(R.string.hint_search),
                                style = MaterialTheme.typography.h6.copy(
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                                )
                            )
                        }
                        innerTextField()
                    }
                    AnimatedVisibility(
                        value.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Spacer(Modifier.width(8.dp))
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(Icons.Default.Clear, "")
                        }
                    }
                }
            }
        }
    )
}
/*
@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SensibleTheme {
        SearchBar(textState, color = MaterialTheme.colors.primary) {}
    }
}

 */

@Preview
@Composable
fun FoodRowPreview(){
    SensibleTheme(darkTheme = false){
        FoodListItem(title = "peanuts", image = null,calories = 100.0, amount = 1, trailing = {
            Icon(
                painterResource(R.drawable.ic_action_add),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        })
    }
}