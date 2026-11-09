package org.sbt.newfound_battles.item

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import org.sbt.newfound_battles.Newfound_battles

object ModArmorMaterials {
    val REGISTRY: DeferredRegister<ArmorMaterial> = DeferredRegister.create(Registries.ARMOR_MATERIAL, Newfound_battles.ID)

    val TUNGSTEN: DeferredHolder<ArmorMaterial, ArmorMaterial> = REGISTRY.register("tungsten") { ->
        ArmorMaterial(
            mapOf(                                   // defense points per piece
                ArmorItem.Type.BOOTS to 2,
                ArmorItem.Type.LEGGINGS to 6,
                ArmorItem.Type.CHESTPLATE to 7,
                ArmorItem.Type.HELMET to 3,
            ),
            12,                                      // enchantability
            SoundEvents.ARMOR_EQUIP_GOLD,            // equip sound
            { Ingredient.of(ModItems.TUNGSTEN_BAR.get()) },   // anvil repair item
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, "tungsten"))),
            1.3f,                                    // toughness
            0.2f                                     // knockback resistance
        )
    }
}