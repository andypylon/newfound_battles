package org.sbt.newfound_battles.item

import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.DiggerItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.component.ItemLore
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

    private val TUNGSTEN_TIER = ModTiers.TUNGSTEN
    private fun tool(name: String, color: Int, tooltip: String? = null, build: (Item.Properties) -> Item) =
        REGISTRY.registerItem(name) { p ->
            val title = Component.translatable("item.${Newfound_battles.ID}.$name").withStyle { it.withColor(color) }
            var props = p.component(DataComponents.ITEM_NAME, title)
            if (tooltip != null) {
                val line = Component.translatable("tooltip.${Newfound_battles.ID}.$tooltip")
                    .withStyle { it.withColor(0xAAAAAA).withItalic(false) }
                props = props.component(DataComponents.LORE, ItemLore(listOf(line)))
            }
            build(props)
        }
    val TUNGSTEN_SWORD = tool("tungsten_sword", 0x91BA93) {
        SwordItem(TUNGSTEN_TIER, it.attributes(SwordItem.createAttributes(TUNGSTEN_TIER, 3, -2.4f)))
    }
    val TUNGSTEN_PICKAXE = tool("tungsten_pickaxe", 0x91BA93) {
        PickaxeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 1.0f, -2.8f)))
    }
    val TUNGSTEN_AXE = tool("tungsten_axe", 0x91BA93) {
        AxeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 5.0f, -3.0f)))
    }
    val TUNGSTEN_SHOVEL = tool("tungsten_shovel", 0x91BA93) {
        ShovelItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 1.5f, -3.0f)))
    }
    val TUNGSTEN_HOE = tool("tungsten_hoe", 0x91BA93) {
        HoeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, -3.0f, 0.0f)))
    }
    val TUNGSTEN_PAXEL = tool("tungsten_paxel", 0x91BA93, tooltip = "tungsten_paxel") {
        PaxelItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 4.0f, -3.0f)))
    }

    val TUNGSTEN_TOOLS = listOf(TUNGSTEN_SWORD, TUNGSTEN_PICKAXE, TUNGSTEN_AXE, TUNGSTEN_SHOVEL, TUNGSTEN_HOE, TUNGSTEN_PAXEL)
}