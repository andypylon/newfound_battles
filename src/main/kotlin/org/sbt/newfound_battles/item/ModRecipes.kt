package org.sbt.newfound_battles.item

import net.minecraft.world.level.ItemLike
import org.sbt.newfound_battles.block.ModBlocks

object ModRecipes {
    // --- smelting ---
    class Smelt(
        val input: ItemLike,
        val result: ItemLike,
        val xp: Float = 0.7f,
        val blast: Boolean = true,      // also generate a blast furnace recipe
    )

    val SMELTING = listOf(
        Smelt(ModBlocks.TUNGSTEN_ORE, ModItems.TUNGSTEN_BAR),
        Smelt(ModItems.TUNGSTEN, ModItems.TUNGSTEN_NUGGET)
    )

    // --- crafting ---
    sealed class Craft(val result: ItemLike, val count: Int, val id: String?)

    class Shaped(
        result: ItemLike,
        val pattern: List<String>,
        val keys: Map<Char, ItemLike>,
        count: Int = 1,
        id: String? = null,
    ) : Craft(result, count, id)

    class Shapeless(
        result: ItemLike,
        val ingredients: Map<ItemLike, Int>,
        count: Int = 1,
        id: String? = null,
    ) : Craft(result, count, id)

    // helper for the common 9 <-> 1 pair
    fun compress(small: ItemLike, big: ItemLike): List<Craft> = listOf(
        Shaped(big, listOf("###", "###", "###"), mapOf('#' to small)),
        Shapeless(small, mapOf(big to 1), count = 9),
    )

    val CRAFTING: List<Craft> =
        compress(ModItems.TUNGSTEN_NUGGET, ModItems.TUNGSTEN_BAR) +
                listOf(
                    // any one-off recipe is just another entry, e.g.:
                    // Shaped(ModItems.TUNGSTEN_PICKAXE, listOf("###", " S ", " S "),
                    //        mapOf('#' to ModItems.TUNGSTEN_BAR, 'S' to Items.STICK)),
                )
}