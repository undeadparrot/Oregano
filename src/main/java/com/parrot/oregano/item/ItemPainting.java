package com.parrot.oregano.item;

import com.parrot.oregano.block.BlockEasel;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Shane on 3/13/2015.
 */
public class ItemPainting extends ItemOregano {

    public ItemPainting()
    {
        super();
        setUnlocalizedName("painting");
    }
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
//    @Override
//    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
//    {
//        LogHelper.info("pbrush>onItemUseFirst");
//        Block block = world.getBlock(x,y,z);
//        if(block instanceof BlockEasel){
//
//        }
//        return true;//continue processing use
//    }
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World p_77659_2_, EntityPlayer player)
    {
        NBTTagCompound tag = CheckGetMakeTag(itemStack);
        tag.setInteger("res",32);
        return itemStack;
    }

    @Override
    public int getItemStackLimit() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List list, boolean par4)
    {
        NBTTagCompound tag = CheckGetMakeTag(itemStack);
        list.add("Size: "+tag.getFloat("w")+"m x "+tag.getFloat("h")+"m");
        list.add("Voila!");
    }

    private NBTTagCompound CheckGetMakeTag(ItemStack itemStack) {
        if(itemStack.stackTagCompound==null)
        {
            itemStack.stackTagCompound=new NBTTagCompound();
            itemStack.stackTagCompound.setFloat("w",0.5F);
            itemStack.stackTagCompound.setFloat("h",1.0f);
            itemStack.stackTagCompound.setInteger("res",16);
        }
        return itemStack.stackTagCompound;
    }


}
