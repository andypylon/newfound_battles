package org.sbt.newfound_battles.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.data.tags.TagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.registries.DeferredItem
import org.sbt.newfound_battles.item.ModItems
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(
    out: PackOutput,
    lookup: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagsProvider.TagLookup<Block>>,
    id: String,
    files: ExistingFileHelper
) : ItemTagsProvider(out, lookup, blockTags, id, files) {

    override fun addTags(provider: HolderLookup.Provider) {
        fun addTo(key: TagKey<Item>, items: List<DeferredItem<out Item>>) {
            tag(key).add(*items.map { it.get() }.toTypedArray())
        }

        val swords: List<DeferredItem<out Item>> = listOf(ModItems.TUNGSTEN_SWORD)
        val axeLike: List<DeferredItem<out Item>> = listOf(ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN_PAXEL)
        val mining: List<DeferredItem<out Item>> = listOf(
            ModItems.TUNGSTEN_PICKAXE, ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN_SHOVEL,
            ModItems.TUNGSTEN_HOE, ModItems.TUNGSTEN_PAXEL
        )
        val tools: List<DeferredItem<out Item>> = ModItems.TUNGSTEN_TOOLS
        val armor: List<DeferredItem<out Item>> = ModItems.TUNGSTEN_ARMOR

        // tools
        addTo(ItemTags.SWORD_ENCHANTABLE, swords)
        addTo(ItemTags.FIRE_ASPECT_ENCHANTABLE, swords)
        addTo(ItemTags.SHARP_WEAPON_ENCHANTABLE, swords + axeLike)
        addTo(ItemTags.WEAPON_ENCHANTABLE, swords + axeLike)
        addTo(ItemTags.MINING_ENCHANTABLE, mining)
        addTo(ItemTags.MINING_LOOT_ENCHANTABLE, mining)

        // armor
        addTo(ItemTags.ARMOR_ENCHANTABLE, armor)
        addTo(ItemTags.HEAD_ARMOR_ENCHANTABLE, listOf(ModItems.TUNGSTEN_HELMET))
        addTo(ItemTags.CHEST_ARMOR_ENCHANTABLE, listOf(ModItems.TUNGSTEN_CHESTPLATE))
        addTo(ItemTags.LEG_ARMOR_ENCHANTABLE, listOf(ModItems.TUNGSTEN_LEGGINGS))
        addTo(ItemTags.FOOT_ARMOR_ENCHANTABLE, listOf(ModItems.TUNGSTEN_BOOTS))

        // everything
        addTo(ItemTags.DURABILITY_ENCHANTABLE, tools + armor)
    }
}