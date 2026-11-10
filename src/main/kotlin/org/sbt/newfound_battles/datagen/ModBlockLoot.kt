package org.sbt.newfound_battles.datagen
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.neoforged.neoforge.common.Tags
import org.sbt.newfound_battles.block.ModBlocks
import org.sbt.newfound_battles.item.ModItems
class ModBlockLoot(registries: HolderLookup.Provider) :
    BlockLootSubProvider(emptySet(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {
        //add(ModBlocks.TUNGSTEN_ORE.get(), createOreDrop(ModBlocks.TUNGSTEN_ORE.get(), ModItems.TUNGSTEN.get()))
        //dropSelf(ModBlocks.TUNGSTEN_ORE.get()) this drops the block and overrides the line above
        add(ModBlocks.TUNGSTEN_ORE.get(), createSilkTouchDispatchTable(
            ModBlocks.TUNGSTEN_ORE.get(), applyExplosionDecay(
                ModBlocks.TUNGSTEN_ORE.get(), LootItem.lootTableItem(
                ModItems.TUNGSTEN.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)))
                    .apply(ApplyBonusCount.addOreBonusCount(registries.holderOrThrow(Enchantments.FORTUNE)))
            ))
        )
        add(ModBlocks.RHODIUM_ORE.get(), createSilkTouchDispatchTable(
            ModBlocks.RHODIUM_ORE.get(), applyExplosionDecay(
                ModBlocks.RHODIUM_ORE.get(), LootItem.lootTableItem(
                    ModItems.RAW_RHODIUM.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                    .apply(ApplyBonusCount.addOreBonusCount(registries.holderOrThrow(Enchantments.FORTUNE)))
            ))
        )
        add(ModBlocks.PLATINUM_ORE.get(), createSilkTouchDispatchTable(
            ModBlocks.PLATINUM_ORE.get(), applyExplosionDecay(
                ModBlocks.PLATINUM_ORE.get(), LootItem.lootTableItem(
                    ModItems.RAW_PLATINUM.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                    .apply(ApplyBonusCount.addOreBonusCount(registries.holderOrThrow(Enchantments.FORTUNE)))
            ))
        )
    }
    // tells the provider which blocks must have a loot table
    override fun getKnownBlocks(): Iterable<Block> = ModBlocks.REGISTRY.entries.map { it.get() }
}