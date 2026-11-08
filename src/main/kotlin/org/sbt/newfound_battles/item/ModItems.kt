package org.sbt.newfound_battles.item

import net.neoforged.neoforge.registries.DeferredRegister
import org.sbt.newfound_battles.Newfound_battles
import org.sbt.newfound_battles.block.ModBlocks

object ModItems {
    val REGISTRY = DeferredRegister.createItems(Newfound_battles.ID)
    val TUNGSTEN_ORE_ITEM = REGISTRY.registerSimpleBlockItem("tungsten_ore", ModBlocks.TUNGSTEN_ORE)
    val TUNGSTEN = REGISTRY.registerSimpleItem("tungsten")
    val TUNGSTEN_NUGGET = REGISTRY.registerSimpleItem("tungsten_nugget")
    val TUNGSTEN_BAR = REGISTRY.registerSimpleItem("tungsten_bar")
}