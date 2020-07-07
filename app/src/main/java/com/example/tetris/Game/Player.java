package com.example.tetris.Game;

public class Player  {

    private String Username ;
    public static double Score = 0 ;

    public double getScore() {
        return Score;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setScore(double score) {
        Score = score;
    }



    public static void ClaculateScore ( int deletedLines ) {

        if (deletedLines == 1 )
            Score = Score + 40 ;
        else if(deletedLines == 2)
            Score = Score + 100 ;
        else if(deletedLines == 3)
            Score = Score + 300 ;
        else if (deletedLines == 4)
            Score = Score + 1200 ;
    }


}
