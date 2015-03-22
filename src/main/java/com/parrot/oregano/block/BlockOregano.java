package com.parrot.oregano.block;

import com.parrot.oregano.Oregano;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.awt.*;

/**
 * Created by Shane on 3/14/2015.
 */
public abstract class BlockOregano extends Block {

    public BlockOregano(Material material)
    {
        super(material);
    }

    public BlockOregano()
    {
        super(Material.rock);
    }


    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", Oregano.MODID.toLowerCase(),super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1) );
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon= iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.')+1));
    }


}
