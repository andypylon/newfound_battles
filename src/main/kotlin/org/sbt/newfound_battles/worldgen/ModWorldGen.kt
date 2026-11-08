package org.sbt.newfound_battles.worldgen

import net.minecraft.core.HolderSet
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.NeoForgeRegistries
import org.sbt.newfound_battles.Newfound_battles
import org.sbt.newfound_battles.block.ModBlocks

object ModWorldGen {
    class OreSpec(
        val name: String,
        val block: DeferredBlock<Block>,
        val veinSize: Int,
        val veinsPerChunk: Int,
        val minY: Int,
        val maxY: Int,
        val replaces: TagKey<Block> = BlockTags.STONE_ORE_REPLACEABLES,
        val biomes: TagKey<Biome> = BiomeTags.IS_OVERWORLD,
    )
    val ORES = listOf(
        OreSpec("tungsten_ore", ModBlocks.TUNGSTEN_ORE, veinSize = 3, veinsPerChunk = 8, minY = 5, maxY = 150),
    )
    private fun <T> key(registry: ResourceKey<out Registry<T>>, name: String): ResourceKey<T> =
        ResourceKey.create(registry, ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, name))
    fun configured(ctx: BootstrapContext<ConfiguredFeature<*, *>>) {
        for (ore in ORES) {
            val target = OreConfiguration.target(TagMatchTest(ore.replaces), ore.block.get().defaultBlockState())
            ctx.register(
                key(Registries.CONFIGURED_FEATURE, ore.name),
                ConfiguredFeature(Feature.ORE, OreConfiguration(listOf(target), ore.veinSize))
            )
        }
    }
    fun placed(ctx: BootstrapContext<PlacedFeature>){
        val configured = ctx.lookup(Registries.CONFIGURED_FEATURE)
        for (ore in ORES) {
            ctx.register(
                key(Registries.PLACED_FEATURE, ore.name),
                PlacedFeature(
                    configured.getOrThrow(key(Registries.CONFIGURED_FEATURE, ore.name)),
                    listOf(
                        CountPlacement.of(ore.veinsPerChunk),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(ore.minY), VerticalAnchor.absolute(ore.maxY)),
                        BiomeFilter.biome()
                    )
                )
            )
        }
    }
    fun biomeModifiers(ctx: BootstrapContext<BiomeModifier>){
        val biomes = ctx.lookup(Registries.BIOME)
        val placed = ctx.lookup(Registries.PLACED_FEATURE)
        for (ore in ORES){
            ctx.register(
                key(NeoForgeRegistries.Keys.BIOME_MODIFIERS, "add_${ore.name}"),
                BiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(ore.biomes),
                    HolderSet.direct(placed.getOrThrow(key(Registries.PLACED_FEATURE, ore.name))),
                    GenerationStep.Decoration.UNDERGROUND_ORES
                )
            )
        }
    }
}