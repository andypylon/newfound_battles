package org.sbt.newfound_battles.datagen
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import org.sbt.newfound_battles.item.ModItems
class ModItemModelProvider(out: PackOutput, id: String, files: ExistingFileHelper) :
    ItemModelProvider(out, id, files) {
    override fun registerModels() {
        basicItem(ModItems.TUNGSTEN.get())
        basicItem(ModItems.TUNGSTEN_BAR.get())
        basicItem(ModItems.TUNGSTEN_NUGGET.get())
        ModItems.TUNGSTEN_ARMOR.forEach { basicItem(it.get())}
        ModItems.TUNGSTEN_TOOLS.forEach { handheldItem(it.get()) }
        basicItem(ModItems.RAW_RHODIUM.get())
        basicItem(ModItems.RAW_PLATINUM.get())
        basicItem(ModItems.PLATINUM_RHODIUM_AMALGATE.get())
        basicItem(ModItems.DEBRIS_RHODIUM_PLATINUM_MIXTURE.get())
        basicItem(ModItems.DEBRIS_ALLOY_BAR.get())
        basicItem(ModItems.PLATINUM_RHODIUM_ALLOY_BAR.get())
        ModItems.ALLOY_ARMOR.forEach { basicItem(it.get())}
        ModItems.ALLOY_TOOLS.forEach { handheldItem(it.get()) }
        ModItems.DEBRIS_ALLOY_ARMOR.forEach { basicItem(it.get())}
        ModItems.DEBRIS_ALLOY_TOOLS.forEach { handheldItem(it.get()) }
    }
}