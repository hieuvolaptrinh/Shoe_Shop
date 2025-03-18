package library.Table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Table extends JTable {

    private Color background = Color.WHITE;
    private Color backgroundClick = new Color(239, 244, 255);
    private Color backgroundHeader;
    private Color foreground = new Color(102, 102, 102);

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getBackgroundClick() {
        return backgroundClick;
    }

    public void setBackgroundClick(Color backgroundClick) {
        this.backgroundClick = backgroundClick;
    }

    public Color getBackgroundHeader() {
        return backgroundHeader;
    }

    public void setBackgroundHeader(Color backgroundHeader) {
        this.backgroundHeader = backgroundHeader;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Table() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == 0) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                if (backgroundHeader != null) {
                    header.setBackground(backgroundHeader);
                }
                return header;
            }
        });

        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {
                if (o instanceof ModelProfile) {
                    ModelProfile data = (ModelProfile) o;
                    Profile cell = new Profile(data);
                    if (selected) {
                        cell.setBackground(backgroundClick);
                    } else {
                        cell.setBackground(background);
                    }
                    return cell;

                } else if (o instanceof ModelAction) {
                    ModelAction data = (ModelAction) o;
                    Action cell = new Action(data);
                    if (selected) {
                        cell.setBackground(backgroundClick);
                    } else {
                        cell.setBackground(background);
                    }
                    return cell;
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                    setBorder(noFocusBorder);
                    com.setForeground(foreground);
                    if (selected) {
                        com.setBackground(backgroundClick);
                    } else {
                        com.setBackground(background);
                    }
                    if (jtable.getColumnName(i1).equals("ID")) {
                        ((JLabel) com).setHorizontalAlignment(JLabel.CENTER);
                    } else {
                        ((JLabel) com).setHorizontalAlignment(JLabel.LEFT);
                    }
                    return com; 
                }
            }
        });
        
    }

    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        if (col == this.getColumnCount() - 1 || col == 0) {
            return new TableCellAction();
        } else {
            return super.getCellEditor(row, col);
        }
    }

    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new library.Swing.ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

}
