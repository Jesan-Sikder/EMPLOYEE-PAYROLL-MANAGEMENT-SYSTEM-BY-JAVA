package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;


public class WelcomeScreen extends JPanel {
    public WelcomeScreen(ActionListener adminListener, ActionListener employeeListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Ash background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;

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

        JButton btnCreator = new RoundedTextButton("Creators");
        btnCreator.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCreator.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCreator.addActionListener(e -> showCreatorsDialog(btnCreator));

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(card);

        contentPanel.add(Box.createVerticalStrut(32));
        contentPanel.add(btnCreator);

        add(contentPanel, gbc);
    }


    private void showCreatorsDialog(Component parent) {

        CreatorInfo[] creators = new CreatorInfo[] {
            new CreatorInfo(
                "MD. Habibur Rahman Jesan",
                "rahman241-15-628@diu.edu.bd",
                "https://github.com/Jesan-Sikder",
                "/view/jesan.JPG",
                "December 2025"
            ),
            new CreatorInfo(
                "Reshad Bin Alam",
                "bin241-15-191@diu.edu.bd",
                "https://github.com/Reshad-Bin-Alam",
                "/view/reshad.JPG",
                "December 2025"
            ),
            new CreatorInfo(
                "Md. Touhidul Islam Raha",
                "islam241-15-708@diu.edu.bd",
                "https://github.com/raha-66",
                "/view/raha.JPG",
                "December 2025"
            ),
            new CreatorInfo(
                "Sheikh Moumoni Ahmed",
                "moumoni241-15-707@diu.edu.bd",
                "https://github.com/Moumoni",
                "/view/momo.JPG",
                "December 2025"
            ),
            new CreatorInfo(
                "MD Rihan Munshi",
                "rihan241-15-692@diu.edu.bd",
                "https://github.com/rihanhub",
                "/view/rahin.JPG",
                "December 2025"
            )
        };

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "About the Creators", true);
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

        JPanel row1 = new JPanel();
        row1.setOpaque(false);
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.add(Box.createHorizontalGlue());
        row1.add(getCreatorPanel(creators[0]));
        row1.add(Box.createHorizontalGlue());
        mainPanel.add(row1);
        mainPanel.add(Box.createVerticalStrut(30));

        JPanel row2 = new JPanel();
        row2.setOpaque(false);
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        row2.add(Box.createHorizontalGlue());
        row2.add(getCreatorPanel(creators[1]));
        row2.add(Box.createHorizontalStrut(50));
        row2.add(getCreatorPanel(creators[2]));
        row2.add(Box.createHorizontalGlue());
        mainPanel.add(row2);
        mainPanel.add(Box.createVerticalStrut(30));

        JPanel row3 = new JPanel();
        row3.setOpaque(false);
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.add(Box.createHorizontalGlue());
        row3.add(getCreatorPanel(creators[3]));
        row3.add(Box.createHorizontalStrut(50));
        row3.add(getCreatorPanel(creators[4]));
        row3.add(Box.createHorizontalGlue());
        mainPanel.add(row3);

        mainPanel.add(Box.createVerticalStrut(25));
        JButton okBtn = new JButton("OK");
        okBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        okBtn.setBackground(new Color(70, 130, 220));
        okBtn.setForeground(Color.WHITE);
        okBtn.setFocusPainted(false);
        okBtn.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        okBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        okBtn.addActionListener(ev -> dialog.dispose());
        mainPanel.add(okBtn);

        dialog.setContentPane(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }


    private JPanel getCreatorPanel(CreatorInfo creator) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(200, 210)); 

        JLabel picLabel;
        URL imgUrl = getClass().getResource(creator.imagePath);
        if (imgUrl != null) {
            ImageIcon userPic = new ImageIcon(imgUrl);
            Image scaledImg = userPic.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
            picLabel = new JLabel(new ImageIcon(scaledImg));
        } else {
            picLabel = new JLabel("Photo not found");
        }
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("<html><center>" + creator.name + "</center></html>");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(new Color(30, 70, 180));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("<html><b>Email:</b> " + creator.email + "</html>");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailLabel.setForeground(new Color(70, 70, 70));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel githubLabel = new JLabel("<html><b>GitHub:</b> <a href=''>" + creator.github.replace("https://", "") + "</a></html>");
        githubLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        githubLabel.setForeground(new Color(40, 130, 220));
        githubLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        githubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        githubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI(creator.github));
                } catch (Exception ignored) {}
            }
        });

        JLabel createdLabel = new JLabel("<html><b>Created:</b> " + creator.created + "</html>");
        createdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        createdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(picLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(emailLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(githubLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(createdLabel);

        return panel;
    }

 
    static class CreatorInfo {
        String name, email, github, imagePath, created;
        CreatorInfo(String name, String email, String github, String imagePath, String created) {
            this.name = name;
            this.email = email;
            this.github = github;
            this.imagePath = imagePath;
            this.created = created;
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
           
            g2.setColor(new Color(245, 248, 252));
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
            g2.setColor(Color.BLACK); 
            g2.drawString(text, x, y);
            g2.dispose();
        }
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