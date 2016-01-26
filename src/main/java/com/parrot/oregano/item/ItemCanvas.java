package com.parrot.oregano.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Shane on 3/13/2015.
 */
public class ItemCanvas extends ItemOregano {


    public ItemCanvas()
    {
        super();
        setUnlocalizedName("canvas");
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 16;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {

        return false;//continue processing use
    }
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float sideX, float sideY, float sideZ)
    {

        return true;
    }



    @SideOnly(Side.CLIENT)
    private IIcon iconCanvasx16;
    @SideOnly(Side.CLIENT)
    private IIcon iconCanvasx32;
    @SideOnly(Side.CLIENT)
    private IIcon iconCanvasx64;
    @SideOnly(Side.CLIENT)
    private IIcon iconCanvasx128;
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        iconCanvasx16 = par1IconRegister.registerIcon("oregano:canvas_16");
        iconCanvasx32 = par1IconRegister.registerIcon("oregano:canvas_32");
        iconCanvasx64= par1IconRegister.registerIcon("oregano:canvas_64");
        iconCanvasx128 = par1IconRegister.registerIcon("oregano:canvas_128");
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int parDamageVal ) {
        switch(parDamageVal) {
            case 0:
                return iconCanvasx16;
            case 1:
                return iconCanvasx32;
            case 2:
                return iconCanvasx64;
            case 3:
                return iconCanvasx128;

        }
        return iconCanvasx16;
    }
    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    @Override
    public void getSubItems(Item stack, CreativeTabs tabs, List list) {
        list.add(new ItemStack(this, 1,0));
        list.add(new ItemStack(this, 1,1));
        list.add(new ItemStack(this, 1,2));
        list.add(new ItemStack(this, 1,3));
    }





    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        super.onCreated(itemStack, world, player);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {

        NBTTagCompound tag = CheckGetMakeTag(itemStack);
        switch(itemStack.getItemDamage())
        {
            case 0:
                list.add("Size: 16x16");
                break;
            case 1:
                list.add("Size: 32x32");
                break;
            case 2:
                list.add("Size: 64x64");
                break;
            case 3:
                list.add("Size: 128x128");
                break;
        }
    }

    private NBTTagCompound CheckGetMakeTag(ItemStack itemStack) {
        if(itemStack.stackTagCompound==null)
        {
            itemStack.stackTagCompound=new NBTTagCompound();
            itemStack.stackTagCompound.setInteger("colour",0xFFFFFF);
        }
        return itemStack.stackTagCompound;
    }


}
