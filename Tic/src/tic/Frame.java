//******************************************************************************
// STUDENT NAME:  Olivia Rios
// FIU EMAIL: orios010@fiu.edu
// CLASS: COP 2210 – Fall 2019
// ASSIGNMENT #     #5
// DATE: 12-2-19
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else, except as outlined in the 
// assignment instructions.
//******************************************************************************
package tic;


import java.util.Random;
import javax.swing.*;

public class Frame extends JFrame {

    private final ImageIcon theX = new ImageIcon("x.png");
    private final ImageIcon theO = new ImageIcon("o.png");
    private final ImageIcon tail = new ImageIcon("tails.png");
    private final ImageIcon head = new ImageIcon("heads.png");
    private final int[][] board = new int[3][3];
   private final JLabel ok[][] = new JLabel[3][3];
    private int move = 0;
    private int userScore = 0;
    private int pcScore = 0;
    private boolean starts;
    private String name;

    public Frame() {
        initComponents();
        ok[0][0] = position_1;
        ok[0][1] = position_2;
        ok[0][2] = position_3;
        ok[1][0] = position_4;
        ok[1][1] = position_5;
        ok[1][2] = position_6;
        ok[2][0] = position_7;
        ok[2][1] = position_8;
        ok[2][2] = position_9;

    }

//    public JLabel[][] labels() {
//        
//        return ok;
//    }

    /*this is the method that is called on the main class*/
    public void on() {
        userName();
        rule();
        starter();

    }

    public void rule() {
        JOptionPane.showMessageDialog(null, "Well " + name + ", let me tell you a bit about the RULES "
                + "of my game. \n"
                + "1) You are always going to be \"X\" and the pc \"O\" \n"
                + "2) A coin is going to be flipped: heads you go first, taisl the pc goes first\n"
                + "3) This is a simple TicTacToe "
        );
    }

    /* To ask for the name*/
    public String userName() {
        name = JOptionPane.showInputDialog(null, "Please enter your name");
        return name;
    }

    /* to choose randomly who is going to start first */
    public void starter() {
      
        Random ran = new Random();
        starts = ran.nextBoolean();
        if (starts) {
            // player goes first 
            JOptionPane.showMessageDialog(null, name + " pick an empty space",
                    "Player Turn", 0, head);
        } else {
           // pc goes first 
            JOptionPane.showMessageDialog(null, "The pc is going to start first.",
                    "Computer Turn", 0, tail);
            pcTurn();
        }
    }

    /*this method is going to be used whenever the player click on the labels */
    public void playerTurn(JLabel choice, int i, int j) {
        if (board[i][j] == 0) {
            board[i][j] = 1;
            choice.setIcon(theX);
            move++;
            System.out.println("after player turn " + move);
            if (move > 1) {
                checkWinner(board);
            }
            pcTurn();
        } else {
            // to advoid the user over riding the taken slot
            JOptionPane.showMessageDialog(null, "taken");
        }
    }

    /*
    Now the pc turn, this is called in two ways 
    1) if they are called first 
    2) right after the player move
     */
    public void pcTurn() {
        System.out.println("in the method before the loop");
        Random num = new Random();
        boolean found = false;
        int n = 0;                                  // to get it out of the loop
        int i = num.nextInt(3);
        int j = num.nextInt(3);

        while (!found) {
            System.out.println("in the loop");
            if (board[i][j] == 0) {
                board[i][j] = 2;
                ok[i][j].setIcon(theO);
                move++;
               break;
                // to exit the loop
            } else {

                // so if the first sey of number is taken then new number should be generated
                i = num.nextInt(3);
                j = num.nextInt(3);
                n++;
                if (n == 50) {
                    for (int[] board1 : board) {
                        for (int m = 0; m < board1.length; m++) {
                            if (board1[m] == 0) {
                                board1[m] = 2;
                                found = true;
                            }
                        }
                    }
                }
            }

        }
        // to start checking for winners
        if (move > 1) {
            checkWinner(board);
            
        }
        System.out.println("end of the method");
    }

    public void checkWinner(int t[][]) {
        // this checks for all the wining posabilitites 
        if (((t[0][0] == 1) && (t[0][1] == 1) && (t[0][2] == 1))
                || ((t[1][0] == 1) && (t[1][1] == 1) && (t[1][2] == 1))
                || ((t[2][0] == 1) && (t[2][1] == 1) && (t[2][2] == 1))
                || ((t[0][0] == 1) && (t[1][1] == 1) && (t[2][2] == 1))
                || ((t[0][2] == 1) && (t[1][1] == 1) && (t[2][0] == 1))
                || ((t[0][0] == 1) && (t[1][0] == 1) && (t[2][0] == 1))
                || ((t[0][1] == 1) && (t[1][1] == 1) && (t[2][1] == 1))
                || ((t[0][2] == 1) && (t[1][2] == 1) && (t[2][2] == 1))) {
            userScore++;                   // keep record of the score
            JOptionPane.showMessageDialog(null, name + " you won!!!!!"
                    + "\n\nThe score is \n" + name + ": " + userScore
                    + "\nComputer: " + pcScore);
            again(board);
        }
        if (((t[0][0] == 2) && (t[0][1] == 2) && (t[0][2] == 2))
                || ((t[1][0] == 2) && (t[1][1] == 2) && (t[1][2] == 2))
                || ((t[2][0] == 2) && (t[2][1] == 2) && (t[2][2] == 2))
                || ((t[0][0] == 2) && (t[1][1] == 2) && (t[2][2] == 2))
                || ((t[0][2] == 2) && (t[1][1] == 2) && (t[2][0] == 2))
                || ((t[0][0] == 2) && (t[1][0] == 2) && (t[2][0] == 2))
                || ((t[0][1] == 2) && (t[1][1] == 2) && (t[2][1] == 2))
                || ((t[0][2] == 2) && (t[1][2] == 2) && (t[2][2] == 2))) {
            pcScore++;                        // keep record of the score
            JOptionPane.showMessageDialog(null, name + " you lost"
                    + "\n\nThe score is: \n" + name + ": " + userScore
                    + "\nComputer: " + pcScore);
            again(board);
        }
        if (move >= 9) {                       // if more than 9 move have taken place but nobody has win 
            JOptionPane.showMessageDialog(null, "its a tie");
            again(board);
        }
    }

    public void clear(){
        for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    ok[i][j].setIcon(new ImageIcon("")); // set the image to nothing
                    board[i][j] = 0;                              // resets all the values 
                    move = 0;                                   // resets all the move
                }
                }
    }
    public void again(int t[][]) {
        System.out.println("its the main foult");
        int ans = JOptionPane.showConfirmDialog(null, "again", " ",
                JOptionPane.YES_NO_OPTION);
        if (ans == 0) {                                          // if the answer is yes
            clear();
            }
            //calls on the coin flipper to decide who begins  
        else {                                                 // if the answer is no 
            System.exit(0);
        }
        starter();
    }

    // this was generated along the design part;
    //like what I did in the desing tab was traslated to code in here
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        position_1 = new javax.swing.JLabel();
        position_2 = new javax.swing.JLabel();
        position_3 = new javax.swing.JLabel();
        position_4 = new javax.swing.JLabel();
        position_5 = new javax.swing.JLabel();
        position_6 = new javax.swing.JLabel();
        position_7 = new javax.swing.JLabel();
        position_8 = new javax.swing.JLabel();
        position_9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        position_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_1MouseClicked(evt);
            }
        });
        getContentPane().add(position_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, 70));

        position_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_2MouseClicked(evt);
            }
        });
        getContentPane().add(position_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 70, 60));

        position_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_3MouseClicked(evt);
            }
        });
        getContentPane().add(position_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 70, 60));

        position_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_4MouseClicked(evt);
            }
        });
        getContentPane().add(position_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 70, 60));

        position_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_5MouseClicked(evt);
            }
        });
        getContentPane().add(position_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 80, 60));

        position_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_6MouseClicked(evt);
            }
        });
        getContentPane().add(position_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 70, 60));

        position_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_7MouseClicked(evt);
            }
        });
        getContentPane().add(position_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 70, 60));

        position_8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_8MouseClicked(evt);
            }
        });
        getContentPane().add(position_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 70, 60));

        position_9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                position_9MouseClicked(evt);
            }
        });
        getContentPane().add(position_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 70, 70));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic/table.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * ****** ON CLICK ACTIONS ******
     */
    // when clicked then the player method would be called

    private void position_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_1MouseClicked
        playerTurn(position_1, 0, 0);
    }//GEN-LAST:event_position_1MouseClicked

    private void position_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_2MouseClicked
        playerTurn(position_2, 0, 1);

    }//GEN-LAST:event_position_2MouseClicked

    private void position_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_3MouseClicked
        playerTurn(position_3, 0, 2);
    }//GEN-LAST:event_position_3MouseClicked

    private void position_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_4MouseClicked
        playerTurn(position_4, 1, 0);
    }//GEN-LAST:event_position_4MouseClicked

    private void position_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_5MouseClicked
        playerTurn(position_5, 1, 1);
    }//GEN-LAST:event_position_5MouseClicked

    private void position_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_6MouseClicked
        playerTurn(position_6, 1, 2);

    }//GEN-LAST:event_position_6MouseClicked

    private void position_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_7MouseClicked
        playerTurn(position_7, 2, 0);
    }//GEN-LAST:event_position_7MouseClicked

    private void position_8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_8MouseClicked
        playerTurn(position_8, 2, 1);
    }//GEN-LAST:event_position_8MouseClicked

    private void position_9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_position_9MouseClicked
        playerTurn(position_9, 2, 2);
    }//GEN-LAST:event_position_9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel position_1;
    private javax.swing.JLabel position_2;
    private javax.swing.JLabel position_3;
    private javax.swing.JLabel position_4;
    private javax.swing.JLabel position_5;
    private javax.swing.JLabel position_6;
    private javax.swing.JLabel position_7;
    private javax.swing.JLabel position_8;
    private javax.swing.JLabel position_9;
    // End of variables declaration//GEN-END:variables
public static void main(String args[]) {
        /*this is all to make sure that the frame works normally*/
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // in here we call on the frame class
        Frame tac = new Frame();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                tac.on();
                tac.setVisible(true);
                tac.setLocationRelativeTo(null);  // to make the game appear on center
                tac.setResizable(false);         // to advoid the player resizing the board
            }
        });
    }
}
