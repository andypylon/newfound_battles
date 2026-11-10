package org.sbt.newfound_battles.item

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
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
    val RHODIUM_ORE_ITEM = REGISTRY.registerSimpleBlockItem("rhodium_ore", ModBlocks.RHODIUM_ORE)
    val PLATINUM_ORE_ITEM = REGISTRY.registerSimpleBlockItem("platinum_ore", ModBlocks.PLATINUM_ORE)
    val PLATINUM_RHODIUM_AMALGATE = colored("platinum_rhodium_amalgate", 0xB0CDD4)
    val DEBRIS_RHODIUM_PLATINUM_MIXTURE = colored("debris_rhodium_platinum_mixture", 0x9E2933)
    val PLATINUM_RHODIUM_ALLOY_BAR = colored("platinum_rhodium_alloy_bar", 0xB0CDD4)
    val DEBRIS_ALLOY_BAR = colored("debris_alloy_bar", 0x9E2933)
    val TUNGSTEN = REGISTRY.registerSimpleItem("tungsten")
    val RAW_RHODIUM = REGISTRY.registerSimpleItem("raw_rhodium")
    val RAW_PLATINUM = REGISTRY.registerSimpleItem("raw_platinum")
    val TUNGSTEN_NUGGET = REGISTRY.registerSimpleItem("tungsten_nugget")
    val TUNGSTEN_BAR = REGISTRY.registerSimpleItem("tungsten_bar")
    private const val TUNGSTEN_ARMOR_DURABILITY = 25
    private fun armor(
        name: String,
        material: Holder<ArmorMaterial>,
        type: ArmorItem.Type,
        color: Int,
        durability: Int,
    ) = REGISTRY.registerItem(name) { p ->
        ColoredArmorItem(material, type, p.durability(type.getDurability(durability)), color)
    }
    val TUNGSTEN_HELMET     = armor("tungsten_helmet", ModArmorMaterials.TUNGSTEN, ArmorItem.Type.HELMET, 0x91BA93, 25)
    val TUNGSTEN_CHESTPLATE = armor("tungsten_chestplate", ModArmorMaterials.TUNGSTEN, ArmorItem.Type.CHESTPLATE, 0x91BA93, 25)
    val TUNGSTEN_LEGGINGS   = armor("tungsten_leggings",   ModArmorMaterials.TUNGSTEN, ArmorItem.Type.LEGGINGS, 0x91BA93, 25)
    val TUNGSTEN_BOOTS      = armor("tungsten_boots",      ModArmorMaterials.TUNGSTEN, ArmorItem.Type.BOOTS, 0x91BA93, 25)
    val TUNGSTEN_ARMOR = listOf(TUNGSTEN_HELMET, TUNGSTEN_CHESTPLATE, TUNGSTEN_LEGGINGS, TUNGSTEN_BOOTS)

    val ALLOY_HELMET     = armor("alloy_helmet", ModArmorMaterials.ALLOY, ArmorItem.Type.HELMET, 0xB0CDD4, 25)
    val ALLOY_CHESTPLATE = armor("alloy_chestplate", ModArmorMaterials.ALLOY, ArmorItem.Type.CHESTPLATE, 0xB0CDD4, 25)
    val ALLOY_LEGGINGS   = armor("alloy_leggings",   ModArmorMaterials.ALLOY, ArmorItem.Type.LEGGINGS, 0xB0CDD4, 25)
    val ALLOY_BOOTS      = armor("alloy_boots",      ModArmorMaterials.ALLOY, ArmorItem.Type.BOOTS, 0xB0CDD4, 25)
    val ALLOY_ARMOR = listOf(ALLOY_HELMET, ALLOY_CHESTPLATE, ALLOY_LEGGINGS, ALLOY_BOOTS)

    val DEBRIS_ALLOY_HELMET     = armor("debris_alloy_helmet", ModArmorMaterials.DEBRIS_ALLOY, ArmorItem.Type.HELMET, 0x9E2933, 25)
    val DEBRIS_ALLOY_CHESTPLATE = armor("debris_alloy_chestplate", ModArmorMaterials.DEBRIS_ALLOY, ArmorItem.Type.CHESTPLATE, 0x9E2933, 25)
    val DEBRIS_ALLOY_LEGGINGS   = armor("debris_alloy_leggings",   ModArmorMaterials.DEBRIS_ALLOY, ArmorItem.Type.LEGGINGS, 0x9E2933, 25)
    val DEBRIS_ALLOY_BOOTS      = armor("debris_alloy_boots",      ModArmorMaterials.DEBRIS_ALLOY, ArmorItem.Type.BOOTS, 0x9E2933, 25)
    val DEBRIS_ALLOY_ARMOR = listOf(DEBRIS_ALLOY_HELMET, DEBRIS_ALLOY_CHESTPLATE, DEBRIS_ALLOY_LEGGINGS, DEBRIS_ALLOY_BOOTS)

    private val TUNGSTEN_TIER = ModTiers.TUNGSTEN
    private val ALLOY_TIER = ModTiers.ALLOY
    private val DEBRIS_ALLOY_TIER = ModTiers.DEBRIS_ALLOY
    private fun colored(
        name: String, color: Int, tooltip: String? = null,
        build: (Item.Properties) -> Item = { Item(it) },
    ) = REGISTRY.registerItem(name) { p ->
        val title = Component.translatable("item.${Newfound_battles.ID}.$name").withStyle { it.withColor(color) }
        var props = p.component(DataComponents.ITEM_NAME, title)
        if (tooltip != null) {
            val line = Component.translatable("tooltip.${Newfound_battles.ID}.$tooltip")
                .withStyle { it.withColor(0xAAAAAA).withItalic(false) }
            props = props.component(DataComponents.LORE, ItemLore(listOf(line)))
        }
        build(props)
    }
    val TUNGSTEN_SWORD = colored("tungsten_sword", 0x91BA93) {
        SwordItem(TUNGSTEN_TIER, it.attributes(SwordItem.createAttributes(TUNGSTEN_TIER, 3, -2.4f)))
    }
    val TUNGSTEN_PICKAXE = colored("tungsten_pickaxe", 0x91BA93) {
        PickaxeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 1.0f, -3.0f)))
    }
    val TUNGSTEN_AXE = colored("tungsten_axe", 0x91BA93) {
        AxeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 5.0f, -3.0f)))
    }
    val TUNGSTEN_SHOVEL = colored("tungsten_shovel", 0x91BA93) {
        ShovelItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 1.5f, -3.0f)))
    }
    val TUNGSTEN_HOE = colored("tungsten_hoe", 0x91BA93) {
        HoeItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, -3.0f, 0.0f)))
    }
    val TUNGSTEN_PAXEL = colored("tungsten_paxel", 0x91BA93, tooltip = "tungsten_paxel") {
        PaxelItem(TUNGSTEN_TIER, it.attributes(DiggerItem.createAttributes(TUNGSTEN_TIER, 4.0f, -3.0f)))
    }
    val ALLOY_SWORD = colored("alloy_sword", 0xB0CDD4) {
        SwordItem(ALLOY_TIER, it.attributes(SwordItem.createAttributes(ALLOY_TIER, 4, -2.3f))) // last values are extra damage and speed (can be both - and +)
    }
    val ALLOY_PICKAXE = colored("alloy_pickaxe", 0xB0CDD4) {
        PickaxeItem(ALLOY_TIER, it.attributes(DiggerItem.createAttributes(ALLOY_TIER, 2.0f, -2.7f)))
    }
    val ALLOY_AXE = colored("alloy_axe", 0xB0CDD4) {
        AxeItem(ALLOY_TIER, it.attributes(DiggerItem.createAttributes(ALLOY_TIER, 6.0f, -2.9f))) //base attack speed 4
    }
    val ALLOY_SHOVEL = colored("alloy_shovel", 0xB0CDD4) {
        ShovelItem(ALLOY_TIER, it.attributes(DiggerItem.createAttributes(ALLOY_TIER, 2.5f, -2.9f)))
    }
    val ALLOY_HOE = colored("alloy_hoe", 0xB0CDD4) {
        HoeItem(ALLOY_TIER, it.attributes(DiggerItem.createAttributes(ALLOY_TIER, -2.5f, 0.0f)))
    }
    val ALLOY_PAXEL = colored("alloy_paxel", 0xB0CDD4, tooltip = "alloy_paxel") {
        PaxelItem(ALLOY_TIER, it.attributes(DiggerItem.createAttributes(ALLOY_TIER, 5.0f, -2.9f)))
    }
    val DEBRIS_ALLOY_SWORD = colored("debris_alloy_sword", 0x9E2933) {
        SwordItem(DEBRIS_ALLOY_TIER, it.attributes(SwordItem.createAttributes(DEBRIS_ALLOY_TIER, 6.5f, -2.0f)))
    }
    val DEBRIS_ALLOY_PICKAXE = colored("debris_alloy_pickaxe", 0x9E2933) {
        PickaxeItem(DEBRIS_ALLOY_TIER, it.attributes(DiggerItem.createAttributes(DEBRIS_ALLOY_TIER, 4.5f, -2.6f)))
    }
    val DEBRIS_ALLOY_AXE = colored("debris_alloy_axe", 0x9E2933) {
        AxeItem(DEBRIS_ALLOY_TIER, it.attributes(DiggerItem.createAttributes(DEBRIS_ALLOY_TIER, 8.5f, -2.8f)))
    }
    val DEBRIS_ALLOY_SHOVEL = colored("debris_alloy_shovel", 0x9E2933) {
        ShovelItem(DEBRIS_ALLOY_TIER, it.attributes(DiggerItem.createAttributes(DEBRIS_ALLOY_TIER, 4.5f, -2.8f)))
    }
    val DEBRIS_ALLOY_HOE = colored("debris_alloy_hoe", 0x9E2933) {
        HoeItem(DEBRIS_ALLOY_TIER, it.attributes(DiggerItem.createAttributes(DEBRIS_ALLOY_TIER, -1.5f, 1.0f)))
    }
    val DEBRIS_ALLOY_PAXEL = colored("debris_alloy_paxel", 0x9E2933, tooltip = "debris_alloy_paxel") {
        PaxelItem(DEBRIS_ALLOY_TIER, it.attributes(DiggerItem.createAttributes(DEBRIS_ALLOY_TIER, 10.5f, -3.0f)))
    }

    val TUNGSTEN_TOOLS = listOf(TUNGSTEN_SWORD, TUNGSTEN_PICKAXE, TUNGSTEN_AXE, TUNGSTEN_SHOVEL, TUNGSTEN_HOE, TUNGSTEN_PAXEL)
    val ALLOY_TOOLS = listOf(ALLOY_SWORD, ALLOY_PICKAXE, ALLOY_AXE, ALLOY_SHOVEL, ALLOY_HOE, ALLOY_PAXEL)
    val DEBRIS_ALLOY_TOOLS = listOf(DEBRIS_ALLOY_SWORD, DEBRIS_ALLOY_PICKAXE, DEBRIS_ALLOY_AXE, DEBRIS_ALLOY_SHOVEL, DEBRIS_ALLOY_HOE, DEBRIS_ALLOY_PAXEL)
}