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
    }
}