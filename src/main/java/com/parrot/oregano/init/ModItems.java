package com.parrot.oregano.init;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.item.*;
import com.parrot.oregano.item.crafting.RecipePaintbrush;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    public static final ItemCanvas canvas=new ItemCanvas();
    public static final ItemPainting painting = new ItemPainting();

    public static void init()
    {
        GameRegistry.registerItem(desertRose,"desertRose");
        GameRegistry.registerItem(paintbrush,"paintbrush");
        GameRegistry.registerItem(canvas,"canvas");
        GameRegistry.registerItem(painting,"painting");


        RecipeSorter.register("oregano:paintbrush", RecipePaintbrush.class, RecipeSorter.Category.SHAPELESS,"after:forge:shapelessore");
        GameRegistry.addShapelessRecipe(new ItemStack(canvas,1,0),new Item[]{Items.painting,Items.paper,Items.paper,Items.paper,Items.paper,Items.paper,Items.paper,Items.paper,Items.paper});
        GameRegistry.addShapelessRecipe(new ItemStack(canvas,1,1),new Object[]{new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0),new ItemStack(canvas,1,0)});
        GameRegistry.addShapelessRecipe(new ItemStack(canvas,1,2),new Object[]{new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1),new ItemStack(canvas,1,1)});
        GameRegistry.addShapelessRecipe(new ItemStack(canvas,1,3),new Object[]{new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2),new ItemStack(canvas,1,2)});
        ((List<IRecipe>)CraftingManager.getInstance().getRecipeList()).add(new RecipePaintbrush());

    }

}
