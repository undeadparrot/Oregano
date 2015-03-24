package com.parrot.oregano.gui.guiscreen;

import com.parrot.oregano.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Mouse;

import java.awt.*;

/**
 * Created by Shane on 3/22/2015.
 */
public class GuiPoster extends GuiScreen {

    int guiWidth = 240;
    int guiHeight = 200;

    int mouseScroll = 200;

    int line=0;

    GuiTextField txtLine1;

    String[] textLines=new String[9];

    public GuiPoster()
    {
        super();


    }

    @Override
    public void initGui()
    {
        //
        //
        txtLine1 = new GuiTextField(fontRendererObj,32,32,guiWidth,24);
        txtLine1.setText("Torky Type");
        txtLine1.setEnabled(true);
        txtLine1.setFocused(true);
        //

        textLines[0]="Line 1";
        textLines[1]="Line 2";
        textLines[2]="Line 3";
        textLines[3]="Line 4";
        textLines[4]="Line 5";
        textLines[5]="Line 6";
        textLines[6]="Line 7";
        textLines[7]="Line 8";
        textLines[8]="Line 9";

    }

    @Override
    public boolean doesGuiPauseGame(){return false;}

    @Override
    public void handleMouseInput()
    {


        super.handleMouseInput();
        mouseScroll=Mouse.getDWheel();
        scrollLines((mouseScroll/120));

    }
    @Override
    public void drawScreen(int x, int y, float ticks)
    {


        int guiLeft=(width-guiWidth)/2;
        int guiTop=(int)((height*2*0.32));

        drawString(fontRendererObj,"Candy Cane Cone",32,32, Color.pink.getRGB());

        txtLine1.xPosition=guiLeft;
        txtLine1.yPosition=guiTop;

        txtLine1.drawTextBox();

        GuiTextField txtAbove=new GuiTextField(fontRendererObj,guiLeft,guiTop-16,guiWidth,12);
        if(line>0) {
            txtAbove.setText(textLines[line - 1]);
            txtAbove.drawTextBox();
        }
        GuiTextField txtBelow=new GuiTextField(fontRendererObj,guiLeft,guiTop+28,guiWidth,12);
        if(line<textLines.length-1) {
            txtBelow.setText(textLines[line + 1]);
            txtBelow.drawTextBox();
        }
        super.drawScreen(x, y, ticks);

    }

    @Override
    protected void keyTyped(char key, int keyboardCode)
    {
        txtLine1.textboxKeyTyped(key, keyboardCode);
        LogHelper.info(key+"_"+keyboardCode);
        switch(keyboardCode) {
            case 200:
                scrollLines(1);
                break;
            case 208 :
                scrollLines(-1);
                break;
            case 28:
                if( (textLines.length) != line+1 ) {
                    scrollLines(-1);
                }
                else{
                    this.mc.displayGuiScreen((GuiScreen)null);
                    this.mc.setIngameFocus();
                }
                break;
        }
        super.keyTyped(key,keyboardCode);

    }

    @Override
    protected void mouseClicked(int x, int y, int mouseButton)
    {


        if((x>txtLine1.xPosition&&x<(txtLine1.xPosition+txtLine1.width))
            &&(y>txtLine1.yPosition&&y<(txtLine1.yPosition+txtLine1.height)))
        {
            int hitx = x-txtLine1.xPosition;
            LogHelper.info("hitx=" + hitx);
            //txtLine1.setEnableBackgroundDrawing(false);
            txtLine1.mouseClicked(x,y,mouseButton);

        }

        if(1==mouseButton)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }

        super.mouseClicked(x,y,mouseButton);
    }

    @Override
    public void onGuiClosed() {

        textLines[line]=txtLine1.getText();

        super.onGuiClosed();

    }

    public void scrollLines(int linesToScroll)
    {
        int linenew=line-linesToScroll;
        if(linenew>=textLines.length){linenew=textLines.length-1;}
        if(linenew<0){linenew=0;}
        textLines[line]=txtLine1.getText();
        if(line != linenew){
            line=linenew;txtLine1.setText(textLines[line]);
        }
    }

}
