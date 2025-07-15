
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Welcome screen with visible black text in buttons,
 * card at top, and centered Creator button below.
 */
public class WelcomeScreen extends JPanel {
    public WelcomeScreen(ActionListener adminListener, ActionListener employeeListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Ash background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Card Panel with outline
        JPanel card = new RoundedPanel(25, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel label = new JLabel("Welcome! Are you Employee or Admin?");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(60, 72, 88));
        card.add(label);
        card.add(Box.createVerticalStrut(30));

        // Buttons with custom rendering
        JButton btnAdmin = new RoundedTextButton("Admin");
        btnAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnAdmin.addActionListener(adminListener);

        JButton btnEmployee = new RoundedTextButton("Employee");
        btnEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnEmployee.addActionListener(employeeListener);

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.add(btnAdmin);
        btnPanel.add(btnEmployee);
        card.add(btnPanel);

      
        JButton btnCreator = new RoundedTextButton("Creator");
btnCreator.setFont(new Font("Segoe UI", Font.BOLD, 16));
btnCreator.setAlignmentX(Component.CENTER_ALIGNMENT);
btnCreator.addActionListener(e -> {
    JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "About the Creator", true);
    JPanel mainPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(240, 245, 255));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        }
    };
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

    // LOAD YOUR PHOTO HERE
    ImageIcon userPic = new ImageIcon(getClass().getResource("jesan.jpg"));
    Image scaledImg = userPic.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
    JLabel picLabel = new JLabel(new ImageIcon(scaledImg));
    picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel nameLabel = new JLabel("MD. Habibur Rahman Jesan");
    nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
    nameLabel.setForeground(new Color(30, 70, 180));
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel emailLabel = new JLabel("<html><b>Email:</b> rahman241-15-628@diu.edu.bd</html>");
    emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
    emailLabel.setForeground(new Color(70, 70, 70));
    emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel githubLabel = new JLabel("<html><b>GitHub:</b> <a href=''>github.com/Jesan-Sikder</a></html>");
    githubLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
    githubLabel.setForeground(new Color(40, 130, 220));
    githubLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    githubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    githubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/Jesan-Sikder"));
            } catch (Exception ignored) {}
        }
    });

    JLabel createdLabel = new JLabel("<html><b>Created:</b> July 2025</html>");
    createdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
    createdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    mainPanel.add(picLabel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(nameLabel);
    mainPanel.add(Box.createVerticalStrut(16));
    mainPanel.add(emailLabel);
    mainPanel.add(Box.createVerticalStrut(8));
    mainPanel.add(githubLabel);
    mainPanel.add(Box.createVerticalStrut(8));
    mainPanel.add(createdLabel);

    JButton okBtn = new JButton("OK");
    okBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
    okBtn.setBackground(new Color(70, 130, 220));
    okBtn.setForeground(Color.WHITE);
    okBtn.setFocusPainted(false);
    okBtn.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
    okBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    okBtn.addActionListener(ev -> dialog.dispose());

    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(okBtn);

    dialog.setContentPane(mainPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(btnCreator);
    dialog.setResizable(false);
    dialog.setVisible(true);
});


        // Vertical layout for card + creator button
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(card);

        contentPanel.add(Box.createVerticalStrut(32));
        contentPanel.add(btnCreator);

        add(contentPanel, gbc);
    }

    // Custom button with round border and visible black text
    static class RoundedTextButton extends JButton {
        public RoundedTextButton(String text) {
            super(text);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 36, 10, 36));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arc = 18;
            int bw = 2;
            // Draw background
            g2.setColor(new Color(245, 248, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            // Draw border
            g2.setColor(new Color(60, 72, 88));
            g2.setStroke(new BasicStroke(bw));
            g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
            // Draw text
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            String text = getText();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 4;
            g2.setColor(Color.BLACK); // Always black text!
            g2.drawString(text, x, y);
            g2.dispose();
        }
    }

    // Custom rounded panel for card effect with border
    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;
        private final Color borderColor;
        private final int borderWidth;

        public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth) {
            this.radius = radius;
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(borderColor);
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, radius, radius);
            g2.dispose();
        }
    }
}