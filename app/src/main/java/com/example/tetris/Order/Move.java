package com.example.tetris.Order;

import com.example.tetris.Block.Block;
import com.example.tetris.Block.BlockSquare;
import com.example.tetris.Board.Board;
public class Move extends Order {

    public Move(String Direction,Block b,Board arena) {

        if (Direction.equals("right"))
        MoveBlockRight(b,arena);
        if (Direction.equals("left"))
        MoveBlockLeft(b,arena);
    }

    public void MoveBlockRight(Block b, Board arena) {

        if (arena.checkPossibleMove (b, 0, 1, arena.getSquares())) {

            for (BlockSquare x : b.getShape ())
            {
                arena.deletepiece (x.getCurrx (), x.getCurry (), arena.getSquares());
            }

            for (BlockSquare x : b.getShape ())
            {
                arena.placepiece (x, 0, 1, arena.getSquares ());
            }
        }

    }



    public void MoveBlockLeft (Block b , Board arena )
    {

        if ( arena.checkPossibleMove (b, 0 , -1 , arena.getSquares ())) {
            for (BlockSquare x : b.getShape ()) {
                arena.deletepiece (x.getCurrx (), x.getCurry (), arena.getSquares ());
            }
            for (BlockSquare x : b.getShape ()) {
                arena.placepiece (x , 0 , -1 , arena.getSquares ());
            }

        }
    }
}