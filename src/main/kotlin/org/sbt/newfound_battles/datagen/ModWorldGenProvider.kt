package org.sbt.newfound_battles.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.registries.NeoForgeRegistries
import org.sbt.newfound_battles.Newfound_battles
import org.sbt.newfound_battles.worldgen.ModWorldGen
import java.util.concurrent.CompletableFuture

class ModWorldGenProvider(out: PackOutput, lookup: CompletableFuture<HolderLookup.Provider>) :
    DatapackBuiltinEntriesProvider(out, lookup, BUILDER, setOf(Newfound_battles.ID)) {
    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldGen::configured)
            .add(Registries.PLACED_FEATURE, ModWorldGen::placed)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModWorldGen::biomeModifiers)
    }
}