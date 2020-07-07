package com.example.tetris.Block;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class BlockSquare implements Cloneable, Serializable {
    private int currx;
    private int curry;



    private int colorcode;
    private static final long serialVersionUID = 3L;



    public int getCurrx() {
        return currx;
    }

    public int getCurry() {
        return curry;
    }

    public void setCurrx(int currx) {
        this.currx = currx;
    }

    public void setCurry(int curry) {
        this.curry = curry;
    }
    public void setColorcode(int colorcode) {
        this.colorcode = colorcode;
    }

    public int getColorcode() {
        return colorcode;
    }
    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        BlockSquare x=new BlockSquare();
        x.setColorcode(this.colorcode);
        return x;
    }
}
