package com.parrot.oregano.gui;

import com.parrot.oregano.gui.guiscreen.GuiPoster;
import com.parrot.oregano.util.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sun.rmi.log.LogHandler;

/**
 * Created by Shane on 3/22/2015.
 */
public class GuiHandlerOregano implements IGuiHandler {

    public static final int Poster = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {

            case Poster:
                //do nothing. this is not a GuiContainer. handle clientside via packets.1
                return null;

            default:
                return null;

        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID) {

            case Poster:
                return new GuiPoster();

            default:
                return null;

        }
    }
}
