package com.example.tetris.Board;

import androidx.annotation.NonNull;

import com.example.tetris.Block.BlockSquare;

import java.io.Serializable;

public class Square implements Serializable {
    private static final long serialVersionUID = 2L;
    private BlockSquare piecesquare;
    private boolean IsActive=false;
    private int x,y;

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Square x=(Square) super.clone();
        x.setPiecesquare(this.piecesquare);
        x.IsActive=IsActive;
        x.x=getX();
        x.y=getY();
        return x;
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiecesquare(BlockSquare piecesquare) {
        this.piecesquare = piecesquare;
    }
    public BlockSquare getPiecesquare() { return piecesquare; }
    public boolean isActive() {
        return IsActive;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
