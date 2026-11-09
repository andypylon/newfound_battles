package org.sbt.newfound_battles.item

import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.SimpleTier
import org.sbt.newfound_battles.ModTags

object ModTiers {
    val TUNGSTEN: Tier = SimpleTier(
        ModTags.Blocks.INCORRECT_FOR_TUNGSTEN_TOOL,
        3500,
        6.0f,
        2.5f,
        15,
        { Ingredient.of(ModItems.TUNGSTEN_BAR.get())}
    )
}