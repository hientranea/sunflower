/*
 * Copyright 2018 Google LLC
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

package com.google.samples.apps.sunflower.adapters

import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.google.samples.apps.sunflower.HomeViewPagerFragmentDirections
import com.google.samples.apps.sunflower.compose.GardenPlantingItem
import com.google.samples.apps.sunflower.compose.base.ComposeListAdapter
import com.google.samples.apps.sunflower.compose.base.ComposeViewHolder
import com.google.samples.apps.sunflower.data.PlantAndGardenPlantings
import com.google.samples.apps.sunflower.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter :
    ComposeListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.PlantAndGardenViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAndGardenViewHolder {
        return PlantAndGardenViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: PlantAndGardenViewHolder, position: Int) {
        holder.bindViewHolder(getItem(position))
    }

    class PlantAndGardenViewHolder(composeView: ComposeView) :
        ComposeViewHolder<PlantAndGardenPlantings>(composeView) {
        @ExperimentalAnimationApi
        @OptIn(ExperimentalMaterialApi::class)
        @Composable
        override fun ViewHolder(data: PlantAndGardenPlantings) {
            val viewModel = PlantAndGardenPlantingsViewModel(data)
            GardenPlantingItem(viewModel = viewModel) { planId ->
                val direction = HomeViewPagerFragmentDirections
                    .actionViewPagerFragmentToPlantDetailFragment(planId)
                composeView.findNavController().navigate(direction)
            }
        }
    }

    companion object {
        private val diffCallback = GardenPlantDiffCallback()
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {

    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }
}
