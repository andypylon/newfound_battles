package org.sbt.newfound_battles.datagen
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.neoforge.data.event.GatherDataEvent
import org.sbt.newfound_battles.Newfound_battles
object DataGenerators {
    fun gatherData(event: GatherDataEvent){
        val gen = event.generator
        val out = gen.packOutput
        val files = event.existingFileHelper
        val lookup = event.lookupProvider
        val id = Newfound_battles.ID

        gen.addProvider(event.includeClient(), ModBlockStateProvider(out, id, files))
        gen.addProvider(event.includeClient(), ModItemModelProvider(out, id, files))
        gen.addProvider(event.includeClient(), ModLanguageProvider(out, id, "en_us"))
        gen.addProvider(event.includeServer(), ModBlockTagProvider(out, lookup, id, files))
        gen.addProvider(event.includeServer(), LootTableProvider(
            out, emptySet(),
            listOf(LootTableProvider.SubProviderEntry({ModBlockLoot(it)}, LootContextParamSets.BLOCK)),
            lookup
        ))
        gen.addProvider(event.includeServer(), ModWorldGenProvider(out, lookup))
        gen.addProvider(event.includeServer(), ModRecipeProvider(out, lookup))
    }
}