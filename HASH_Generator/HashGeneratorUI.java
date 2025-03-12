import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashGeneratorUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hash Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JTextField inputField = new JTextField();
        panel.add(new JLabel("Enter Text: "));
        panel.add(inputField);

        JTextField md5Field = new JTextField();
        md5Field.setEditable(false);
        JTextField sha256Field = new JTextField();
        sha256Field.setEditable(false);

        JButton md5Button = new JButton("Generate MD5 Hash");
        md5Button.addActionListener(e -> {
            String input = inputField.getText();
            try {
                String md5Hash = generateMD5Hash(input);
                md5Field.setText(md5Hash);
            } catch (NoSuchAlgorithmException ex) {
                md5Field.setText("Error generating MD5 Hash");
            }
        });

        JButton sha256Button = new JButton("Generate SHA-256 Hash");
        sha256Button.addActionListener(e -> {
            String input = inputField.getText();
            try {
                String sha256Hash = generateSHA256Hash(input);
                sha256Field.setText(sha256Hash);
            } catch (NoSuchAlgorithmException ex) {
                sha256Field.setText("Error generating SHA-256 Hash");
            }
        });

        JButton copyButton = new JButton("Copy Hash to Clipboard");
        copyButton.addActionListener(e -> {
            String hashToCopy = !md5Field.getText().isEmpty() ? md5Field.getText() : sha256Field.getText();
            if (!hashToCopy.isEmpty()) {
                StringSelection stringSelection = new StringSelection(hashToCopy);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(frame, "Hash copied to clipboard!");
            } else {
                JOptionPane.showMessageDialog(frame, "No hash to copy!");
            }
        });

        panel.add(md5Button);
        panel.add(md5Field);
        panel.add(sha256Button);
        panel.add(sha256Field);
        panel.add(copyButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
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
