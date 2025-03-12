import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashGeneratorUI {
    private static Color PRIMARY_COLOR = new Color(75, 0, 130);  // Indigo
    private static Color SECONDARY_COLOR = new Color(147, 112, 219);  // Medium Purple
    private static Color BACKGROUND_COLOR = new Color(240, 240, 255);  // Light Lavender
    private static Color TEXT_COLOR = new Color(50, 50, 50);  // Dark Gray
    private static Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static Font MAIN_FONT = new Font("Arial", Font.PLAIN, 14);
    private static JLabel statusLabel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Hash Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);  // Center on screen

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Input Section
        JLabel inputLabel = createStyledLabel("Enter Text:");
        JTextField inputField = createStyledTextField();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        contentPanel.add(inputLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(inputField, gbc);

        // Hash Output Fields
        JTextField md5Field = createStyledTextField();
        JTextField sha256Field = createStyledTextField();
        md5Field.setEditable(false);
        sha256Field.setEditable(false);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        
        JButton md5Button = createStyledButton("Generate MD5");
        JButton sha256Button = createStyledButton("Generate SHA-256");
        JButton copyButton = createStyledButton("Copy to Clipboard");

        buttonPanel.add(md5Button);
        buttonPanel.add(sha256Button);
        buttonPanel.add(copyButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPanel.add(buttonPanel, gbc);

        // MD5 Section
        JLabel md5Label = createStyledLabel("MD5 Hash:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPanel.add(md5Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPanel.add(md5Field, gbc);

        // SHA-256 Section
        JLabel sha256Label = createStyledLabel("SHA-256 Hash:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        contentPanel.add(sha256Label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        contentPanel.add(sha256Field, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Status Bar
        statusLabel = new JLabel(" ");
        statusLabel.setFont(MAIN_FONT);
        statusLabel.setForeground(TEXT_COLOR);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        // Button Actions
        md5Button.addActionListener(e -> {
            String input = inputField.getText();
            try {
                String md5Hash = generateMD5Hash(input);
                md5Field.setText(md5Hash);
                updateStatus("MD5 hash generated successfully!");
            } catch (NoSuchAlgorithmException ex) {
                md5Field.setText("Error generating MD5 Hash");
                updateStatus("Error: Failed to generate MD5 hash");
            }
        });

        sha256Button.addActionListener(e -> {
            String input = inputField.getText();
            try {
                String sha256Hash = generateSHA256Hash(input);
                sha256Field.setText(sha256Hash);
                updateStatus("SHA-256 hash generated successfully!");
            } catch (NoSuchAlgorithmException ex) {
                sha256Field.setText("Error generating SHA-256 Hash");
                updateStatus("Error: Failed to generate SHA-256 hash");
            }
        });

        copyButton.addActionListener(e -> {
            String hashToCopy = !md5Field.getText().isEmpty() ? md5Field.getText() : sha256Field.getText();
            if (!hashToCopy.isEmpty() && !hashToCopy.startsWith("Error")) {
                StringSelection stringSelection = new StringSelection(hashToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                updateStatus("Hash copied to clipboard!");
            } else {
                updateStatus("No valid hash to copy!");
            }
        });

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel titleLabel = new JLabel("Hash Generator");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        return titlePanel;
    }

    private static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(MAIN_FONT);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private static JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(MAIN_FONT);
        field.setMargin(new Insets(5, 5, 5, 5));
        return field;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(MAIN_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(SECONDARY_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(SECONDARY_COLOR);
            }
        });

        return button;
    }

    private static void updateStatus(String message) {
        statusLabel.setText(message);
        Timer timer = new Timer(3000, e -> statusLabel.setText(" "));
        timer.setRepeats(false);
        timer.start();
    }

    public static String generateMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md5.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    public static String generateSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = sha256.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
