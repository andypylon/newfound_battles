package org.sbt.newfound_battles.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import org.sbt.newfound_battles.Newfound_battles
import org.sbt.newfound_battles.block.ModBlocks
import org.sbt.newfound_battles.item.ModItems
import org.sbt.newfound_battles.item.ModRecipes
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(out: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    RecipeProvider(out, registries) {

    override fun buildRecipes(output: RecipeOutput) {
        ModRecipes.SMELTING.forEach { smelt(output, it) }
        ModRecipes.CRAFTING.forEach {
            when (it) {
                is ModRecipes.Shaped -> shaped(output, it)
                is ModRecipes.Shapeless -> shapeless(output, it)
            }
        }
    }

    private fun name(item: ItemLike) = BuiltInRegistries.ITEM.getKey(item.asItem()).path
    private fun rl(path: String) = ResourceLocation.fromNamespaceAndPath(Newfound_battles.ID, path)

    private fun smelt(out: RecipeOutput, s: ModRecipes.Smelt) {
        val base = "${name(s.result)}_from_${name(s.input)}"
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(s.input), RecipeCategory.MISC, s.result, s.xp, 200)
            .unlockedBy("has_${name(s.input)}", has(s.input))
            .save(out, rl("${base}_smelting"))
        if (s.blast)
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(s.input), RecipeCategory.MISC, s.result, s.xp, 100)
                .unlockedBy("has_${name(s.input)}", has(s.input))
                .save(out, rl("${base}_blasting"))
    }

    private fun shaped(out: RecipeOutput, c: ModRecipes.Shaped) {
        val b = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.result, c.count)
        c.pattern.forEach { b.pattern(it) }
        c.keys.forEach { (char, item) -> b.define(char, item) }
        val trigger = c.keys.values.first()
        b.unlockedBy("has_${name(trigger)}", has(trigger))
            .save(out, rl(c.id ?: "${name(c.result)}_shaped"))
    }

    private fun shapeless(out: RecipeOutput, c: ModRecipes.Shapeless) {
        val b = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, c.result, c.count)
        c.ingredients.forEach { (item, n) -> b.requires(item, n) }
        val trigger = c.ingredients.keys.first()
        b.unlockedBy("has_${name(trigger)}", has(trigger))
            .save(out, rl(c.id ?: "${name(c.result)}_shapeless"))
    }
}