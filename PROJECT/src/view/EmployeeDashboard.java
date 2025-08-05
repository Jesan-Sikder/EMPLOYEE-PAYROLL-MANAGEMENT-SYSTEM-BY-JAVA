
// package view;

// import java.awt.*;
// import java.awt.event.ActionListener;
// import javax.swing.*;

// /**
//  * Modern Employee dashboard panel.
//  */
// public class EmployeeDashboard extends JPanel {
//     public JButton btnViewPayslip, btnPayrollHistory, btnLogout;
//     public JLabel lblWelcome;

//     public EmployeeDashboard(String empName, ActionListener listener) {
//         setLayout(new GridBagLayout());
//         setBackground(new Color(220, 223, 228)); // Ash background

//         JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
//         card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
//         card.setBorder(BorderFactory.createEmptyBorder(28, 44, 28, 44));

//         lblWelcome = new JLabel("Welcome, " + empName + "!", JLabel.CENTER);
//         lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
//         lblWelcome.setForeground(new Color(60, 72, 88));
//         lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
//         card.add(lblWelcome);
//         card.add(Box.createVerticalStrut(28));

//         btnViewPayslip = createStyledButton("View Current Month Payslip", listener);
//         btnPayrollHistory = createStyledButton("View Payroll History", listener);
//         btnLogout = createStyledButton("Logout", listener);

//         card.add(btnViewPayslip);
//         card.add(Box.createVerticalStrut(14));
//         card.add(btnPayrollHistory);
//         card.add(Box.createVerticalStrut(14));
//         card.add(btnLogout);

//         add(card);
//     }

//     private JButton createStyledButton(String text, ActionListener listener) {
//         JButton btn = new JButton(text) {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 Graphics2D g2 = (Graphics2D) g.create();
//                 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                 int arc = 16;
//                 int bw = 2;
//                 g2.setColor(new Color(245, 248, 252));
//                 g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
//                 g2.setColor(new Color(60, 72, 88));
//                 g2.setStroke(new BasicStroke(bw));
//                 g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
//                 g2.dispose();
//                 super.paintComponent(g);
//             }
//         };
//         btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
//         btn.setForeground(Color.BLACK);
//         btn.setFocusPainted(false);
//         btn.setOpaque(false);
//         btn.setContentAreaFilled(false);
//         btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
//         btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//         btn.addActionListener(listener);
//         btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//         return btn;
//     }

//     // Rounded panel for card effect
//     static class RoundedPanel extends JPanel {
//         private final int radius;
//         private final Color bgColor;
//         private final Color borderColor;
//         private final int borderWidth;

//         public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth) {
//             this.radius = radius;
//             this.bgColor = bgColor;
//             this.borderColor = borderColor;
//             this.borderWidth = borderWidth;
//             setOpaque(false);
//         }

//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             Graphics2D g2 = (Graphics2D) g.create();
//             g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//             g2.setColor(bgColor);
//             g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
//             g2.setStroke(new BasicStroke(borderWidth));
//             g2.setColor(borderColor);
//             g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, radius, radius);
//             g2.dispose();
//         }
//     }
// }
// package view;

// import java.awt.*;
// import java.awt.event.ActionListener;
// import javax.swing.*;

// /**
//  * Redesigned Employee Dashboard panel.
//  */
// public class EmployeeDashboard extends JPanel {
//     public JButton btnPayslip, btnPayrollHistory, btnProfile, btnSettings, btnSupport, btnLogout;
//     public JLabel lblWelcome, lblRole, lblTip, avatar;

//     public EmployeeDashboard(String empName, ActionListener listener) {
//         setLayout(new GridBagLayout());
//         setBackground(new Color(220, 223, 228)); // Keep ash background

//         JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
//         card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
//         card.setBorder(BorderFactory.createEmptyBorder(28, 44, 28, 44));

//         // Avatar (circular with initials or emoji)
//         avatar = new JLabel(getInitials(empName), JLabel.CENTER);
//         avatar.setFont(new Font("Segoe UI", Font.BOLD, 46));
//         avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
//         avatar.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
//         card.add(avatar);

//         // Welcome message
//         lblWelcome = new JLabel("Welcome, " + empName + "!", JLabel.CENTER);
//         lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
//         lblWelcome.setForeground(new Color(60, 72, 88));
//         lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
//         card.add(lblWelcome);

//         // Role message
//         lblRole = new JLabel("Employee", JLabel.CENTER);
//         lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//         lblRole.setForeground(new Color(120, 132, 148));
//         lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);
//         card.add(lblRole);

//         card.add(Box.createVerticalStrut(22));

//         // Button grid (2 rows, 3 columns)
//         JPanel grid = new JPanel(new GridLayout(2, 3, 18, 14));
//         grid.setOpaque(false);

//         btnPayslip = createStyledButton("🪙 Payslip", listener);
//         btnPayrollHistory = createStyledButton("📄 Payroll History", listener);
//         btnProfile = createStyledButton("🧑‍💼 Profile", listener);
//         btnSettings = createStyledButton("⚙️ Settings", listener);
//         btnSupport = createStyledButton("🛠️ Support", listener);
//         btnLogout = createStyledButton("🚪 Logout", listener);

//         grid.add(btnPayslip);
//         grid.add(btnPayrollHistory);
//         grid.add(btnProfile);
//         grid.add(btnSettings);
//         grid.add(btnSupport);
//         grid.add(btnLogout);

//         card.add(grid);

//         card.add(Box.createVerticalStrut(18));

//         // Tip at the bottom
//         lblTip = new JLabel("Tip: Keep your profile up to date for personalized support.", JLabel.CENTER);
//         lblTip.setFont(new Font("Segoe UI", Font.ITALIC, 13));
//         lblTip.setForeground(new Color(140, 148, 160));
//         lblTip.setAlignmentX(Component.CENTER_ALIGNMENT);
//         card.add(lblTip);

//         add(card);
//     }

//     private JButton createStyledButton(String text, ActionListener listener) {
//         JButton btn = new JButton(text) {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 Graphics2D g2 = (Graphics2D) g.create();
//                 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                 int arc = 16;
//                 int bw = 2;
//                 g2.setColor(new Color(245, 248, 252));
//                 g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
//                 g2.setColor(new Color(60, 72, 88));
//                 g2.setStroke(new BasicStroke(bw));
//                 g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
//                 g2.dispose();
//                 super.paintComponent(g);
//             }
//         };
//         btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
//         btn.setForeground(Color.BLACK);
//         btn.setFocusPainted(false);
//         btn.setOpaque(false);
//         btn.setContentAreaFilled(false);
//         btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//         btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//         btn.addActionListener(listener);
//         btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//         return btn;
//     }

//     // Helper to get initials for avatar
//     private String getInitials(String name) {
//         if (name == null || name.isEmpty()) return "";
//         String[] parts = name.trim().split("\\s+");
//         String initials = "";
//         for (String part : parts) {
//             if (!part.isEmpty()) initials += part.charAt(0);
//         }
//         return initials.toUpperCase();
//     }

//     // Rounded panel for card effect (same as before)
//     static class RoundedPanel extends JPanel {
//         private final int radius;
//         private final Color bgColor;
//         private final Color borderColor;
//         private final int borderWidth;

//         public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth) {
//             this.radius = radius;
//             this.bgColor = bgColor;
//             this.borderColor = borderColor;
//             this.borderWidth = borderWidth;
//             setOpaque(false);
//         }

//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             Graphics2D g2 = (Graphics2D) g.create();
//             g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//             g2.setColor(bgColor);
//             g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
//             g2.setStroke(new BasicStroke(borderWidth));
//             g2.setColor(borderColor);
//             g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, radius, radius);
//             g2.dispose();
//         }
//     }
// }
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Redesigned Employee Dashboard panel.
 * - Uses only text on buttons (no emojis)
 * - Card is larger, with increased padding
 */
public class EmployeeDashboard extends JPanel {
    public JButton btnPayslip, btnPayrollHistory, btnProfile, btnSettings, btnSupport, btnLogout;
    public JLabel lblWelcome, lblRole, lblTip, avatar;

    public EmployeeDashboard(String empName, ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Keep ash background

        JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(38, 60, 38, 60)); // more padding
        card.setPreferredSize(new Dimension(700, 420)); // make panel larger

        // Avatar (circular with initials)
        avatar = new JLabel(getInitials(empName), JLabel.CENTER);
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 60));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatar.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        card.add(avatar);

        // Welcome message
        lblWelcome = new JLabel("Welcome, " + empName + "!", JLabel.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcome.setForeground(new Color(60, 72, 88));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblWelcome);

        // Role message
        lblRole = new JLabel("Employee", JLabel.CENTER);
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblRole.setForeground(new Color(120, 132, 148));
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblRole);

        card.add(Box.createVerticalStrut(32));

        // Button grid (2 rows, 3 columns)
        JPanel grid = new JPanel(new GridLayout(2, 3, 30, 16));
        grid.setOpaque(false);

        btnPayslip = createStyledButton("Payslip", listener);
        btnPayrollHistory = createStyledButton("Payroll History", listener);
        btnProfile = createStyledButton("Profile", listener);
        btnSettings = createStyledButton("Settings", listener);
        btnSupport = createStyledButton("Support", listener);
        btnLogout = createStyledButton("Logout", listener);

        grid.add(btnPayslip);
        grid.add(btnPayrollHistory);
        grid.add(btnProfile);
        grid.add(btnSettings);
        grid.add(btnSupport);
        grid.add(btnLogout);

        card.add(grid);

        card.add(Box.createVerticalStrut(26));

        // Tip at the bottom
        lblTip = new JLabel("Tip: Keep your profile up to date for personalized support.", JLabel.CENTER);
        lblTip.setFont(new Font("Segoe UI", Font.ITALIC, 15));
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
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    // Helper to get initials for avatar
    private String getInitials(String name) {
        if (name == null || name.isEmpty()) return "";
        String[] parts = name.trim().split("\\s+");
        String initials = "";
        for (String part : parts) {
            if (!part.isEmpty()) initials += part.charAt(0);
        }
        return initials.toUpperCase();
    }

    // Rounded panel for card effect (same as before)
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