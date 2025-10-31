
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class ReportPanel extends JPanel {
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton btnBack;

    public ReportPanel(String reportTitle, Object[][] rowData, String[] columnNames, ActionListener backListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel card = new RoundedPanel(22, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BorderLayout(0, 12));
        card.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        JLabel lbl = new JLabel(reportTitle, JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(new Color(60, 72, 88));
        card.add(lbl, BorderLayout.NORTH);

      
        tableModel = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(245, 248, 252));
        header.setForeground(new Color(60, 72, 88));

        int[] colWidths = {80, 180, 130, 110, 110};
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(i < colWidths.length ? colWidths[i] : 120);
        }

        JScrollPane scroll = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        card.add(scroll, BorderLayout.CENTER);

        btnBack = createStyledButton("Back", backListener);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnBack);
        card.add(btnPanel, BorderLayout.SOUTH);

        add(card, gbc);

        card.setMinimumSize(new Dimension(500, 300));
        card.setPreferredSize(new Dimension(900, 500));
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