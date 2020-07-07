package com.example.tetris.Order;

import com.example.tetris.Block.Block;
import com.example.tetris.Board.Board;

public class Speeddown extends Order {
    public Speeddown(Block b, Board arena){
        arena.moveBlockDown(b,arena.getSquares());
        arena.moveBlockDown(b,arena.getSquares());
    }

}
