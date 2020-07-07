package com.example.tetris.Board;

        import android.widget.Toast;

        import com.example.tetris.Block.Block;
        import com.example.tetris.Block.BlockSquare;
        import com.example.tetris.Game.Player;

        import java.io.Serializable;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int BoardHeight = 35;
    public static final  int BoardWidth = 16 ;
    private Square[][] arena;
    public  Board() {
        arena=new Square[BoardHeight][BoardWidth];
        for (int i = 0; i < BoardHeight; i++) {
            for(int j=0;j<BoardWidth;j++){
                arena[i][j] = new Square(i,j);
                arena[i][j].setActive(false);
            }
        }
    }
    public void defaultplaceBlock(Block b, Square[][] arena ){
        b.moving=true;
        for (int i=0;i<4;i++)
        {
            int j=0;
            arena[b.defaultcoordinates[i][j]][b.defaultcoordinates[i][j+1]].setPiecesquare(b.getShape()[i]);
            b.getShape()[i].setCurrx(b.defaultcoordinates[i][j]);
            b.getShape()[i].setCurry(b.defaultcoordinates[i][j+1]);
            arena[b.defaultcoordinates[i][j]][b.defaultcoordinates[i][j+1]].setActive(true);

        }

    }
    public void moveBlockDown(Block b,Square[][] arena){
        if (checkPossibleMove(b,1,0,arena)) {
            for (BlockSquare x : b.getShape()) {
                deletepiece(x.getCurrx(), x.getCurry(),arena);
            }
            for (BlockSquare x : b.getShape()) {
                placepiece(x,1,0,arena);

            }
        }
        else
            b.moving=false;
    }
    public void placepiece(BlockSquare b,int i,int j,Square[][] arena) {
        arena[b.getCurrx()+i][b.getCurry()+j].setActive(true);
        arena[b.getCurrx()+i][b.getCurry()+j].setPiecesquare(b);
        b.setCurrx(b.getCurrx()+i);
        b.setCurry(b.getCurry()+j);
    }
    public void deletepiece(int x,int y,Square[][] arena){
        arena[x][y].setPiecesquare(null);
        arena[x][y].setActive(false);
    }
    public Boolean checkPossibleMove(Block b,int i,int j,Square[][] arena){
        int y=0;
        BlockSquare tmp1=b.getShape()[0],tmp2=b.getShape()[1],tmp3=b.getShape()[2],tmp4=b.getShape()[3];
        for (BlockSquare x:b.getShape()){
            if ((x.getCurrx()+i<BoardHeight)&&(x.getCurry()+j<BoardWidth)&&x.getCurry()+j>=0) {
                if (arena[x.getCurrx() + i][x.getCurry() + j].isActive()) {
                    Square t=arena[x.getCurrx() + i][x.getCurry() + j];
                    if ((t.getPiecesquare().equals(tmp1) || t.getPiecesquare().equals(tmp2) || t.getPiecesquare().equals(tmp3) || t.getPiecesquare().equals(tmp4))) {
                        y++;

                    }
                }
                else{
                    y++;
                }
            }
        }
       return (y==4);
    }


    public void RowComplete() {

        int LinesDeleted=0;
        int shiftfrom=0;
        Boolean shift=false;
        for (int i = BoardHeight-1; i >=0; i--) {
            int Done = 1;
            for (int j = 0; j < BoardWidth; j++) {

                if (getSquares()[i][j].isActive () == false) {
                    Done = 0;
                    break;
                }
            }
            if (Done == 1) {
                DeleteRow (i);
                LinesDeleted++;
                shiftfrom=i-1;
                shift=true;
            }
        }
        if(shift)
        ShiftRows(shiftfrom,shiftfrom+LinesDeleted);
        Player.ClaculateScore(LinesDeleted);


    }
    public void DeleteRow(int i) {
        for (int j = 0; j < BoardWidth; j++)
            deletepiece (i, j, getSquares ());
    }
    public void ShiftRows(int row1,int row2) {
       for(int i=row1;i>=0;i--) {
           Square[] x = getSquares()[row2];
           getSquares()[row2] = getSquares()[i];
           getSquares()[i] = x;
           coordinatesswap(getSquares()[row1],getSquares()[row2]);
           row2--;

       }
    }
    public Square[][] getSquares() { return arena; }
    public void coordinatesswap (Square[]x,Square[]y){
        for(int i=0;i<16;i++)
        {
            int j=x[i].getX();
            x[i].setX(y[i].getX());
            y[i].setX(j);
        }
    }
}
