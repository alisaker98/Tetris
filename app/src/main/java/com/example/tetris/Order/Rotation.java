package com.example.tetris.Order;

import com.example.tetris.Block.Block;
import com.example.tetris.Block.BlockSquare;
import com.example.tetris.Board.Board;
import com.example.tetris.Board.Square;

import java.util.ArrayList;
import java.util.List;

public class Rotation extends Order {
    private int[][] pivot,vr,vt,r,result;
    private List<int[][]> vectors=new ArrayList<>();
    private List<int [][]> results=new ArrayList<>();
    public Rotation(Block b,Board arena){
        for (BlockSquare x:b.getShape()){
            vectors.add(new int[][]{{x.getCurrx()},{x.getCurry()}});
        }
        vr=new int[2][1];
        vt=new int[2][1];
        pivot=new int [2][1];
        r=new int[][]{{0,-1},{1,0}};
        applyrotate(b,arena);
    }

    private Boolean applyrotate(Block b,Board arena) {
        if (b.gettype().equals("box"))
            return false;
        pivot=vectors.get(2);
        int y=0;
        for (int [][] x :vectors) {
            result = calculatenewcoor(x);
            if (checkPossibleRotate(result[0][0], result[1][0], b, arena)) {
                y++;
                results.add(result);
            }
        }
        if (y==4){
            int i=0;
            for (BlockSquare x:b.getShape()){
                arena.deletepiece(x.getCurrx(),x.getCurry(),arena.getSquares());
                arena.placepiece(x,results.get(i)[0][0]-x.getCurrx(),results.get(i)[1][0]-x.getCurry(),arena.getSquares());
                i++;
            }
            return true;
        }
        else
            return false;
    }
    public int[][] calculatenewcoor(int [][] x){
        int [][]res = new int[2][1];

        vr[0][0]=x[0][0]-pivot[0][0];
        vr[1][0]=x[1][0]-pivot[1][0];
        vt[0][0]=(r[0][0]*vr[0][0])+(r[0][1]*vr[1][0]);
        vt[1][0]=(r[1][0]*vr[0][0])+(r[1][1]*vr[1][0]);
        res[0][0]=pivot[0][0]+vt[0][0];
        res[1][0]=pivot[1][0]+vt[1][0];

        return res;
    }
    public Boolean checkPossibleRotate(int x, int y, Block b, Board arena) {
        BlockSquare tmp1=b.getShape()[0],tmp2=b.getShape()[1],tmp3=b.getShape()[2],tmp4=b.getShape()[3];
        if (x < Board.BoardHeight && y < Board.BoardWidth&&x>=0&&y>=0) {
            if (arena.getSquares()[x][y].isActive())
            {
                Square t=arena.getSquares()[x][y];
                if ((t.getPiecesquare().equals(tmp1) || t.getPiecesquare().equals(tmp2) || t.getPiecesquare().equals(tmp3) || t.getPiecesquare().equals(tmp4)))
                    return true;
                    else return false;
            }
                else
                    return true;
        }
        return false;
    }
}
