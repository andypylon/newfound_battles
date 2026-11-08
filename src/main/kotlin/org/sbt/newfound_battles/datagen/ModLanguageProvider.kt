package org.sbt.newfound_battles.datagen
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider
import org.sbt.newfound_battles.block.ModBlocks
import org.sbt.newfound_battles.item.ModItems
class ModLanguageProvider(out: PackOutput, id: String, locale: String) :
    LanguageProvider(out, id, locale) {
    override fun addTranslations() {
        addBlock(ModBlocks.TUNGSTEN_ORE, "Tungsten Ore")
        addItem(ModItems.TUNGSTEN, "Raw Tungsten")
        addItem(ModItems.TUNGSTEN_BAR, "Tungsten Bar")
        addItem(ModItems.TUNGSTEN_NUGGET, "Tungsten Nugget")
    }
}