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