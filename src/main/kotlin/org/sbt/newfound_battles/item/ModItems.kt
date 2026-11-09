package org.sbt.newfound_battles.item

import net.minecraft.world.item.ArmorItem
import net.neoforged.neoforge.registries.DeferredRegister
import org.sbt.newfound_battles.Newfound_battles
import org.sbt.newfound_battles.block.ModBlocks

object ModItems {
    val REGISTRY = DeferredRegister.createItems(Newfound_battles.ID)
    val TUNGSTEN_ORE_ITEM = REGISTRY.registerSimpleBlockItem("tungsten_ore", ModBlocks.TUNGSTEN_ORE)
    val TUNGSTEN = REGISTRY.registerSimpleItem("tungsten")
    val TUNGSTEN_NUGGET = REGISTRY.registerSimpleItem("tungsten_nugget")
    val TUNGSTEN_BAR = REGISTRY.registerSimpleItem("tungsten_bar")
    private const val TUNGSTEN_ARMOR_DURABILITY = 25
    private fun armor(name: String, type: ArmorItem.Type, color: Int) = REGISTRY.registerItem(name) { p ->
        ColoredArmorItem(
            ModArmorMaterials.TUNGSTEN, type,
            p.durability(type.getDurability(TUNGSTEN_ARMOR_DURABILITY)),
            color
        )
    }
    val TUNGSTEN_HELMET     = armor("tungsten_helmet",     ArmorItem.Type.HELMET,     0x91BA93)
    val TUNGSTEN_CHESTPLATE = armor("tungsten_chestplate", ArmorItem.Type.CHESTPLATE, 0x91BA93)
    val TUNGSTEN_LEGGINGS   = armor("tungsten_leggings",   ArmorItem.Type.LEGGINGS,   0x91BA93)
    val TUNGSTEN_BOOTS      = armor("tungsten_boots",      ArmorItem.Type.BOOTS,      0x91BA93)
    val TUNGSTEN_ARMOR = listOf(TUNGSTEN_HELMET, TUNGSTEN_CHESTPLATE, TUNGSTEN_LEGGINGS, TUNGSTEN_BOOTS)

}