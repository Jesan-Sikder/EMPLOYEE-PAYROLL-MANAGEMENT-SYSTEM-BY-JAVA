
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Employee;


public class EmployeeManagementPanel extends JPanel {
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton btnAdd, btnEdit, btnBack;

    public EmployeeManagementPanel(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(236, 239, 244)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        Color accent = new Color(60, 72, 88);

        JPanel card = new RoundedPanel(24, new Color(255, 255, 255), accent, 2, true);
        card.setLayout(new BorderLayout(0, 18));
        card.setBorder(BorderFactory.createEmptyBorder(32, 40, 32, 40));

        JLabel lbl = new JLabel("Employee Management", JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setForeground(accent);
        card.add(lbl, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Name", "Position", "Salary", "PF %", "Tax %", "Bonus", "Overtime", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setRowHeight(28);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(220, 223, 228)); 
        header.setForeground(accent);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, accent));

        int[] colWidths = {80, 170, 120, 110, 80, 80, 95, 80, 320}; 
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true));
        card.add(scroll, BorderLayout.CENTER);

        btnAdd = createButton("Add Employee", listener, accent);
        btnEdit = createButton("Edit Employee", listener, accent);
        btnBack = createButton("Back", listener, new Color(186, 191, 208));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnBack);
        card.add(btnPanel, BorderLayout.SOUTH);

        add(card, gbc);

        card.setMinimumSize(new Dimension(700, 380));
        card.setPreferredSize(new Dimension(1050, 550));
    }

    public void loadEmployees(HashMap<String, Employee> employees) {
        tableModel.setRowCount(0);
        for (Employee emp : employees.values()) {
            tableModel.addRow(new Object[] {
                emp.getId(), emp.getName(), emp.getPosition(), emp.getBaseSalary(),
                emp.getPfPercent(), emp.getTaxPercent(), emp.getBonus(), emp.getOvertimeHours(), emp.getEmail()
            });
        }
    }

    private JButton createButton(String text, ActionListener listener, Color primary) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 18;
                int bw = 2;
                if (getModel().isPressed()) {
                    g2.setColor(primary.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(primary.brighter());
                } else {
                    g2.setColor(primary);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(255, 255, 255, 50)); // Soft highlight
                g2.drawRoundRect(bw/2, bw/2, getWidth()-bw, getHeight()-bw, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
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
                g2.setColor(new Color(60, 72, 88, 40)); 
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
}