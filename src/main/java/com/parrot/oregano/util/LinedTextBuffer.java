package com.parrot.oregano.util;

import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by smatu on 1/21/2016.
 */
public class LinedTextBuffer {
    public String text = "";
    public ArrayList<String> lines = new ArrayList<String>();
    public ArrayList<Integer> linesStringPositions = new ArrayList<Integer>();
    FontRenderer fontRenderer;
    int width;

    public LinedTextBuffer(String _text,FontRenderer _fr,int _width) {
        if(_text!=null){
            this.text = _text;
        }
        if(_fr!=null)   {
            fontRenderer=_fr;

        }
        width=_width;
    }


    public void UpdateLinesFromText(){
        if(fontRenderer!=null){

            lines=new ArrayList<String>();
            linesStringPositions=new ArrayList<Integer>();
            int positionInString=0;

            //first break the string up into lines based on linebreaks
            String[] ls= (text.split("\n"));
            linesStringPositions.add(positionInString);

            //now break up each line based on width
            for (int i = 0; i < ls.length; i++) {
                int accumulatedLength=0;
                String line =ls[i]+"\n";
                String[] tokens = line.split(" ");
                String partialline="";
                for (int h = 0;h<tokens.length;h++) {
                    String tok = tokens[h] + ((h==tokens.length-1)?"":" ");
                    int tokenwidth=fontRenderer.getStringWidth(tok);
                    if(accumulatedLength+tokenwidth>width){
                        positionInString=UpdateLinesFromText_NextLine(positionInString,partialline);
                        partialline="";
                        accumulatedLength=0;
                    }
                    accumulatedLength+= tokenwidth;
                    partialline+=tok;
                }
                positionInString = UpdateLinesFromText_NextLine(positionInString, partialline);
                partialline="";
                accumulatedLength=0;
                positionInString+=0;

            }


        }
    }

    private int UpdateLinesFromText_NextLine(int positionInString, String partialline) {
        int accumulatedLength;
        positionInString+=partialline.length();
        lines.add(partialline);
        linesStringPositions.add(positionInString);
        return positionInString;
    }

    public void ReplaceLine(int row, String s) {
        int startOfLinePosition=linesStringPositions.get(row);
        int endOfLinePosition=linesStringPositions.get(row+1);
        String textbefore=text.substring(0,startOfLinePosition);
        String textafter=text.substring(endOfLinePosition);
        String textline=text.substring(startOfLinePosition,endOfLinePosition);
        text=textbefore+s+textafter;
        UpdateLinesFromText();
    }
}
