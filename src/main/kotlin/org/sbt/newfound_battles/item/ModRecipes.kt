package org.sbt.newfound_battles.item

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.level.ItemLike
import org.sbt.newfound_battles.block.ModBlocks

object ModRecipes {

    class Smith(
        val template: ItemLike, val base: ItemLike, val addition: ItemLike, val result: ItemLike,
        val category: RecipeCategory = RecipeCategory.COMBAT,
    )

    fun upgrade(template: ItemLike, addition: ItemLike, from: List<ItemLike>, to: List<ItemLike>,
                category: RecipeCategory = RecipeCategory.COMBAT): List<Smith> {
        require(from.size == to.size) { "upgrade lists differ in size: ${from.size} vs ${to.size}" }
        return from.zip(to) { base, result -> Smith(template, base, addition, result, category) }
    }

    val SMITHING: List<Smith> =
        upgrade(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DEBRIS_ALLOY_BAR,
            ModItems.ALLOY_ARMOR, ModItems.DEBRIS_ALLOY_ARMOR) +
                upgrade(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DEBRIS_ALLOY_BAR,
                    ModItems.ALLOY_TOOLS, ModItems.DEBRIS_ALLOY_TOOLS, RecipeCategory.TOOLS)
    // --- smelting ---
    class Smelt(
        val input: ItemLike,
        val result: ItemLike,
        val xp: Float = 0.7f,
        val blast: Boolean = true,      // also generate a blast furnace recipe
    )

    val SMELTING = listOf(
        Smelt(ModBlocks.TUNGSTEN_ORE, ModItems.TUNGSTEN_BAR),
        Smelt(ModItems.TUNGSTEN, ModItems.TUNGSTEN_NUGGET),
        Smelt(ModItems.PLATINUM_RHODIUM_AMALGATE, ModItems.PLATINUM_RHODIUM_ALLOY_BAR),
        Smelt(ModItems.DEBRIS_RHODIUM_PLATINUM_MIXTURE, ModItems.DEBRIS_ALLOY_BAR)
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
           ) +
        Shaped(ModItems.PLATINUM_RHODIUM_AMALGATE, listOf("RP"),
            mapOf('R' to ModItems.RAW_RHODIUM, 'P' to ModItems.RAW_PLATINUM)) +
        Shaped(ModItems.DEBRIS_RHODIUM_PLATINUM_MIXTURE, listOf("###", "#@#", "###"),
            mapOf('#' to ModItems.PLATINUM_RHODIUM_AMALGATE, '@' to Items.ANCIENT_DEBRIS)) +
        armorSet(ModItems.PLATINUM_RHODIUM_ALLOY_BAR, ModItems.ALLOY_HELMET, ModItems.ALLOY_CHESTPLATE, ModItems.ALLOY_LEGGINGS,
            ModItems.ALLOY_BOOTS) +
        toolSet(ModItems.PLATINUM_RHODIUM_ALLOY_BAR, Items.STICK, ModItems.ALLOY_SWORD, ModItems.ALLOY_PICKAXE, ModItems.ALLOY_AXE, ModItems.ALLOY_SHOVEL, ModItems.ALLOY_HOE) +
        listOf(Shapeless(ModItems.ALLOY_PAXEL, mapOf(ModItems.ALLOY_PICKAXE to 1, ModItems.ALLOY_AXE to 1, ModItems.ALLOY_SHOVEL to 1, ModItems.PLATINUM_RHODIUM_ALLOY_BAR to 1)))
}