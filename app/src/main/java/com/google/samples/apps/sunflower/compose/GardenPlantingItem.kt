/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.google.android.material.composethemeadapter.MdcTheme
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.GardenPlanting
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings
import com.google.samples.apps.sunflower.viewmodels.PlantAndGardenPlantingsViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun GardenPlantingItem(viewModel: PlantAndGardenPlantingsViewModel, onClick: (String) -> Unit) {
    val cardCornerRadius = dimensionResource(id = R.dimen.card_corner_radius)
    MdcTheme {
        Card(
            onClick = { onClick(viewModel.plantId) },
            elevation = dimensionResource(id = R.dimen.card_elevation),
            shape = RoundedCornerShape(topEnd = cardCornerRadius, bottomStart = cardCornerRadius),
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
                .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
        ) {
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(
                        data = viewModel.imageUrl,
                        builder = { fadeIn() }),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.a11y_plant_item_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.plant_item_image_height))
                )
                Text(
                    text = viewModel.plantName,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                        .wrapContentWidth(Alignment.CenterHorizontally),
                )
                Text(
                    text = stringResource(id = R.string.plant_date_header),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                )
                Text(
                    text = viewModel.plantDateString,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(id = R.string.watered_date_header),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.margin_normal))
                        .wrapContentWidth(Alignment.CenterHorizontally),
                )
                Text(
                    text = viewModel.waterDateString,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                Text(
                    text = LocalContext.current.resources.getQuantityString(
                        R.plurals.watering_next,
                        viewModel.wateringInterval,
                        viewModel.wateringInterval
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(id = R.dimen.margin_normal))
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }

}

@Preview
@Composable
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
private fun GardenPlantingItemPreview() {
    val plant = Plant("1", "Apple", "description", 3, 30, "")
    val gardenPlantings = GardenPlanting(plantId = "1")
    val plantAndGarden = PlantAndGardenPlantings(plant, listOf(gardenPlantings))
    val viewModel = PlantAndGardenPlantingsViewModel(plantAndGarden)
    GardenPlantingItem(viewModel, {})
}
