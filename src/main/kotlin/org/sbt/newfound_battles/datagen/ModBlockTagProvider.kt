package org.sbt.newfound_battles.datagen
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import org.sbt.newfound_battles.block.ModBlocks
import java.util.concurrent.CompletableFuture
class ModBlockTagProvider(
    out: PackOutput, lookup: CompletableFuture<HolderLookup.Provider>,
    id: String, files: ExistingFileHelper
) : BlockTagsProvider(out, lookup, id, files) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.TUNGSTEN_ORE.get())
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.TUNGSTEN_ORE.get())
    }
}