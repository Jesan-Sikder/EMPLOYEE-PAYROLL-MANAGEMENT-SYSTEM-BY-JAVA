
package view;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Employee;
import model.Payroll;

public class PayrollHistoryPanel extends JPanel {
    public JButton btnBack;
    private DefaultTableModel tableModel;
    private JTable table;

    public PayrollHistoryPanel(String title) {
        setLayout(new GridBagLayout());
        setBackground(new Color(225, 228, 233));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

    
        Color accent = new Color(60, 72, 88);

        JPanel card = new RoundedPanel(18, new Color(255,255,255), accent, 2, true);
        card.setLayout(new BorderLayout(0, 18));
        card.setBorder(BorderFactory.createEmptyBorder(32, 50, 32, 50));
        card.setPreferredSize(new Dimension(900, 500));

        JLabel lbl = new JLabel(title, JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setForeground(accent);
        card.add(lbl, BorderLayout.NORTH);

        String[] columns = {"Emp ID", "Name", "Date", "Base Salary", "PF", "Tax", "Bonus", "Overtime Pay", "Net Salary"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(220, 223, 228)); 
        header.setForeground(accent);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, accent));


        int[] colWidths = {90, 170, 110, 110, 90, 90, 90, 110, 120};
        for (int i = 0; i < colWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true));
        card.add(scroll, BorderLayout.CENTER);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setBackground(new Color(230,233,238));
        btnBack.setForeground(accent);
        btnBack.setBorder(BorderFactory.createLineBorder(accent, 1, true));
        btnBack.setFocusPainted(false);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnBack);
        card.add(btnPanel, BorderLayout.SOUTH);

        add(card, gbc);
    }

 
    public void loadLatestPayrolls(Collection<Employee> employees) {
        tableModel.setRowCount(0);
        for (Employee emp : employees) {
            List<Payroll> history = emp.getPayrollHistory();
            if (history.isEmpty()) continue;
            Payroll last = history.get(history.size() - 1);
            tableModel.addRow(new Object[] {
                emp.getId(),
                emp.getName(),
                last.getDate(),
                String.format("%.2f", last.getBaseSalary()),
                String.format("%.2f", last.getPfDeduction()),
                String.format("%.2f", last.getTaxDeduction()),
                String.format("%.2f", last.getBonus()),
                String.format("%.2f", last.getOvertimePay()),
                String.format("%.2f", last.getNetSalary())
            });
        }
    }


    public void loadAllPayrolls(Collection<Employee> employees) {
        tableModel.setRowCount(0);
        for (Employee emp : employees) {
            for (Payroll p : emp.getPayrollHistory()) {
                tableModel.addRow(new Object[] {
                    emp.getId(),
                    emp.getName(),
                    p.getDate(),
                    String.format("%.2f", p.getBaseSalary()),
                    String.format("%.2f", p.getPfDeduction()),
                    String.format("%.2f", p.getTaxDeduction()),
                    String.format("%.2f", p.getBonus()),
                    String.format("%.2f", p.getOvertimePay()),
                    String.format("%.2f", p.getNetSalary())
                });
            }
        }
    }


    public void loadPayrollHistory(List<Payroll> payrolls, String empId, String name) {
        tableModel.setRowCount(0); // clear table
        for (Payroll p : payrolls) {
            tableModel.addRow(new Object[] {
                empId,
                name,
                p.getDate(),
                String.format("%.2f", p.getBaseSalary()),
                String.format("%.2f", p.getPfDeduction()),
                String.format("%.2f", p.getTaxDeduction()),
                String.format("%.2f", p.getBonus()),
                String.format("%.2f", p.getOvertimePay()),
                String.format("%.2f", p.getNetSalary())
            });
        }
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
                g2.setColor(new Color(60, 72, 88, 40)); // dark accent shadow
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