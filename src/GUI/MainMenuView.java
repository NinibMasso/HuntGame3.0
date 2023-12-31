package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MainMenuView extends JPanel {

    // CardLayout
    private final JPanel cardPanel = new JPanel(new CardLayout());

    // Main menu panel
    private final ImageIcon HGbanner = new ImageIcon("src/IconImages/HGBanner.png");
    private final JLabel bannerLabel = new JLabel();
    private final JLabel bannerLabel2 = new JLabel();
    private final JLabel bannerLabel3 = new JLabel();
    private final JLabel bannerLabel4 = new JLabel();
    private final JPanel menuPanel = new JPanel();
    private final JPanel bannerPanel = new JPanel();
    private final JPanel bannerPanel2 = new JPanel();
    private final JPanel bannerPanel3 = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private JButton newGameButton = new JButton("New Game");
    private JButton controlsButton = new JButton("Controls");
    private JButton aboutButton = new JButton("About");

    private JButton lastPlayedButton = new JButton("Last played");
    private JButton lastPlayedBackButton = new JButton("Go back");
    private final JTextArea lastPlayedText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton quitButton = new JButton("QUIT");

    private JButton highscoreButton = new JButton("High score");
    private JButton highscoreBackButton = new JButton("Go back");
    private final JTextArea highscoreText = new JTextArea("TExt:\n Text: \n Text: \n Text:");

    // Creator panel.
    private final JPanel aboutPanel = new JPanel();
    private final JTextArea aboutText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton aboutBackButton = new JButton("Go Back");

    // Controls panel
    private final JPanel controlsPanel = new JPanel();

    private final JPanel lastPlayedPanel = new JPanel();
    private final JPanel highscorePanel = new JPanel();
    private final JTextArea controlsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton controlsBackButton = new JButton("Go Back");
    private final MainFrame mainFrame;

    public MainMenuView(MainFrame mainFrame) {
        // mainFrame
        this.mainFrame = mainFrame;


        // ActionListeners
        ActionListener exitListener = ae -> System.exit(0);
        ActionListener goBackListener = ae -> showMenuPanel();
        ActionListener controlListener = ae -> showControlPanel();
        ActionListener newGameListener = ae -> startNewGame();
        ActionListener highScoreListener = ae -> {
            try {
                showHighscore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        ActionListener lastPlayedListener = ae -> {
            try {
                showLastPlayed();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };

        ActionListener aboutListener = ae -> {
            try {
                showAboutPanel();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };

        // Menu panel
        initializePanels();
        // button design
        initializeButtonDesign();
        // add buttons
        addButtons();
        // Aboutpanel
        initializeAboutPanel();
        // control panel
        initializeControlPanel();
        //Lastplayedpanel
        initializeLastPlayedPanel();
        //highscorepanel
        initializeHighScorePanel();

        // Add panels to cardPanel
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(aboutPanel, "About");
        cardPanel.add(controlsPanel, "controls");
        cardPanel.add(lastPlayedPanel, "last_played");
        cardPanel.add(highscorePanel, "high_score");

        // add ActionListeners to buttons
        quitButton.addActionListener(exitListener);
        aboutButton.addActionListener(aboutListener);
        controlsButton.addActionListener(controlListener);
        newGameButton.addActionListener(newGameListener);
        highscoreButton.addActionListener(highScoreListener);
        lastPlayedButton.addActionListener(lastPlayedListener);

        // Go back buttons
        highscoreBackButton.addActionListener(goBackListener);
        lastPlayedBackButton.addActionListener(goBackListener);
        aboutBackButton.addActionListener(goBackListener);
        controlsBackButton.addActionListener(goBackListener);

        showMenuPanel();
        add(cardPanel);

        setSize(615, 400);
        setVisible(true);
    }

    private void initializePanels() {
        menuPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setLayout(new GridLayout(3, 2));
        bannerLabel.setIcon(HGbanner);
    }

    private void initializeButtonDesign() {
        newGameButton = mainFrame.buttonDesigner(newGameButton);
        controlsButton = mainFrame.buttonDesigner(controlsButton);
        aboutButton = mainFrame.buttonDesigner(aboutButton);
        lastPlayedButton = mainFrame.buttonDesigner(lastPlayedButton);
        highscoreButton = mainFrame.buttonDesigner(highscoreButton);
        quitButton = mainFrame.buttonDesigner(quitButton);
    }

    private void addButtons() {
        menuPanel.add(bannerLabel);
        buttonPanel.add(newGameButton);
        buttonPanel.add(controlsButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(lastPlayedButton);
        buttonPanel.add(highscoreButton);
        buttonPanel.add(quitButton);
        menuPanel.add(buttonPanel);
    }

    private void initializeAboutPanel() {
        aboutPanel.setLayout(new BorderLayout());
        aboutBackButton = mainFrame.buttonDesigner(aboutBackButton);
        aboutPanel.add(aboutText, BorderLayout.CENTER);
        aboutPanel.add(aboutBackButton, BorderLayout.SOUTH);
        aboutText.setBackground(Color.BLACK);
        aboutText.setForeground(new Color(80, 115, 30));
        aboutText.setFont(new Font("Chiller", Font.BOLD, 25));
        aboutText.setEditable(false);
    }

    private void initializeControlPanel() {
        bannerPanel.setLayout(new GridLayout(0, 1));
        bannerLabel2.setIcon(HGbanner);
        bannerPanel.add(bannerLabel2);
        controlsPanel.setLayout(new BorderLayout());
        controlsPanel.setBackground(Color.BLACK);
        controlsBackButton = mainFrame.buttonDesigner(controlsBackButton);
        controlsPanel.add(controlsText, BorderLayout.CENTER);
        controlsPanel.add(controlsBackButton, BorderLayout.SOUTH);
        controlsText.setBackground(Color.BLACK);
        controlsText.setForeground(new Color(80, 115, 30));
        controlsText.setFont(new Font("Chiller", Font.BOLD, 25));
        controlsText.setEditable(false);
        controlsPanel.add(bannerPanel, BorderLayout.NORTH);
    }

    private void initializeLastPlayedPanel() {
        bannerPanel2.setLayout(new GridLayout(0, 1));
        bannerLabel3.setIcon(HGbanner);
        bannerPanel2.add(bannerLabel3);
        lastPlayedPanel.setLayout(new BorderLayout());
        lastPlayedPanel.setBackground(Color.BLACK);
        lastPlayedBackButton = mainFrame.buttonDesigner(lastPlayedBackButton);
        lastPlayedPanel.add(lastPlayedText, BorderLayout.CENTER);
        lastPlayedPanel.add(lastPlayedBackButton, BorderLayout.SOUTH);
        lastPlayedText.setBackground(Color.BLACK);
        lastPlayedText.setForeground(new Color(80, 115, 30));
        lastPlayedText.setFont(new Font("Chiller", Font.BOLD, 25));
        lastPlayedText.setEditable(false);
        lastPlayedPanel.add(bannerPanel2, BorderLayout.NORTH);
    }

    private void initializeHighScorePanel() {
        bannerPanel3.setLayout(new GridLayout(0, 1));
        bannerLabel4.setIcon(HGbanner);
        bannerPanel3.add(bannerLabel4);
        highscorePanel.setLayout(new BorderLayout());
        highscorePanel.setBackground(Color.BLACK);
        highscoreBackButton = mainFrame.buttonDesigner(highscoreBackButton);
        highscorePanel.add(highscoreText, BorderLayout.CENTER);
        highscorePanel.add(highscoreBackButton, BorderLayout.SOUTH);
        highscoreText.setBackground(Color.BLACK);
        highscoreText.setForeground(new Color(80, 115, 30));
        highscoreText.setFont(new Font("Chiller", Font.BOLD, 25));
        highscoreText.setEditable(false);
        highscorePanel.add(bannerPanel3, BorderLayout.NORTH);
    }


    public void showHighscore() throws IOException {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "high_score");
        GameView gameView = new GameView();
        String text = gameView.getController().getHighscoreText();
        highscoreText.setText(text);
    }


    public void showLastPlayed() throws FileNotFoundException {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "last_played");
        LinkedList<String> lastTwoLines = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/TextFiles/LogFile"))) {


            String rubriker = reader.readLine() + "\n" + reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                lastTwoLines.addLast(line);

                if (lastTwoLines.size() > 2) {
                    lastTwoLines.removeFirst();
                }
            }

            StringBuilder textFromLastThreeLines = new StringBuilder();
            for (String lastLine : lastTwoLines) {
                textFromLastThreeLines.append(lastLine).append("\n");
            }
            lastPlayedText.setText(rubriker + "\n " + textFromLastThreeLines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // CardLayout methods
    public void startNewGame() {
        mainFrame.showGameView();
    }

    private void showMenuPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "menu");
    }

    private void showAboutPanel() throws FileNotFoundException {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "About");

        try (BufferedReader reader = new BufferedReader(new FileReader("src/TextFiles/About.txt"))) {
            StringBuilder textFromFile = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                textFromFile.append(line).append("\n");
            }
            aboutText.setText(textFromFile.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showControlPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "controls");

        try (BufferedReader reader = new BufferedReader(new FileReader("src/TextFiles/Controls.txt"))) {
            StringBuilder textFromFile = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                textFromFile.append(line).append("\n");
            }
            controlsText.setText(textFromFile.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}