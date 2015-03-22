package com.parrot.oregano.item;

import com.parrot.oregano.Oregano;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Shane on 3/13/2015.
 */
public class ItemOregano extends Item {

    public ItemOregano()
    {
        super();
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s:%s", Oregano.MODID.toLowerCase(),super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.') + 1) );
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s:%s", Oregano.MODID.toLowerCase(),super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf('.')+1) );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon= iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.')+1));
    }

}
