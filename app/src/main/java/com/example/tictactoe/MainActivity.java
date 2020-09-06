package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn[][] = new Button[3][3];
    Button btnX,btn0,btnPlay;

    char[][] board = new char[3][3];

    char c = 'X';

    int f=0;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn[0][0] = findViewById(R.id.btn1);
        btn[0][1] = findViewById(R.id.btn2);
        btn[0][2] = findViewById(R.id.btn3);
        btn[1][0] = findViewById(R.id.btn4);
        btn[1][1] = findViewById(R.id.btn5);
        btn[1][2] = findViewById(R.id.btn6);
        btn[2][0] = findViewById(R.id.btn7);
        btn[2][1] = findViewById(R.id.btn8);
        btn[2][2] = findViewById(R.id.btn9);
        btnX = findViewById(R.id.btnX);
        btn0 = findViewById(R.id.btn0);
        btnPlay = findViewById(R.id.btnPlay);



        c = 'X';
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = '_';
                btn[i][j].setText("");
            }
        }


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = 'X';
                f=0;
             //   GameLogic.setPlayer('X');
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        board[i][j] = '_';
                        btn[i][j].setText("");
                    }
                }
                count = 0;
            }
        });


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c='0';
                f=0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        board[i][j] = '_';
                        btn[i][j].setText("");
                    }
                }
             //   GameLogic.setPlayer('0');
                GameLogic.Move bestMove = GameLogic.findBestMove(board,c);
                Toast.makeText(MainActivity.this, "X!!!", Toast.LENGTH_SHORT).show();
                board[bestMove.row][bestMove.col] = 'X';
                btn[bestMove.row][bestMove.col].setText("X");
                count = 1;

            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                f=0;
                c='X';
              //  GameLogic.setPlayer('0');
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        board[i][j] = '_';
                        btn[i][j].setText("");
                    }
                }
                count= 0;
            }
        });

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                final int finalI = i;
                final int finalJ = j;
                btn[finalI][finalJ].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(c=='0'&&board[finalI][finalJ]=='_'&&f==0){
                            board[finalI][finalJ] = '0';
                            btn[finalI][finalJ].setText("0");
                            if(Evaluate.evaluate(board, finalI, finalJ)) {
                                f=1;
                                Toast.makeText(MainActivity.this, "Player 0 wins", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                GameLogic.Move bestMove = GameLogic.findBestMove(board,c);
                                board[bestMove.row][bestMove.col] = 'X';
                                btn[bestMove.row][bestMove.col].setText("X");
                                if(Evaluate.evaluate(board, bestMove.row, bestMove.col)) {
                                    f=1;
                                    //PLAYER X WINS
                                    Toast.makeText(MainActivity.this, "Player X wins", Toast.LENGTH_SHORT).show();
                                }
                                count = count + 2;
                                if(count == 9){
                                    f=1;

                                    Toast.makeText(MainActivity.this, "Draw!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        if(c=='X'&&board[finalI][finalJ]=='_'&&f==0){
                            board[finalI][finalJ] = 'X';
                            btn[finalI][finalJ].setText("X");
                            Toast.makeText(MainActivity.this, "X!!!!", Toast.LENGTH_SHORT).show();

                            if(Evaluate.evaluate(board,finalI,finalJ)){
                                //PLAYER X WINS
                                f=1;
                                Toast.makeText(MainActivity.this, "Player X wins", Toast.LENGTH_SHORT).show();
                            }
                            else if(count==8){
                                //DRAW
                                f=1;
                                Toast.makeText(MainActivity.this, "Draw!!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                GameLogic.Move bestMove = GameLogic.findBestMove(board,c);
                                board[bestMove.row][bestMove.col] = '0';
                                if(Evaluate.evaluate(board, bestMove.row, bestMove.col)) {
                                    f=1;
                                    Toast.makeText(MainActivity.this, "Player 0 wins", Toast.LENGTH_SHORT).show();
                                    //PLAYER 0 WINS
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "O!!!!", Toast.LENGTH_SHORT).show();
                                }
                                btn[bestMove.row][bestMove.col].setText("0");
                               // Toast.makeText(MainActivity.this, "O!!!!", Toast.LENGTH_SHORT).show();

                                count = count + 2;

                            }

                        }

                    }
                });

            }
        }
    }



    //  public void onClick(View view){




        /*
        switch(view.getId()){
            case R.id.btn1:
                if(c=='0'){
                    board[0][0] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[0][0] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn2:
                if(c=='0'){
                    board[0][1] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[0][1] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn3:
                if(c=='0'){
                    board[0][2] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[0][2] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn4:
                if(c=='0'){
                    board[1][0] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';

                }
                else{
                    board[1][0] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';

                }
                count++;
                break;
            case R.id.btn5:
                if(c=='0'){
                    board[1][1] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[1][1] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn6:
                if(c=='0'){
                    board[1][2] = '0';GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';

                }
                else{
                    board[1][2] = 'X';GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';

                }
                count++;
                break;
            case R.id.btn7:
                if(c=='0'){
                    board[2][0] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[2][0] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn8:
                if(c=='0'){
                    board[2][1] = '0';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';
                }
                else{
                    board[2][1] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
            case R.id.btn9:
                if(c=='0'){
                    board[2][2] = '0';GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = 'X';

                }
                else{
                    board[2][2] = 'X';
                    GameLogic.Move bestMove = GameLogic.findBestMove(board);
                    board[bestMove.row][bestMove.col] = '0';
                }
                count++;
                break;
        }

         */


}


//}