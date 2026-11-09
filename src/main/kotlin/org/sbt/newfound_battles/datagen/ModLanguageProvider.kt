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
        addItem(ModItems.TUNGSTEN_HELMET, "Tungsten Helmet")
        addItem(ModItems.TUNGSTEN_CHESTPLATE, "Tungsten Chestplate")
        addItem(ModItems.TUNGSTEN_LEGGINGS, "Tungsten Leggings")
        addItem(ModItems.TUNGSTEN_BOOTS, "Tungsten Boots")
        addItem(ModItems.TUNGSTEN_SWORD, "Tungsten Sword")
        addItem(ModItems.TUNGSTEN_AXE, "Tungsten Axe")
        addItem(ModItems.TUNGSTEN_PICKAXE, "Tungsten Pickaxe")
        addItem(ModItems.TUNGSTEN_SHOVEL, "Tungsten Shovel")
        addItem(ModItems.TUNGSTEN_HOE, "Tungsten Hoe")
        addItem(ModItems.TUNGSTEN_PAXEL, "Tungsten Paxel")
        add("tooltip.newfound_battles.tungsten_paxel", "The might of the tungsten tools, combined.")
    }
}