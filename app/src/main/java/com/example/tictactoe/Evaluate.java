package com.example.tictactoe;

public class Evaluate {
    static public boolean evaluate(char b[][],int i1,int j1){
        if(b[0][j1]==b[1][j1]&&b[1][j1]==b[2][j1]){
            if(b[0][j1]=='_'){
                return false;
            }
            else{
                return true;
            }
        }
        else if(b[i1][0]==b[i1][1]&&b[i1][1]==b[i1][2]&&b[i1][0]!='_'){
            return true;
        }
        else if((b[0][0]==b[1][1]&&b[1][1]==b[2][2]&&b[0][0]!='_')||(b[0][2]==b[1][1]&&b[1][1]==b[2][0]&&b[0][2]!='_')){
            return true;
        }

        return false;

    }
}
