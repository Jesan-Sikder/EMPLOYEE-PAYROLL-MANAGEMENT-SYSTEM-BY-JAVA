
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JTextField txtId;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginPanel(String labelText, ActionListener loginListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Ash background

        JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(32, 44, 32, 44));

        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(new Color(60, 72, 88));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(label);
        card.add(Box.createVerticalStrut(28));

        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new GridLayout(2, 2, 12, 18));

        JLabel lblUser = new JLabel("Username/ID:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUser.setForeground(new Color(60, 72, 88));
        form.add(lblUser);

        txtId = new JTextField(16);
        styleTextField(txtId);
        form.add(txtId);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setForeground(new Color(60, 72, 88));
        form.add(lblPassword);

        txtPassword = new JPasswordField(16);
        styleTextField(txtPassword);
        form.add(txtPassword);

        card.add(form);
        card.add(Box.createVerticalStrut(24));

        btnLogin = new RoundedTextButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.addActionListener(loginListener);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(btnLogin);

        add(card);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(new Color(245, 248, 252));
        field.setForeground(Color.BLACK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
    }

    public String getId() { return txtId.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

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
            g2.setColor(new Color(76, 175, 80));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.setColor(new Color(60, 72, 88));
            g2.setStroke(new BasicStroke(bw));
            g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            String text = getText();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 4;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.dispose();
        }
    }
}