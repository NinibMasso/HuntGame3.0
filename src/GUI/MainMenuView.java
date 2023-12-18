    package GUI;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionListener;
    import java.io.BufferedReader;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.IOException;

    public class MainMenuView extends JPanel {

        // CardLayout
        private JPanel cardPanel = new JPanel(new CardLayout());

        // Main menu panel
        private ImageIcon HGbanner = new ImageIcon("src/IconImages/HGBanner.png");
        private JLabel bannerLabel = new JLabel();
        private JLabel bannerLabel2 = new JLabel();
        private JPanel menuPanel = new JPanel();
        private JPanel bannerPanel = new JPanel();
        private JPanel buttonPanel = new JPanel();
        private JButton newGameButton = new JButton("New Game");
        private JButton controlsButton = new JButton("Controls");
        private JButton aboutButton = new JButton("About");
        private JButton quitButton = new JButton("QUIT");

        // Creator panel.
        private JPanel aboutPanel = new JPanel();
        private JTextArea aboutText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
        private JButton aboutBackButton = new JButton("Go Back");

        // Controls panel
        private JPanel controlsPanel = new JPanel();
        private JTextArea controlsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
        private JButton controlsBackButton = new JButton("Go Back");
        private final MainFrame mainFrame;
        private GameView gameView;


        public MainMenuView(MainFrame mainFrame) {
            // mainFrame
            this.mainFrame = mainFrame;

            // ActionListeners
            ActionListener exitListener = ae -> System.exit(0);
            ActionListener goBackListener = ae -> showMenuPanel();
            ActionListener controlListener = ae -> showControlPanel();
            ActionListener newGameListener = ae -> startNewGame();

            ActionListener aboutListener = ae -> {
                try {
                    showAboutPanel();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            };

            // Menu panel
            menuPanel.setLayout(new GridLayout(0, 1));
            buttonPanel.setLayout(new GridLayout(2, 2));
            bannerLabel.setIcon(HGbanner);

            // button design
            newGameButton = mainFrame.buttonDesigner(newGameButton);
            controlsButton = mainFrame.buttonDesigner(controlsButton);
            aboutButton = mainFrame.buttonDesigner(aboutButton);
            quitButton = mainFrame.buttonDesigner(quitButton);

            // add buttons
            menuPanel.add(bannerLabel);
            buttonPanel.add(newGameButton);
            buttonPanel.add(controlsButton);
            buttonPanel.add(aboutButton);
            buttonPanel.add(quitButton);
            menuPanel.add(buttonPanel);

            // Aboutpanel
            aboutPanel.setLayout(new BorderLayout());
            aboutBackButton = mainFrame.buttonDesigner(aboutBackButton);
            aboutPanel.add(aboutText, BorderLayout.CENTER);
            aboutPanel.add(aboutBackButton, BorderLayout.SOUTH);
            aboutText.setBackground(Color.BLACK);
            aboutText.setForeground(new Color(80,115, 30));
            aboutText.setFont(new Font("Chiller", Font.BOLD, 25));
            aboutText.setEditable(false);

            // control panel
            bannerPanel.setLayout(new GridLayout(0,1));
            bannerLabel2.setIcon(HGbanner);
            bannerPanel.add(bannerLabel2);
            controlsPanel.setLayout(new BorderLayout());
            controlsPanel.setBackground(Color.BLACK);
            controlsBackButton = mainFrame.buttonDesigner(controlsBackButton);
            controlsPanel.add(controlsText, BorderLayout.CENTER);
            controlsPanel.add(controlsBackButton, BorderLayout.SOUTH);
            controlsText.setBackground(Color.BLACK);
            controlsText.setForeground(new Color(80,115, 30));
            controlsText.setFont(new Font("Chiller", Font.BOLD, 25));
            controlsText.setEditable(false);
            controlsPanel.add(bannerPanel, BorderLayout.NORTH);

            // Add panels to cardPanel
            cardPanel.add(menuPanel, "menu");
            cardPanel.add(aboutPanel, "About");
            cardPanel.add(controlsPanel, "controls");

            // add ActionListeners to buttons
            quitButton.addActionListener(exitListener);
            aboutButton.addActionListener(aboutListener);
            controlsButton.addActionListener(controlListener);
            newGameButton.addActionListener(newGameListener);

            // Go back buttons
            aboutBackButton.addActionListener(goBackListener);
            controlsBackButton.addActionListener(goBackListener);

            showMenuPanel();
            add(cardPanel);

            setSize(615, 400);
            setVisible(true);

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

            try(BufferedReader reader = new BufferedReader(new FileReader("src/TextFiles/About.txt"))){
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
