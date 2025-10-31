
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class TaxRulePanel extends JPanel {
    public JTextField txtPf, txtTax, txtBonus, txtOvertime;
    public JButton btnSave, btnBack;

    public TaxRulePanel(ActionListener saveListener, ActionListener backListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); 

        JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(28, 44, 28, 44));

        JLabel lbl = new JLabel("Update Tax Rules", JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbl.setForeground(new Color(60, 72, 88));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lbl);
        card.add(Box.createVerticalStrut(22));

        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new GridLayout(4, 2, 12, 18));

        JLabel lblPf = new JLabel("Default PF %:");
        lblPf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblPf.setForeground(new Color(60, 72, 88));
        form.add(lblPf);

        txtPf = new JTextField();
        styleTextField(txtPf);
        form.add(txtPf);

        JLabel lblTax = new JLabel("Default Tax %:");
        lblTax.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTax.setForeground(new Color(60, 72, 88));
        form.add(lblTax);

        txtTax = new JTextField();
        styleTextField(txtTax);
        form.add(txtTax);

        JLabel lblBonus = new JLabel("Default Bonus:");
        lblBonus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblBonus.setForeground(new Color(60, 72, 88));
        form.add(lblBonus);

        txtBonus = new JTextField();
        styleTextField(txtBonus);
        form.add(txtBonus);

        JLabel lblOvertime = new JLabel("Overtime Rate/Hour:");
        lblOvertime.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblOvertime.setForeground(new Color(60, 72, 88));
        form.add(lblOvertime);

        txtOvertime = new JTextField();
        styleTextField(txtOvertime);
        form.add(txtOvertime);

        card.add(form);
        card.add(Box.createVerticalStrut(22));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 0));
        btnSave = createStyledButton("Save", saveListener);
        btnBack = createStyledButton("Back", backListener);
        btnPanel.add(btnSave);
        btnPanel.add(btnBack);

        card.add(btnPanel);

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
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 32, 10, 32));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        return btn;
    }

    public void loadValues(double pf, double tax, double bonus, double overtime) {
        txtPf.setText(String.format("%.2f", pf));
        txtTax.setText(String.format("%.2f", tax));
        txtBonus.setText(String.format("%.2f", bonus));
        txtOvertime.setText(String.format("%.2f", overtime));
    }

    public double getPf() { return Double.parseDouble(txtPf.getText().trim()); }
    public double getTax() { return Double.parseDouble(txtTax.getText().trim()); }
    public double getBonus() { return Double.parseDouble(txtBonus.getText().trim()); }
    public double getOvertime() { return Double.parseDouble(txtOvertime.getText().trim()); }

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