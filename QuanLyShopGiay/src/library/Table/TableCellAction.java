package library.Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellAction extends DefaultCellEditor {

    private Object data;

    public TableCellAction() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        data = o;
        if (o instanceof ModelAction) {
            Action cell = new Action((ModelAction) data);
            cell.setBackground(new Color(239, 244, 255));
            return cell;
        }
        if (o instanceof ModelProfile) {
            Profile cell = new Profile((ModelProfile) data);
            cell.setBackground(new Color(239, 244, 255));
            return cell;
        }else {
            return super.getTableCellEditorComponent(jtable, o, bln, i, i1);
        }
        
    }

    //  This method to pass data to cell render when focus lose in cell
    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
