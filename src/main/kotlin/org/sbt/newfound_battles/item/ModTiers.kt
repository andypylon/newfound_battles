package org.sbt.newfound_battles.item

import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier
import org.sbt.newfound_battles.ModTags

object ModTiers {
    val TUNGSTEN: Tier = SimpleTier(
        ModTags.Blocks.INCORRECT_FOR_TUNGSTEN_TOOL,
        750,
        6.0f,
        2.5f,
        15,
        { Ingredient.of(ModItems.TUNGSTEN_BAR.get())}
    )
    val ALLOY: Tier = SimpleTier(
        ModTags.Blocks.INCORRECT_FOR_ALLOY_TOOL,
        1750,
        6.0f,
        2.5f,
        15,
        { Ingredient.of(ModItems.PLATINUM_RHODIUM_ALLOY_BAR.get())}
    )
    val DEBRIS_ALLOY: Tier = SimpleTier(
        ModTags.Blocks.INCORRECT_FOR_DEBRIS_ALLOY_TOOL,
        5000,
        6.0f,
        2.5f,
        25,
        { Ingredient.of(ModItems.DEBRIS_RHODIUM_PLATINUM_MIXTURE.get())}
    )
}