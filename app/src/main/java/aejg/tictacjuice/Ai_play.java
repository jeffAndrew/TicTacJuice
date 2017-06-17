package aejg.tictacjuice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Ai_play extends AppCompatActivity {

    int casillas [][];
    int i = 0;
    int j = 0;
    Button ai_Buttons[][];
    Ai ai;
    boolean empty = false;





    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_play);

        setBoard();

    }

    private  void setBoard(){
        ai = new Ai();
        ai_Buttons = new Button[4][4];
        casillas = new int[4][4];

        ai_Buttons[1][3] = (Button)findViewById(R.id.aibtn_1);
        ai_Buttons[1][2] = (Button)findViewById(R.id.aibtn_2);
        ai_Buttons[1][1] = (Button)findViewById(R.id.aibtn_3);

        ai_Buttons[2][3] = (Button)findViewById(R.id.aibtn_4);
        ai_Buttons[2][2] = (Button)findViewById(R.id.aibtn_5);
        ai_Buttons[2][1] = (Button)findViewById(R.id.aibtn_6);

        ai_Buttons[3][3] = (Button)findViewById(R.id.aibtn_7);
        ai_Buttons[3][2] = (Button)findViewById(R.id.aibtn_8);
        ai_Buttons[3][1] = (Button)findViewById(R.id.aibtn_9);

        for (i = 1; i <= 3; i++){
            for (j = 1; j <= 3; j++){
                casillas[i][j] = 2;
            }
        }

        for (i = 1; i <= 3; i++){

            for (j = 1; j <= 3;j++){

                ai_Buttons[i][j].setOnClickListener(new MyClickListener(i,j));

                if (!ai_Buttons[i][j].isEnabled()){

                    ai_Buttons[i][j].setText(" ");
                    ai_Buttons[i][j].setEnabled(true);

                }


            }

        }

    }


    class MyClickListener implements View.OnClickListener{

        int x,y;

        public MyClickListener(int x, int y){
            this.x = x;
            this.y =y;

        }

        @Override
        public void onClick(View v) {

            if (ai_Buttons[x][y].isEnabled()){

                ai_Buttons[x][y].setEnabled(false);
                ai_Buttons[x][y].setBackgroundResource(R.drawable.xtictac);
                casillas[x][y] = 0;

                if (!checkBoard()){

                    ai.takeTurn();
                }

            }

        }



    }

    private class Ai{


        public void takeTurn(){

            if(ai_Buttons[1][1].equals(2) && ((ai_Buttons[1][2].equals(0) &&
                    ai_Buttons[1][3].equals(0)) || (ai_Buttons[2][2].equals(0) &&
                    ai_Buttons[3][3].equals(0)) || (ai_Buttons[2][1].equals(0) && ai_Buttons[3][1].equals(0))))
            {
                markSquare(1,1);
            }

            if(ai_Buttons[1][1].equals(2) && ((ai_Buttons[1][2].equals(0) &&
                    ai_Buttons[1][3].equals(0)) || (ai_Buttons[2][2].equals(0) &&
                   ai_Buttons[3][3].equals(0))|| (ai_Buttons[2][1].equals(0) && ai_Buttons[3][1].equals(0))))
            {
                markSquare(1,1);
            }


            else if(ai_Buttons[1][2].equals(2) &&
                    (ai_Buttons[2][2].equals(0) && ai_Buttons[3][2].equals(0))||
                    (ai_Buttons[1][1].equals(0) && ai_Buttons[1][3].equals(0)))
            {
                markSquare(1,2);
            }

            else if(ai_Buttons[1][3].equals(2) && (ai_Buttons[1][1].equals(0) &&
                    ai_Buttons[1][2].equals(0))||(ai_Buttons[3][1].equals(0) &&
                    ai_Buttons[2][2].equals(0))|| (ai_Buttons[2][3].equals(0) && ai_Buttons[3][3].equals(0)))
            {
                markSquare(1,3);
            }

            else if(ai_Buttons[2][1].equals(2) && (ai_Buttons[2][2].equals(0) &&
                    ai_Buttons[2][3].equals(0))||(ai_Buttons[1][1].equals(0) && ai_Buttons[3][1].equals(0)))
            {
                markSquare(2,1);
            }

            else if(ai_Buttons[2][2].equals(2) && (ai_Buttons[1][1].equals(0) &&
                   ai_Buttons[3][3].equals(0)) || (ai_Buttons[1][2].equals(0) && ai_Buttons[3][2].equals(0)) ||
                    (ai_Buttons[3][1].equals(0) && ai_Buttons[1][3].equals(0)) || (ai_Buttons[2][1].equals(0) && ai_Buttons[2][3].equals(0)))
            {
                markSquare(2,2);
            }

            else if(ai_Buttons[2][3].equals(2) && (ai_Buttons[2][1].equals(0) &&ai_Buttons[2][2].equals(0)) ||
                    (ai_Buttons[1][3].equals(0) && ai_Buttons[3][3].equals(0)))
            {
                markSquare(2,3);
            }

            else if(ai_Buttons[3][1].equals(2) && (ai_Buttons[1][1].equals(0) && ai_Buttons[2][1].equals(0)) ||
                    (ai_Buttons[3][2].equals(0) && ai_Buttons[3][3].equals(0)) ||
                    (ai_Buttons[2][2].equals(0) && ai_Buttons[1][3].equals(0)))
            {
                markSquare(3,1);
            }

            else if(ai_Buttons[3][2].equals(2) && (ai_Buttons[1][2].equals(0) && ai_Buttons[2][2].equals(0)) ||
                    (ai_Buttons[3][1].equals(0) && ai_Buttons[3][3].equals(0)))
            {
                markSquare(3,2);
            }

            else if(ai_Buttons[3][3].equals(2) && (ai_Buttons[1][1].equals(0) && ai_Buttons[2][2].equals(0)) ||
                    (ai_Buttons[1][3].equals(0) && ai_Buttons[2][3].equals(0)) ||
                    (ai_Buttons[3][1].equals(0) && ai_Buttons[3][2].equals(0)))
            {
                markSquare(3,3);
            }

            else
            {
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);

                while (a == 0 || b == 0 || casillas [a][b]!=2)
                {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }

                markSquare(a,b);
            }
        }
    }


    private void markSquare(int x, int y){

        ai_Buttons[x][y].setEnabled(false);

        ai_Buttons[x][y].setBackgroundResource(R.drawable.otictac);

        casillas[x][y] = 1;

        checkBoard();
    }

    private boolean checkBoard(){

        boolean gameOver = false;

        if((casillas[1][1] == 0 && casillas[2][2] == 0 && casillas[3][3] == 0)||
                (casillas[1][3] == 0 && casillas[2][2] == 0 && casillas[3][1] == 0)||
                (casillas[1][2] == 0 && casillas[2][2] == 0 && casillas[3][2] == 0)||
                (casillas[1][3] == 0 && casillas[2][3] == 0 && casillas[3][3] == 0)||
                (casillas[1][1] == 0 && casillas[1][2] == 0 && casillas[1][3] == 0)||
                (casillas[2][1] == 0 && casillas[2][2] == 0 && casillas[2][3] == 0)||
                (casillas[3][1] == 0 && casillas[3][2] == 0 && casillas[3][3] == 0)||
                (casillas[1][1] == 0 && casillas[2][1] == 0 && casillas[3][1] == 0)){

            Toast.makeText(getApplicationContext(),"You Win",Toast.LENGTH_LONG).show();

            AlertDialog nextgame = new AlertDialog.Builder(this)
                    .setTitle("Has Ganado!")
                    .setMessage("¿Desea Otra Partida?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Ai_play.this,Ai_play.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent2 = new Intent(Ai_play.this,MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                    })
                    .create();
            nextgame.show();

            gameOver = true;
        }

        else if ((casillas[1][1] == 1 && casillas[2][2] == 1 && casillas[3][3] == 1)||
                (casillas[1][3] == 1 && casillas[2][2] == 1 && casillas[3][1] == 1)||
                (casillas[1][2] == 1 && casillas[2][2] == 1 && casillas[3][2] == 1)||
                (casillas[1][3] == 1 && casillas[2][3] == 1 && casillas[3][3] == 1)||
                (casillas[1][1] == 1 && casillas[1][2] == 1 && casillas[1][3] == 1)||
                (casillas[2][1] == 1 && casillas[2][2] == 1 && casillas[2][3] == 1)||
                (casillas[3][1] == 1 && casillas[3][2] == 1 && casillas[3][3] == 1)||
                (casillas[1][1] == 1 && casillas[2][1] == 1 && casillas[3][1] == 1)){

            Toast.makeText(getApplicationContext(),"You Lose",Toast.LENGTH_LONG).show();

            AlertDialog nextgame = new AlertDialog.Builder(this)
                    .setTitle("Has Perdido")
                    .setMessage("¿Desea Otra Partida?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Ai_play.this,Ai_play.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent2 = new Intent(Ai_play.this,MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                    })
                    .create();
            nextgame.show();

            gameOver = true;

        }

      else if ( empty == false){

            for (i = 1; i <= 3; i++){
                for (j = 1; j <= 3; j++){
                    if(ai_Buttons[i][j].equals(2)){
                       // Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_LONG).show();
                        empty = true;
                        break;
                    }
                }
            }
        }

        else {
            Toast.makeText(getApplicationContext(),"Draw",Toast.LENGTH_LONG).show();

        }



        return  gameOver;

    }





}
