import java.util.ArrayList;

public class MiniMax {

    private String heuristica;
    String[][] tablero;

    private int xInici;
    private int yInici;
    private int moviment;
    ArrayList<Integer> listX=new ArrayList<Integer>();
    ArrayList<Integer> listY=new ArrayList<Integer>();
    ArrayList<Integer> mov=new ArrayList<Integer>();

    private int heuristicaGLOBAL;
    private int heuristicaGLOBALA;
    private int heuristicaGLOBALc;

    public MiniMax(String heuristica, String[][] tablero) {
        this.heuristica = heuristica;
        this.tablero = tablero;
        heuristicaGLOBAL = 0;
        heuristicaGLOBALA=8;
        heuristicaGLOBALc=-100;

    }

    public void setHeuristica(String heuristica){
        this.heuristica=heuristica;
    }


    public void fills(String[][] nouTauler, String color) {
        this.tablero = nouTauler;
        boolean primercop = false;
        heuristicaGLOBALA=8;

        if(color.equals("NEGRAS")){
            for (int x = 0; x <= 7; x++) {
                for (int j = 0; j <= 7; j++) {
                    if (nouTauler[x][j].equals("N")) {  //TROBEM UNA BLANCA        PRIMER VERTICAL DESPRES HORITZONTAL
                        generarFills(x, j, nouTauler,"NEGRAS",1);
                    }
                }
            }
        }else{
            for (int x = 0; x <= 7; x++) {
                for (int j = 0; j <= 7; j++) {
                    if (nouTauler[x][j].equals("B")) {  //TROBEM UNA BLANCA        PRIMER VERTICAL DESPRES HORITZONTAL
                        generarFills(x, j, nouTauler,"BLANCAS",1);
                    }
                }
            }
        }
    }

    public int generarFillsContrari(String[][] tablero, String color){
        int heuristica=0;
        String ficha="";
        if(color.equals("NEGRAS")){
            ficha="N";
        }else {
            ficha="B";
        }
        for(int i=0; i<=7;i++){
            for(int j=0; j<=7;j++){
                if(tablero[i][j].equals(ficha)){
                    heuristica=generarFills(i,j,tablero,color,2);
                }
            }
        }

        return heuristica;
    }


    public int generarFills(int x, int j, String[][] tauler,String color, int nivell) {  //x vertical j horitzontal
        String[][] tableroNou = new String[8][8];
        boolean fillPosible = false;
        int resultatHeuristica1 = 0;
        int resultatHeuristica2 = 0;
        int moviment = 0;
        int heuristicaFinal=0;
        int heuristicaFills=0;

        fillPosible = ComprovarMovimientos(x, j, color, tauler, 1);

        if (fillPosible == true) { //FILL DRET ES POSSIBLE

            resultatHeuristica1 = aplicarHeuristica(tauler,x,j,1,color,nivell);

            if(heuristica.equals("A")){
                if ((heuristicaGLOBALA > resultatHeuristica1)&&(nivell==1)) {
                    heuristicaGLOBALA=resultatHeuristica1;
                    this.xInici = x;
                    this.yInici = j;
                    this.moviment = 1;
                    heuristicaFinal=heuristicaGLOBALA;
                }
                if(nivell==2){
                    heuristicaFinal=heuristicaGLOBALA;
                }
            }

            if(heuristica.equals("B")){
                if((resultatHeuristica1==0)&&(nivell==1)){
                    int randomPosition = (int) Math.floor(Math.random()*listY.size());
                    this.xInici=listX.get(randomPosition);
                    this.yInici = listY.get(randomPosition);
                    this.moviment = mov.get(randomPosition);
                }
            }

            if(heuristica.equals("C")){
                if ((heuristicaGLOBALc < resultatHeuristica1)&&(nivell==1)) {
                    heuristicaGLOBALA=resultatHeuristica1;
                    this.xInici = x;
                    this.yInici = j;
                    this.moviment = 1;
                    heuristicaFinal=heuristicaGLOBALA;
                }
                if(nivell==2){
                    heuristicaFinal=heuristicaGLOBALA;
                }
            }

        }

        fillPosible = false;
        fillPosible = ComprovarMovimientos(x, j, color, tauler, 2);

        if (fillPosible == true) { //FILL ESQUERRA ES POSSIBLE

            resultatHeuristica2 = aplicarHeuristica(tauler,x,j,2,color,nivell);

            if(heuristica.equals("A")){
                if ((heuristicaGLOBALA > resultatHeuristica2)&&(nivell==1)) {
                    heuristicaGLOBALA=resultatHeuristica2;
                    this.xInici = x;
                    this.yInici = j;
                    this.moviment = 2;
                    heuristicaFinal=heuristicaGLOBALA;
                }
                if(nivell==2){
                    heuristicaFinal=heuristicaGLOBALA;
                }
            }

            if(heuristica.equals("B")){
                if((resultatHeuristica2==0)&&(nivell==1)){
                    int randomPosition = (int) Math.floor(Math.random()*listY.size());
                    this.xInici=listX.get(randomPosition);
                    this.yInici = listY.get(randomPosition);
                    this.moviment = mov.get(randomPosition);
                }
            }

            if(heuristica.equals("C")){
                if ((heuristicaGLOBALc < resultatHeuristica2)&&(nivell==1)) {
                    heuristicaGLOBALA=resultatHeuristica2;
                    this.xInici = x;
                    this.yInici = j;
                    this.moviment = 2;
                    heuristicaFinal=heuristicaGLOBALA;
                }
                if(nivell==2){
                    heuristicaFinal=heuristicaGLOBALA;
                }
            }
        }
        return heuristicaFinal;
    }

    public int aplicarHeuristica(String[][] tablero,int c,int d,int moviment,String color,int nivell) {
        int heuristicaResult = 0;
        for(int a=0; a<listX.size();a++){
            listX.remove(a);
            listY.remove(a);
            mov.remove(a);
        }
        String lletra="";
        if(color.equals("BLANCAS")){
            if (heuristica.equals("A")) {
                String[][] copia=copyTablero(tablero);
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c - 1][d + 1].equals("N")) {
                        copia[c - 1][d + 1] = ".";
                        copia[c - 2][d + 2] = "B";
                    } else {
                        copia[c - 1][d + 1] = "B";
                        copia[c][d] = ".";
                    }
                } else {
                    if (copia[c - 1][d - 1].equals("N")) {
                        copia[c - 1][d - 1] = ".";
                        copia[c - 2][d - 2] = "B";
                    } else {
                        copia[c - 1][d - 1] = "B";
                        copia[c][d] = ".";
                    }
                }
                boolean primerCop=false;
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){
                        if(copia[i][j].equals("B")){
                            if(primerCop==false){
                                primerCop=true;
                                heuristicaResult=i;
                            }
                        }
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }
            }
            if (heuristica.equals("B")) {
                int position=7;
                for (int x = 0; x <= 7; x++) {
                    for (int j = 0; j <= 7; j++) {
                        if(tablero[x][j].equals("B")){
                            if(ComprovarMovimientos(x,j,"BLANCAS",tablero,1)){
                                listX.add(x);
                                listY.add(j);
                                mov.add(1);

                                if((x+1<=7)&&(x+2<=7)){
                                    if((j-1>=0)&&(j-2>=0)){
                                        if(tablero[x-1][j+1].equals("N")&&tablero[x-2][j+2].equals(".")){
                                            heuristicaResult=heuristicaResult+1;
                                            this.xInici = x;
                                            this.yInici = j;
                                            this.moviment = 1;
                                        }
                                    }
                                }
                            }
                            if(ComprovarMovimientos(x,j,"BLANCAS",tablero,2)){
                                listX.add(x);
                                listY.add(j);
                                mov.add(2);

                                if((x-1>=0)&&(x-2>=0)){
                                    if((j-1>=0)&&(j-2>=0)){
                                        if(tablero[x-1][j-1].equals("N")&&tablero[x-2][j-2].equals(".")){
                                            heuristicaResult=heuristicaResult+1;
                                            this.xInici = x;
                                            this.yInici = j;
                                            this.moviment = 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                String[][] copia=copyTablero(tablero);           //Si falles treure segon nivell B
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c - 1][d + 1].equals("N")) {
                        copia[c - 1][d + 1] = ".";
                        copia[c - 2][d + 2] = "B";
                    } else {
                        copia[c - 1][d + 1] = "B";
                        copia[c][d] = ".";
                    }
                } else {
                    if (copia[c - 1][d - 1].equals("N")) {
                        copia[c - 1][d - 1] = ".";
                        copia[c - 2][d - 2] = "B";
                    } else {
                        copia[c - 1][d - 1] = "B";
                        copia[c][d] = ".";
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }

            }
            if (heuristica.equals("C")) {
                String[][] copia=copyTablero(tablero);
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c - 1][d + 1].equals("N")) {
                        copia[c - 1][d + 1] = ".";
                        copia[c - 2][d + 2] = "B";

                    } else {
                        copia[c - 1][d + 1] = "B";
                        copia[c][d] = ".";

                    }
                } else {
                    if (copia[c - 1][d - 1].equals("N")) {
                        copia[c - 1][d - 1] = ".";
                        copia[c - 2][d - 2] = "B";

                    } else {
                        copia[c - 1][d - 1] = "B";
                        copia[c][d] = ".";

                    }
                }
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){
                        if(copia[i][j].equals("B")){
                            heuristicaResult=heuristicaResult+i;
                        }
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }
            }
        }else{   //FER LES HEURISTIQES PER QUAN LA MAQUINA ES NEGRAS
            if (heuristica.equals("A")) {
                String[][] copia=copyTablero(tablero);
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c + 1][d + 1].equals("B")) {
                        copia[c + 2][d + 2] = "N";
                        copia[c + 1][d + 1] = ".";

                    } else {
                        copia[c + 1][d + 1] = "N";
                    }
                } else {

                    if (copia[c + 1][d - 1].equals("B")) {
                        copia[c + 2][d - 2] = "N";
                        copia[c + 1][d - 1] = ".";
                    } else {
                        copia[c + 1][d - 1] = "N";
                    }
                }
                boolean primerCop=false;
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){
                        if(copia[i][j].equals("B")){
                            if(primerCop==false){
                                primerCop=true;
                                heuristicaResult=i;
                            }
                        }
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }
            }

            if(heuristica.equals("B")){
                int position=7;
                for (int x = 0; x <= 7; x++) {
                    for (int j = 0; j <= 7; j++) {
                        if(tablero[x][j].equals("N")){
                            if(ComprovarMovimientos(x,j,"NEGRAS",tablero,1)){
                                listX.add(x);
                                listY.add(j);
                                mov.add(1);

                                if((x+1<=7)&&(x+2<=7)){
                                    if((j+1<=7)&&(j+2<=7)){
                                        if(tablero[x+1][j+1].equals("B")&&tablero[x+2][j+2].equals(".")){
                                            heuristicaResult=heuristicaResult+1;
                                            this.xInici = x;
                                            this.yInici = j;
                                            this.moviment = 1;
                                        }
                                    }
                                }
                            }
                            if(ComprovarMovimientos(x,j,"NEGRAS",tablero,2)){
                                listX.add(x);
                                listY.add(j);
                                mov.add(2);
                                if((x+1<=7)&&(x+2<=7)){
                                    if((j+1<=7)&&(j+2<=7)){
                                        if(tablero[x+1][j-1].equals("B")&&tablero[x+2][j-2].equals(".")){
                                            heuristicaResult=heuristicaResult+1;
                                            this.xInici = x;
                                            this.yInici = j;
                                            this.moviment = 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                String[][] copia=copyTablero(tablero);              //Si falles treure segon nivell B
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c + 1][d + 1].equals("B")) {
                        copia[c + 2][d + 2] = "N";
                        copia[c + 1][d + 1] = ".";

                    } else {
                        copia[c + 1][d + 1] = "N";
                    }
                } else {

                    if (copia[c + 1][d - 1].equals("B")) {
                        copia[c + 2][d - 2] = "N";
                        copia[c + 1][d - 1] = ".";
                    } else {
                        copia[c + 1][d - 1] = "N";
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }

            }

            if(heuristica.equals("C")){
                String[][] copia=copyTablero(tablero);
                copia[c][d]=".";
                if (moviment == 1) {
                    if (copia[c + 1][d + 1].equals("B")) {
                        copia[c + 2][d + 2] = "N";
                        copia[c + 1][d + 1] = ".";

                    } else {
                        copia[c + 1][d + 1] = "N";
                    }
                } else {

                    if (copia[c + 1][d - 1].equals("B")) {
                        copia[c + 2][d - 2] = "N";
                        copia[c + 1][d - 1] = ".";
                    } else {
                        copia[c + 1][d - 1] = "N";
                    }
                }
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){
                        if(copia[i][j].equals("B")){
                            heuristicaResult=-(heuristicaResult+i);
                        }
                    }
                }
                if(nivell==2){
                    int heuristicaFills=generarFillsContrari(copia,color);
                    if(heuristicaFills>heuristicaResult){
                        heuristicaResult=heuristicaFills;
                    }
                }
            }

        }

            return heuristicaResult;
        }

        public int getxInici () {
            return xInici;
        }

        public int getyInici () {
            return yInici;
        }

        public int getMoviment () {
            return moviment;
        }

    public static boolean ComprovarMovimientos(int vertical, int horizontal, String color, String[][] tablero, int moviment) {
        boolean movementPossible = true;
        try {
            if (color.equals("NEGRAS")) {  //COMPROVAR MOVIMIENTO PARA NEGRAS

                if (vertical == 7) {
                    movementPossible = false;   //comprovar no podem anar mes amunt
                } else {
                    if (moviment == 1) {            //no dreta
                        if (horizontal == 7) {
                            movementPossible = false;
                        } else {
                            if (tablero[vertical + 1][horizontal + 1].equals("N")) {
                                movementPossible = false;
                            }
                            if (tablero[vertical + 1][horizontal + 1].equals("B") && !tablero[vertical + 2][horizontal + 2].equals(".")) {
                                movementPossible = false;
                            }
                        }
                    } else {
                        if (horizontal == 0) {              //no Esquerra
                            movementPossible = false;
                        } else {
                            if (tablero[vertical + 1][horizontal - 1].equals("N")) {
                                movementPossible = false;
                            }
                            if (tablero[vertical + 1][horizontal - 1].equals("B") && !tablero[vertical + 2][horizontal - 2].equals(".")) {
                                movementPossible = false;
                            }
                        }

                    }

                }
            } else { //COMPROVAR MOVIMIENTO PARA BLANCAS
                if (vertical == 0) {
                    movementPossible = false;
                } else {
                    if (moviment == 1) {
                        if (horizontal == 7) {
                            movementPossible = false;

                        } else {
                            if (tablero[vertical - 1][horizontal + 1].equals("B")) {
                                movementPossible = false;

                            }
                            if (tablero[vertical - 1][horizontal + 1].equals("N") && !tablero[vertical - 2][horizontal + 2].equals(".")) {
                                movementPossible = false;

                            }

                        }
                    } else {
                        if (horizontal == 0) {
                            movementPossible = false;

                        } else {
                            if (tablero[vertical - 1][horizontal - 1].equals("B")) {
                                movementPossible = false;


                            }
                            if (tablero[vertical - 1][horizontal - 1].equals("N") && !tablero[vertical - 2][horizontal - 2].equals(".")) {
                                movementPossible = false;

                            }
                        }
                    }

                }
            }

        } catch (ArrayIndexOutOfBoundsException a) {
            movementPossible = false;

        }
        return movementPossible;
    }

    public String[][] copyTablero(String[][] tablero) {
        String[][] tableroNou = new String[8][8];
        for (int x = 0; x <= 7; x++) {
            for (int j = 0; j <= 7; j++) {
                tableroNou[x][j] = tablero[x][j];
            }
        }
        return tableroNou;
    }
    public void imprimirTablero(String[][] tablero){
        System.out.println("    \t0\t1\t2\t3\t4\t5\t6\t7");
        System.out.println("     ----------------------------------");
        for(int x=0;x<8;x++){
            System.out.print(x+"\t|  ");
            for(int j=0; j<8; j++){
                if(j==7){
                    System.out.print(tablero[x][j] +"  ");
                }else{
                    System.out.print(tablero[x][j]+"\t");
                }
            }
            System.out.print("| ");

            System.out.println();
        }
        System.out.println("     ----------------------------------");
    }

    public int getHueristica(){
        return heuristicaGLOBALA;
    }

}

