package com.parrot.oregano.gui.guiscreen;

import com.parrot.oregano.util.LinedTextBuffer;
import com.parrot.oregano.util.LogHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

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

    LinedTextBuffer content ;
    int cursorRow=0;
    int cursorCol=0;

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

        content= new LinedTextBuffer("",fontRendererObj,201 );

        content.text+=("Roses are red\nChocolate cookies are brownishorangey, oh so tasty\nViolets are a little red\n");
        content.UpdateLinesFromText();
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

        //drawRect(32,32,guiLeft+32,guiTop+32,Color.PINK.getRGB());
        //drawString(fontRendererObj,"Candy Cane Cone",32,32, Color.pink.getRGB());

        txtLine1.xPosition=guiLeft;
        txtLine1.yPosition=guiTop;

        drawTextContent();

//        GuiTextField txtAbove=new GuiTextField(fontRendererObj,guiLeft,guiTop-16,guiWidth,12);
//        if(line>0) {
//            txtAbove.setText(textLines[line - 1]);
//            txtAbove.drawTextBox();
//        }
//        GuiTextField txtBelow=new GuiTextField(fontRendererObj,guiLeft,guiTop+28,guiWidth,12);
//        if(line<textLines.length-1) {
//            txtBelow.setText(textLines[line + 1]);
//            txtBelow.drawTextBox();
//        }
        super.drawScreen(x, y, ticks);

    }

    private void drawTextContent() {

        int left=32;
        int top=32;
        int fonth=11;

        if(content.lines.size()==0)
        {
            content.UpdateLinesFromText();
            return;
        }
        int cursortop = top+(cursorRow*fonth);
        cursorCol=Math.min(content.lines.get(cursorRow).length(),cursorCol);
        cursorRow=Math.min(content.lines.size(),cursorRow);
        int widthofleftstring=fontRendererObj.getStringWidth(content.lines.get(cursorRow).substring(0,cursorCol));
        int cursorleft=left+(widthofleftstring)-1;
        drawRect(cursorleft,cursortop,cursorleft+3,cursortop+fonth,Color.YELLOW.getRGB());

        //txtLine1.drawTextBox();
        for (int i=0;i<content.lines.size();i++){
            String s = content.lines.get(i);
            drawString(fontRendererObj,s,left,top+(i*fonth),Color.LIGHT_GRAY.getRGB());
        }

    }

    @Override
    protected void keyTyped(char key, int keyboardCode)
    {
        try{

            txtLine1.textboxKeyTyped(key, keyboardCode);
            LogHelper.info(key+"_"+keyboardCode);
            String s="";
            int lineCount=content.lines.size();
            int currentcursorx=fontRendererObj.getStringWidth(content.lines.get(cursorRow).substring(0,cursorCol));
            switch(keyboardCode) {
                case 205:
                    if(cursorCol<content.lines.get(cursorRow).length()-1)   {cursorCol++;}
                    break;
                case 203 :
                    if(cursorCol>0)   {cursorCol--;}
                    break;
                case 200:
                    if(cursorRow>0)   {cursorRow--;}
                    cursorCol= getColInString(content.lines.get(cursorRow),currentcursorx);
                    break;
                case 208 :
                    if(cursorRow<content.lines.size()-1)   {cursorRow++;}
                    cursorCol= getColInString(content.lines.get(cursorRow),currentcursorx);
                    break;
                case 211:
                    if(content.lines.get(cursorRow).length()>cursorCol+1)
                    {
                         s = content.lines.get(cursorRow).substring(0,cursorCol)+content.lines.get(cursorRow).substring(cursorCol+1);
                        content.ReplaceLine(cursorRow,s);

                    }
                    else{
                        if(cursorRow<content.lines.size()-1)
                        {
                            s = content.lines.get(cursorRow).substring(0,content.lines.get(cursorRow).length()-1);
                            content.ReplaceLine(cursorRow,s);
                        }

                    }
                    break;
                case 14:
                    if(cursorCol<1 && cursorRow==0) {
                        return;
                    }
                    if((cursorCol==0)){
                        if(cursorRow>=1) {
                            int currentlinelengthbefore=content.lines.get(cursorRow-1).length()-1;
                            s = content.lines.get(cursorRow - 1).substring(0, content.lines.get(cursorRow - 1).length()-1) ;
                            content.ReplaceLine(cursorRow-1, s);
                            cursorRow--;
                            cursorCol=currentlinelengthbefore;
                        }
                    }
                    else{
                        s = content.lines.get(cursorRow).substring(0,cursorCol-1)+content.lines.get(cursorRow).substring(cursorCol);
                        content.ReplaceLine(cursorRow,s);
                        cursorCol--;
                    }
                    break;
                case 199 :
                    cursorCol=0;
                    break;
                case 207 :
                    cursorCol=content.lines.get(cursorRow).length()-1;
                    break;
                case 28:
                    s = content.lines.get(cursorRow).substring(0,cursorCol)+"\n"+content.lines.get(cursorRow).substring(cursorCol);
                    content.ReplaceLine(cursorRow,s);
                    cursorCol=0;
                    if(content.lines.size()>lineCount){
                        cursorRow++;
                    }

                    break;
                default:
                    if(ChatAllowedCharacters.isAllowedCharacter(key))
                    {
                        //int positionOfStartOfRow= content.linesStringPositions.get(cursorRow);
                        s = content.lines.get(cursorRow).substring(0,cursorCol)+Character.toString(key)+content.lines.get(cursorRow).substring(cursorCol);
                        content.ReplaceLine(cursorRow,s);
                        cursorCol++;
                        if(content.lines.size()>lineCount){
                            cursorRow++;
                        }
                    }
            }

        }
        catch(Exception exception){
            LogHelper.info(exception);
        }
        super.keyTyped(key,keyboardCode);

    }

    @Override
    protected void mouseClicked(int x, int y, int mouseButton)
    {

        int left=32;
        int top=32;
        int fonth=11;

        int clickedrow = (y-top)/(fonth);
        if(clickedrow<content.lines.size()&&clickedrow>=0)
        {
            String s = content.lines.get(clickedrow);
            int clickedcol= getColInString(s,x-left);
            //int widthofleftstring=fontRendererObj.getStringWidth(content.get(cursorRow).substring(0,cursorCol));
            //int cursorleft=left+(widthofleftstring)-1;
            cursorRow=clickedrow;
            cursorCol=clickedcol;
        }




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

    private int getColInString(String s, int x) {
        int accumulatedwidth=0;
        int i=0;
        for (i=0;i<s.length();i++)
        {
            accumulatedwidth+=fontRendererObj.getCharWidth(s.charAt(i));
            if(x<accumulatedwidth){
                return i;
            }
        }
        return i;
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
