package view;

import java.awt.*;
import javax.swing.*;
import model.Employee;

public class ModernEmployeeDialog extends JDialog {
    private JTextField idField, pwdField, nameField, emailField, positionField,
            salaryField, pfField, taxField, bonusField, overtimeField;
    private boolean confirmed = false;

    public ModernEmployeeDialog(Frame owner, Employee emp, boolean isEdit) {
        super(owner, isEdit ? "Edit Employee" : "Add Employee", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel card = new RoundedPanel(24, new Color(255,255,255), new Color(76,110,245), 2, true);
        card.setLayout(new BorderLayout(0, 18));
        card.setBorder(BorderFactory.createEmptyBorder(32, 40, 32, 40));

        JLabel title = new JLabel(isEdit ? "Edit Employee" : "Add Employee", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(76,110,245));
        card.add(title, BorderLayout.NORTH);

        JPanel fields = new JPanel(new GridBagLayout());
        fields.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);

        idField = createField(fields, gbc, "ID:", emp == null ? "" : emp.getId());
        pwdField = createField(fields, gbc, "Password:", emp == null ? "" : emp.getPassword());
        nameField = createField(fields, gbc, "Name:", emp == null ? "" : emp.getName());
        emailField = createField(fields, gbc, "Email:", emp == null ? "" : emp.getEmail());
        positionField = createField(fields, gbc, "Position:", emp == null ? "" : emp.getPosition());
        salaryField = createField(fields, gbc, "Base Salary:", emp == null ? "" : String.valueOf(emp.getBaseSalary()));
        pfField = createField(fields, gbc, "PF %:", emp == null ? "" : String.valueOf(emp.getPfPercent()));
        taxField = createField(fields, gbc, "Tax %:", emp == null ? "" : String.valueOf(emp.getTaxPercent()));
        bonusField = createField(fields, gbc, "Bonus:", emp == null ? "" : String.valueOf(emp.getBonus()));
        overtimeField = createField(fields, gbc, "Overtime Hours:", emp == null ? "" : String.valueOf(emp.getOvertimeHours()));

        card.add(fields, BorderLayout.CENTER);

        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));
        btnPanel.setOpaque(false);

        JButton okBtn = createButton("OK", new Color(76, 110, 245));
        JButton cancelBtn = createButton("Cancel", new Color(186, 191, 208));

        okBtn.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        cancelBtn.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        card.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(card);
        pack();
        setLocationRelativeTo(owner);
    }

    private JTextField createField(JPanel panel, GridBagConstraints gbc, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(70, 72, 78));
        gbc.gridx = 0;
        panel.add(lbl, gbc);

        JTextField field = new JTextField(value, 18);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setPreferredSize(new Dimension(230, 32));
        gbc.gridx = 1;
        panel.add(field, gbc);

        gbc.gridy++;
        return field;
    }

    private JButton createButton(String text, Color primary) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 14;
                int bw = 2;
                if (getModel().isPressed()) {
                    g2.setColor(primary.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(primary.brighter());
                } else {
                    g2.setColor(primary);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(255,255,255,50));
                g2.drawRoundRect(bw/2, bw/2, getWidth()-bw, getHeight()-bw, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 28, 8, 28));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;
        private final Color borderColor;
        private final int borderWidth;
        private final boolean shadow;

        public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth, boolean shadow) {
            this.radius = radius;
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
            this.shadow = shadow;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            if (shadow) {
                g2.setColor(new Color(76, 110, 245, 40));
                g2.fillRoundRect(8, 8, w - 16, h - 16, radius, radius);
            }
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, w, h, radius, radius);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(borderColor);
            g2.drawRoundRect(borderWidth/2, borderWidth/2, w-borderWidth, h-borderWidth, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public boolean isConfirmed() { return confirmed; }
    public Employee getEmployee() {
        try {
            return new Employee(
                idField.getText().trim(),
                pwdField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                positionField.getText().trim(),
                Double.parseDouble(salaryField.getText().trim()),
                Double.parseDouble(pfField.getText().trim()),
                Double.parseDouble(taxField.getText().trim()),
                Double.parseDouble(bonusField.getText().trim()),
                Integer.parseInt(overtimeField.getText().trim())
            );
        } catch (Exception ex) {
            return null;
        }
    }
}