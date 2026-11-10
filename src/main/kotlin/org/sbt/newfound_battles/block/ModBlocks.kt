package org.sbt.newfound_battles.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import org.sbt.newfound_battles.Newfound_battles

object ModBlocks {
    val REGISTRY = DeferredRegister.createBlocks(Newfound_battles.ID)

    val TUNGSTEN_ORE: DeferredBlock<Block> = REGISTRY.registerSimpleBlock(
        "tungsten_ore",
        BlockBehaviour.Properties.of().strength(4.0f, 4.0f).requiresCorrectToolForDrops()
    )
    val RHODIUM_ORE: DeferredBlock<Block> = REGISTRY.registerSimpleBlock(
        "rhodium_ore",
        BlockBehaviour.Properties.of().strength(40.0f, 16.0f).requiresCorrectToolForDrops()
    )
    val PLATINUM_ORE: DeferredBlock<Block> = REGISTRY.registerSimpleBlock(
        "platinum_ore",
        BlockBehaviour.Properties.of().strength(20.0f, 8.0f).requiresCorrectToolForDrops()
    )
    val ORES = listOf(TUNGSTEN_ORE, RHODIUM_ORE, PLATINUM_ORE)
}