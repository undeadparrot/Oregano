package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.item.ItemDesertRose;
import com.parrot.oregano.item.ItemOregano;
import com.parrot.oregano.item.ItemPaintbrush;
import com.parrot.oregano.item.crafting.PaintbrushRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;

import java.util.List;

/**
 * Created by Shane on 3/13/2015.
 */

@GameRegistry.ObjectHolder(Oregano.MODID)
public class ModItems {

    public static final ItemOregano desertRose = new ItemDesertRose();
    public static final ItemPaintbrush paintbrush=new ItemPaintbrush();

    public static void init()
    {
        GameRegistry.registerItem(desertRose,"desertRose");
        GameRegistry.registerItem(paintbrush,"paintbrush");

        RecipeSorter.register("oregano:paintbrush", PaintbrushRecipe.class, RecipeSorter.Category.SHAPELESS,"after:forge:shapelessore");
        ((List<IRecipe>)CraftingManager.getInstance().getRecipeList()).add(new PaintbrushRecipe());

    }

}
