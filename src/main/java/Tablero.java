package main.java;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel{

    private JLabel[] posicionesGanadoras;

    Tablero(){
        initBoard();
    }

    private void initBoard(){
        this.setLayout(new GridLayout(3,3,0,0));
        posicionesGanadoras=new JLabel[3];
    }

    public void pintarEnTablero(JLabel boton,String jugador){
        UIManager.put("Label.disabledForeground",Color.BLACK);
        boton.setEnabled(false);
        boton.setText(jugador);
        System.out.println("Ficha puesta por ->"+jugador);

    }

    public boolean empate(JLabel[][] botones){
        boolean empate=true;

        for (JLabel[] fila:botones) {
            for (JLabel boton: fila) {
                if (boton.getText().equals("")){
                    empate=false;
                }
            }
        }
        return empate;
    }

    public boolean ganadorFilas(JLabel[][] botones,String jugador){
        int contadorFilas=0;
        boolean ganador=false;

        for (JLabel[] fila:botones) {
            for (JLabel boton: fila) {
                if (boton.getText().equals(jugador)){
                    posicionesGanadoras[contadorFilas]=boton;
                    contadorFilas++;
                }
            }
            if (contadorFilas==3){
                ganador=true;
                pintarBotonesAlGanar();
            }
            contadorFilas=0;
        }

        return ganador;
    }

    public boolean ganadorColumnas(JLabel[][] botones,String jugador){
        int contadorColumnas=0;
        boolean ganador=false;

        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                if(botones[j][i].getText().equals(jugador)){
                    posicionesGanadoras[contadorColumnas]=botones[j][i];
                    contadorColumnas++;
                }
            }
            if(contadorColumnas==3){
                ganador=true;
                pintarBotonesAlGanar();
            }
            contadorColumnas=0;
        }
        return ganador;
    }

    public boolean ganadorDiagonales(JLabel[][] botones,String jugador){
        int contadorDiagonal1=0;
        int contadorDiagonal2=0;
        boolean ganador=false;

        for (int i = 0; i < botones.length; i++) {
            if(botones[i][i].getText().equals(jugador)){
                posicionesGanadoras[contadorDiagonal1]=botones[i][i];
                contadorDiagonal1++;
            }
        }
        if(contadorDiagonal1==3){
            pintarBotonesAlGanar();
            ganador=true;
        }
        for (int i = 0; i<botones.length ; i++) {
            if(botones[i][botones.length-1-i].getText().equals(jugador)){
                posicionesGanadoras[contadorDiagonal2]=botones[i][botones.length-1-i];
                contadorDiagonal2++;
            }
        }
        if(contadorDiagonal2==3){
            pintarBotonesAlGanar();
            ganador=true;
        }
        return ganador;
    }

    public void pintarBotonesAlGanar(){
        for (JLabel b:posicionesGanadoras) {
            b.setBackground(Color.GREEN);
        }
    }
}
