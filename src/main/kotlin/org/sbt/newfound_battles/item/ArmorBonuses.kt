package org.sbt.newfound_battles.item
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item
import net.neoforged.neoforge.event.ItemAttributeModifierEvent
import net.neoforged.neoforge.event.tick.PlayerTickEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import org.sbt.newfound_battles.Newfound_battles
object ArmorBonuses {
    class Bonus(
        val attribute: Holder<Attribute>, val amount: Double, val op: AttributeModifier.Operation,
        val slot: EquipmentSlotGroup? = null,
    )

    val BONUSES: Map<Item, List<Bonus>> by lazy {
        mapOf(
            ModItems.TUNGSTEN_CHESTPLATE.get() to listOf(
                Bonus(Attributes.MAX_HEALTH, 2.0, AttributeModifier.Operation.ADD_VALUE)),            // +1 heart
            ModItems.TUNGSTEN_BOOTS.get() to listOf(
                Bonus(Attributes.MOVEMENT_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)), // +10% speed
            ModItems.TUNGSTEN_SWORD.get() to listOf(
                Bonus(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.TUNGSTEN_PICKAXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.TUNGSTEN_AXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.TUNGSTEN_SHOVEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.TUNGSTEN_HOE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.TUNGSTEN_PAXEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_SWORD.get() to listOf(
                Bonus(Attributes.ENTITY_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_AXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_SHOVEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_PICKAXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_HOE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.ALLOY_PAXEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
//            ModItems.ALLOY_HELMET.get() to listOf(
//                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)),
//            ModItems.ALLOY_CHESTPLATE.get() to listOf(
//                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)),
            ModItems.ALLOY_CHESTPLATE.get() to listOf(
                Bonus(Attributes.MAX_HEALTH, 4.0, AttributeModifier.Operation.ADD_VALUE)), // 2 points = 1 heart
//            ModItems.ALLOY_LEGGINGS.get() to listOf(
//                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)),
//            ModItems.ALLOY_BOOTS.get() to listOf(
//                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)),
            ModItems.ALLOY_BOOTS.get() to listOf(
                Bonus(Attributes.MOVEMENT_SPEED, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
            ModItems.DEBRIS_ALLOY_SWORD.get() to listOf(
                Bonus(Attributes.ENTITY_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_AXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_SHOVEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_PICKAXE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_HOE.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_PAXEL.get() to listOf(
                Bonus(Attributes.BLOCK_INTERACTION_RANGE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)),
            ModItems.DEBRIS_ALLOY_CHESTPLATE.get() to listOf(
                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.5, AttributeModifier.Operation.ADD_VALUE)),
            ModItems.DEBRIS_ALLOY_CHESTPLATE.get() to listOf(
                Bonus(Attributes.MAX_HEALTH, 10.0, AttributeModifier.Operation.ADD_VALUE)), // 2 points = 1 heart
            ModItems.DEBRIS_ALLOY_BOOTS.get() to listOf(
                Bonus(Attributes.KNOCKBACK_RESISTANCE, 0.2, AttributeModifier.Operation.ADD_VALUE)),
            ModItems.DEBRIS_ALLOY_BOOTS.get() to listOf(
                Bonus(Attributes.MOVEMENT_SPEED, 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                Bonus(Attributes.JUMP_STRENGTH, 0.6, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)),
        )
    }

    fun onItemModifiers(event: ItemAttributeModifierEvent) {
        val item = event.itemStack.item
        val bonuses = BONUSES[item] ?: return
        val itemName = BuiltInRegistries.ITEM.getKey(item).path
        bonuses.forEachIndexed { i, b ->
            val slot = b.slot
                ?: (item as? ArmorItem)?.let { EquipmentSlotGroup.bySlot(it.type.slot) }
                ?: return@forEachIndexed
            event.addModifier(
                b.attribute,
                AttributeModifier(ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, "${itemName}_$i"), b.amount, b.op),
                slot
            )
        }
    }
}