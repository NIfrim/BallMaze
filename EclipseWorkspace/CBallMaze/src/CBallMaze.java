/**
 Program:   Assignment 2: Application Ball Maze
 Filename:  CBallMaze.java
 @author:   Nicolae-Madalin Ifrim
 Course:    BSc Foundation Computing Year 1
 Module:    CSY1020 Problem Solving & Programming
 Tutor:     Gary Hill
 @version:  2.0 Incorporates Artificial Intelligence!
 Date:      23/11/17
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.text.DecimalFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class CBallMaze extends JFrame implements ActionListener, ChangeListener {
//  ***************** MENU BAR, SCROLL BAR AND STATUS BAR **************************************************************
    JMenuBar topMenuBar;          //declare Menu Bar;
    JMenu scenarioMenu, editMenu, helpMenu;  //declare sub-Menus;
    JMenuItem exitItem, helpItem, aboutItem, instructionsItem;
    String aboutBoxContent = (" Application written by © Student Nicolae Ifrim\n"
            + " Studying Computing, at University of Northampton\n"
            + " St Georges Avenue, Northampton, NN2 6JD, United Kingdom,\n"
            + " E-Mail: nicolae.ifrim16@my.northampton.ac.uk\n"
            + " student number: 16432285");
    String instructionsContent = ("To win the game you must reach the sandstone block\n"
            + "     - When Run is pressed the ball will start moving\n"
            + " trying to reach the sandstone block by itself.\n"
            + ""
            + "     - When Act is pressed the ball will stop the auto-play,\n"
            + " this can be resumed by clicking on Run again. \n"
            + " Each press on Act would move one step towards the goal.\n"
            + " The ball can be moved manually by clicking the directional buttons");
    JLabel statusBar = new JLabel(" Example - DrawingArea JPanel");
//  --------------------------------------------------------------------------------------------------------------------
    
    //    ********** MAZE LAYOUT ONE ***********************************************************************************
    private String mazeLayout1[] =
            {
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
                    "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "white", "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"
            };
    //  ********** MAZE LAYOUT TWO *************************************************************************************
    private String mazeLayout2[] =
            {
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "white", "sand", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "white", "white", "white", "white", "white", "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"
            };
    //  ********** MAZE LAYOUT THREE ***********************************************************************************
    private String mazeLayout3[] =
            {
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "white", "sand", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white",
                    "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand",
                    "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "white", "sand", "white",
                    "goal", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand", "sand"
            };
//  --------------------------------------------------------------------------------------------------------------------

//  ***********  GLOBAL VARIABLES **************************************************************************************
    private JTextField jTFOption, jTFDirection, jTFSquare, jTFTimerHh, jTFTimerMm, jTFTimerSs;

    private ImageIcon   sandImageIcon, whiteImageIcon, ballImageIcon, goalImageIcon, runImageIcon,
                        actImageIcon, ballSandstoneImageIcon, resetImageIcon, compassWestImageIcon,
                        compassEastImageIcon, compassNortImageIcon, compassSouthImageIcon, frameIcon;

    private Image   sandImage, whiteImage, ballImage, ballSandstoneImage, compassWestImage, goalImage,
                    compassEastImage, compassNorthImage, compassSouthImage, frameImage;

    private JLabel 	jLOption, jLSquare, jLDirection, jLCompass, jLSepparator, jLSpeed;

    private JButton jBEmpty, jBUp, jBDown, jBRight, jBLeft, jBOption1, jBOption2, jBOption3, jBExit, jBAct, jBRun, jBReset;
    private JButton jBMazeTile[] = new JButton[208];

    private JSlider jSSpeed;

    private JPanel jPMaze, jPRight, jPBottom, jPRightTop, jPRightTimer, jPRightDirections, jPRightOptions, jPRightCompass;

    private Timer jTimer;

    private DecimalFormat twoDigits = new DecimalFormat("  00  ");

    private int count, seconds, minutes, hours, speed, blockNum, previousBlock, columnSize, rowSize, totalBlocks;
    private final int RIGHT_PANEL_PADDING = 10, MOVE_LEFT = -1, MOVE_UP = -16, MOVE_DOWN = 16, MOVE_RIGHT = 1;

    private String moveSound = "sounds/move.wav", dropSound = "sounds/drop.wav", winSound = "sounds/win.WAV";

    private boolean running = false;
    private boolean option1 = true;
    private boolean option2 = false;
    private boolean option3 = false;
    private boolean leftEndReached = false;
    private boolean rightEndReached = true;
    private boolean actPressed = false;

    private Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), loweredBevel = BorderFactory.createLoweredBevelBorder();

    private JMenu controlsMenu;
// ---------------------------------------------------------------------------------------------------------------------
    
    public static void main (String[] args)
    {

        CBallMaze frame = new CBallMaze();	// Create an instance of the CBallMaze Class and store it into the frame object;
        frame.setSize(775, 650);			// Set the size of the frame;
        frame.createGUI(frame);				// Call the method that creates the interface;
        frame.setVisible(true);				
        frame.setResizable(false);			// Disable resizing of the window;
        frame.setTitle("CBallMaze - Ball Maze Application");	// Set the title of the window;
    }

//    ************ CREATE GUI METHOD ***********************************************************************************
    private void createGUI(CBallMaze frame)
    {

        columnSize = frame.getWidth() / 10;	// Used for positioning purposes, creates a layout of 10 columns by 20 rows;
        rowSize = frame.getHeight() / 20;	// Used for positioning purposes, creates a layout of 10 columns by 20 rows;
        // Set the visual aspect of the window based on the operating system user interface manager;
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println("Systems UI manager: " + e);
        }

        loadImages();	// Method that loads all the necessary images;

        frame.setIconImage(frameImage);	// Set the icon to the window;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new BorderLayout());

        // ************* CALLING METHODS FOR ADDING AND POSITIONING MAIN PANELS ****************************************
        mazePanel(window);		// Method that creates the maze panel;
        rightPanel(window);		// Method that creates the panel at the right side of the window;
        bottomPanel(window);	// Method that creates the panel at the bottom;

        //Maze Panel Content
        mazeCreation(jPMaze);	// Method for generating the maze layout;

        //Right Panel Content
        rightPanelContent();	// Method for adding content to the panel at the right;

        //Bottom Content
        bottomPanelContent();	// Method for adding content to the bottom panel;
        
        //--------------------------------------------------------------------------------------------------------------
        
        menuSetup();        // Setting the menu
        centreWindow();     // Centring the Window to the screen

        jTimer = new Timer(1000, this);	// Timer interval for running the digital timer;

        activeDefaultButtons();     // Sets which buttons are active on default
    }
//----------------------------------------------------------------------------------------------------------------------
    
// ***************** SETTING WHAT BUTTONS ARE PRESSED AT THE START OF THE SCENARIO *************************************

    private void activeDefaultButtons()
    {
        //  If Run or Act is pressed, it disables Option Buttons
        if (running || actPressed)
        {

            jBUp.setEnabled(true);
            jBDown.setEnabled(true);
            jBLeft.setEnabled(true);
            jBRight.setEnabled(true);
            jBOption1.setEnabled(false);
            jBOption2.setEnabled(false);
            jBOption3.setEnabled(false);
        }
        // If they are not pressed, it enables Option Buttons but disables directional Buttons
        else if (!running || !actPressed)
        {

            jBLeft.setEnabled(false);
            jBRight.setEnabled(false);
            jBUp.setEnabled(false);
            jBDown.setEnabled(false);
            jBOption1.setEnabled(false);
            jBOption2.setEnabled(true);
            jBOption3.setEnabled(true);
        }
    }
// ---------------------------------------------------------------------------------------------------------------------
    
// ***************** METHOD USED FOR CREATING ALL INSTANCES OF IMAGEICONS AND IMAGES ***********************************

    // Resize Images from here if needed. Try and catch used for exception handling
    private void loadImages()
    {

        try
        {
        	
            // **************** CREATES INSTANCES OF IMAGEICON CLASS AND STORES THEM UNDER DIFFERENT NAMES *************
            sandImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/sand.jpg")));
            whiteImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/white32x32.jpg")));
            ballImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/goldBall.jpg")));
            goalImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/sandstone.jpg")));
            ballSandstoneImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/goldBallSandstone.jpg")));
            frameIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/greenfoot.png")));

            compassWestImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/west.jpg")));
            compassEastImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/east.jpg")));
            compassNortImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/north.jpg")));
            compassSouthImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/south.jpg")));

            runImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/run.png")));
            actImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/step.png")));
            resetImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/reset.png")));

            // *************** GETS THE IMAGES FROM THE ICONS AND SCALES THEM ******************************************
            frameImage = frameIcon.getImage();
            sandImage = sandImageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            goalImage = goalImageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            whiteImage = whiteImageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ballImage = ballImageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ballSandstoneImage = ballSandstoneImageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            compassWestImage = compassWestImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            compassEastImage = compassEastImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            compassNorthImage = compassNortImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            compassSouthImage = compassSouthImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            // ---------------------------------------------------------------------------------------------------------
        }
        catch (Exception e)
        {
            System.err.println("Image Error in loadImages method" + e);
        }
    }
// ---------------------------------------------------------------------------------------------------------------------
    
//  ************* CREATING INSTANCES OF THE MENU BAR, MENU'S AND MENU ITEMS ********************************************

    // Creates a top menu bar with three menus: scenario, edit and help. Each in turn have menu items:
    // Scenario has instructions and exit, edit has none, help has about and help items;
    private void menuSetup()
    {

        topMenuBar = new JMenuBar();
        setJMenuBar(topMenuBar);

        scenarioMenu = new JMenu("Scenario");
        instructionsItem = new JMenuItem("Instructions");
        scenarioMenu.add(instructionsItem);
        instructionsItem.addActionListener(this);
        exitItem = new JMenuItem("Exit");
        scenarioMenu.add(exitItem);
        exitItem.addActionListener(this);
        topMenuBar.add(scenarioMenu);

        editMenu = new JMenu("Edit");
        topMenuBar.add(editMenu );

        controlsMenu = new JMenu("Controls");
        topMenuBar.add(controlsMenu);

        helpMenu = new JMenu("Help");
        helpItem = new JMenuItem("Help Topics");
        helpMenu.add(helpItem);
        helpItem.addActionListener(this);
        aboutItem = new JMenuItem("About...");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        topMenuBar.add(helpMenu);
    }

    // Method used to open a link, currently used to open Java documentation when clicking on Help Topics;
    public static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//  --------------------------------------------------------------------------------------------------------------------
    
//  ********* METHODS FOR CREATING THE MAIN PANELS *********************************************************************

    private void mazePanel(Container window)
    {

        jPMaze = new JPanel();
        // columnSize is window width / 10, rowSize is mainHeight / 20;
        jPMaze.setPreferredSize(new Dimension(columnSize * 8 - 30, rowSize * 18));
        jPMaze.setBackground(Color.WHITE);
        jPMaze.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        window.add(jPMaze, BorderLayout.WEST);
    }

    private void rightPanel(Container window)
    {

        jPRight = new JPanel();
        jPRight.setPreferredSize(new Dimension(columnSize * 2, rowSize * 18)); // columnSize is window width / 10, rowSize is mainHeight / 10;
        jPRight.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        window.add(jPRight, BorderLayout.EAST);
    }

    private void bottomPanel(Container window)
    {

        jPBottom = new JPanel();
        jPBottom.setPreferredSize(new Dimension(columnSize * 10, (int) (rowSize * 1.5)));
        jPBottom.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        jPBottom.setLayout(null);
        window.add(jPBottom, BorderLayout.SOUTH);
    }
// ---------------------------------------------------------------------------------------------------------------------
    
//  ******* METHODS FOR ADDING CONTENT TO THE MAIN PANELS **************************************************************

    private void rightPanelContent()
    {
        // ******** MAKING PANELS FOR TOP (option, square, direction), TIMER, DIRECTIONS, OPTIONS AND COMPASS **********
        jPRightTop = new JPanel();
        jPRightTop.setPreferredSize(new Dimension(columnSize * 2 - RIGHT_PANEL_PADDING, rowSize * 2));
        jPRightTop.setLayout(new GridLayout(3, 2));
        jPRight.add(jPRightTop, BorderLayout.NORTH);

        jPRightTimer = new JPanel();
        jPRightTimer.setPreferredSize(new Dimension(columnSize * 2 - RIGHT_PANEL_PADDING, rowSize * 2));
        jPRight.add(jPRightTimer, BorderLayout.CENTER);

        jPRightDirections = new JPanel();
        jPRightDirections.setPreferredSize(new Dimension(columnSize * 2 - RIGHT_PANEL_PADDING, rowSize * 4));
        jPRightDirections.setLayout(new GridLayout(3, 3));
        jPRight.add(jPRightDirections, BorderLayout.CENTER);

        jPRightOptions = new JPanel();
        jPRightOptions.setPreferredSize(new Dimension(columnSize * 2 - RIGHT_PANEL_PADDING, rowSize * 3));
        jPRightOptions.setLayout(new GridLayout(2, 2));
        jPRight.add(jPRightOptions, BorderLayout.CENTER);

        jPRightCompass = new JPanel();
        jPRightCompass.setPreferredSize(new Dimension(columnSize * 2 - RIGHT_PANEL_PADDING, rowSize * 5));
        jPRight.add(jPRightCompass, BorderLayout.SOUTH);
        // -------------------------------------------------------------------------------------------------------------
        
        // ********** CREATING ALL THE LABELS AND TEXT FIELDS, SETTING BACKGROUND COLORS AND ADDING TEXT ***************
        jLOption = new JLabel("	Option:			");
        jPRightTop.add(jLOption, BorderLayout.WEST);

        jTFOption = new JTextField();
        jPRightTop.add(jTFOption);
        jTFOption.setText("1");
        jTFOption.setHorizontalAlignment(JTextField.CENTER);
        jTFOption.setBackground(Color.WHITE);
        jTFOption.setEditable(false);

        jLSquare = new JLabel("	Square:			");
        jPRightTop.add(jLSquare, BorderLayout.WEST);

        jTFSquare = new JTextField("15");
        jPRightTop.add(jTFSquare);
        jTFSquare.setHorizontalAlignment(JTextField.CENTER);
        jTFSquare.setBackground(Color.WHITE);
        jTFSquare.setEditable(false);

        jLDirection = new JLabel("	Direction:			");
        jPRightTop.add(jLDirection, BorderLayout.WEST);

        jTFDirection = new JTextField("W");
        jPRightTop.add(jTFDirection);
        jTFDirection.setHorizontalAlignment(JTextField.CENTER);
        jTFDirection.setBackground(Color.WHITE);
        jTFDirection.setEditable(false);

        jLOption = new JLabel("          DIGITAL TIMER          ");
        jPRightTimer.add(jLOption);
        // -------------------------------------------------------------------------------------------------------------
        
        // ********** CREATES THE TIMER FIELDS WITH THE BACKGROUND/FOREGROUND AND ADDS SEPARATORS (:) ******************
        jTFTimerHh = new JTextField(twoDigits.format(hours));
        jPRightTimer.add(jTFTimerHh);
        jTFTimerHh.setHorizontalAlignment(JTextField.CENTER);
        jTFTimerHh.setBackground(Color.BLACK);
        jTFTimerHh.setForeground(Color.WHITE);
        jTFTimerHh.setEditable(false);

        jLSepparator = new JLabel(":");
        jPRightTimer.add(jLSepparator);

        jTFTimerMm = new JTextField(twoDigits.format(minutes));
        jPRightTimer.add(jTFTimerMm);
        jTFTimerMm.setHorizontalAlignment(JTextField.CENTER);
        jTFTimerMm.setBackground(Color.BLACK);
        jTFTimerMm.setForeground(Color.WHITE);
        jTFTimerMm.setEditable(false);

        jLSepparator = new JLabel(":");
        jPRightTimer.add(jLSepparator);

        jTFTimerSs = new JTextField(twoDigits.format(seconds));
        jPRightTimer.add(jTFTimerSs);
        jTFTimerSs.setHorizontalAlignment(JTextField.CENTER);
        jTFTimerSs.setBackground(Color.BLACK);
        jTFTimerSs.setForeground(Color.WHITE);
        jTFTimerSs.setEditable(false);
        // -------------------------------------------------------------------------------------------------------------
        
        // ********** CREATES DIRECTIONAL, OPTIONS AND EXIT BUTTONS ****************************************************
        jBEmpty = new JButton("     ");
        jPRightDirections.add(jBEmpty);
        jBEmpty.setBorder(loweredBevel);
        jBEmpty.setEnabled(false);

        jBUp = new JButton("  ^  ");
        jPRightDirections.add(jBUp);
        jBUp.setBackground(Color.WHITE);
        jBUp.setFocusPainted(false);
        jBUp.setMargin(new Insets(0, 0, 0, 0));
        jBUp.addActionListener(this);

        jBEmpty = new JButton("     ");
        jPRightDirections.add(jBEmpty);
        jBEmpty.setBorder(loweredBevel);
        jBEmpty.setEnabled(false);

        jBLeft = new JButton("  <  ");
        jPRightDirections.add(jBLeft);
        jBLeft.setBackground(Color.WHITE);
        jBLeft.setFocusPainted(false);
        jBLeft.setMargin(new Insets(0, 0, 0, 0));
        jBLeft.addActionListener(this);

        jBEmpty = new JButton("     ");
        jPRightDirections.add(jBEmpty);
        jBEmpty.setBorder(loweredBevel);
        jBEmpty.setEnabled(false);

        jBRight = new JButton("  >  ");
        jPRightDirections.add(jBRight);
        jBRight.setBackground(Color.WHITE);
        jBRight.setFocusPainted(false);
        jBRight.setMargin(new Insets(0, 0, 0, 0));
        jBRight.addActionListener(this);

        jBEmpty = new JButton("     ");
        jPRightDirections.add(jBEmpty);
        jBEmpty.setBorder(loweredBevel);
        jBEmpty.setEnabled(false);

        jBDown = new JButton("  v  ");
        jPRightDirections.add(jBDown);
        jBDown.setBackground(Color.WHITE);
        jBDown.setFocusPainted(false);
        jBDown.setMargin(new Insets(0, 0, 0, 0));
        jBDown.addActionListener(this);

        jBEmpty = new JButton("     ");
        jPRightDirections.add(jBEmpty);
        jBEmpty.setBorder(loweredBevel);
        jBEmpty.setEnabled(false);

        jBOption1 = new JButton("Option 1");
        jPRightOptions.add(jBOption1);
        jBOption1.addActionListener(this);
        jBOption1.setMargin(new Insets(0, 0, 0, 0));

        jBOption2 = new JButton("Option 2");
        jPRightOptions.add(jBOption2);
        jBOption2.addActionListener(this);
        jBOption2.setMargin(new Insets(0, 0, 0, 0));

        jBOption3 = new JButton("Option 3");
        jPRightOptions.add(jBOption3);
        jBOption3.addActionListener(this);
        jBOption3.setMargin(new Insets(0, 0, 0, 0));

        jBExit = new JButton("Exit");
        jPRightOptions.add(jBExit);
        jBExit.addActionListener(this);
        jBExit.setMargin(new Insets(0, 0, 0, 0));
        // -------------------------------------------------------------------------------------------------------------
        
        // ********** CREATES THE COMPASS LABEL WITH THE COMPASS IMAGE *************************************************
        jLCompass = new JLabel();
        jLCompass.setIcon(new ImageIcon(compassWestImage));
        jPRightCompass.add(jLCompass);
        jLCompass.setBorder(loweredEtched);
        // -------------------------------------------------------------------------------------------------------------
        
    }

    private void bottomPanelContent()
    {

        int jPBottomWidth = jPBottom.getPreferredSize().width;
        int jPBottomHeight = jPBottom.getPreferredSize().height;
        int marginLeft = 20;

        // ************ RUN, ACT AND RESET BUTTONS ON THE BOTTOM PANEL *************************************************
        jBRun = new JButton();
        jPBottom.add(jBRun);
        jBRun.setMargin(new Insets(0, 0, 0, 0));
        jBRun.setIcon(runImageIcon);
        jBRun.setText("Run");
        jBRun.setSize(70, 30);
        jBRun.setLocation((marginLeft), ((jPBottomHeight/2) - (jBRun.getHeight()/2)));
        jBRun.addActionListener(this);

        jBAct = new JButton();
        jPBottom.add(jBAct);
        jBAct.setMargin(new Insets(0, 0, 0, 0));
        jBAct.setIcon(actImageIcon);
        jBAct.setText("Act");
        jBAct.setSize(70, 30);
        jBAct.setLocation((jBRun.getWidth() + (marginLeft * 2)), ((jPBottomHeight/2) - (jBRun.getHeight()/2)));
        jBAct.addActionListener(this);

        jBReset = new JButton();
        jPBottom.add(jBReset);
        jBReset.setMargin(new Insets(0, 0, 0, 0));
        jBReset.setIcon(resetImageIcon);
        jBReset.setText("Reset");
        jBReset.setSize(70, 30);
        jBReset.setLocation((jBRun.getWidth() + jBAct.getWidth() + (marginLeft * 3)), ((jPBottomHeight/2) - (jBRun.getHeight()/2)));
        jBReset.addActionListener(this);
        // -------------------------------------------------------------------------------------------------------------
        
        jSSpeed = new JSlider(JSlider.HORIZONTAL, 10, 100, 100);
        jSSpeed.setMajorTickSpacing((jSSpeed.getMaximum() - jSSpeed.getMinimum()) / 4); // Sets the appropriate ticks spacing, the number of the ticks can be changed;
        jSSpeed.setPaintTicks(true);
        jSSpeed.setSize(200, 30);
        jSSpeed.setLocation((jPBottomWidth - jSSpeed.getWidth() - marginLeft), ((jPBottomHeight/2) - (jSSpeed.getHeight()/2)));	// Position for the slider;
        jSSpeed.setInverted(true);
        jSSpeed.addChangeListener(this);
        jPBottom.add(jSSpeed);

        speed = jSSpeed.getValue() * 10;    // This is used to control the speed of the running program. 
        jLSpeed = new JLabel("Speed:");
        jLSpeed.setSize(100, 30);
        jLSpeed.setLocation((jPBottomWidth - jSSpeed.getWidth() - (marginLeft * 3)), ((jPBottomHeight/2) - (jSSpeed.getHeight()/2))); // Position for the slider label;
        jPBottom.add(jLSpeed);
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ********** CREATES A 16 X 13 GRID LAYOUT MAZE AND FILLS IT WITH TILES BASED ON PREDEFINED LAYOUTS ******************

    private void mazeCreation(JPanel jPMaze)
    {
    	
        int columns = 16, rows = 13;
        totalBlocks = rows * columns;
        blockNum = 15;					// This represents the starting position for the ball;

        jPMaze.setLayout(new GridLayout(rows, columns));

        for (int count = 0; count < totalBlocks; count++)
        {

            makeSand(count);
            makeWhiteBlock(count);
            makeWinBlock(count);
        }

        System.out.println("Ball starting (blockNum) position: " + blockNum);
        addBall(blockNum);
    }

//  --------------------------------------------------------------------------------------------------------------------
    // Method used when maze layout is changed;
    private void changeMaze()
    {
        jPMaze.removeAll();     // Removes all the buttons (tiles) from the jPMaze panel;
        mazeCreation(jPMaze);   // Creates new Maze Layout;
    }

//  ******** METHOD USED TO RESET THE MAZE WHEN OPTION BUTTONS ARE CLICKED, ALSO SETS BUTTONS ENABLED/DISABLED *********
    private void mazeReset(Object optionClicked)
    {

        //  Creates toggled effect when option buttons are clicked
        //  Also changes the text in JTFOption to the number of the Option Button clicked
        if (optionClicked == jBOption1)
         {

             jTFOption.setText("1");

             jBOption1.setEnabled(false);
             jBOption2.setEnabled(true);
             jBOption3.setEnabled(true);

             option2 = false;
             option3 = false;
             option1 = true;
         }
         else if (optionClicked == jBOption2)
         {

             jTFOption.setText("2");

             jBOption1.setEnabled(true);
             jBOption2.setEnabled(false);
             jBOption3.setEnabled(true);

             option2 = true;
             option3 = false;
             option1 = false;
         }
         else if (optionClicked == jBOption3)
         {

             jTFOption.setText("3");

             jBOption1.setEnabled(true);
             jBOption2.setEnabled(true);
             jBOption3.setEnabled(false);

             option2 = false;
             option3 = true;
             option1 = false;
         }
         else if (optionClicked == jBReset)
         {

             jTFOption.setText("1");
             jTFSquare.setText("15");
             jTFDirection.setText("W");
             jSSpeed.setValue(100);

             option1 = true;
             option2 = false;
             option3 = false;

             jBOption1.setEnabled(false);
             jBOption2.setEnabled(true);
             jBOption3.setEnabled(true);
         }
        
         changeMaze();
    }

//  --------------------------------------------------------------------------------------------------------------------
    //  ****************************  METHODS USED FOR BALL MOVEMENT ************************************************

    // Sets the ball Image as an Icon on the tile with the index number "num";
    private void addBall(int num)
    {
        jBMazeTile[num].setIcon(new ImageIcon(ballImage));
    }

    // Sets the ball with sandstone underneath Image as an Icon on the tile with the number "num";
    private void addBallSandstone(int num)
    {
        jBMazeTile[num].setIcon(new ImageIcon(ballSandstoneImage));
    }

    // Sets the sand Image as an Icon on the tile with the number "num";
    private void removeBall(int num)
    {
        jBMazeTile[num].setIcon(sandImageIcon);
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************* METHODS USED FOR FILLING THE MAZE WITH THE PROPER TILES, BASED ON THE MAZE LAYOUT ********************

    // Make the tile with the Sand Image;
    private void makeSand(int count)
    {

        // Checks what Option button is pressed and uses the corresponding Layout to generate sand tiles;
        if ((option1 == true && mazeLayout1[count].equals("sand"))
                || (option2 == true && mazeLayout2[count].equals("sand"))
                || (option3 == true && mazeLayout3[count].equals("sand")))
        {

            jBMazeTile[count] = new JButton();
            jPMaze.add(jBMazeTile[count]);
            jBMazeTile[count].setFocusPainted(false);
            jBMazeTile[count].setMargin(new Insets(0, 0, 0, 0));
            jBMazeTile[count].setBorderPainted(false);
            jBMazeTile[count].setIcon(new ImageIcon (sandImage));
        }
    }

    // Make the tile with the White Image;
    private void makeWhiteBlock(int count)
    {

        // Checks what Option button is pressed and uses the corresponding Layout to generate white tiles;
        if ((option1 == true && mazeLayout1[count].equals("white"))
                || (option2 == true && mazeLayout2[count].equals("white"))
                || (option3 == true && mazeLayout3[count].equals("white")))
        {

            jBMazeTile[count] = new JButton();
            jBMazeTile[count].setMargin(new Insets(0, 0, 0, 0));
            jPMaze.add(jBMazeTile[count]);
            jBMazeTile[count].setFocusPainted(false);
            jBMazeTile[count].setBorderPainted(false);
            jBMazeTile[count].setIcon(new ImageIcon(whiteImage));
        }
    }

    // Make the tile with the Sandstone Image;
    private void makeWinBlock(int count)
    {

        // Checks what Option button is pressed and uses the corresponding Layout to generate sandstone tile;
        if ((option1 == true && mazeLayout1[count].equals("goal"))
                || (option2 == true && mazeLayout2[count].equals("goal"))
                || (option3 == true && mazeLayout3[count].equals("goal")))
        {

            jBMazeTile[count] = new JButton();
            jBMazeTile[count].setMargin(new Insets(0, 0, 0, 0));
            jPMaze.add(jBMazeTile[count]);
            jBMazeTile[count].setFocusPainted(false);
            jBMazeTile[count].setBorderPainted(false);
            jBMazeTile[count].setIcon(goalImageIcon);
        }
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************ CENTRES THE WINDOW ON THE SCREEN **********************************************************************

    public void centreWindow()
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();

        if (frameSize.height > screenSize.height)
        {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width)
        {
            frameSize.width = screenSize.width;
        }

        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************ CHECKS IF BALL CAN MOVE IN THE DIRECTION PRESSED (n represents the direction) *************************

    // n = 1 means move Right, n = -1 means move Left, n = 16 means move Down, n = -16 means move Up
    private boolean canMove(int n)
    {
        boolean moveCheck = false;
        if (blockNum + n >= 0 && blockNum + n < totalBlocks)    // Check so it doesn't go out of bounds;
        {
            if ((mazeLayout1[blockNum + n].equalsIgnoreCase("sand") && option1 == true)
                    || (mazeLayout2[blockNum + n].equalsIgnoreCase("sand") && option2 == true)
                    || (mazeLayout3[blockNum + n].equalsIgnoreCase("sand") && option3 == true)
                    || (mazeLayout1[blockNum + n].equalsIgnoreCase("goal") && option1 == true)
                    || (mazeLayout2[blockNum + n].equalsIgnoreCase("goal") && option2 == true)
                    || (mazeLayout3[blockNum + n].equalsIgnoreCase("goal") && option3 == true))
            {
                moveCheck = true;
            }
        }

        return moveCheck;
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************** THIS METHOD TAKES A STRING ARGUMENT WHICH IS USED TO THEN PLAY A SOUND ***************************
    private void playSound(String soundPath)
    {

        AudioInputStream audioInputStream = null;   // Initialising AudioInputStream object as null;
        Clip clip = null;       // Initialising Clip object as null;

        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
        }
        catch (Exception e)
        {
            System.err.println("Loading sound file error: " + e);
        }

        try
        {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {
            System.err.println("Opening audioInputStream in playSound method, error: " + e);
        }

        clip.start();
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************ METHOD USED FOR MOVEMENT ******************************************************************************

    private void move(int direction)
    {

        if (blockNum + direction < 208 && blockNum + direction >= 0)    // So it doesn't go out of bounds;
        {

            if ((mazeLayout1[blockNum + direction].equals("sand") && option1 == true)
                    || (mazeLayout2[blockNum + direction].equals("sand") && option2 == true)
                    || (mazeLayout3[blockNum + direction].equals("sand") && option3 == true)
                    || (mazeLayout1[blockNum + direction].equals("goal") && option1 == true)
                    || (mazeLayout2[blockNum + direction].equals("goal") && option2 == true)
                    || (mazeLayout3[blockNum + direction].equals("goal") && option3 == true))
            {

                // For the ball to drop until it cannot drop anymore
                if (direction == 16)
                {

                    while (canMove(MOVE_DOWN))
                    {

                        blockNum += direction * 3;
                        previousBlock = blockNum - direction * 3;

                        // Removing ball from previous block, calling it in the loop will remove each time the loop runs;
                        // In case it drop multiple times, there wouldn't be any duplicate balls;
                        removeBall(previousBlock);
                    }

                    playSound(dropSound);

                } else
                    {
                        blockNum += direction;
                        previousBlock = blockNum - direction;
                    }

                if ((mazeLayout1[blockNum].equalsIgnoreCase("goal") && option1 == true)
                        || (mazeLayout2[blockNum].equalsIgnoreCase("goal") && option2 == true)
                        || (mazeLayout3[blockNum].equalsIgnoreCase("goal") && option3 == true))
                {

                    addBallSandstone(blockNum);
                    removeBall(previousBlock);
                    playSound(winSound);
                    gameWin();

                } else
                    {

                        addBall(blockNum);
                        removeBall(previousBlock);
                        playSound(moveSound);

                    }
            }
        }
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ************  METHOD USED TO DISPLAY CONFIRM MESSAGE WHEN SANDSTONE TILE IS REACHED ********************************

    //  The user is presented with 2 choices, they can either choose yes and restart the maze, or no to exit the game
    private void gameWin()
    {

        if ((mazeLayout1[blockNum].equalsIgnoreCase("goal") && option1 == true)
            || (mazeLayout2[blockNum].equalsIgnoreCase("goal") && option2 == true)
            || (mazeLayout3[blockNum ].equalsIgnoreCase("goal") && option3 == true))
        {

            jTimer.stop();
            playSound(winSound);

            int dialogBtnResult = JOptionPane.showConfirmDialog(null, "You won, clicking No will exit the Game, would you like to reset?", "Restart Game?", JOptionPane.YES_NO_OPTION);

            if (dialogBtnResult == YES_OPTION)
            {
                jBReset.doClick();
            }
            else if (dialogBtnResult == NO_OPTION)
            {
                System.exit(0);
            }
        }
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ********* THIS METHOD STARTS A SERIES OF BALL MOVEMENTS WITH THE PURPOSE OF REACHING THE GOAL **********************

    private void moveBall()
    {

        if (!(canMove(MOVE_LEFT)))
        {
        	
            leftEndReached = true;
            rightEndReached = false;
        } 
        else if (!(canMove(MOVE_RIGHT)))
        {

            leftEndReached = false;
            rightEndReached = true;
        }

        if (canMove(MOVE_LEFT) && leftEndReached == false && (running || actPressed))
        {
            jBLeft.doClick();
        }
        else if (canMove(MOVE_RIGHT) && rightEndReached == false && (running || actPressed))
        {
            jBRight.doClick();
        }

        gameWin();      // Calling the gameWin method that checks if sandstone block is reached;
    }

//  --------------------------------------------------------------------------------------------------------------------
//  ******************* A METHOD USED TO START A TIMER AND SET A DELAY TO IT *******************************************
    private void startTimer()
    {
        jTimer.start();
        jTimer.setDelay(speed);
    }
//  --------------------------------------------------------------------------------------------------------------------
//  ****************** A METHOD USED TO STOP A TIMER, RESET SOME VARIABLES AND TEXT FIELDS *****************************

    private void stopTimer()
    {

        running = false;
        actPressed = false;
        leftEndReached = false;
        rightEndReached = true;
        count = 0;
        seconds = 0;
        minutes = 0;
        hours= 0;

        jTimer.stop();
        activeDefaultButtons();

        jTFTimerSs.setText(twoDigits.format(seconds));
        jTFTimerMm.setText(twoDigits.format(minutes));
        jTFTimerHh.setText(twoDigits.format(hours));

    }
//  --------------------------------------------------------------------------------------------------------------------
//  ********* DIGITAL TIMER FUNCTIONALITY ******************************************************************************

    private void digitalTimer(int count)
    {

        jTFTimerHh.setText(twoDigits.format(hours));    // Always display 2 digits for hours
        jTFTimerMm.setText(twoDigits.format(minutes));  // Always display 2 digits for minutes
        jTFTimerSs.setText(twoDigits.format(seconds));  // Always display 2 digits for seconds

        seconds = count % 60;               // When count > 60 seconds starts from 0 again
        minutes = (count / 60) % 60 ;       // Minutes increase when count > than 60, and restarts from 0 when count/60 > 60
        hours = (count / (60 * 60)) % 24;   //

    }

//  --------------------------------------------------------------------------------------------------------------------

    public void actionPerformed(ActionEvent event)
    {

        Object source = event.getSource();

//  ********** MENU ACTIONS ********************************************************************************************

        if (source == instructionsItem)
        {
            JOptionPane.showMessageDialog(null, instructionsContent, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (source == exitItem)
        {
            System.exit(0);
        }
        else if (source == helpItem)
        {
            openWebPage("https://docs.oracle.com/javase/7/docs/api/");
        }
        else if (source == aboutItem)
        {
            JOptionPane.showMessageDialog(null, aboutBoxContent,"About the author", JOptionPane.INFORMATION_MESSAGE);
        }

//--------------------------------------------------------------------------------------------------------------------

        if (source == jBRun)
        {

            running = true;					//The 'running' boolean is used to check if the Run button has been pressed
            actPressed = false;

            startTimer();
            activeDefaultButtons();
        }
        else if (source == jBAct)
        {

            actPressed = true;
            running = false;

            startTimer();
            activeDefaultButtons();
            moveBall();
        }
        else if (source == jBReset)
        {

            stopTimer();
            mazeReset(source);
            changeMaze();
        }
        else if (source == jBExit)
        {
            System.exit(0);
        }
        else if (source == jBOption1)
        {
            mazeReset(source);
        }
        else if (source == jBOption2)
        {
            mazeReset(source);
        }
        else if (source == jBOption3)
        {
            mazeReset(source);
        }

        if (source == jBUp)
        {

            move(MOVE_UP);

            jTFDirection.setText("N");
            jTFSquare.setText(Integer.toString(blockNum));
            jLCompass.setIcon(new ImageIcon(compassNorthImage));
        }
        else if (source == jBDown)
        {

            move(MOVE_DOWN);

            jTFDirection.setText("S");
            jTFSquare.setText(Integer.toString(blockNum));
            jLCompass.setIcon(new ImageIcon(compassSouthImage));
        }
        else if (source == jBRight)
        {

            // Calls move method with a value of 1 stored in MOVE_RIGHT
            move(MOVE_RIGHT);

            // For the drop effect, when moving right
            // Calls the move method with a value of 16 stored in MOVE_DOWN
            move(MOVE_DOWN);

            jTFDirection.setText("E");
            jTFSquare.setText(Integer.toString(blockNum));
            jLCompass.setIcon(new ImageIcon(compassEastImage));
        }
        else if (source == jBLeft)
        {

            // Calls move method with a value of -1 stored in MOVE_LEFT
            move(MOVE_LEFT);

            // For the drop effect, when moving left.
            // Calls the move method with a value of 16 stored in MOVE_DOWN
            move(MOVE_DOWN);

            jTFDirection.setText("W");
            jTFSquare.setText(Integer.toString(blockNum));
            jLCompass.setIcon(new ImageIcon(compassWestImage));
        }

        if (source == jTimer && (running || actPressed))
        {
            count++;
            digitalTimer(count);
            activeDefaultButtons();

            if (running)
            {
                moveBall();
            }
        }
    }

    public void stateChanged(ChangeEvent event)
    {

        speed = jSSpeed.getValue() * 10;	// Update speed with the new slider value;
        if (running || actPressed)
        {
            jTimer.setDelay(speed);
        }
    }
}


