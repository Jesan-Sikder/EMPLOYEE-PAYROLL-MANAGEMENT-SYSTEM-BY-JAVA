
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminDashboard extends JPanel {
    public JButton btnManageEmp, btnPayrollHistory, btnReports, btnTaxRules, btnProcessPayroll, btnLogout;
    public JLabel lblTitle, lblTip;

    public AdminDashboard(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228));

        JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));
        card.setPreferredSize(new Dimension(1100, 520)); // Wider panel

    
        lblTitle = new JLabel("ADMIN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 70));
        lblTitle.setForeground(new Color(60, 72, 88));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        card.add(lblTitle);

    
        JLabel subHeading = new JLabel("Admin Dashboard", JLabel.CENTER);
        subHeading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        subHeading.setForeground(new Color(60, 72, 88));
        subHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subHeading);

        card.add(Box.createVerticalStrut(36));

        JPanel grid = new JPanel(new GridLayout(2, 3, 48, 22));
        grid.setOpaque(false);

        btnManageEmp = createStyledButton("Manage Employees", listener);
        btnPayrollHistory = createStyledButton("View Payroll Histories", listener);
        btnReports = createStyledButton("Generate Reports", listener);
        btnTaxRules = createStyledButton("Update Tax Rules", listener);
        btnProcessPayroll = createStyledButton("Process Monthly Payroll", listener);
        btnLogout = createStyledButton("Logout", listener);

        grid.add(btnManageEmp);
        grid.add(btnPayrollHistory);
        grid.add(btnReports);
        grid.add(btnTaxRules);
        grid.add(btnProcessPayroll);
        grid.add(btnLogout);

        card.add(grid);

        card.add(Box.createVerticalStrut(32));

        lblTip = new JLabel("Tip: Regularly review payroll and tax rules for accuracy.", JLabel.CENTER);
        lblTip.setFont(new Font("Segoe UI", Font.ITALIC, 17));
        lblTip.setForeground(new Color(140, 148, 160));
        lblTip.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblTip);

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
                g2.setColor(new Color(245, 248, 252));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(60, 72, 88));
                g2.setStroke(new BasicStroke(bw));
                g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(260, 48)); // Ensures full label is visible
        return btn;
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
}