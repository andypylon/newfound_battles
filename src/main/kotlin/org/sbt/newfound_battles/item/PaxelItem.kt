package org.sbt.newfound_battles.item

import net.minecraft.world.InteractionResult
import net.minecraft.world.item.DiggerItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.Tier
import net.minecraft.world.item.context.UseOnContext
import org.sbt.newfound_battles.ModTags

class PaxelItem(tier: Tier, props: Item.Properties) :
    DiggerItem(tier, ModTags.Blocks.MINEABLE_WITH_PAXEL, props) {

    override fun useOn(ctx: UseOnContext): InteractionResult {
        val axe = Items.IRON_AXE.useOn(ctx)
        if (axe != InteractionResult.PASS) return axe
        // grass can be both flattened and tilled, so sneak picks the hoe
        val other = if (ctx.player?.isShiftKeyDown == true) Items.IRON_HOE else Items.IRON_SHOVEL
        return other.useOn(ctx)
    }
}