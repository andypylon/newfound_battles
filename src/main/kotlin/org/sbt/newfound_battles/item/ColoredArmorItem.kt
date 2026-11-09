package org.sbt.newfound_battles.item

import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ColoredArmorItem(
    material: Holder<ArmorMaterial>,
    type: ArmorItem.Type,
    props: Item.Properties,
    private val color: Int,
) : ArmorItem(material, type, props) {
    override fun getName(stack: ItemStack): Component =
        super.getName(stack).copy().withStyle { it.withColor(color) }
}