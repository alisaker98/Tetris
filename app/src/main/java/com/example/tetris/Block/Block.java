    package com.example.tetris.Block;

    import androidx.annotation.NonNull;

    import com.example.tetris.Board.Board;

    import java.net.Proxy;

    public class Block implements Coordinates,Cloneable {


      private BlockSquare shape[];
      private BlockType type;
      public int [][] defaultcoordinates;
      public Boolean moving;
        public Block(String type){
          shape=new BlockSquare[4];
          for (int i=0;i<4;i++)
          {
            shape[i]=new BlockSquare();
          }
          setType(type);
          setcoordinates();
          setcolorcode();
        }
        public void setType(String type) {
            this.type=BlockType.valueOf(type);
        }
      public String gettype() { return type.toString(); }
      public BlockSquare[] getShape() { return shape; }
        public void draw(Board arena) {

        }

      @NonNull
      @Override
      protected Object clone() throws CloneNotSupportedException {
        Block x=new Block(this.gettype());
        for (int i=0;i<4;i++){
          x.shape[i]=(BlockSquare) shape[i].clone();
        }
        return x;
      }

      public void setcoordinates(){
          switch (type) {
            case box:
              defaultcoordinates=boxcoor;
              break;
            case leftl:
              defaultcoordinates=leftlcoor;
              break;
            case rightl:
              defaultcoordinates=rightlcoor;
              break;
            case leftz:
              defaultcoordinates=leftzcoor;
              break;
            case rightz:
              defaultcoordinates=rightzcoor;
              break;
            case tblock:
              defaultcoordinates=tblockcoor;
              break;
            case longone:
              defaultcoordinates=longonecoor;
              break;
          }
        }
      public void setcolorcode(){
          if (type==BlockType.box)
          {
            for (int i=0;i<4;i++)
            shape[i].setColorcode(1);
          }
            if (type==BlockType.longone)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(2);


        }
        if (type==BlockType.tblock)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(3);

        }
        if (type==BlockType.leftl)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(4);

        }
        if (type==BlockType.rightl)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(5);
        }
        if (type==BlockType.leftz)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(6);


        }
        if (type==BlockType.rightz)
        {
          for (int i=0;i<4;i++)
          shape[i].setColorcode(7);

        }
}




    }
