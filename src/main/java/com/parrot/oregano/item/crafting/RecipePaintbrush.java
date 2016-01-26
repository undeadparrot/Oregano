package com.parrot.oregano.item.crafting;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.init.ModItems;
import com.parrot.oregano.item.ItemPaintbrush;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by smatu on 1/17/2016.
 */
public class RecipePaintbrush implements IRecipe {
    ItemStack itemstackPaintbrush=null;
    ItemStack itemstackDye = null;

    int oreBlack = OreDictionary.getOreID("dyeBlack");
    int oreRed = OreDictionary.getOreID("dyeRed");
    int oreGreen = OreDictionary.getOreID("dyeGreen");
    int oreBrown = OreDictionary.getOreID("dyeBrown");
    int oreBlue = OreDictionary.getOreID("dyeBlue");
    int orePurple = OreDictionary.getOreID("dyePurple");
    int oreCyan = OreDictionary.getOreID("dyeCyan");
    int oreLightGray = OreDictionary.getOreID("dyeLightGray");
    int oreGray = OreDictionary.getOreID("dyeGray");
    int orePink = OreDictionary.getOreID("dyePink");
    int oreLime = OreDictionary.getOreID("dyeLime");
    int oreYellow = OreDictionary.getOreID("dyeYellow");
    int oreLightBlue = OreDictionary.getOreID("dyeLightBlue");
    int oreMagenta = OreDictionary.getOreID("dyeMagenta");
    int oreOrange = OreDictionary.getOreID("dyeOrange");
    int oreWhite = OreDictionary.getOreID("dyeWhite");

    @Override
    public boolean matches(InventoryCrafting inv, World world) {

        int paintbrushslot = 0;
        int invSize=inv.getSizeInventory();
        itemstackPaintbrush=null;
        itemstackDye = null;
        int dyeOredictId = OreDictionary.getOreID("dye");
        for(int i =0; i<invSize && itemstackDye==null;i++)
        {
            ItemStack inslot = inv.getStackInSlot(i);
            if(inslot==null)
            {
                continue;
            }
            if(inslot.getItem() instanceof ItemPaintbrush)
            {
                itemstackPaintbrush=inslot;
                paintbrushslot=i;
                break;
            }
        }
        if(itemstackPaintbrush==null){
            return false;
        }
        for(int i =0; i<invSize  ;i++)
        {
            ItemStack inslot = inv.getStackInSlot(i);
            if(inslot==null)
            {
                continue;
            }
            if(itemstackDye!=null && i!=paintbrushslot)
            {
                return false;
            }
            int[] slotOredictIds = OreDictionary.getOreIDs(inslot);
            for (int oredictId : slotOredictIds)
            {
                if(oredictId==dyeOredictId){
                    itemstackDye=inslot;
                }
            }

        }

        if(itemstackDye==null){
            return false;
        }
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = new ItemStack(ModItems.paintbrush,1,itemstackPaintbrush.getItemDamage());
        NBTTagCompound oldtag=itemstackPaintbrush.getTagCompound();
        NBTTagCompound newtag=new NBTTagCompound();
        int currentcolour=0xFFFFFF;
        if(oldtag==null)
        {
            oldtag = new NBTTagCompound();
        }else{
            currentcolour=oldtag.getInteger("colour");
        }
        newtag.setInteger("colour",getBlendedIntRGB(currentcolour,getIntRGBFromDye(itemstackDye)));
        result.setTagCompound(newtag);


        return (ItemStack)result;
    }

    private int getBlendedIntRGB(int col1, int col2) {
        int r1 = col1>>16;
        int g1 = (col1&0x00ff00)>>8;
        int b1 = col1&0x0000ff;
        int r2 = col2>>16;
        int g2 = (col2&0x00ff00)>>8;
        int b2 = col2&0x0000ff;
        int r = (r1/2)+(r2/2);
        int g = (g1/2)+(g2/2);
        int b = (b1/2)+(b2/2);
        int colour =0x00000000;
        colour=colour|(r<<16);
        colour=colour|(g<<8);
        colour=colour|(b);
        return (colour);
    }

    public int getIntFromRGB(int[] rgb)
    {
        if(rgb.length<3)
        {
            return 0;
        }
        return (rgb[0]<<16)+
                (rgb[1]<<8)+
                (rgb[2]);
    }
    public int getIntRGBFromDye(ItemStack stack){

        for (int ore : OreDictionary.getOreIDs(stack)){


            if(ore==oreBlack) return 0x191616;
            if(ore==oreRed) return 0x963430;
            if(ore==oreGreen) return 0x35461B;
            if(ore==oreBrown) return 0x4F321F;
            if(ore==oreBlue) return 0x2E388D;
            if(ore==orePurple) return 0x7E3DB5;
            if(ore==oreCyan) return 0x2E6E89;
            if(ore==oreLightGray) return 0x9AA1A1;
            if(ore==oreGray) return 0x404040;
            if(ore==orePink) return 0xD08499;
            if(ore==oreLime) return 0x41AE38;
            if(ore==oreYellow) return 0xB1A627;
            if(ore==oreLightBlue) return 0x6B8AC9 ;
            if(ore==oreMagenta) return 0xB350BC;
            if(ore==oreOrange) return 0xDB7D3E;
            if(ore==oreWhite) return 0xDDDDDD;


        }
        return 0x0;
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(ModItems.paintbrush,1,itemstackPaintbrush.getItemDamage());
    }
}
