package com.example.tetris.Game;


import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.View;



import com.example.tetris.Block.BlockType;
import com.example.tetris.Block.Coordinates;

import com.example.tetris.R;



public class NextPieceView extends View implements Coordinates{
         BlockType type;
         Bitmap squarePiece,tPiece,leftzPiece,longPiece,rightzPiece, leftlPiece,rightlPiece;


        public NextPieceView(Context context) {
            super(context);
            squarePiece = BitmapFactory.decodeResource(getResources(), R.drawable.boxpic);
            tPiece = BitmapFactory.decodeResource(getResources(), R.drawable.tpic);
            leftzPiece = BitmapFactory.decodeResource(getResources(), R.drawable.leftzpic);
            longPiece = BitmapFactory.decodeResource(getResources(), R.drawable.longpic);
            rightzPiece = BitmapFactory.decodeResource(getResources(), R.drawable.rightzpic);
            leftlPiece = BitmapFactory.decodeResource(getResources(), R.drawable.leftlpic);
            rightlPiece = BitmapFactory.decodeResource(getResources(), R.drawable.rightlpic);

        }
        public void loop(){
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            type=Game.blocktypes.poll();
            type=Game.blocktypes.peek();

           if (type==BlockType.box) {
                   canvas.drawBitmap(squarePiece,0,0,null);
           }
            if (type==BlockType.rightz) {
                    canvas.drawBitmap(rightzPiece,0,0,null);
            }
            if (type==BlockType.leftz){
                    canvas.drawBitmap(leftzPiece,0,0,null);
            }

            if (type==BlockType.leftl){
                    canvas.drawBitmap(leftlPiece,0,0,null);
            }

            if (type==BlockType.rightl){
                    canvas.drawBitmap(rightlPiece,0,0,null);
            }

            if (type==BlockType.longone){
                    canvas.drawBitmap(longPiece,0,0,null);
            }

            if (type==BlockType.tblock){
                    canvas.drawBitmap(tPiece,0,0,null);
            }
        }
    }

