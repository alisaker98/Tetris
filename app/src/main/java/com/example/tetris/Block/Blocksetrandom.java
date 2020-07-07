package com.example.tetris.Block;

import java.util.Random;

public class Blocksetrandom  {
    Block[] defblocks;

    public Blocksetrandom() {
        defblocks=new Block[7];
        setblocks();
    }
    public void setblocks(){
        defblocks[0]=new Block("box");
        defblocks[1]=new Block ( "longone");
        defblocks[2]=new Block("leftl");
        defblocks[3]=new Block("rightl");
        defblocks[4]=new Block("leftz");
        defblocks[5]=new Block("rightz");
        defblocks[6]=new Block("tblock");
    }
    public Block randBlock()  {
        Block x;
        Random i=new Random();
        int rand=i.nextInt(7);
        try {
            x=(Block) defblocks[rand].clone();

            return x;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

      return null;
    }
}
