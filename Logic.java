    // ============================================================================
    //    Taken and adapted from: http://programmingnotes.org/
    // ============================================================================
import javax.swing.*;
import java.awt.*;
    
    public class Logic
    {
        public static void getMove(int rowMove, int colMove, int move, Font font, JButton square[][], 
        String startingPlayer, boolean ai)
        {   // gets the current move "X" or "O" for the user & displays to screen
            square[rowMove][colMove].setFont(font);
            int size = square.length;
            //TODO: create logic to set which player made the move
            
            String player2;
            if (startingPlayer.equals("O")){
                player2 = "X";
            }else{
                player2 = "O";
            }
                
            if (ai == false){ 
                String currentPlayer = startingPlayer;
                if (move % 2 != 0) {
                    currentPlayer = player2;
                }
                square[rowMove][colMove].setText(currentPlayer);
            }else{
                //for ai
                square[rowMove][colMove].setText(startingPlayer);
                
                if(move < (size * size) - 1) {
                    boolean chosen = false;
                    int r = 0; 
                    int c = 0;
                    
                    int c1 = checkPosWinRow(player2, square, size);
                    if (c1 != -1) {
                        c = checkColOfRow(c1, square);
                        if (c != -1) {
                            r = c1;
                            chosen = true;
                        }
                        
                    }
                    
                    if (!chosen) {
                        int c2 = checkPosWinCol(player2, square, size);
                        if (c2 != -1) {
                            r = checkRowOfCol(c2, square);
                            if (r != -1) {
                                c = c2;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        if (checkPosWinDiag1(player2, square, size)) {
                            r = getPosWinDiag1(square, size);
                            if (r != -1) {
                                c = r;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        if (checkPosWinDiag2(player2, square, size)) {
                            r = getPosWinDiag2(square, size);
                            if (r != -1) {
                                c = size - 1 - r;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        int c5 = checkPosWinRow(startingPlayer, square, size);
                        if (c5 != -1) {
                            c = checkColOfRow(c5, square);
                            if (c != -1) {
                                r = c5;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        int c6 = checkPosWinCol(startingPlayer, square, size);
                        if (c6 != -1) {
                            r = checkRowOfCol(c6, square);
                            if (r != -1) {
                                c = c6;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        if (checkPosWinDiag1(startingPlayer, square, size)) {
                            r = getPosWinDiag1(square, size);
                            if (r != -1) {
                                c = r;
                                chosen = true;
                            }
                        }
                    }
                    
                    if (!chosen) {
                        if (checkPosWinDiag2(startingPlayer, square, size)) {
                            r = getPosWinDiag2(square, size);
                            if (r != -1) {
                                c = size - 1 - r;
                                chosen = true;
                            }
                        }
                    }
                    
                    while (!chosen) {
                        r = (int)(Math.random() * size);
                        c = (int)(Math.random() * size);
                        if (checkAvailable(r, c, square)) {
                            chosen = true;
                        }
                    }
                    
                    
                    square[r][c].setFont(font);
                    square[r][c].setText(player2);
                }
            }
        }
    
    
    ///////////////////////////////////////////
    
    public static boolean checkAvailable(int row, int col, JButton square[][]) {
        if (!square[row][col].getText().equals("X") && !square[row][col].getText().equals("O")) {
            return true;
        }
        return false;
    }
    
    public static int checkPosWinRow(String OX, JButton square[][], int size) { //fix: returns when it's not supposed to
        for (int row = 0; row < size; row++) {
            int count = 0;
            for (int col = 0; col < square[0].length; col++) {
               if (square[row][col].getText().equals(OX)) {
                   count++;
               }
            }
            if (count == size - 1) {
                return row;
            }
        }
        return -1;
    }
    
    public static int checkColOfRow(int row, JButton square[][]) {
         for (int col = 0; col < square[0].length; col++) {
             if (square[row][col].getText().equals("")) {
                 return col;
             }
         }
         return -1; 
    }
    
    public static int checkPosWinCol(String OX, JButton square[][], int size) {
        for (int col = 0; col < square[0].length; col++) {
            int count = 0;
            for (int row = 0; row < size; row++) {
               if (square[row][col].getText() != null && square[row][col].getText().equals(OX)) {
                   count++;
               }
            }
            if (count == size - 1) {
                return col;
            }
        }
        return -1;
    }
    
    public static int checkRowOfCol(int col, JButton square[][]) {
        for (int row = 0; row < square[0].length; row++) {
             if (square[row][col].getText().equals("")) {
                 return row;
             }
         }
         return -1; 
    }
    
    public static boolean checkPosWinDiag1(String OX, JButton square[][], int size) {
        int count = 0;
        for (int row = 0; row < size; row++) {
           if (square[row][row].getText() != null && square[row][row].getText().equals(OX)) {
                count++;
           }
           if (count == size - 1) {
                return true;
           }
        }
        return false;
    }
    
    public static int getPosWinDiag1(JButton square[][], int size) {
        for (int row = 0; row < size; row++) {
            if (square[row][row].getText().equals("")) {
                return row;
            }
        }
        return -1;
    }
    
    public static boolean checkPosWinDiag2(String OX, JButton square[][], int size) {
        int count = 0;
        for (int row = 0; row < size; row++) {
            if (square[row][size - 1 - row].getText() != null && square[row][size - 1 - row].getText().equals(OX)) {
                count++;
            }
            if (count == size) {
                return true;
            }
        }
        return false;
    }
    
    public static int getPosWinDiag2(JButton square[][], int size) {
        for (int row = 0; row < size; row++) {
            if (square[row][size - 1 - row].getText().equals("")) {
                return row;
            }
        }
        return -1;
    }
        
        
     
        
    
    
    public static boolean checkRows(JButton square[][], String OX, int size) {
        for (int row = 0; row < size; row++) {
            int count = 0;
            for (int col = 0; col < square[0].length; col++) {
               if (square[row][col].getText() != null && square[row][col].getText().equals(OX)) {
                   count++;
               }
            }
            if (count == size) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkCols(JButton square[][], String OX, int size) {
        for (int col = 0; col < square[0].length; col++) {
            int count = 0;
            for (int row = 0; row < size; row++) {
               if (square[row][col].getText() != null && square[row][col].getText().equals(OX)) {
                   count++;
               }
            }
            if (count == size) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkDiags(JButton square[][], String OX, int size) {
        int count = 0;
        for (int row = 0; row < size; row++) {
            if (square[row][row].getText() != null && square[row][row].getText().equals(OX)) {
                count++;
            }
        }
        if (count == size) {
            return true;
        }
        count = 0;
        for (int row = 0; row < size; row++) {
            if (square[row][size - 1 - row].getText() != null && square[row][size - 1 - row].getText().equals(OX)) {
                count++;
            }
        }
        if (count == size) {
            return true;
        }
        return false;
    }

    public static boolean checkWin(JButton square[][], String OX, int size){
        if (Logic.checkRows(square, OX, size) || Logic.checkCols(square, OX, size) 
                || Logic.checkDiags(square, OX, size)){
            return true;
        }
        return false;
    }
    
    public static boolean checkFilled(JButton square[][], int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < square[0].length; col++) {
                if (!square[row][col].getText().equals("X") && !square[row][col].getText().equals("O")) {
                    return false;
                }
            }
        }
        return true;
   }
    

    public static void showGame(JPanel pnlSouth, JPanel pnlPlayingField)
    {   // shows the Playing Field
        pnlSouth.setLayout(new BorderLayout());
        pnlSouth.add(pnlPlayingField, BorderLayout.CENTER);
        pnlPlayingField.requestFocus(); 
    }

    public static void clearPanelSouth(JPanel pnlSouth, JPanel pnlTop, 
    JPanel pnlNewGame, JPanel pnlPlayingField, JPanel pnlBottom, JPanel radioPanel) 
    {   // clears any posible panels on screen
        pnlSouth.remove(pnlTop); 
        pnlSouth.remove(pnlBottom);
        pnlSouth.remove(pnlPlayingField);
        pnlTop.remove(pnlNewGame);
        pnlSouth.remove(radioPanel);
    }
}
