public class Tablero {

    private String tab[][];

    public Tablero(){
        String tab[][] = new String[8][8];
    }

    public String[][] inicializarTablero(String[][] tab){

        for(int x=0;x<8;x++){
            for(int j=0; j<8; j++){
                tab[x][j]=".";
            }
        }

        tab[0][1]="N";
        tab[1][0]="N";
        tab[2][1]="N";
        tab[1][2]="N";
        tab[0][3]="N";
        tab[2][3]="N";
        tab[1][4]="N";
        tab[0][5]="N";
        tab[2][5]="N";
        tab[1][6]="N";
        tab[0][7]="N";
        tab[2][7]="N";

        tab[5][0]="B";
        tab[5][2]="B";
        tab[6][5]="B";
        tab[7][0]="B";
        tab[6][1]="B";
        tab[7][2]="B";
        tab[6][3]="B";
        tab[5][4]="B";
        tab[7][4]="B";
        tab[5][6]="B";
        tab[7][6]="B";
        tab[6][7]="B";


        return tab;
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

}


