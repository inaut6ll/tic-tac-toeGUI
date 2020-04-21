// ============================================================================
//     Taken and adapted from: http://programmingnotes.org/
// ============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
    // setting up ALL the variables
    private int size = -1;
    JFrame window = new JFrame("Tic Tac Toe");

    JMenuBar menuMain = new JMenuBar();
    JMenuItem menuNewGame = new JMenuItem("New Game"), 
    menuGameTitle = new JMenuItem("Tic Tac Toe"),
    menuStartingPlayer = new JMenuItem("Starting Player"),
    menuExit = new JMenuItem("      Quit");

    JButton [][] square;

    JPanel panelNewGame = new JPanel(),
        panelNorth = new JPanel(),
        panelSouth = new JPanel(),
        panelTop = new JPanel(),
        panelBottom = new JPanel(),
        panelPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();

    private JRadioButton selectX = new JRadioButton("User Plays X", false);
    private JRadioButton selectO = new JRadioButton("User Plays O", false);
    private ButtonGroup radioGroup;
    private String startingPlayer= "";
    private String player2 = "";
    final int X = 900, Y = 480, color = 160; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean squareClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Rufscript", Font.BOLD, 100);
    private int movesMade = 0;
    private int maxMoves;

    //===============================  GUI  ========================================//
    public GUI() //This is the constructor
    {
        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(0, 0);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelNorth.setBackground(new Color(100, 0, 0));
        panelSouth.setBackground(new Color(200, color, color));

        panelTop.setBackground(new Color(200, color, color));
        panelBottom.setBackground(new Color(200, color, color));

        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(200, color, color));
        panelBottom.setBackground(new Color(200, color, color));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));

        // adding menu items to menu bar
        menuMain.add(menuGameTitle);
        menuGameTitle.setEnabled(false);
        menuGameTitle.setFont(new Font("Courier New",Font.BOLD,22));
        menuMain.add(menuNewGame);
        menuNewGame.setFont(new Font("Courier New",Font.BOLD,22));
        menuMain.add(menuStartingPlayer);
        menuStartingPlayer.setFont(new Font("Courier New",Font.BOLD,22));
        menuMain.add(menuExit);
        menuExit.setFont(new Font("Courier New",Font.BOLD,22));//---->Menu Bar Complete

        // adding X & O options to menu
        selectX.setFont(new Font("Courier New",Font.BOLD,22));
        selectO.setFont(new Font("Courier New",Font.BOLD,22));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(selectX); // add plain to group
        radioGroup.add(selectO);
        radioPanel.add(selectX);
        radioPanel.add(selectO);

        // adding Action Listener to all the Buttons and Menu Items
        menuNewGame.addActionListener(this);
        menuExit.addActionListener(this);
        menuStartingPlayer.addActionListener(this);


        // adding everything needed to panelNorth and panelSouth
        panelNorth.add(menuMain);
        Logic.showGame(panelSouth,panelPlayingField);

        // adding to window and Showing window
        window.add(panelNorth, BorderLayout.NORTH);
        window.add(panelSouth, BorderLayout.CENTER);
        window.setVisible(true);
    }// End GUI
    
    // setting up the playing field
    public void setLay(){
        square = new JButton[size][size];
        maxMoves = square.length * square[0].length;
        
        panelPlayingField.setLayout(new GridLayout(size, size, 2, 2));
        panelPlayingField.setBackground(Color.black);
        for(int r = 0; r < square.length; r++)   
            for(int c = 0; c < square[0].length; c++)
            {
                //creates button for each possible X/O location
                square[r][c] = new JButton();
                square[r][c].setBackground(new Color(200, 160, 160));
                square[r][c].addActionListener(this);
                panelPlayingField.add(square[r][c]);
                square[r][c].setEnabled(setTableEnabled);
            }
    }

    // ===========  Start Action Performed  ===============//
    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user
        Object source = click.getSource();

        // check if a button was clicked on the gameboard
        if (square != null) {
            for(int rowMove=0; rowMove < square.length; rowMove++) 
                for(int colMove = 0; colMove < square[0].length; colMove++)
                {
                    if(source == square[rowMove][colMove] && movesMade < maxMoves 
                        && !square[rowMove][colMove].getText().equals("O")
                        && !square[rowMove][colMove].getText().equals("X"))  
                    {
                        squareClicked = true;
                        Logic.getMove(rowMove, colMove, movesMade, font, 
                            square, startingPlayer);          
                        panelPlayingField.requestFocus();
                        movesMade++;
                    }
                }
        }
        // if a button was clicked on the gameboard, check for a winner
        if(squareClicked) 
        {
            if(Logic.checkWin(square, startingPlayer, size)){
                inGame = false;
                int option = JOptionPane.showConfirmDialog(null, "Player 1 won. Yaaaaaaaaaaay. Do you want to play again?", 
                    "Game Won" ,JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }else {
                    redrawGameBoard();
                }
            }else if(Logic.checkWin(square, player2, size)){
                inGame = false;
                int option = JOptionPane.showConfirmDialog(null, "Player 2 won. Cool, very cool. Do you want to play again?", 
                    "Game Won" ,JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }else {
                    redrawGameBoard();
                }
            }else if(Logic.checkFilled(square, size)){
                inGame = false;
                int option = JOptionPane.showConfirmDialog(null, "It's a tie. I am not surprised. Do you want to play again?", 
                    "Game Won" ,JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }else {
                    redrawGameBoard();
                }
            }else {
                inGame = true;
                squareClicked = false;
            }
        }

        // check if the user clicks on a menu item
        if(source == menuNewGame)    
        {
            Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
                panelPlayingField,panelBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                Logic.showGame(panelSouth,panelPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " your current game will be lost..." + "\n" +"Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        Logic.showGame(panelSouth,panelPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    redrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == menuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        // select X or O player 
        else if(source == menuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                Logic.showGame(panelSouth,panelPlayingField);
            }
            else
            {
                setTableEnabled = true;
                Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
                    panelPlayingField,panelBottom,radioPanel);

                selectX.addActionListener(new RadioListener());
                selectO.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(selectX);
                radioPanel.add(selectO);
                panelSouth.setLayout(new GridLayout(2, 1, 2, 1));
                panelSouth.add(radioPanel);
                panelSouth.add(panelBottom);
            }
        }
        panelSouth.setVisible(false); 
        panelSouth.setVisible(true);  
    }// End Action Performed

    // ===========  Start RadioListener  ===============//  
    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton) event.getSource();
            if(theButton.getText().equals("User Plays X")) 
            {
                startingPlayer = "X";
                player2 = "O";
            }
            if(theButton.getText().equals("User Plays O"))
            {
                startingPlayer = "O";
                player2 = "X";
            }

            // redisplay the gameboard to the screen
            if(size == -1){
                String input = JOptionPane.showInputDialog(null, "What size board do you want?", 
                        "3");
                size = Integer.parseInt(input);
                setLay();
            }
            
            panelSouth.setVisible(false); 
            panelSouth.setVisible(true);          
            redrawGameBoard();
        }
    }// End RadioListener
    /*
    ----------------------------------
    Start of all the other methods.
    ----------------------------------
     */
    private void redrawGameBoard()  
    {
        Logic.clearPanelSouth(panelSouth,panelTop,panelNewGame,
            panelPlayingField,panelBottom,radioPanel);
        Logic.showGame(panelSouth,panelPlayingField);       

        movesMade = 0;
        squareClicked = false;

        for(int row = 0; row < square.length; row++){
            for(int col = 0; col < square[0].length; col++)
            {
                square[row][col].setText("");
                square[row][col].setEnabled(setTableEnabled);
            }
        }

        win = false;        
    }
}   
