import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPartida {

    public static void main (String [ ] args) throws IOException {

        long time_start, time_end;
        time_start = System.currentTimeMillis();

        Tablero iniTab = new Tablero();
        String[][] tablero = new String[8][8];
        tablero = iniTab.inicializarTablero(tablero);

        Scanner reader = new Scanner(System.in);

        int player=0;
        String play="";

        System.out.println("Que jugador quieres ser 1-NEGRAS 2-BLANCAS 3-MAQUINA VS MAQUINA");
        play=reader.nextLine();

        while ((!play.equals("1"))&&(!play.equals("2"))&&(!play.equals("3"))){
            System.out.println("Que jugador quieres ser 1-NEGRAS 2-BLANCAS 3-MAQUINA VS MAQUINA");
            play=reader.nextLine();
        }
        if(play.equals("1")){
            player=1;
        }
        if(play.equals("2")){
            player=2;
        }
        if(play.equals("3")){
            player=3;
        }

        Scanner reader1 = new Scanner(System.in);
        MiniMax minmax = new MiniMax("", tablero);         //maquina negres
        MiniMax minmax1 = new MiniMax("", tablero);       //maquina es blanques

        if(player==3){
            System.out.println("TRIA 1r HEURISTIAC A-B-C");
            String heu=reader1.nextLine();
            while ((!heu.equals("A"))&&(!heu.equals("B"))&&(!heu.equals("C"))){
                System.out.println("TRIA 1r HEURISTIAC A-B-C");
                heu=reader1.nextLine();
            }

            minmax.setHeuristica(heu);

            System.out.println("TRIA 2n HEURISTIAC A-B-C");
            String heu1=reader1.nextLine();

            while ((!heu1.equals("A"))&&(!heu1.equals("B"))&&(!heu1.equals("C"))){
                System.out.println("TRIA 2n HEURISTIAC A-B-C");
                heu1=reader1.nextLine();
            }
            minmax1.setHeuristica(heu1);
        }

        if(player==1){
            System.out.println("TRIA HEURISTIAC A-B-C");
            String heu1=reader1.nextLine();
            while ((!heu1.equals("A"))&&(!heu1.equals("B"))&&(!heu1.equals("C"))){
                System.out.println("TRIA HEURISTIAC A-B-C");
                heu1=reader1.nextLine();
            }
            minmax1.setHeuristica(heu1);
        }

        if(player==2){
            System.out.println("TRIA HEURISTIAC A-B-C");
            String heu1=reader1.nextLine();
            while ((!heu1.equals("A"))&&(!heu1.equals("B"))&&(!heu1.equals("C"))){
                System.out.println("TRIA HEURISTIAC A-B-C");
                heu1=reader1.nextLine();
            }
            minmax.setHeuristica(heu1);
        }

        boolean endGame = false;
        int turn = 1;
        int moviments=0;
        while (endGame==false) {
            iniTab.imprimirTablero(tablero);
            if (turn == 1) {

                moviments++;
                turn = 0;

                if(turnNegre(tablero)==true){
                    if (player == 1) {               //JUGADOR ES NEGRES

                        int horitzontal = 0;
                        int vertical = 0;
                        int moviment = 0;
                        boolean movimentDone = false;

                        while (movimentDone == false) {

                            System.out.println("Turno para NEGRAS. Selecciona dama:");
                            System.out.println("COORDENADA HORIZONTAL");
                            String hor=reader.nextLine();

                            while ((!hor.equals("0"))&&(!hor.equals("1"))&&(!hor.equals("2"))&&(!hor.equals("3"))&&(!hor.equals("4"))&&(!hor.equals("5"))&&(!hor.equals("6"))&&(!hor.equals("7"))){
                                System.out.println("COORDENADA HORIZONTAL 0-7");
                                hor=reader.nextLine();
                            }
                            switch (hor){
                                case "0":
                                    horitzontal=0;
                                    break;
                                case "1":
                                    horitzontal=1;
                                    break;
                                case "2":
                                    horitzontal=2;
                                    break;
                                case "3":
                                    horitzontal=3;
                                    break;
                                case "4":
                                    horitzontal=4;
                                    break;
                                case "5":
                                    horitzontal=5;
                                    break;
                                case "6":
                                    horitzontal=6;
                                    break;
                                case "7":
                                    horitzontal=7;
                                    break;
                            }
                            System.out.println("COORDENADA VERTICAL");
                            String ver=reader.nextLine();

                            while ((!ver.equals("0"))&&(!ver.equals("1"))&&(!ver.equals("2"))&&(!ver.equals("3"))&&(!ver.equals("4"))&&(!ver.equals("5"))&&(!ver.equals("6"))&&(!ver.equals("7"))){
                                System.out.println("COORDENADA HORIZONTAL 0-7");
                                ver=reader.nextLine();
                            }
                            switch (ver){
                                case "0":
                                    vertical=0;
                                    break;
                                case "1":
                                    vertical=1;
                                    break;
                                case "2":
                                    vertical=2;
                                    break;
                                case "3":
                                    vertical=3;
                                    break;
                                case "4":
                                    vertical=4;
                                    break;
                                case "5":
                                    vertical=5;
                                    break;
                                case "6":
                                    vertical=6;
                                    break;
                                case "7":
                                    vertical=7;
                                    break;
                            }

                            System.out.println(tablero[vertical][horitzontal]);

                            while (tablero[vertical][horitzontal].equals(".") || (tablero[vertical][horitzontal].equals("B"))) {
                                System.out.println("Esta dama no existe");
                                System.out.println("Seleccione una dama NEGRA:");
                                System.out.println("COORDENADA HORIZONTAL");
                                horitzontal = reader.nextInt();
                                System.out.println("COORDENADA VERTICAL");
                                vertical = reader.nextInt();
                            }

                            System.out.println("1-DERECHA 2-IZQUIERDA");
                            String mov=reader.nextLine();
                            while ((!mov.equals("2"))&&(!mov.equals("1"))){
                                System.out.println("1-DERECHA 2-IZQUIERDA");
                                mov=reader.nextLine();
                            }
                            switch (mov){
                                case "1":
                                    moviment=1;
                                    break;
                                case "2":
                                    moviment=2;
                                    break;
                            }

                            if (ComprovarMovimientos(vertical, horitzontal, "NEGRAS", tablero, moviment) == true) {
                                movimentDone = true;
                            } else {
                                movimentDone = false;
                                System.out.println("MOVIMIENTO INCORRECTO!");
                            }
                        }

                        if (moviment == 1) {
                            if (tablero[vertical + 1][horitzontal + 1].equals("B")) {
                                tablero[vertical + 2][horitzontal + 2] = "N";
                                tablero[vertical + 1][horitzontal + 1] = ".";

                            } else {
                                tablero[vertical + 1][horitzontal + 1] = "N";
                            }
                        } else {

                            if (tablero[vertical + 1][horitzontal - 1].equals("B")) {
                                tablero[vertical + 2][horitzontal - 2] = "N";
                                tablero[vertical + 1][horitzontal - 1] = ".";
                            } else {
                                tablero[vertical + 1][horitzontal - 1] = "N";
                            }
                        }
                        tablero[vertical][horitzontal] = ".";
                    } else {   //MAQUINA ES NEGRES

                        System.out.println("TURN MAQUINA NEGRAS");
                        turn = 2;

                        minmax.fills(tablero,"NEGRAS");

                        int x = minmax.getxInici();
                        int y = minmax.getyInici();
                        int moviment = minmax.getMoviment();

                        System.out.println("Movimiento negras: ");
                        System.out.println("X: " +x);
                        System.out.println("Y: " +y);
                        System.out.println("Movimiento: " +moviment);

                        if (moviment == 1) {
                            if (tablero[x + 1][y + 1].equals("B")) {
                                tablero[x + 2][y + 2] = "N";
                                tablero[x + 1][y + 1] = ".";

                            } else {
                                tablero[x + 1][y + 1] = "N";
                            }
                        } else {

                            if (tablero[x + 1][y - 1].equals("B")) {
                                tablero[x + 2][y - 2] = "N";
                                tablero[x + 1][y - 1] = ".";
                            } else {
                                tablero[x + 1][y - 1] = "N";
                            }
                        }
                        tablero[x][y] = ".";
                    }
                }else{
                    System.out.println("JUGADOR NEGRO NO TIENEN MOVIMENTOS!");
                    if(turnBlanc(tablero)==false){
                        System.out.println("EL JUGADOR BLANCO TAMPOCO TIENE MOVIMIENTO!");
                        endGame=true;
                    }
                }
            } else {
                moviments++;
                turn = 1;

                if(turnBlanc(tablero)==true){
                    if (player == 2) {              //JUGADOR ES BLANQUES

                        int horitzontal = 0;
                        int vertical = 0;
                        int moviment = 0;
                        boolean movimentDone = false;

                        while (movimentDone == false) {
                            System.out.println("Turno para BLANCAS. Selecciona dama:");
                            System.out.println("COORDENADA HORIZONTAL");
                            String hor=reader.nextLine();

                            while ((!hor.equals("0"))&&(!hor.equals("1"))&&(!hor.equals("2"))&&(!hor.equals("3"))&&(!hor.equals("4"))&&(!hor.equals("5"))&&(!hor.equals("6"))&&(!hor.equals("7"))){
                                System.out.println("COORDENADA HORIZONTAL 0-7");
                                hor=reader.nextLine();
                            }
                            switch (hor){
                                case "0":
                                    horitzontal=0;
                                    break;
                                case "1":
                                    horitzontal=1;
                                    break;
                                case "2":
                                    horitzontal=2;
                                    break;
                                case "3":
                                    horitzontal=3;
                                    break;
                                case "4":
                                    horitzontal=4;
                                    break;
                                case "5":
                                    horitzontal=5;
                                    break;
                                case "6":
                                    horitzontal=6;
                                    break;
                                case "7":
                                    horitzontal=7;
                                    break;
                            }


                            System.out.println("COORDENADA VERTICAL");
                            String ver=reader.nextLine();

                            while ((!ver.equals("0"))&&(!ver.equals("1"))&&(!ver.equals("2"))&&(!ver.equals("3"))&&(!ver.equals("4"))&&(!ver.equals("5"))&&(!ver.equals("6"))&&(!ver.equals("7"))){
                                System.out.println("COORDENADA HORIZONTAL 0-7");
                                ver=reader.nextLine();
                            }
                            switch (ver){
                                case "0":
                                    vertical=0;
                                    break;
                                case "1":
                                    vertical=1;
                                    break;
                                case "2":
                                    vertical=2;
                                    break;
                                case "3":
                                    vertical=3;
                                    break;
                                case "4":
                                    vertical=4;
                                    break;
                                case "5":
                                    vertical=5;
                                    break;
                                case "6":
                                    vertical=6;
                                    break;
                                case "7":
                                    vertical=7;
                                    break;
                            }

                            while (tablero[vertical][horitzontal].equals(".") || (tablero[vertical][horitzontal].equals("N"))) {
                                System.out.println("Esta dama no existe o no tiene movimientos disponibles");
                                System.out.println("SELECCIONE CASILLA DE UNA DAMA BLANCA:");
                                System.out.println("COORDENADA HORIZONTAL");
                                horitzontal = reader.nextInt();
                                System.out.println("COORDENADA VERTICAL");
                                vertical = reader.nextInt();
                            }

                            System.out.println("1-DERECHA 2-IZQUIERDA");
                            String mov=reader.nextLine();

                            while ((!mov.equals("2"))&&(!mov.equals("1"))){
                                System.out.println("1-DERECHA 2-IZQUIERDA");
                                mov=reader.nextLine();
                            }
                            switch (mov){
                                case "1":
                                    moviment=1;
                                    break;
                                case "2":
                                    moviment=2;
                                    break;
                            }

                            if (ComprovarMovimientos(vertical, horitzontal, "BLANCAS", tablero, moviment) == true) {
                                movimentDone = true;
                            } else {
                                movimentDone = false;
                                System.out.println("MOVIMIENTO INCORRECTO!");
                            }
                        }

                        tablero[vertical][horitzontal] = ".";
                        if (moviment == 1) {
                            if (tablero[vertical - 1][horitzontal + 1].equals("N")) {
                                tablero[vertical - 1][horitzontal + 1] = ".";
                                tablero[vertical - 2][horitzontal + 2] = "B";

                            } else {
                                tablero[vertical - 1][horitzontal + 1] = "B";

                            }
                        } else {
                            if (tablero[vertical - 1][horitzontal - 1].equals("N")) {
                                tablero[vertical - 1][horitzontal - 1] = ".";
                                tablero[vertical - 2][horitzontal - 2] = "B";

                            } else {
                                tablero[vertical - 1][horitzontal - 1] = "B";

                            }
                        }
                    } else {  //MAQUINA ES BLANQES
                        System.out.println("TURN MAQUINA BLANQUES");
                        minmax1.fills(tablero,"BLANCAS");

                        int x = minmax1.getxInici();
                        int y = minmax1.getyInici();
                        int moviment = minmax1.getMoviment();

                        System.out.println("Moviment blancas:");
                        System.out.println("X: " +x);
                        System.out.println("Y: " +y);
                        System.out.println("Movimiento: " +moviment);

                        if (moviment == 1) {
                            if (tablero[x - 1][y + 1].equals("N")) {
                                tablero[x - 1][y + 1] = ".";
                                tablero[x - 2][y + 2] = "B";

                            } else {
                                tablero[x - 1][y + 1] = "B";
                                tablero[x][y] = ".";

                            }
                        } else {
                            if (tablero[x - 1][y - 1].equals("N")) {
                                tablero[x - 1][y - 1] = ".";
                                tablero[x - 2][y - 2] = "B";

                            } else {
                                tablero[x - 1][y - 1] = "B";
                                tablero[x][y] = ".";

                            }
                        }
                        tablero[x][y] = ".";
                        turn = 1;
                    }
                }else {
                    System.out.println("EL JUGADOR BLANCO NO TIENE MOVIMIENTOS!");
                    if(turnNegre(tablero)==false){
                        System.out.println("EL JUGADOR BLANCO TAMPOCO TIENE MOVIMIENTOS!");
                        endGame=true;
                    }
                }
              }
            }
            minmax.imprimirTablero(tablero);

            //COMPROVAR GUANYADOR
            String winner=whoWin(tablero);
            switch (winner){
                case "BLACK":
                    System.out.println("NEGRAS GANAN! ");
                    break;
                case "WHITE":
                    System.out.println("BLANCAS GANAN! ");
                    break;
                case "DRAW":
                    System.out.println("EMPATE! ");
                    break;
            }

        time_end = System.currentTimeMillis();
        System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");

    }

    public static String whoWin(String[][] tablero){
       int pointsWhite=0;
       int pointsBlack=0;
       String winner="";

        for(int i=0;i<8;i++){
            if(tablero[7][i].equals("N")){
                pointsBlack++;
            }
            if(tablero[0][i].equals("B")){
                pointsWhite++;
            }
        }
        System.out.println("BLANCAS: "+ pointsWhite);
        System.out.println("NEGRAS: "+ pointsBlack);

        if(pointsBlack>pointsWhite){
            winner="BLACK";
        }else {
            winner="WHITE";
        }
        if(pointsBlack==pointsWhite){
             pointsWhite=0;
             pointsBlack=0;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(tablero[i][j].equals("N")){
                        pointsBlack++;
                    }
                    if(tablero[i][j].equals("B")){
                        pointsWhite++;
                    }
                }
            }
            if(pointsBlack>pointsWhite){
                winner="BLACK";
            }else {
                winner="WHITE";
            }
            if(pointsBlack==pointsWhite){
                winner="DRAW";
            }
        }
        return winner;
    }


    public static boolean ComprovarMovimientos(int vertical, int horizontal, String color,String[][] tablero,int moviment){
       boolean movementPossible=true;
        try
        {
            if(color.equals("NEGRAS")){  //COMPROVAR MOVIMIENTO PARA NEGRAS

                if(vertical==7){
                    movementPossible=false;
                }else{
                    if(moviment==1){            //no dreta
                        if(horizontal==7){
                            movementPossible=false;
                        }else{
                            if(tablero[vertical+1][horizontal+1].equals("N")){
                                movementPossible=false;
                            }
                            if(tablero[vertical+1][horizontal+1].equals("B")&&!tablero[vertical+2][horizontal+2].equals(".")){
                                movementPossible=false;
                            }
                        }
                    }else{
                        if(horizontal==0){              //no Esquerra
                            movementPossible=false;
                        }else{
                            if(tablero[vertical+1][horizontal-1].equals("N")){
                                movementPossible=false;
                            }
                            if(tablero[vertical+1][horizontal-1].equals("B")&&!tablero[vertical+2][horizontal-2].equals(".")){
                                movementPossible=false;
                            }
                        }

                    }

                }
            }else { //COMPROVAR MOVIMIENTO PARA BLANCAS
                if(vertical==0){
                    movementPossible=false;
                }else{
                    if(moviment==1){
                        if(horizontal==7){
                            movementPossible=false;
                        }else{
                            if(tablero[vertical-1][horizontal+1].equals("B")){
                                movementPossible=false;
                            }
                            if(tablero[vertical-1][horizontal+1].equals("N")&&!tablero[vertical-2][horizontal+2].equals(".")){
                                movementPossible=false;
                            }

                        }
                    }else{
                        if(horizontal==0){
                            movementPossible=false;
                        }else{
                            if(tablero[vertical-1][horizontal-1].equals("B")){
                                movementPossible=false;
                            }
                            if(tablero[vertical-1][horizontal-1].equals("N")&&!tablero[vertical-2][horizontal-2].equals(".")){
                                movementPossible=false;
                            }
                        }
                    }

                }
            }

        }catch (ArrayIndexOutOfBoundsException a){
            movementPossible=false;

        }
        return movementPossible;
    }

    public static  boolean turnBlanc(String[][] tablero){
        boolean moreMovements=false;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tablero[i][j].equals("B")){
                    boolean a;
                    a=ComprovarMovimientos(i,j,"BLANCAS",tablero,1);
                    if(a==true){
                        moreMovements=true;
                    }
                    a=ComprovarMovimientos(i,j,"BLANCAS",tablero,2);
                    if(a==true){
                        moreMovements=true;
                    }
                }
            }
        }
        return moreMovements;
    }

    public static boolean turnNegre(String[][] tablero){
        boolean moreMovements=false;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tablero[i][j].equals("N")){
                    boolean a;
                    a=ComprovarMovimientos(i,j,"NEGRAS",tablero,1);
                    if(a==true){
                        moreMovements=true;
                    }
                    a=ComprovarMovimientos(i,j,"NEGRAS",tablero,2);
                    if(a==true){
                        moreMovements=true;
                    }
                }
            }
        }
        return moreMovements;
    }

}
