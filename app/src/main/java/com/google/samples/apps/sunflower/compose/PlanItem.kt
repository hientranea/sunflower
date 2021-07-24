package com.google.samples.apps.sunflower.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.google.android.material.composethemeadapter.MdcTheme
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlanItem(plan: Plant, onClick: (Plant) -> Unit) {
    val cardCornerRadius = dimensionResource(id = R.dimen.card_corner_radius)
    MdcTheme {
        Card(
            onClick = { onClick(plan)},
            elevation = dimensionResource(id = R.dimen.card_elevation),
            shape = RoundedCornerShape(topEnd = cardCornerRadius, bottomStart = cardCornerRadius),
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
                .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
        ) {
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(data = plan.imageUrl, builder = { fadeIn() }),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.a11y_plant_item_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.plant_item_image_height))
                )
                Text(
                    text = plan.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
private fun PlantItemPreview() {
    val plant = Plant("id", "Apple", "description", 3, 30, "")
    PlanItem(plant, onClick = {})
}
