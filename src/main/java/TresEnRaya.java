package main.java;

import main.java.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TresEnRaya{

    private final JFrame ventana=new JFrame();
    private final JPanel panelInformacion=new JPanel();
    private final JLabel textoInformacion=new JLabel();
    private final Tablero tablero=new Tablero();
    private final JLabel[][] botones=new JLabel[3][3];
    private String turnoJugador="X";
    private final JButton volverJugar=new JButton("RESET");
    private MouseListener myListener;


    TresEnRaya(){
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(700,700);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());

        //PANEL -- TEXT
        panelInformacion.setBackground(Color.BLACK);
        panelInformacion.setPreferredSize(new Dimension(100,120));
        panelInformacion.setLayout(new BorderLayout());
        panelInformacion.setBorder(BorderFactory.createLineBorder(Color.ORANGE,7));

        textoInformacion.setText("TRES EN RAYA");
        textoInformacion.setFont(new Font("Tahoma",Font.BOLD,40));
        textoInformacion.setForeground(Color.WHITE);
        textoInformacion.setHorizontalAlignment(JLabel.CENTER);
        textoInformacion.setVerticalAlignment(JLabel.CENTER);

        //BOTON RESET
        volverJugar.setFont(new Font("Tahoma",Font.BOLD,25));
        volverJugar.setFocusable(false);
        volverJugar.setVisible(false);
        volverJugar.setPreferredSize(new Dimension(150,100));
        volverJugar.addActionListener(e -> resetJugar());

        panelInformacion.add(volverJugar,BorderLayout.EAST);
        panelInformacion.add(textoInformacion);


        //PANEL -- BOARD
        //Evento Click
        myListener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                accionClickCelda(e);
                ((JLabel) e.getSource()).removeMouseListener(this);
            }
        };


        initBotones();

        ventana.add(panelInformacion,BorderLayout.NORTH);
        ventana.add(tablero);
    }

    private void initBotones(){
        for (int i=0;i<botones.length;i++){
            for(int j=0;j<botones[i].length;j++){
                botones[i][j]=new JLabel();
                botones[i][j].setFont(new Font("MV Boli",Font.BOLD,60));
                botones[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].setOpaque(true);
                botones[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                botones[i][j].setVerticalAlignment(SwingConstants.CENTER);
                botones[i][j].setFocusable(false);

                botones[i][j].addMouseListener(myListener);

                tablero.add(botones[i][j]);
            }
        }
    }

    public void accionClickCelda(MouseEvent  e) {

        tablero.pintarEnTablero((JLabel)e.getSource(),turnoJugador);

        //CHECK GANAR
        if(tablero.ganadorFilas(botones,turnoJugador) || tablero.ganadorColumnas(botones,turnoJugador) || tablero.ganadorDiagonales(botones,turnoJugador)){
            textoInformacion.setText("¡¡ HA GANADO "+turnoJugador+" !!");
            textoInformacion.setForeground(Color.GREEN);
            deshabilitarBotones(e);
            volverJugar.setVisible(true);
            volverJugar.setEnabled(true);

        }else if(tablero.empate(botones)){
            textoInformacion.setText("¡¡ EMPATE !!");
            deshabilitarBotones(e);
            volverJugar.setVisible(true);
            volverJugar.setEnabled(true);
        }else{
            cambiarJugador();
        }

        System.out.println("Jugador actual ->"+turnoJugador+"\n");


    }

    public void deshabilitarBotones(MouseEvent  e){
        for (JLabel[] fila:botones) {
            for(JLabel boton:fila){
                boton.setEnabled(false);
                boton.removeMouseListener(myListener);
            }
        }
    }

    public void cambiarJugador(){
        if(turnoJugador.equals("X")){
            turnoJugador="O";
        }else if(turnoJugador.equals("O")){
            turnoJugador="X";
        }
        textoInformacion.setText("Turno de "+turnoJugador);
    }

    public void resetJugar(){
        for (JLabel[] fila:botones) {
            for(JLabel boton:fila){
                boton.setEnabled(true);
                boton.setText("");
                boton.setBackground(Color.WHITE);
                boton.addMouseListener(myListener);
            }
        }
        turnoJugador="X";
        volverJugar.setVisible(false);
        volverJugar.setEnabled(false);
        textoInformacion.setForeground(Color.WHITE);
        textoInformacion.setText("TRES EN RAYA");
    }

}
