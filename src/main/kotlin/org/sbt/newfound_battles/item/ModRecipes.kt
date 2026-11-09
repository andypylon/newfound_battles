package org.sbt.newfound_battles.item

import net.minecraft.world.item.Items
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
    fun armorSet(material: ItemLike, helmet: ItemLike, chest: ItemLike, legs: ItemLike, boots: ItemLike): List<Craft> = listOf(
        Shaped(helmet, listOf("###", "# #"), mapOf('#' to material)),
        Shaped(chest, listOf("# #", "###", "###"), mapOf('#' to material)),
        Shaped(legs, listOf("###", "# #", "# #"), mapOf('#' to material)),
        Shaped(boots, listOf("# #", "# #"), mapOf('#' to material)),
    )
    fun toolSet(bar: ItemLike, handle: ItemLike, sword: ItemLike, pickaxe: ItemLike,
                axe: ItemLike, shovel: ItemLike, hoe: ItemLike): List<Craft> {
        val keys = mapOf('#' to bar, 'S' to handle)
        return listOf(
            Shaped(sword,   listOf("#", "#", "S"), keys),
            Shaped(pickaxe, listOf("###", " S ", " S "), keys),
            Shaped(axe,     listOf("##", "#S", " S"), keys),
            Shaped(shovel,  listOf("#", "S", "S"), keys),
            Shaped(hoe,     listOf("##", " S", " S"), keys),
        )
    }

    val CRAFTING: List<Craft> =
        compress(ModItems.TUNGSTEN_NUGGET, ModItems.TUNGSTEN_BAR) +
        armorSet(ModItems.TUNGSTEN_BAR, ModItems.TUNGSTEN_HELMET, ModItems.TUNGSTEN_CHESTPLATE, ModItems.TUNGSTEN_LEGGINGS, ModItems.TUNGSTEN_BOOTS) +
        toolSet(ModItems.TUNGSTEN_BAR, Items.STICK, ModItems.TUNGSTEN_SWORD, ModItems.TUNGSTEN_PICKAXE,
         ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN_SHOVEL, ModItems.TUNGSTEN_HOE) +
         listOf(
         Shapeless(ModItems.TUNGSTEN_PAXEL, mapOf(
           ModItems.TUNGSTEN_PICKAXE to 1, ModItems.TUNGSTEN_AXE to 1,
           ModItems.TUNGSTEN_SHOVEL to 1, ModItems.TUNGSTEN_BAR to 1)),
           )
}