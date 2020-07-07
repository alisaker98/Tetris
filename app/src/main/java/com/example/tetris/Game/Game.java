package com.example.tetris.Game;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tetris.Block.Block;
import com.example.tetris.Block.BlockSquare;
import com.example.tetris.Block.BlockType;
import com.example.tetris.Block.Blocksetrandom;
import com.example.tetris.Board.Board;
import com.example.tetris.Board.Square;
import com.example.tetris.Order.Move;
import com.example.tetris.Order.Order;
import com.example.tetris.Order.Rotation;
import com.example.tetris.Order.Speeddown;
import com.example.tetris.R;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Activity {
    public Board arena;
    public Blocksetrandom randomblockset;
    Player player;
    Difficulty difficulty;
    int latency=300;
    Roundmanager RM;
    NextPieceView nextpiece;
    Boolean gameactive=false,gameover=true;
    RelativeLayout boardview,nextpieceview;
    Thread t;
    Button b1,b2,b3,b4;
    Bitmap squarePiece,tPiece,leftzPiece,longPiece,rightzPiece, leftlPiece,rightlPiece;
    TextView score;
    Button pause;
    static    Queue<BlockType> blocktypes;



    public Game() {
        super();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        squarePiece = BitmapFactory.decodeResource(getResources(), R.drawable.box);
         tPiece = BitmapFactory.decodeResource(getResources(), R.drawable.t);
        leftzPiece = BitmapFactory.decodeResource(getResources(), R.drawable.lz);
         longPiece = BitmapFactory.decodeResource(getResources(), R.drawable.longone);
         rightzPiece = BitmapFactory.decodeResource(getResources(), R.drawable.rz);
        leftlPiece = BitmapFactory.decodeResource(getResources(), R.drawable.l);
         rightlPiece = BitmapFactory.decodeResource(getResources(), R.drawable.l);
        boardview = (RelativeLayout) findViewById(R.id.board);
        nextpieceview=(RelativeLayout)findViewById(R.id.nextpieceviewer);
        score=findViewById(R.id.score);
        pause=findViewById(R.id.pause);
        randomblockset=new Blocksetrandom();
        arena=new Board();
        RM=new Roundmanager(this);
        nextpiece=new NextPieceView(this);
        boardview.addView(RM);
        nextpieceview.addView(nextpiece);
        gameactive=true;
        b1=findViewById(R.id.leftmove);
        b2=findViewById(R.id.rightmove);
        b3=findViewById(R.id.downmove);
        b4=findViewById(R.id.rotatebutton);
        t=new Thread(RM);
        t.start();
        player=new Player();
        difficulty=Difficulty.easy;
    }
    @Override
    protected void onStart() {
        super.onStart();
        gameactive=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameactive=false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public class Roundmanager extends View implements Runnable {
        Queue<Block> blockset;

        Order order;
        Block b;

        public Roundmanager(Context context) {
            super(context);
            blockset = new LinkedList<>();
            blocktypes=new LinkedList<>();
            for(int i=0;i<3;i++) {
                Block a = randomblockset.randBlock();
                blockset.add(a);
                blocktypes.add(BlockType.valueOf(a.gettype()));
            }

        }




        @Override
        public void run() {

                b = blockset.poll();
                Block a;
                a = randomblockset.randBlock();
                blockset.add(a);
                blocktypes.add(BlockType.valueOf(a.gettype()));
                while (!gameover(b)) {

                    arena.defaultplaceBlock(b, arena.getSquares());
                    while (b.moving) {

                        arena.moveBlockDown(b, arena.getSquares());
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                order = new Move("left", b, arena);
                                invalidate();
                            }
                        });
                        b2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                order = new Move("right", b, arena);
                                invalidate();
                            }
                        });
                        b3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                order = new Speeddown(b, arena);
                                invalidate();
                            }
                        });
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                order = new Rotation(b, arena);
                                invalidate();
                            }
                        });
                        invalidate();

                        try {

                            Thread.sleep(latency);

                        } catch (InterruptedException e) {
                            Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                    arena.RowComplete();
                    invalidate();
                    nextpiece.loop();
                    check_difficulty_level();
                    run();
                }


        }


        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            String score1 = String.valueOf(Player.Score);
            score.setText(score1);
            int h = boardview.getWidth()/16;
            int g = boardview.getHeight()/35;
            super.onDraw(canvas);
                for (int x = 0; x < Board.BoardHeight; x++) {
                    for (int y = 0; y < Board.BoardWidth; y++) {

                        BlockSquare temp = arena.getSquares()[x][y].getPiecesquare();
                        Rect a = new Rect((y) * h, (x) * g, (y) * h+h, (x) *g+g);
                        if (arena.getSquares()[x][y].isActive() == true) {
                            if (temp.getColorcode() == 1)
                                canvas.drawBitmap(squarePiece, null, a, null);
                            if (temp.getColorcode() == 2)
                                canvas.drawBitmap(longPiece, null, a, null);
                            if (temp.getColorcode() == 3)
                                canvas.drawBitmap(tPiece, null, a, null);
                            if (temp.getColorcode() == 4)
                                canvas.drawBitmap(leftlPiece, null, a, null);
                            if (temp.getColorcode() == 5)
                                canvas.drawBitmap(rightlPiece, null, a, null);
                            if (temp.getColorcode() == 6)
                                canvas.drawBitmap(leftzPiece, null, a, null);
                            if (temp.getColorcode() == 7)
                                canvas.drawBitmap(rightzPiece, null, a, null);
                        }
                    }
                }
        }
        private void check_difficulty_level() {
            if (Player.Score >= 5000) {
                difficulty = Difficulty.normal;
                latency-=75;
            }
            else if(Player.Score>=10000){
                difficulty=Difficulty.hard;
                latency-=50;
            }
            else if (Player.Score>=15000){
                difficulty=Difficulty.expert;
                latency-=50;
            }
        }

    }

     public Boolean gameover(Block b){
     return checkpossibleplace(b);
    }
    public Boolean checkpossibleplace(Block b){
        int y=0;
        for (int[] x:b.defaultcoordinates){
            if (!arena.getSquares()[x[0]][x[1]].isActive()){
                y++;
            }
        }
        if (y==4)
            return false;
        else
            return true;
    }



}
