package ui;

import exceptions.LoseException;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

//The Casino App
public class CasinoApp implements ActionListener {
    private static SlotMachine slots;//the casino's slot machine
    private static BlackJack blackJack;//the casino's blackjack table
    private static JFrame frame;//the window
    private static ArrayList<Pane> panes;//all of the panels
    private static int panelNum;//the current panels number

    //EFFECTS: constructs the casino
    public CasinoApp() {
        try {
            AccountManager.initialize();
        } catch (IOException e) {
            System.exit(0);
        }
        slots = new SlotMachine();
        blackJack = new BlackJack();
        createFrame();
        panes = new ArrayList<>();
        createPanes();
        panelNum = 0;
        setPane(0);
    }

    //MODIFIES: this
    //EFFECTS: sets up frame
    private static void createFrame() {
        frame = new JFrame("Casino");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: sets up panes
    private void createPanes() {
        for (int i = 0; i < 12; i++) {
            panes.add(new Pane(i, this));
        }
    }

    //MODIFIES: this
    //EFFECTS: switches the pane at newPanelNum into frame and sets panelNum as newPanelNum
    public static void setPane(int newPanelNum) {
        panelNum = newPanelNum;
        frame.setContentPane(panes.get(panelNum));
        frame.setVisible(true);
    }

    //EFFECTS: determines if e was caused by button5 or not
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panes.get(panelNum).getButton5())) {
            buttonFive(panes.get(panelNum));
        } else {
            notButtonFive(e, panes.get(panelNum));
        }
    }

    //EFFECTS: manages behavior when button5, the "Cancel" button, is pressed
    private static void buttonFive(Pane pane) {
        switch (panelNum) {
            case 1: case 2: case 11: setPane(0);
                break;
            case 6: pane.setLabel2("How much money would you like to cash out (in cents)?");
                setPane(3);
                break;
            case 7: pane.setLabel2("How much money would you like to bet (in cents)?");
                setPane(3);
                break;
            default: setPane(3);
        }
    }

    //EFFECTS: determines which panel was active when e was caused
    private static void notButtonFive(ActionEvent e, Pane pane) {
        switch (panelNum) {
            case 0: panelZero(e, pane);
                break;
            case 1: case 11: panelOne(pane);
                break;
            case 2: panelTwo(pane);
                break;
            case 3: panelThree(e, pane);
                break;
            case 4: panelFour(e, pane);
                break;
            case 5: panelFive(pane);
                break;
            case 6: panelSix(pane);
                break;
            case 7: case 9: panelSeven(pane);
                break;
            case 8: panelEight(pane);
                break;
            case 10: panelTen(e, pane);
        }
    }

    //EFFECTS: sets either the new account pane or the load accounts pane
    //         depending on which button was pressed and if there are any accounts
    private static void panelZero(ActionEvent e, Pane pane) {
        if ((e.getSource()).equals(pane.getButton1())) {
            setPane(1);
        } else if (AccountManager.getNumAccounts() == 0) {
            setPane(11);
        } else {
            setPane(2);
        }
    }

    //MODIFIES: this
    //EFFECTS: makes a new account if the text field isn't empty
    private static void panelOne(Pane pane) {
        if (!pane.getFieldText().isEmpty()) {
            AccountManager.newAccount(pane.getFieldText());
            update();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the chosen account
    private static void panelTwo(Pane pane) {
        AccountManager.loadAccount(pane.getComboIndex());
        update();
    }

    //EFFECTS: sets the manage money pane, slots pane or blackjack pane, or exits depending on which button sent e
    private static void panelThree(ActionEvent e, Pane pane) {
        if (e.getSource().equals(pane.getButton1())) {
            setPane(4);
        } else if (e.getSource().equals(pane.getButton2())) {
            setPane(7);
        } else if (e.getSource().equals(pane.getButton3())) {
            setPane(9);
        } else {
            exit();
        }
    }

    //EFFECTS: sets the add balance pane or cash out pane depending on which button sent e
    private static void panelFour(ActionEvent e, Pane pane) {
        if (e.getSource().equals(pane.getButton1())) {
            setPane(5);
        } else {
            setPane(6);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds and subtracts the requested amount from balance and earnings respectively
    private static void panelFive(Pane pane) {
        if (!pane.getFieldText().isEmpty()) {
            if ((Integer.MAX_VALUE - Integer.parseInt(pane.getFieldText().replaceAll(",", ""))
                    < AccountManager.getBalance())) {
                pane.setLabel2("That's too much balance!");
            } else if (Integer.MIN_VALUE + Integer.parseInt(pane.getFieldText().replaceAll(",", ""))
                    > AccountManager.getEarnings()) {
                pane.setLabel2("That's too little earnings!");
            } else {
                AccountManager.addBalance(Integer.parseInt(pane.getFieldText().replaceAll(",", "")));
                AccountManager.addEarnings(-Integer.parseInt(pane.getFieldText().replaceAll(",", "")));
                update();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: subtracts and adds the requested amount from balance and earnings respectively,
    // only if balance >= the requested amount
    private static void panelSix(Pane pane) {
        if (!pane.getFieldText().isEmpty()) {
            if (Integer.parseInt(pane.getFieldText().replaceAll(",", "")) > AccountManager.getBalance()) {
                pane.setLabel2("You don't have that much money!");
            } else if ((Integer.MAX_VALUE - Integer.parseInt(pane.getFieldText().replaceAll(",", ""))
                    < AccountManager.getEarnings())) {
                pane.setLabel2("That's too much earnings!");
            } else {
                AccountManager.addBalance(-Integer.parseInt(pane.getFieldText().replaceAll(",", "")));
                AccountManager.addEarnings(Integer.parseInt(pane.getFieldText().replaceAll(",", "")));
                update();
                pane.setLabel2("How much money would you like to cash out (in cents)?");
            }
        }
    }

    //MODIFIES: pane
    //EFFECTS: makes a bet for the requested amount only if balance >= the requested amount
    private static void panelSeven(Pane pane) {
        if (!pane.getFieldText().isEmpty()) {
            if (Integer.parseInt(pane.getFieldText().replaceAll(",", "")) > AccountManager.getBalance()) {
                pane.setLabel2("You don't have that much money!");
            } else if ((Integer.MAX_VALUE - Integer.parseInt(pane.getFieldText().replaceAll(",", "")))
                    < AccountManager.getBalance()) {
                pane.setLabel2("If you win you'll have too much money!");
            } else {
                if (panelNum == 9) {
                    LogManager.makeBet(AccountManager.getName(),
                            Integer.parseInt(pane.getFieldText().replaceAll(",", "")), "BlackJack");
                    startBlackJack();
                } else {
                    LogManager.makeBet(AccountManager.getName(),
                            Integer.parseInt(pane.getFieldText().replaceAll(",", "")), "Slots");
                }
                setPane(panelNum + 1);
                pane.setLabel2("How much money would you like to bet (in cents)?");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up a round of blackjack
    private static void startBlackJack() {
        panes.get(10).setBJPassable(true);
        panes.get(10).setBJDoubleable(true);
        blackJack.deal();
        panes.get(10).setFirstLabelBJ(blackJack.getObscuredDealer());
        panes.get(10).setSecondLabelBJ(blackJack.getPlayer());
    }

    //MODIFIES: this, pane
    //EFFECTS: runs a game of slots
    private static void panelEight(Pane pane) {
        if (pane.getButton1().getText().equals("Spin")) {
            slots.spin();
            pane.updateSlotImages(slots.getSlots());
            if (slots.isWinner()) {
                win(pane);
            } else {
                lose(pane);
            }
        } else {
            slotsEndGame(pane);
        }
    }

    //MODIFIES: pane
    //EFFECTS: ends and resets the game of slots
    private static void slotsEndGame(Pane pane) {
        update();
        pane.setButton1Text("Spin");
        pane.setLabel1("");
    }

    //MODIFIES: this, pane
    //EFFECTS: runs a game of blackjack
    private static void panelTen(ActionEvent e, Pane pane) {
        if (e.getSource().equals(pane.getButton1())) {
            pane.setBJDoubleable(false);
            if (pane.getButton1().getText().equals("Hit")) {
                bjHit(pane);
            } else {
                bjEndGame(pane);
            }
        } else if (e.getSource().equals(pane.getButton2()) && pane.getBJPassable()) {
            pane.setBJPassable(false);
            pane.setBJDoubleable(false);
            bjPass(pane);
        } else if (e.getSource().equals(pane.getButton3()) && pane.getBJDoubleable()) {
            pane.setBJDoubleable(false);
            pane.setLabel1("Your bet was doubled.");
            LogManager.bjDouble();
        } else {
            pane.setLabel1("Invalid action");
        }
    }

    //MODIFIES: pane
    //EFFECTS: hits the player in a game of blackjack
    private static void bjHit(Pane pane) {
        pane.setLabel3(pane.getLabel3() + " + " + blackJack.hit());
        if (blackJack.getPlayer() > 21) {
            pane.setBJPassable(false);
            lose(pane);
        }
    }

    //MODIFIES: pane
    //EFFECTS: ends and resets the game of blackjack
    private static void bjEndGame(Pane pane) {
        update();
        pane.setButton1Text("Hit");
        pane.setLabel1("");
    }

    //MODIFIES: this, pane
    //EFFECTS: hits the dealer until >= 17, then registers win or lose for blackjack
    private static void bjPass(Pane pane) {
        pane.setLabel2("The dealers score is " + blackJack.getDealer());
        try {
            while (blackJack.getDealer() < 17) {
                blackJack.isLost();
                pane.setLabel2(pane.getLabel2() + " + " + blackJack.hitDealer());
            }
            if (blackJack.getDealer() <= 21) {
                blackJack.isLost();
            }
            win(pane);
        } catch (LoseException e) {
            lose(pane);
        }
    }

    //MODIFIES: this, pane
    //EFFECTS: registers the loss of the player in a game
    private static void lose(Pane pane) {
        pane.setLabel1("You Lose!");
        pane.setButton1Text("OK");
        AccountManager.addBalance(-LogManager.getWinnings());
    }

    private static void win(Pane pane) {
        pane.setLabel1("You Win!");
        pane.setButton1Text("OK");
        LogManager.win();
        AccountManager.addBalance(LogManager.getWinnings());
    }

    //MODIFIES: this
    //EFFECTS: updates amounts of money and the name on the relevant panes
    private static void update() {
        panes.get(4).updateMoneyPanel();
        panes.get(3).updateMainPanel();
        setPane(3);
    }

    //EFFECTS: saves account and log, then terminates the program
    public static void exit() {
        try {
            AccountManager.saveAccount();
            LogManager.saveLog();
        } catch (IOException e) {
            System.exit(0);
        }
        System.exit(0);
    }
}
