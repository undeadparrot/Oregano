package com.parrot.oregano.gui.guiscreen;

import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

/**
 * Created by Shane on 3/22/2015.
 */
public class GuiPoster extends GuiScreen {

    int guiWidth = 128;
    int guiHeight = 200;

    public GuiPoster()
    {
        super();


    }

    @Override
    public void initGui()
    {
        //
        //
        //
    }

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiLeft=(width-guiWidth)/2;
        int guiRight=(width-guiWidth)/2;

        drawString(fontRendererObj,"Candy Cane Cone",32,32, Color.pink.getRGB());

        super.drawScreen(x, y, ticks);

    }



}
