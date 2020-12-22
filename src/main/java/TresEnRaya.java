package main.java;

import main.java.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TresEnRaya implements ActionListener {

    private final JFrame ventana=new JFrame();
    private final JPanel panelInformacion=new JPanel();
    private final JLabel textoInformacion=new JLabel();
    private final Tablero tablero=new Tablero();
    private final JButton[][] botones=new JButton[3][3];
    private String turnoJugador="X";
    private final JButton volverJugar=new JButton("RESET");

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
        initBotones();

        ventana.add(panelInformacion,BorderLayout.NORTH);
        ventana.add(tablero);
    }

    private void initBotones(){
        for (int i=0;i<botones.length;i++){
            for(int j=0;j<botones[i].length;j++){
                botones[i][j]=new JButton();
                botones[i][j].setFont(new Font("MV Boli",Font.BOLD,60));
                botones[i][j].setFocusable(false);
                botones[i][j].addActionListener(this);
                tablero.add(botones[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        tablero.pintarEnTablero((JButton)e.getSource(),turnoJugador);

        //CHECK GANAR
        if(tablero.ganadorFilas(botones,turnoJugador) || tablero.ganadorColumnas(botones,turnoJugador) || tablero.ganadorDiagonales(botones,turnoJugador)){
            textoInformacion.setText("¡¡ HA GANADO "+turnoJugador+" !!");
            textoInformacion.setForeground(Color.GREEN);
            deshabilitarBotones();
            volverJugar.setVisible(true);
            volverJugar.setEnabled(true);

        }else if(tablero.empate(botones)){
            textoInformacion.setText("¡¡ EMPATE !!");
            deshabilitarBotones();
            volverJugar.setVisible(true);
            volverJugar.setEnabled(true);
        }else{
            cambiarJugador();
        }

    }

    public void deshabilitarBotones(){
        for (JButton[] fila:botones) {
            for(JButton boton:fila){
                boton.setEnabled(false);
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
        for (JButton[] fila:botones) {
            for(JButton boton:fila){
                boton.setEnabled(true);
                boton.setText("");
                boton.setBackground(null);
            }
        }
        turnoJugador="X";
        volverJugar.setVisible(false);
        volverJugar.setEnabled(false);
        textoInformacion.setText("TRES EN RAYA");
    }

}
