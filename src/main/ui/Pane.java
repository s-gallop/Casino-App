package ui;

import model.AccountManager;
import model.Slot;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

//A panel of the application
public class Pane extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JFormattedTextField field;
    private JComboBox<String> combo;
    private JLabel slotImage1;
    private JLabel slotImage2;
    private JLabel slotImage3;
    private JLabel slotImage4;
    private JLabel slotImage5;
    private JLabel slotImage6;
    private JLabel slotImage7;
    private JLabel slotImage8;
    private JLabel slotImage9;
    private boolean bjPassable;
    private boolean bjDoubleable;

    //EFFECTS: constructs a gridbaglayout pane with the above components
    public Pane(int panelNum, ActionListener listener) {
        super(new GridBagLayout());
        setupLabels();
        setupButtons(listener);
        setupField();
        setupCombo();
        setupSlotsImages();
        constructPane(panelNum);
    }

    //MODIFIES: this
    //EFFECTS: sets up labels
    private void setupLabels() {
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
    }

    //MODIFIES: this
    //EFFECTS: sets up buttons
    private void setupButtons(ActionListener listener) {
        button1 = new JButton();
        button1.setSize(200, 150);
        button1.addActionListener(listener);
        button2 = new JButton();
        button2.setSize(200, 150);
        button2.addActionListener(listener);
        button3 = new JButton();
        button3.setSize(200, 150);
        button3.addActionListener(listener);
        button4 = new JButton();
        button4.setSize(200, 150);
        button4.addActionListener(listener);
        button5 = new JButton();
        button5.setText("Cancel");
        button5.setSize(200, 150);
        button5.addActionListener(listener);
    }

    //MODIFIES: this
    //EFFECTS: sets up field
    private void setupField() {
        field = new JFormattedTextField();
        field.setColumns(50);
    }

    //MODIFIES: this
    //EFFECTS: sets up combo box
    private void setupCombo() {
        String[] options = new String[AccountManager.getNumAccounts()];
        for (int i = 0; i < AccountManager.getNumAccounts(); i++) {
            options[i] = AccountManager.getAccounts().get(i)[0];
        }
        combo = new JComboBox<>(options);
    }

    //MODIFIES: this
    //EFFECTS: sets up slots images
    private void setupSlotsImages() {
        try {
            slotImage1 = new JLabel(imageToJPanel("./data/SlotMachineImage1.png"));
            slotImage2 = new JLabel(imageToJPanel("./data/SlotMachineImage1.png"));
            slotImage3 = new JLabel(imageToJPanel("./data/SlotMachineImage1.png"));
            slotImage4 = new JLabel(imageToJPanel("./data/SlotMachineImage2.png"));
            slotImage5 = new JLabel(imageToJPanel("./data/SlotMachineImage2.png"));
            slotImage6 = new JLabel(imageToJPanel("./data/SlotMachineImage2.png"));
            slotImage7 = new JLabel(imageToJPanel("./data/SlotMachineImage3.png"));
            slotImage8 = new JLabel(imageToJPanel("./data/SlotMachineImage3.png"));
            slotImage9 = new JLabel(imageToJPanel("./data/SlotMachineImage3.png"));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    //EFFECTS: returns a 100 by 100 image icon of the picture at path
    private ImageIcon imageToJPanel(String path) throws IOException {
        return new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    }

    //EFFECTS: constructs a pane based on panelNum
    private void constructPane(int panelNum) {
        switch (panelNum) {
            case 0: accountsPanel();
                break;
            case 1: newAccountPanel(false);
                break;
            case 11: newAccountPanel(true);
                break;
            case 2: pickAccountPanel();
                break;
            case 3: mainPanel();
                break;
            case 4: manageMoneyPanel();
                break;
            case 5: addBalancePanel();
                break;
            case 6: cashOutPanel();
                break;
            case 7: case 9: getBetPanel();
                break;
            case 8: slotsPanel();
                break;
            case 10: blackJackPanel();
        }
    }

    //EFFECTS: constructs the choose to make a new account or load account pane
    private void accountsPanel() {
        twoButtonsPanel("New Account", "Load Account", 0);
    }

    //MODIFIES: this
    //EFFECTS: constructs the new accounts pane
    private void newAccountPanel(boolean cantLoad) {
        GridBagConstraints constraints = new GridBagConstraints();
        if (cantLoad) {
            label1.setText("There are no saved accounts. Please make a new account.");
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.insets = new Insets(0, 0, 20, 0);
            this.add(label1, constraints);
        }
        getAnswerPanel("What is your name?", 1);
    }

    //MODIFIES: this
    //EFFECTS: constructs the pick account to load panel
    private void pickAccountPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 20, 0);
        label1.setText("Choose an account.");
        this.add(label1, constraints);
        constraints.gridy++;
        this.add(combo, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 20);
        button1.setText("Confirm");
        this.add(button1, constraints);
        this.add(button5, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs the main pane
    private void mainPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 20, 0);
        label1.setText(AccountManager.getName() + ", your balance is " + AccountManager.getBalance() / 100.0 + ".");
        this.add(label1);
        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 0, 20);
        button1.setText("Manage Money");
        this.add(button1, constraints);
        button2.setText("Slots");
        this.add(button2, constraints);
        button3.setText("BlackJack");
        this.add(button3, constraints);
        button4.setText("Save and Quit");
        this.add(button4, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs the money managing pane
    private void manageMoneyPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 0, 20, 0);
        label1.setText("Your balance is $" + AccountManager.getBalance() / 100.0
                + " and your earnings are $" + AccountManager.getEarnings() / 100.0 + ".");
        this.add(label1, constraints);
        twoButtonsPanel("Add Balance", "Cash Out", 1);
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 20, 0, 0);
        this.add(button5, constraints);
    }

    //EFFECTS: constructs the add balance pane
    private void addBalancePanel() {
        getMoneyPanel("How much money would you like to add (an integer in cents)?");
    }

    //EFFECTS: constructs the cash out pane
    private void cashOutPanel() {
        getMoneyPanel("How much money would you like to cash out (an integer in cents)?");
    }

    //EFFECTS: constructs the get bet pane
    private void getBetPanel() {
        getMoneyPanel("How much money would you like to bet (an integer in cents)?");
    }

    //MODIFIES: this
    //EFFECTS: constructs the slots pane
    private void slotsPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 20, 20);
        constraints.gridwidth = 3;
        this.add(label1, constraints);
        constraints.gridwidth = 1;
        constraints.gridy++;
        this.add(slotImage1, constraints);
        this.add(slotImage2, constraints);
        this.add(slotImage3, constraints);
        constraints.gridy++;
        this.add(slotImage4, constraints);
        this.add(slotImage5, constraints);
        this.add(slotImage6, constraints);
        constraints.gridy++;
        this.add(slotImage7, constraints);
        this.add(slotImage8, constraints);
        this.add(slotImage9, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        button1.setText("Spin");
        this.add(button1, constraints);
    }

    //MODIFIES: this
    //EFFECTS: constructs the blackjack pane
    private void blackJackPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 0, 50, 0);
        this.add(label1, constraints);
        constraints.gridy++;
        this.add(label2, constraints);
        constraints.gridy++;
        this.add(label3, constraints);
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 20);
        button1.setText("Hit");
        this.add(button1, constraints);
        button2.setText("Pass");
        this.add(button2, constraints);
        button3.setText("Double");
        this.add(button3, constraints);
    }

    //MODIFIES: this
    //EFFECTS: adds two buttons to the pane with names name1 and name2
    private void twoButtonsPanel(String name1, String name2, int rowNum) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = rowNum;
        button1.setText(name1);
        button2.setText(name2);
        this.add(button1, constraints);
        constraints.insets = new Insets(0, 20, 0, 0);
        this.add(button2, constraints);
    }

    //MODIFIES: this
    //EFFECTS: adds the question message, a text field and two buttons to the pane
    private void getAnswerPanel(String message, int rowNum) {
        GridBagConstraints constraints = new GridBagConstraints();
        label2.setText(message);
        constraints.gridwidth = 2;
        constraints.gridy = rowNum++;
        this.add(label2, constraints);
        constraints.gridy = rowNum++;
        constraints.insets = new Insets(20, 0, 0, 0);
        this.add(field, constraints);
        constraints.gridy = rowNum;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(20, 0, 0, 20);
        constraints.gridx = 0;
        button1.setText("Confirm");
        this.add(button1, constraints);
        constraints.gridx++;
        this.add(button5, constraints);
    }

    //MODIFIES: this
    //EFFECTS: Formats the text field for integers only, then gets an answer pane
    private void getMoneyPanel(String message) {
        NumberFormatter format = new NumberFormatter(NumberFormat.getNumberInstance());
        format.setMinimum(1);
        format.setMaximum(Integer.MAX_VALUE);
        format.setAllowsInvalid(false);
        field = new JFormattedTextField(format);
        field.setColumns(50);
        getAnswerPanel(message, 0);
    }

    //MODIFIES: this
    //EFFECTS: updates the name and balance on the main pane
    public void updateMainPanel() {
        label1.setText(AccountManager.getName() + ", your balance is $" + AccountManager.getBalance() / 100.0 + ".");
    }

    //MODIFIES: this
    //EFFECTS: updates the balance and earnings on the money managing pane
    public void updateMoneyPanel() {
        label1.setText("Your balance is $" + AccountManager.getBalance() / 100.0
                + " and your earnings are $" + AccountManager.getEarnings() / 100.0 + ".");
    }

    public boolean getBJPassable() {
        return bjPassable;
    }

    public void setBJPassable(boolean state) {
        bjPassable = state;
    }

    public boolean getBJDoubleable() {
        return bjDoubleable;
    }

    public void setBJDoubleable(boolean state) {
        bjDoubleable = state;
    }

    public void setLabel1(String text) {
        label1.setText(text);
    }

    public String getLabel2() {
        return label2.getText();
    }

    public void setLabel2(String text) {
        label2.setText(text);
    }

    public void setFirstLabelBJ(int score) {
        label2.setText("The dealers score is greater than " + score);
    }

    public String getLabel3() {
        return label3.getText();
    }

    public void setLabel3(String text) {
        label3.setText(text);
    }

    public void setSecondLabelBJ(int score) {
        label3.setText("Your score is " + score);
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1Text(String text) {
        button1.setText(text);
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton5() {
        return button5;
    }

    public String getFieldText() {
        return field.getText();
    }

    public int getComboIndex() {
        return combo.getSelectedIndex();
    }

    //MODIFIES: this
    //EFFECTS: updates the images of the slots according to the positions in list
    public void updateSlotImages(ArrayList<Slot> list) {
        try {
            slotImage1.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(0).getPos(0)) + ".png"));
            slotImage2.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(1).getPos(0)) + ".png"));
            slotImage3.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(2).getPos(0)) + ".png"));
            slotImage4.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(0).getPos(1)) + ".png"));
            slotImage5.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(1).getPos(1)) + ".png"));
            slotImage6.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(2).getPos(1)) + ".png"));
            slotImage7.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(0).getPos(2)) + ".png"));
            slotImage8.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(1).getPos(2)) + ".png"));
            slotImage9.setIcon(imageToJPanel("./data/SlotMachineImage" + (list.get(2).getPos(2)) + ".png"));
        } catch (IOException e) {
            System.exit(0);
        }
    }
}
