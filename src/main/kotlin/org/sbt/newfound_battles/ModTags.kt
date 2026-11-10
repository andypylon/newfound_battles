package org.sbt.newfound_battles

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey

object ModTags {
    object Blocks {
        val MINEABLE_WITH_PAXEL = tag("mineable/paxel")
        val INCORRECT_FOR_TUNGSTEN_TOOL = tag("incorrect_for_tungsten_tool")
        private fun tag(name: String) =
            TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, name))
        val INCORRECT_FOR_ALLOY_TOOL = tag("incorrect_for_alloy_tool")
        val INCORRECT_FOR_DEBRIS_ALLOY_TOOL = tag("incorrect_for_debris_alloy_tool")
    }
}