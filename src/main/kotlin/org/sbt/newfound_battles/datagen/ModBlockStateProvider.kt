package org.sbt.newfound_battles.datagen
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import org.sbt.newfound_battles.block.ModBlocks
class ModBlockStateProvider(out: PackOutput, id: String, files: ExistingFileHelper) :
    BlockStateProvider(out, id, files) {
    override fun registerStatesAndModels() {
        // blockstate + block model + item model in one call
        for (b in ModBlocks.ORES)
            simpleBlockWithItem(b.get(), cubeAll(b.get()))
    }
}