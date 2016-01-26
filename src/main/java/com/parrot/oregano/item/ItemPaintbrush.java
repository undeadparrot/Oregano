package com.parrot.oregano.item;

import com.parrot.oregano.block.BlockEasel;
import com.parrot.oregano.tileentity.TileEntityEasel;
import com.parrot.oregano.util.BlockHelper;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.*;

import static com.parrot.oregano.util.IntersectionHelper.intersectLinePlane;

/**
 * Created by Shane on 3/13/2015.
 */
public class ItemPaintbrush extends ItemOregano {


    public ItemPaintbrush()
    {
        super();
        setUnlocalizedName("paintbrush");
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32000;
    }
    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }
    /**
     * Called each tick while using an item.
     * @param stack The Item being used
     * @param player The Player using the item
     * @param count The amount of time in tick the item has been used for continuously
     */
    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        LogHelper.info("duration "+count);
        if(count%1==0) {
            //LogHelper.info("duration "+count);
            MovingObjectPosition hit = player.rayTrace(2F, 1.0F);

            TileEntity tileEntityGeneric = player.worldObj.getTileEntity(hit.blockX,hit.blockY,hit.blockZ);
            if(tileEntityGeneric!=null && tileEntityGeneric instanceof TileEntityEasel)
            {
                boolean remote=player.worldObj.isRemote;
                TileEntityEasel tileEntity= ((TileEntityEasel) tileEntityGeneric);
                paintBlock(player.worldObj,player,tileEntity,hit.blockX,hit.blockY,hit.blockZ );
            }
        }
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
        LogHelper.info("pbrush>onPlayerStoppedUsing");
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        LogHelper.info("pbrush>onItemUseFirst");
        Block block = world.getBlock(x,y,z);
        NBTTagCompound tag = new NBTTagCompound();
        String blockname = block.getUnlocalizedName();
        if(blockname.contains("openblocks.paintcan")) {
            world.getTileEntity(x, y, z).writeToNBT(tag);
            stack.getTagCompound().setInteger("colour", tag.getInteger("color"));
        }
        return false;//continue processing use
    }
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float sideX, float sideY, float sideZ)
    {
        ForgeDirection fd = BlockHelper.GetFDFromEntity(player,false);
        LogHelper.info("pbrush>FD "+fd+"__IDinv "+BlockHelper.GetFDFromEntity(player,true));
        LogHelper.info("pbrush>p_?_7 "+p_77648_7_+" side(x:y:z)="+sideX+"_"+sideY+"_"+sideZ);
        LogHelper.info("pbrush>onItemUse "+x+"_"+y+"_"+z+" "+p_77648_7_);
        TileEntity tileEntityGeneric = world.getTileEntity(x,y,z);
        if(tileEntityGeneric!=null && tileEntityGeneric instanceof TileEntityEasel)
        {
            boolean remote=world.isRemote;
            TileEntityEasel tileEntity= ((TileEntityEasel) tileEntityGeneric);
            paintBlock(world,player,tileEntity,x,y,z);
            return false;
        }
        return true;
    }

    private void paintBlock(World world, EntityPlayer player, TileEntityEasel tileEntity, int x, int y, int z) {
        ItemStack heldItem = player.getHeldItem();
        if(!(heldItem.getItem()  instanceof ItemPaintbrush))
        {
            return;
        }
        int brushColour = CheckGetMakeTag(heldItem).getInteger("colour");
        MovingObjectPosition mop= Minecraft.getMinecraft().objectMouseOver;
        //   Minecraft.getMinecraft().thePlayer.sendChatMessage(sideX+"_"+sideY+"_"+sideZ);
        float eyeHeight=player.getEyeHeight();
        Vec3 lookVec=player.getLookVec();
        Vec3 headPoint = BlockEasel.getUniversalHeadVec(player);
        Vec3 xyz=Vec3.createVectorHelper(x, y, z);
        Vec3 pos=xyz;
        //lookVec.dotProduct()


        float depth=+0.2F+ BlockEasel.thickness;
        //+0.05//+0.05//-0.05
        float angleconst=tileEntity.tilt;

        float[] canvasSize=tileEntity.getCanvasSize();
        float wid=canvasSize[0];
        float wd=wid/2.0F;
        float height=canvasSize[1];
        float hd=height/2.0F;
        Vec3 planeP1=Vec3.createVectorHelper(-wd, 0.0F, depth+angleconst );
        Vec3 planeP2=Vec3.createVectorHelper(wd, 0.0F, depth+angleconst );
        Vec3 planeP3=Vec3.createVectorHelper(-wd, height, depth-angleconst );



        Vec3 xyzoffset=Vec3.createVectorHelper(0.5F,0.0F,0.5F);
        planeP1=planeP1.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);
        planeP2=planeP2.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);
        planeP3=planeP3.addVector(x,y,z).addVector(xyzoffset.xCoord,xyzoffset.yCoord,xyzoffset.zCoord);

        Vec3 centreofPlane=Vec3.createVectorHelper(
                planeP1.xCoord+((planeP2.xCoord-planeP1.xCoord)/2.0F),
                planeP1.yCoord+((planeP3.yCoord-planeP1.yCoord)/2.0F),
                planeP1.zCoord+((planeP3.zCoord-planeP1.zCoord)/2.0F)
        );


        tileEntity.debugVecMap.put("pP1",xyz.subtract(planeP1));
        tileEntity.debugVecMap.put("pP2",xyz.subtract(planeP2));
        tileEntity.debugVecMap.put("pP3",xyz.subtract(planeP3));
        tileEntity.debugVecMap.put("pO",xyz.subtract(centreofPlane));

        Vec3 planeN =(planeP2.subtract(planeP1).crossProduct(planeP3.subtract(planeP1))   );
        //Vec3 planeN =(planeP3.subtract(planeP1).crossProduct(planeP2.subtract(planeP1))   );//inverted

        Vec3 normalArrow=centreofPlane.addVector(planeN.xCoord*0.5F,planeN.yCoord*0.5F,planeN.zCoord*0.5F);
        tileEntity.debugVecMap.put("pN", xyz.subtract(normalArrow));
        tileEntity.debugVecMap.put("pN_dot1",xyz.subtract(normalArrow.addVector(planeN.xCoord*-0.25F,planeN.yCoord*-0.25F,planeN.zCoord*-0.25F)));
        tileEntity.debugVecMap.put("pN_dot2",xyz.subtract(normalArrow.addVector(planeN.xCoord*0.25F,planeN.yCoord*0.25F,planeN.zCoord*0.25F)));

        Vec3 lineP1=headPoint;
        Vec3 lineP2 = headPoint.addVector(lookVec.xCoord * 3.0F, lookVec.yCoord *3.0F,lookVec.zCoord*3.0F);
        tileEntity.debugVecMap.put("lP2",xyz.subtract(lineP2));
        tileEntity.debugVecMap.put("lP2_dot1",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.25F,lookVec.yCoord*0.25F,lookVec.zCoord*0.25F)));
        tileEntity.debugVecMap.put("lP2_dot2",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.50F,lookVec.yCoord*0.50F,lookVec.zCoord*0.50F)));
        tileEntity.debugVecMap.put("lP2_dot3",xyz.subtract(lineP1.addVector(lookVec.xCoord*0.75F,lookVec.yCoord*0.75F,lookVec.zCoord*0.75F)));

        float u = (float) ((planeN.dotProduct(planeP3.subtract(lineP1)))
                /
                (planeN.dotProduct(lineP2.subtract(lineP1))));
        float u2 = (float)(
                (planeN.dotProduct(planeP1.subtract(lineP1)))
                        /
                        (planeN.dotProduct(lookVec))
        );
        float d= (float) planeN.dotProduct(planeP1);
        Vec3 ab=lineP2.subtract(lineP1);
        float t= (float) ((d - planeN.dotProduct(lineP1) )/ planeN.dotProduct(ab));
        t=Math.abs(t);
        Vec3 poi=null;
        Vec3 poiLocal=null,poiNormalized=null;
        Vec3 planeSize=Vec3.createVectorHelper(planeP2.xCoord-planeP1.xCoord,planeP3.yCoord-planeP1.yCoord,planeP3.zCoord-planeP1.zCoord);
        u2=Math.abs(u2);
        if(u2>=0.0F && u2<=1.0f)
        {
            //LogHelper.info("It intersects!");
            poi = Vec3.createVectorHelper(
                    lineP1.xCoord + (u2 * lookVec.normalize().xCoord),
                    lineP1.yCoord + (u2 * lookVec.normalize().yCoord),
                    lineP1.zCoord + (u2 * lookVec.normalize().zCoord)
            );
            //tileEntity.debugVecMap.put("x",xyz.subtract(poi));

            poiLocal=Vec3.createVectorHelper(poi.xCoord-planeP1.xCoord,poi.yCoord-planeP1.yCoord,poi.zCoord-planeP1.zCoord);
            poiNormalized=Vec3.createVectorHelper(poiLocal.xCoord/planeSize.xCoord,poiLocal.yCoord/planeSize.yCoord,poiLocal.zCoord/planeSize.zCoord);

        }
        //LogHelper.info("intersectionA=" +poi);
        //LogHelper.info("intersectionB=" + );
        poi=intersectLinePlane(lineP1, lookVec, 2.0F, planeP1, planeP3, planeP2);

        if(poi!=null){tileEntity.debugVecMap.put("x",xyz.subtract(poi));}

        AxisAlignedBB aabb=AxisAlignedBB.getBoundingBox(x,y,z,x+0.5F,y+0.5F,z+0.5F);
        LogHelper.info("AABBINTERCEPT:"+aabb.calculateIntercept(lineP1,lineP2));

        tileEntity.testPosition=xyz.subtract(lineP1);
        tileEntity.debugVecMap.put("H",xyz.subtract(lineP1));

        if(poi!=null) {
            poiLocal=Vec3.createVectorHelper(poi.xCoord-planeP1.xCoord,poi.yCoord-planeP1.yCoord,poi.zCoord-planeP1.zCoord);
            poiNormalized=Vec3.createVectorHelper(poiLocal.xCoord/planeSize.xCoord,poiLocal.yCoord/planeSize.yCoord,poiLocal.zCoord/planeSize.zCoord);
            int w = (int) canvasSize[2];
            int h = (int) canvasSize[2];
            int whalf = w/2;
            int hhalf = h/2;
            int cropw = (int)(w*wid);
            int croph = (int)(h*height);
            int topofcrop=(h-croph)/2;
            int ux=whalf-(cropw/2);
            int uy=hhalf-(croph/2);

            int pixx = ux+(int) (poiNormalized.xCoord * cropw);
            int pixy =  topofcrop+((int)((1.0-poiNormalized.yCoord)*croph));//((h)-uy+((int) (poiNormalized.yCoord * croph)));
            if (poiNormalized.xCoord >= 1.0F || poiNormalized.xCoord <= 0.0F || poiNormalized.yCoord <= 0.0F || poiNormalized.yCoord >= 1.0F) {
                //LogHelper.info("not inside the canvas");
                return;
            }
            int pix = pixx + (pixy * tileEntity.width);
            if (tileEntity.data.length >= pix) {
                    paintPixels(tileEntity.data ,pixx,pixy,w,h, brushColour,heldItem.getItemDamage()*2   );
                tileEntity.dirty = true;

            }
        }
    }

    private void paintPixels(int[] data,int x,int y, int w, int h, int rgb, int radius) {
        int hitx,hity;
        rgb = rgb|0xFF000000;
        if(radius<1){
            radius=1;
        }
        for (int i=-radius;i<radius;i++)
        {
            for (int j=-radius;j<radius;j++) {
                hitx = x + i;
                hity = y + j;
                if (hitx < w && hitx >= 0 && hity < h && hity >= 0) {
                    data[(hity * w) + hitx] = rgb;
                }
            }
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World p_77659_2_, EntityPlayer player)
    {
        //LogHelper.info("pbrush>onItemRightClick");
        if(player.isInWater()){
            int brushColour=CheckGetMakeTag(itemStack).getInteger("colour");
            CheckGetMakeTag(itemStack).setInteger("colour",(new Random()).nextInt());
        }
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    @Override
    public int getItemStackLimit() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    private IIcon iconBrush;
    @SideOnly(Side.CLIENT)
    private IIcon iconTip;
    @SideOnly(Side.CLIENT)
    private IIcon iconBigBrush;
    @SideOnly(Side.CLIENT)
    private IIcon iconBigTip;
    @SideOnly(Side.CLIENT)
    private IIcon iconMediumBrush;
    @SideOnly(Side.CLIENT)
    private IIcon iconMediumTip;
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        iconBrush = par1IconRegister.registerIcon("oregano:paintbrush");
        iconTip= par1IconRegister.registerIcon("oregano:paintbrush_tip");
        iconBigBrush = par1IconRegister.registerIcon("oregano:paintbrush_big");
        iconBigTip = par1IconRegister.registerIcon("oregano:paintbrush_big_tip");
        iconMediumBrush = par1IconRegister.registerIcon("oregano:paintbrush_medium");
        iconMediumTip = par1IconRegister.registerIcon("oregano:paintbrush_medium_tip");
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int parDamageVal, int parRenderPass) {
        if(parRenderPass==0)  {
            switch(parDamageVal) {
                case 0:
                    return iconBrush;
                case 1:
                    return iconMediumBrush;
                case 2:
                    return iconBigBrush;
            }
        }
        else{
            switch(parDamageVal) {
                case 0:
                    return iconTip;
                case 1:
                    return iconMediumTip;
                case 2:
                    return iconBigTip;
            }
        }
        return iconBrush;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int parRenderPass) {
        if(parRenderPass==1) {
            return (CheckGetMakeTag(itemStack).getInteger("colour"));
        }
        return 0xFFFFFF;
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
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        super.onCreated(itemStack, world, player);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List list, boolean par4)
    {
        NBTTagCompound tag = CheckGetMakeTag(itemStack);
        switch(itemStack.getItemDamage())
        {
            case 0:
                list.add("Size: Small");
                break;
            case 1:
                list.add("Size: Medium");
                break;
            case 2:
                list.add("Size: Large");
                break;
        }
        int redcomponent = tag.getInteger("colour");
        redcomponent=redcomponent>>4;
        list.add(EnumChatFormatting.RED+"Red:"+(tag.getInteger("colour")>>16));
        list.add(EnumChatFormatting.GREEN+"Green:"+((tag.getInteger("colour")&0x00FF00)>>8));
        list.add(EnumChatFormatting.BLUE+"Blue :"+(tag.getInteger("colour")&0x0000FF));
        list.add("Voila!");
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
