package com.parrot.oregano.client.render.tileentity;

import com.parrot.oregano.Oregano;
import com.parrot.oregano.init.ModItems;
import com.parrot.oregano.init.ModModels;
import com.parrot.oregano.tileentity.TileEntityBlob;
import com.sun.prism.util.tess.Tess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by Shane on 3/14/2015.
 */
public class TileEntityRendererBlob extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x  , (float) y  , (float) z  );

        IModelCustom model = ModModels.chestBarrelMini;
        bindTexture(new ResourceLocation("minecraft", "textures/blocks/planks_spruce.png"));
        model.renderPart("Box");
        model.renderPart("Lid");
        bindTexture(new ResourceLocation("minecraft", "textures/blocks/iron_block.png"));
        model.renderPart("Clasp");
        bindTexture(new ResourceLocation("minecraft", "textures/blocks/wool_colored_purple.png"));
        model.renderPart("Lining");

        GL11.glPopMatrix();



        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.50F, (float) z + 0.5F);
        GL11.glTranslatef(-0.1F,-0.25F,-0.05F);
        GL11.glScalef(0.5F,0.5F,0.5F);
        World world = entity.getWorldObj();
        TileEntityBlob tileEntity=((TileEntityBlob)entity);

        ItemStack stack = tileEntity.inventoryItem;
        if(stack!=null) {

            EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
            entItem.hoverStart = 0.0F;

            GL11.glPushMatrix();
            GL11.glRotatef(-120.50F, 0.15F, 0.15F, 0.25F);
            RenderItem.renderInFrame = true;
            RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();

        }

        GL11.glPushMatrix();
        //GL11.glTranslatef((float) x + 0.5F, (float) y + 0.50F, (float) z + 0.5F);
        //GL11.glTranslatef((float) x, (float) y, (float) z);
        //String iconname=Block.getBlockFromName("Stone").getBlockTextureFromSide(3).getIconName();
        this.bindTexture(new ResourceLocation("textures/entity/chest/trapped.png"));
        Tessellator tessellator = Tessellator.instance;
        //int lightValue = entity.getWorldObj().getBlock((int)x,(int)y,(int)z).getMixedBrightnessForBlock(entity.getWorldObj(), (int)x, (int)y, (int)z);
        //tessellator.setBrightness(lightValue);
        //tessellateCenteredCubeWithUV(tessellator, 00.50F, 0.50F, 00.50F, 0.45F, 0.45F, 0.15F);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        Float tx = tileEntity.x;
        GL11.glTranslatef(tx,0.0F,0.0F);
        GL11.glScalef(0.009f, -0.009f, 0.009f);
        GL11.glColor4f(1.250F, 0.5F, 1.0F, 0.5F);
        FontRenderer fontrenderer = this.func_147498_b();
//        fontrenderer.drawString("Hellow_Wurldses",
//                -fontrenderer.getStringWidth("Hellow_Wurld") / 2,
//                25,
//                0);
//        fontrenderer.drawString(tx+"",
//                -fontrenderer.getStringWidth(tx+"") / 2,
//                35,
//                0);
        if(stack!=null) {
//            fontrenderer.drawString(tileEntity.inventoryItem.getDisplayName() + "",
//                    -fontrenderer.getStringWidth(tx + "") / 2,
//                    -45,
//                    0);
        }
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    public static void tessellateCenteredCubeWithUV(Tessellator tessellator, float x,float y, float z, float uvx, float uvy,float uvsize ) {
        float uvx2=uvx+uvsize;
        float uvy2=uvy+uvsize;
        GL11.glPushMatrix();
        tessellator.startDrawingQuads();
        //GL11.glTranslatef(x/2.0F,y/2.0F,z/2.0F);
        GL11.glScalef(x, y, z);

        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(-0.5F, -0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(-0.5F, -0.5F, -0.5F,    uvx, uvy);
        tessellator.addVertexWithUV(0.5F, -0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, 0.5F,    uvx2, uvy);
        tessellator.addVertexWithUV(0.5F, 0.5F, 0.5F,    uvx2, uvy2);
        tessellator.addVertexWithUV(0.5F, 0.5F, -0.5F,    uvx, uvy2);
        tessellator.addVertexWithUV(-0.5F, 0.5F, -0.5F,    uvx, uvy);
        tessellator.draw();
        GL11.glPopMatrix();


    }

}


