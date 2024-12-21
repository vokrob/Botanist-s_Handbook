package com.vokrob.botanists_handbook.ui_components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.vokrob.botanists_handbook.MainViewModel
import com.vokrob.botanists_handbook.ui.theme.BgTransparent2
import com.vokrob.botanists_handbook.ui.theme.Gray
import com.vokrob.botanists_handbook.ui.theme.MainRed
import com.vokrob.botanists_handbook.utils.ListItem

@Composable
fun MainListItem(
    mainViewModel: MainViewModel = hiltViewModel(),
    item: ListItem,
    onClick: (ListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(5.dp)
            .clickable { onClick(item) },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, MainRed)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, text, favoriteButton) = createRefs()

            AssetImage(
                imageName = item.imageName,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainRed)
                    .padding(10.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = { mainViewModel.insertItem(item.copy(isFav = !item.isFav)) },
                modifier = Modifier.constrainAs(favoriteButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = if (item.isFav) MainRed else Gray,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BgTransparent2)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun AssetImage(imageName: String, contentDescription: String, modifier: Modifier) {
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}





















