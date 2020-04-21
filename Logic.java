    // ============================================================================
    //    Taken and adapted from: http://programmingnotes.org/
    // ============================================================================
    import javax.swing.*;
    import java.awt.*;
    
    public class Logic
    {
        public static void getMove(int rowMove, int colMove, int move, Font font, JButton square[][], 
        String startingPlayer)
        {   // gets the current move "X" or "O" for the user & displays to screen
            square[rowMove][colMove].setFont(font);
            //TODO: create logic to set which player made the move
            String player2;
            String currentPlayer = startingPlayer;
            if (startingPlayer.equals("O")){
                player2 = "X";
            }else{
                player2 = "O";
            }
            if (move % 2 != 0) {
                currentPlayer = player2;
            }
            square[rowMove][colMove].setText(currentPlayer);
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
