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
            15,                                      // enchantability
            SoundEvents.ARMOR_EQUIP_GOLD,            // equip sound
            { Ingredient.of(ModItems.TUNGSTEN_BAR.get()) },   // anvil repair item
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, "tungsten"))),
            1.3f,                                    // toughness
            0.2f                                     // knockback resistance
        )
    }

    val ALLOY: DeferredHolder<ArmorMaterial, ArmorMaterial> = REGISTRY.register("alloy") { ->
        ArmorMaterial(
            mapOf(                                   // defense points per piece
                ArmorItem.Type.BOOTS to 3,
                ArmorItem.Type.LEGGINGS to 6,
                ArmorItem.Type.CHESTPLATE to 8,
                ArmorItem.Type.HELMET to 4,
            ),
            15,                                      // enchantability
            SoundEvents.ARMOR_EQUIP_DIAMOND,            // equip sound
            { Ingredient.of(ModItems.PLATINUM_RHODIUM_ALLOY_BAR.get()) },   // anvil repair item
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, "alloy"))),
            3.0f,                                    // toughness
            0.2f                                     // knockback resistance
        )
    }

    val DEBRIS_ALLOY: DeferredHolder<ArmorMaterial, ArmorMaterial> = REGISTRY.register("debris_alloy") { ->
        ArmorMaterial(
            mapOf(                                   // defense points per piece
                ArmorItem.Type.BOOTS to 5,
                ArmorItem.Type.LEGGINGS to 8,
                ArmorItem.Type.CHESTPLATE to 10,
                ArmorItem.Type.HELMET to 4,
            ),
            25,                                      // enchantability
            SoundEvents.ARMOR_EQUIP_NETHERITE,            // equip sound
            { Ingredient.of(ModItems.DEBRIS_ALLOY_BAR.get()) },   // anvil repair item
            listOf(ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, "debris_alloy"))),
            5.0f,                                    // toughness
            0.5f                                     // knockback resistance
        )
    }
}