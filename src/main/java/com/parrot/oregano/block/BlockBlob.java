package com.parrot.oregano.block;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.network.PacketHandler;
import com.parrot.oregano.network.message.MessagePlaySoundToClient;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Shane on 3/14/2015.
 */
public class BlockBlob extends BlockBush implements ITileEntityProvider, IGrowable {

    public BlockBlob()
    {
        super();
        setBlockName("blob");
        setBlockTextureName("sand");
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

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBlob();
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        //
    //    //    //
        //
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ)
    {
        boolean remote=world.isRemote;
        if(!remote) {
            MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
            TileEntityBlob tileEntity = ((TileEntityBlob) world.getTileEntity(x, y, z));
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(sideX+"_"+sideY+"_"+sideZ);
            tileEntity.x += ((sideX - 0.5F) / 2.0F);
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(((sideX-0.5F)/2.0F)+"!");
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(tileEntity.x+"!");
            //Minecraft.getMinecraft().thePlayer.sendChatMessage(remote+">"+player.getHeldItem()+"!"+player.getItemInUse()+"?"+player.isSneaking()+"("+tileEntity.inventoryItem+")");
            if (player.getHeldItem() != null && !player.isSneaking() && null == tileEntity.inventoryItem) {
                //Minecraft.getMinecraft().thePlayer.sendChatMessage(remote+">"+player.getHeldItem()+"into inwentory()");
                tileEntity.inventoryItem = player.getHeldItem().copy();
                tileEntity.inventoryItem.stackSize=1;
                player.getHeldItem().stackSize--;
                tileEntity.dirty=true;
            } else if (player.isSneaking() && tileEntity.inventoryItem != null) {
                EntityItem entItem = new EntityItem(world, x, y, z, tileEntity.inventoryItem.copy());
                //entItem.
                if (tileEntity.inventoryItem.hasTagCompound())
                {
                    entItem.getEntityItem().setTagCompound((NBTTagCompound)tileEntity.inventoryItem.getTagCompound().copy());
                }
                entItem.motionY=0F;
                entItem.motionX=0F;
                entItem.motionZ=0F;

                world.spawnEntityInWorld(entItem);

                tileEntity.inventoryItem = null;
                tileEntity.dirty=true;
            }
            world.markTileEntityChunkModified(x, y, z, tileEntity);


        }

        return true;
    }

    @Override
    public void updateTick(World world,int x, int y,int z,Random rand)
    {
        func_149853_b(world,rand,x,y,z);
    }

    @Override
    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
        //IS STILL GROWING method. returns true if plant is not yet fully grown.
        return true;
    }

    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
        //CAN BE BONED method. returns true if can be sped up by using bonemeal.
        return true;
    }

    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        //GROW BABY GROW method. increment growth stage.

        String s1 = "fireworks." + "blast" + "_far";
        world.playSound(x, y, z, s1, 20.0F, 15.95F  , true);
        PacketHandler.INSTANCE.sendToAll(new MessagePlaySoundToClient(s1,x,y,z));

//        TileEntityBlob tileEntity = (TileEntityBlob) world.getTileEntity(x,y,z);
//
//        if(   tileEntity.inventoryItem==null   )
//        {
//            ItemStack itemStack = new ItemStack(Items.golden_carrot);
//            tileEntity.inventoryItem=itemStack;
            LogHelper.info("Setting stack.");
//        }
//
//        if(!world.isRemote) {
//            world.setBlock(x, y + 1, z, ModBlocks.blob);
//            world.markBlockForUpdate(x, y+1,z );
//            LogHelper.info("Setting block:"+random.nextInt());
//        }

    }
}
