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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.sunflower.HomeViewPagerFragmentDirections
import com.google.samples.apps.sunflower.PlantListFragment
import com.google.samples.apps.sunflower.compose.PlanItem
import com.google.samples.apps.sunflower.compose.base.ComposeListAdapter
import com.google.samples.apps.sunflower.compose.base.ComposeViewHolder
import com.google.samples.apps.sunflower.data.Plant

/**
 * Adapter for the [RecyclerView] in [PlantListFragment].
 */
class PlantAdapter : ComposeListAdapter<Plant, PlantAdapter.PlantViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bindViewHolder(getItem(position))
    }

    class PlantViewHolder(composeView: ComposeView) : ComposeViewHolder<Plant>(composeView) {
        @OptIn(ExperimentalMaterialApi::class)
        @Composable
        override fun ViewHolder(plant: Plant) {
            PlanItem(plant) {
                composeView.findNavController().navigate(
                    HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                        it.plantId
                    )
                )
            }
        }
    }

    companion object {
        private val diffCallback = PlantDiffCallback()
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {

    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}
