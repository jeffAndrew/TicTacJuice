package aejg.tictacjuice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Friend_Play extends AppCompatActivity {

    int jugadorActivo = 0; // jugador activo variable  a iniciar el juego tomando referencia la X
    Integer [] estadodeljuego = {2,2,2,2,2,2,2,2,2}; // posiciones no jugadas
    int [][] casilladeganar = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; // condicion de ganar


    boolean gameover = false;


    public void gamelogic(View view){ //logica del juego para colocar las fichas de forma ordenada

        ImageView tappedView = (ImageView) view;

        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if(estadodeljuego[tappedLocation] == 2 && !gameover) {

            estadodeljuego[tappedLocation] = jugadorActivo;

            tappedView.setTranslationY(-3000f);

            if (jugadorActivo == 0) {
                tappedView.setImageResource(R.drawable.xtictac);
                jugadorActivo = 1;
            } else if (jugadorActivo == 1) {
                tappedView.setImageResource(R.drawable.otictac);
                jugadorActivo = 0; // reiniciamos la variable del jugador para que regrese a la X luego de que el O se a iniciado.
            }
            tappedView.animate().translationYBy(3000f).setDuration(500);
        }

        for (int[]fasesdegane : casilladeganar){

            if(estadodeljuego[fasesdegane[0]] == estadodeljuego[fasesdegane[1]]
                    && estadodeljuego[fasesdegane[1]] == estadodeljuego[fasesdegane[2]]
                    && estadodeljuego[fasesdegane[0]] != 2){

                if(jugadorActivo == 0){

                    Toast.makeText(getApplicationContext(),"EL ganador es O",Toast.LENGTH_LONG).show();

                    AlertDialog nextgame = new AlertDialog.Builder(this)
                            .setTitle(" Ganador es O ")
                            .setMessage("¿Desea Otra Partida?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Friend_Play.this,Friend_Play.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(Friend_Play.this,MainActivity.class);
                                    startActivity(intent2);
                                    finish();
                                }
                            })
                            .create();
                    nextgame.show();

                }

                if(jugadorActivo == 1){

                    Toast.makeText(getApplicationContext(),"EL ganador es X",Toast.LENGTH_LONG).show();

                    AlertDialog nextgame = new AlertDialog.Builder(this)
                            .setTitle(" Ganador es X ")
                            .setMessage("¿Desea Otra Partida?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Friend_Play.this,Friend_Play.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(Friend_Play.this,MainActivity.class);
                                    startActivity(intent2);
                                    finish();
                                }
                            })
                            .create();
                    nextgame.show();

                }


                gameover = true;


                    /*  else {
                          List<Integer> casillas = Arrays.asList(estadodeljuego);

                        if(!casillas.contains(new Integer(2))){
                        Toast.makeText(getApplicationContext(),"Es un Empate",Toast.LENGTH_LONG).show();
                        break;
                         }
                         AlertDialog nextgame = new AlertDialog.Builder(this)
                            .setTitle("Juego Terminado!! Empate!")
                            .setMessage("¿Desea Otra Partida?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Friend_Play.this,Friend_Play.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(Friend_Play.this,MainActivity.class);
                                    startActivity(intent2);
                                    finish();
                                }
                            })
                            .create();
                        nextgame.show();

                }*/
            }

        }

            boolean emptySquare = false; // Metodo para revisar las casillas vacias y buscar empate
            for (int squareState: estadodeljuego){
                if (squareState == 2){
                    emptySquare = true;
                    break;
                }
            }

            if (!emptySquare && !gameover){
                gameover = true;
                Toast.makeText(getApplicationContext(),"Empate!!",Toast.LENGTH_LONG).show();
                AlertDialog nextgame = new AlertDialog.Builder(this)
                        .setTitle(" Empate!!! ")
                        .setMessage("¿Desea Otra Partida?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Friend_Play.this,Friend_Play.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent2 = new Intent(Friend_Play.this,MainActivity.class);
                                startActivity(intent2);
                                finish();
                            }
                        })

                        .create();
                nextgame.show();
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend__play);


    }
}
