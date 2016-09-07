package compiladores.lexico;

public class AnalizadorLexico {
    int matEstados[][];
    AccionSemantica accionesSemanticas[][];
    private final static int FINAL = -1;
    private final static int ERROR = -2;

    AnalizadorLexico() {
        matEstados[0][0] = 1;
        matEstados[0][1] = FINAL;
        matEstados[0][2] = ERROR;
        matEstados[0][3] = 2;
        matEstados[0][4] = 7;
        matEstados[0][5] = ERROR;
        matEstados[0][6] = 1;
        matEstados[0][7] = 1;
        matEstados[0][8] = FINAL;
        matEstados[0][9] = 7;
        matEstados[0][10] = FINAL;
        matEstados[0][11] = FINAL;
        matEstados[0][12] = FINAL;
        matEstados[0][13] = FINAL;
        matEstados[0][14] = FINAL;
        matEstados[0][15] = FINAL;
        matEstados[0][16] = ERROR;
        matEstados[0][17] = 8;
        matEstados[0][18] = ERROR;
        matEstados[0][19] = ERROR;
        matEstados[0][20] = 0;
        matEstados[0][21] = 0;
        matEstados[0][22] = 9;
        matEstados[0][23] = 17;
        matEstados[0][24] = 17;
        matEstados[0][25] = 17;
        matEstados[0][26] = 17;
        matEstados[0][27] = FINAL;

        matEstados[1][1] = FINAL;
        matEstados[1][0] = FINAL;
        matEstados[1][2] = FINAL;
        matEstados[1][3] = FINAL;
        matEstados[1][4] = FINAL;
        matEstados[1][5] = FINAL;
        matEstados[1][6] = FINAL;
        matEstados[1][7] = FINAL;
        matEstados[1][8] = FINAL;
        matEstados[1][9] = FINAL;
        matEstados[1][10] = FINAL;
        matEstados[1][11] = FINAL;
        matEstados[1][12] = FINAL;
        matEstados[1][13] = FINAL;
        matEstados[1][14] = FINAL;
        matEstados[1][15] = FINAL;
        matEstados[1][16] = FINAL;
        matEstados[1][17] = FINAL;
        matEstados[1][18] = FINAL;
        matEstados[1][19] = FINAL;
        matEstados[1][20] = FINAL;
        matEstados[1][21] = FINAL;
        matEstados[1][22] = FINAL;
        matEstados[1][23] = FINAL;
        matEstados[1][24] = FINAL;
        matEstados[1][25] = FINAL;
        matEstados[1][26] = FINAL;
        matEstados[1][27] = FINAL;

        matEstados[2][0] = FINAL;
        matEstados[2][1] = FINAL;
        matEstados[2][2] = FINAL;
        matEstados[2][3] = FINAL;
        matEstados[2][4] = FINAL;
        matEstados[2][5] = 3;
        matEstados[2][6] = FINAL;
        matEstados[2][7] = FINAL;
        matEstados[2][8] = FINAL;
        matEstados[2][9] = FINAL;
        matEstados[2][10] = FINAL;
        matEstados[2][11] = FINAL;
        matEstados[2][12] = FINAL;
        matEstados[2][13] = FINAL;
        matEstados[2][14] = FINAL;
        matEstados[2][15] = FINAL;
        matEstados[2][16] = FINAL;
        matEstados[2][17] = FINAL;
        matEstados[2][18] = FINAL;
        matEstados[2][19] = FINAL;
        matEstados[2][20] = FINAL;
        matEstados[2][21] = FINAL;
        matEstados[2][22] = FINAL;
        matEstados[2][23] = FINAL;
        matEstados[2][24] = FINAL;
        matEstados[2][25] = FINAL;
        matEstados[2][26] = FINAL;
        matEstados[2][27] = FINAL;

        matEstados[3][0] = 3;
        matEstados[3][1] = 3;
        matEstados[3][2] = 5;
        matEstados[3][3] = 3;
        matEstados[3][4] = 3;
        matEstados[3][5] = 4;
        matEstados[3][6] = 3;
        matEstados[3][7] = 3;
        matEstados[3][8] = 3;
        matEstados[3][9] = 3;
        matEstados[3][10] = 3;
        matEstados[3][11] = 3;
        matEstados[3][12] = 3;
        matEstados[3][13] = 3;
        matEstados[3][14] = 3;
        matEstados[3][15] = 3;
        matEstados[3][16] = 3;
        matEstados[3][17] = 3;
        matEstados[3][18] = 3;
        matEstados[3][19] = 3;
        matEstados[3][20] = 3;
        matEstados[3][21] = 3;
        matEstados[3][22] = 3;
        matEstados[3][23] = 3;
        matEstados[3][24] = 3;
        matEstados[3][25] = 3;
        matEstados[3][26] = 3;
        matEstados27[15] = 3;

        matEstados[4][0] = 3;
        matEstados[4][1] = 3;
        matEstados[4][2] = 3;
        matEstados[4][3] = 0;
        matEstados[4][4] = 3;
        matEstados[4][5] = 4;
        matEstados[4][6] = 3;
        matEstados[4][7] = 3;
        matEstados[4][8] = 3;
        matEstados[4][9] = 3;
        matEstados[4][10] = 3;
        matEstados[4][11] = 3;
        matEstados[4][12] = 3;
        matEstados[4][13] = 3;
        matEstados[4][14] = 3;
        matEstados[4][15] = 3;
        matEstados[4][16] = 3;
        matEstados[4][17] = 3;
        matEstados[4][18] = 3;
        matEstados[4][19] = 3;
        matEstados[4][20] = 3;
        matEstados[4][21] = 3;
        matEstados[4][22] = 3;
        matEstados[4][23] = 3;
        matEstados[4][24] = 3;
        matEstados[4][25] = 3;
        matEstados[4][26] = 3;
        matEstados27[15] = 3;

        matEstados[5][0] = ERROR;
        matEstados[5][1] = ERROR;
        matEstados[5][2] = ERROR;
        matEstados[5][3] = ERROR;
        matEstados[5][4] = ERROR;
        matEstados[5][5] = ERROR;
        matEstados[5][6] = ERROR;
        matEstados[5][7] = ERROR;
        matEstados[5][8] = ERROR;
        matEstados[5][9] = ERROR;
        matEstados[5][10] = ERROR;
        matEstados[5][11] = ERROR;
        matEstados[5][12] = ERROR;
        matEstados[5][13] = ERROR;
        matEstados[5][14] = ERROR;
        matEstados[5][15] = ERROR;
        matEstados[5][16] = ERROR;
        matEstados[5][17] = ERROR;
        matEstados[5][18] = 6;
        matEstados[5][19] = ERROR;
        matEstados[5][20] = ERROR;
        matEstados[5][21] = ERROR;
        matEstados[5][22] = ERROR;
        matEstados[5][23] = ERROR;
        matEstados[5][24] = ERROR;
        matEstados[5][25] = ERROR;
        matEstados[5][26] = ERROR;
        matEstados[5][27] = ERROR;

        matEstados[6][0] = 6;
        matEstados[6][1] = 6;
        matEstados[6][2] = 6;
        matEstados[6][3] = 6;
        matEstados[6][4] = 6;
        matEstados[6][5] = 6;
        matEstados[6][6] = 6;
        matEstados[6][7] = 6;
        matEstados[6][8] = 6;
        matEstados[6][9] = 6;
        matEstados[6][10] = 6;
        matEstados[6][11] = 6;
        matEstados[6][12] = 6;
        matEstados[6][13] = 6;
        matEstados[6][14] = 6;
        matEstados[6][15] = 6;
        matEstados[6][16] = 6;
        matEstados[6][17] = 6;
        matEstados[6][18] = 6;
        matEstados[6][19] = 6;
        matEstados[6][20] = FINAL;
        matEstados[6][21] = 6;
        matEstados[6][22] = 6;
        matEstados[6][23] = 6;
        matEstados[6][24] = 6;
        matEstados[6][25] = 6;
        matEstados[6][26] = 6;
        matEstados27[15] = 6;

        matEstados[7][0] = ERROR;
        matEstados[7][1] = ERROR;
        matEstados[7][2] = ERROR;
        matEstados[7][3] = ERROR;
        matEstados[7][4] = ERROR;
        matEstados[7][5] = ERROR;
        matEstados[7][6] = ERROR;
        matEstados[7][7] = ERROR;
        matEstados[7][8] = FINAL;
        matEstados[7][9] = ERROR;
        matEstados[7][10] = ERROR;
        matEstados[7][11] = ERROR;
        matEstados[7][12] = ERROR;
        matEstados[7][13] = ERROR;
        matEstados[7][14] = ERROR;
        matEstados[7][15] = ERROR;
        matEstados[7][16] = ERROR;
        matEstados[7][17] = ERROR;
        matEstados[7][18] = ERROR;
        matEstados[7][19] = ERROR;
        matEstados[7][20] = ERROR;
        matEstados[7][21] = ERROR;
        matEstados[7][22] = ERROR;
        matEstados[7][23] = ERROR;
        matEstados[7][24] = ERROR;
        matEstados[7][25] = ERROR;
        matEstados[7][26] = ERROR;
        matEstados[7][27] = ERROR;

        matEstados[8][0] = 8;
        matEstados[8][1] = 8;
        matEstados[8][2] = 8;
        matEstados[8][3] = 8;
        matEstados[8][4] = 8;
        matEstados[8][5] = 8;
        matEstados[8][6] = 8;
        matEstados[8][7] = 8;
        matEstados[8][8] = 8;
        matEstados[8][9] = 8;
        matEstados[8][10] = 8;
        matEstados[8][11] = 8;
        matEstados[8][12] = 8;
        matEstados[8][13] = 8;
        matEstados[8][14] = 8;
        matEstados[8][15] = 8;
        matEstados[8][16] = 8;
        matEstados[8][17] = FINAL;
        matEstados[8][18] = 8;
        matEstados[8][19] = 8;
        matEstados[8][20] = 8;
        matEstados[8][21] = 8;
        matEstados[8][22] = 8;
        matEstados[8][23] = 8;
        matEstados[8][24] = 8;
        matEstados[8][25] = 8;
        matEstados[8][26] = 8;
        matEstados27[15] = 8;

        matEstados[9][0] = ERROR;
        matEstados[9][1] = ERROR;
        matEstados[9][2] = ERROR;
        matEstados[9][3] = ERROR;
        matEstados[9][4] = ERROR;
        matEstados[9][5] = ERROR;
        matEstados[9][6] = ERROR;
        matEstados[9][7] = ERROR;
        matEstados[9][8] = ERROR;
        matEstados[9][9] = ERROR;
        matEstados[9][10] = ERROR;
        matEstados[9][11] = ERROR;
        matEstados[9][12] = ERROR;
        matEstados[9][13] = ERROR;
        matEstados[9][14] = ERROR;
        matEstados[9][15] = ERROR;
        matEstados[9][16] = ERROR;
        matEstados[9][17] = ERROR;
        matEstados[9][18] = ERROR;
        matEstados[9][19] = ERROR;
        matEstados[9][20] = ERROR;
        matEstados[9][21] = ERROR;
        matEstados[9][22] = ERROR;
        matEstados[9][23] = 10;
        matEstados[9][24] = 12;
        matEstados[9][25] = ERROR;
        matEstados[9][26] = ERROR;
        matEstados[9][27] = ERROR;

        matEstados[10][0] = 11;
        matEstados[10][1] = 11;
        matEstados[10][2] = ERROR;
        matEstados[10][3] = ERROR;
        matEstados[10][4] = ERROR;
        matEstados[10][5] = ERROR;
        matEstados[10][6] = ERROR;
        matEstados[10][7] = ERROR;
        matEstados[10][8] = ERROR;
        matEstados[10][9] = ERROR;
        matEstados[10][10] = ERROR;
        matEstados[10][11] = ERROR;
        matEstados[10][12] = ERROR;
        matEstados[10][13] = ERROR;
        matEstados[10][14] = ERROR;
        matEstados[10][15] = ERROR;
        matEstados[10][16] = ERROR;
        matEstados[10][17] = ERROR;
        matEstados[10][18] = 11;
        matEstados[10][19] = 11;
        matEstados[10][20] = ERROR;
        matEstados[10][21] = ERROR;
        matEstados[10][22] = ERROR;
        matEstados[10][23] = ERROR;
        matEstados[10][24] = ERROR;
        matEstados[10][25] = ERROR;
        matEstados[10][26] = ERROR;
        matEstados[10][27] = ERROR;

        matEstados[11][11] = FINAL;
        matEstados[11][1] = FINAL;
        matEstados[11][2] = FINAL;
        matEstados[11][3] = FINAL;
        matEstados[11][4] = FINAL;
        matEstados[11][5] = FINAL;
        matEstados[11][6] = FINAL;
        matEstados[11][7] = FINAL;
        matEstados[11][8] = FINAL;
        matEstados[11][9] = FINAL;
        matEstados[11][10] = FINAL;
        matEstados[11][11] = FINAL;
        matEstados[11][12] = FINAL;
        matEstados[11][13] = FINAL;
        matEstados[11][14] = FINAL;
        matEstados[11][15] = FINAL;
        matEstados[11][16] = FINAL;
        matEstados[11][17] = FINAL;
        matEstados[11][18] = 11;
        matEstados[11][19] = 11;
        matEstados[11][20] = FINAL;
        matEstados[11][21] = FINAL;
        matEstados[11][22] = FINAL;
        matEstados[11][23] = FINAL;
        matEstados[11][24] = FINAL;
        matEstados[11][25] = FINAL;
        matEstados[11][26] = FINAL;
        matEstados[11][27] = FINAL;

        matEstados[12][0] = 13;
        matEstados[12][1] = 13;
        matEstados[12][2] = ERROR;
        matEstados[12][3] = ERROR;
        matEstados[12][4] = ERROR;
        matEstados[12][5] = ERROR;
        matEstados[12][6] = ERROR;
        matEstados[12][7] = ERROR;
        matEstados[12][8] = ERROR;
        matEstados[12][9] = ERROR;
        matEstados[12][10] = ERROR;
        matEstados[12][11] = ERROR;
        matEstados[12][12] = ERROR;
        matEstados[12][13] = ERROR;
        matEstados[12][14] = ERROR;
        matEstados[12][15] = ERROR;
        matEstados[12][16] = ERROR;
        matEstados[12][17] = ERROR;
        matEstados[12][18] = 13;
        matEstados[12][19] = 13;
        matEstados[12][20] = ERROR;
        matEstados[12][21] = ERROR;
        matEstados[12][22] = ERROR;
        matEstados[12][23] = ERROR;
        matEstados[12][24] = ERROR;
        matEstados[12][25] = ERROR;
        matEstados[12][26] = ERROR;
        matEstados[12][27] = ERROR;

        matEstados[13][0] = ERROR;
        matEstados[13][1] = ERROR;
        matEstados[13][2] = ERROR;
        matEstados[13][3] = ERROR;
        matEstados[13][4] = ERROR;
        matEstados[13][5] = ERROR;
        matEstados[13][6] = ERROR;
        matEstados[13][7] = ERROR;
        matEstados[13][8] = ERROR;
        matEstados[13][9] = ERROR;
        matEstados[13][10] = ERROR;
        matEstados[13][11] = ERROR;
        matEstados[13][12] = ERROR;
        matEstados[13][13] = ERROR;
        matEstados[13][14] = ERROR;
        matEstados[13][15] = ERROR;
        matEstados[13][16] = 14;
        matEstados[13][17] = ERROR;
        matEstados[13][18] = 13;
        matEstados[13][19] = 13;
        matEstados[13][20] = ERROR;
        matEstados[13][21] = ERROR;
        matEstados[13][22] = ERROR;
        matEstados[13][23] = ERROR;
        matEstados[13][24] = ERROR;
        matEstados[13][25] = ERROR;
        matEstados[13][26] = ERROR;
        matEstados[13][27] = ERROR;

        matEstados[14][14] = FINAL;
        matEstados[14][1] = FINAL;
        matEstados[14][2] = FINAL;
        matEstados[14][3] = FINAL;
        matEstados[14][4] = FINAL;
        matEstados[14][5] = FINAL;
        matEstados[14][6] = FINAL;
        matEstados[14][7] = FINAL;
        matEstados[14][8] = FINAL;
        matEstados[14][9] = FINAL;
        matEstados[14][10] = FINAL;
        matEstados[14][11] = FINAL;
        matEstados[14][12] = FINAL;
        matEstados[14][13] = FINAL;
        matEstados[14][14] = FINAL;
        matEstados[14][15] = FINAL;
        matEstados[14][16] = FINAL;
        matEstados[14][17] = FINAL;
        matEstados[14][18] = 14;
        matEstados[14][19] = 14;
        matEstados[14][20] = FINAL;
        matEstados[14][21] = FINAL;
        matEstados[14][22] = FINAL;
        matEstados[14][23] = FINAL;
        matEstados[14][24] = FINAL;
        matEstados[14][25] = 15;
        matEstados[14][26] = FINAL;
        matEstados[14][27] = FINAL;

        matEstados[15][0] = 16;
        matEstados[15][1] = 16;
        matEstados[15][2] = ERROR;
        matEstados[15][3] = ERROR;
        matEstados[15][4] = ERROR;
        matEstados[15][5] = ERROR;
        matEstados[15][6] = ERROR;
        matEstados[15][7] = ERROR;
        matEstados[15][8] = ERROR;
        matEstados[15][9] = ERROR;
        matEstados[15][10] = ERROR;
        matEstados[15][11] = ERROR;
        matEstados[15][12] = ERROR;
        matEstados[15][13] = ERROR;
        matEstados[15][14] = ERROR;
        matEstados[15][15] = ERROR;
        matEstados[15][16] = ERROR;
        matEstados[15][17] = ERROR;
        matEstados[15][18] = 16;
        matEstados[15][19] = 16;
        matEstados[15][20] = ERROR;
        matEstados[15][21] = ERROR;
        matEstados[15][22] = ERROR;
        matEstados[15][23] = ERROR;
        matEstados[15][24] = ERROR;
        matEstados[15][25] = ERROR;
        matEstados[15][26] = ERROR;

        matEstados[16][0] = FINAL;
        matEstados[15][27] = ERROR;
        matEstados[16][1] = FINAL;
        matEstados[16][2] = FINAL;
        matEstados[16][3] = FINAL;
        matEstados[16][4] = FINAL;
        matEstados[16][5] = FINAL;
        matEstados[16][6] = FINAL;
        matEstados[16][7] = FINAL;
        matEstados[16][8] = FINAL;
        matEstados[16][9] = FINAL;
        matEstados[16][10] = FINAL;
        matEstados[16][11] = FINAL;
        matEstados[16][12] = FINAL;
        matEstados[16][13] = FINAL;
        matEstados[16][14] = FINAL;
        matEstados[16][15] = FINAL;
        matEstados[16][16] = FINAL;
        matEstados[16][17] = FINAL;
        matEstados[16][18] = 16;
        matEstados[16][19] = 16;
        matEstados[16][20] = FINAL;
        matEstados[16][21] = FINAL;
        matEstados[16][22] = FINAL;
        matEstados[16][23] = FINAL;
        matEstados[16][24] = FINAL;
        matEstados[16][25] = FINAL;
        matEstados[16][26] = FINAL;
        matEstados[16][27] = FINAL;

        matEstados[17][0] = FINAL;
        matEstados[17][1] = FINAL;
        matEstados[17][2] = FINAL;
        matEstados[17][3] = FINAL;
        matEstados[17][4] = FINAL;
        matEstados[17][5] = FINAL;
        matEstados[17][6] = FINAL;
        matEstados[17][7] = FINAL;
        matEstados[17][8] = FINAL;
        matEstados[17][9] = FINAL;
        matEstados[17][10] = FINAL;
        matEstados[17][11] = FINAL;
        matEstados[17][12] = FINAL;
        matEstados[17][13] = FINAL;
        matEstados[17][14] = FINAL;
        matEstados[17][15] = FINAL;
        matEstados[17][16] = FINAL;
        matEstados[17][17] = FINAL;
        matEstados[17][18] = FINAL;
        matEstados[17][19] = FINAL;
        matEstados[17][20] = FINAL;
        matEstados[17][21] = FINAL;
        matEstados[17][22] = 17;
        matEstados[17][23] = 17;
        matEstados[17][24] = 17;
        matEstados[17][25] = 17;
        matEstados[17][26] = 17;
        matEstados[17][27] = FINAL;
    }

    private int getColumn(char c) {
        switch (c) {
            case "a":  return 26; case "b":  return 26; case "c":  return 26; case "d":  return 26;
            case "e":  return 26; case "f":  return 24; case "g":  return 26; case "h":  return 26;
            case "i":  return 23; case "j":  return 26; case "k":  return 26; case "l":  return 26;
            case "m":  return 26; case "n":  return 26; case "o":  return 26; case "p":  return 26;
            case "q":  return 26; case "r":  return 26; case "s":  return 26; case "t":  return 26;
            case "u":  return 26; case "v":  return 26; case "w":  return 26; case "x":  return 26;
            case "y":  return 26; case "z":  return 26;
                
            case "A": return 26; case "B": return 26; case "C": return 26; case "D": return 26;
            case "E": return 25; case "F": return 26; case "G": return 26; case "H": return 26;
            case "I": return 26; case "J": return 26; case "K": return 26; case "L": return 26;
            case "M": return 26; case "N": return 26; case "O": return 26; case "P": return 26;
            case "Q": return 26; case "R": return 26; case "S": return 26; case "T": return 26;
            case "U": return 26; case "V": return 26; case "W": return 26; case "X": return 26;
            case "Y": return 26; case "Z": return 26; 
            
            case "0": return 18;  case "1": return 18;  case "2": return 19;  case "3": return 19;
            case "4": return 19;  case "5": return 19;  case "6": return 19;  case "7": return 19;
            case "8": return 19;  case "9": return 19;  
                
            case "@": return 2;  case "+": return 0;  case "/": return 3;  case "*": return 27;
            case "-": return 1;  case "<": return 6; case ">": return 7; case "=": return 8;
            case "_": return 22; case "(": return 10; case ")": return 11; case "\"":return 17;
            case ",": return 14; case ";": return 15; case "'": return 17; case "\t": return 21;
            case ".": return 16; case "/n": return 20; case " ": return 23; case "!": return 9;
            case "#" : return 5; case "{" : return 12; case "}": return 13; case ":": return 4;
                
            default: return 255;
        }
    }

    void ejecutar() {
        
    }
}
