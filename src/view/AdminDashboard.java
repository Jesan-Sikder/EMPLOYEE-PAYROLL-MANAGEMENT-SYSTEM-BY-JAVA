package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Modern Admin dashboard panel with navigation buttons.
 */
public class AdminDashboard extends JPanel {
    public JButton btnManageEmp, btnPayrollHistory, btnReports, btnTaxRules, btnProcessPayroll, btnLogout;

    public AdminDashboard(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Ash background

        JPanel card = new RoundedPanel(28, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(32, 44, 32, 44));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(60, 72, 88));
        card.add(title);
        card.add(Box.createVerticalStrut(28));

        btnManageEmp = createStyledButton("Manage Employees", listener);
        btnPayrollHistory = createStyledButton("View Payroll Histories", listener);
        btnReports = createStyledButton("Generate Reports", listener);
        btnTaxRules = createStyledButton("Update Tax Rules", listener);
        btnProcessPayroll = createStyledButton("Process Monthly Payroll", listener);
        btnLogout = createStyledButton("Logout", listener);

        card.add(btnManageEmp);
        card.add(Box.createVerticalStrut(14));
        card.add(btnPayrollHistory);
        card.add(Box.createVerticalStrut(14));
        card.add(btnReports);
        card.add(Box.createVerticalStrut(14));
        card.add(btnTaxRules);
        card.add(Box.createVerticalStrut(14));
        card.add(btnProcessPayroll);
        card.add(Box.createVerticalStrut(14));
        card.add(btnLogout);

        add(card);
    }

    private JButton createStyledButton(String text, ActionListener listener) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 16;
                int bw = 2;
                g2.setColor(isRolloverEnabled() && getModel().isRollover() ? new Color(76, 175, 80) : new Color(245, 248, 252));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(60, 72, 88));
                g2.setStroke(new BasicStroke(bw));
                g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
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