
package Business_logic;

import javax.swing.Icon;
import library.Table.Action;
import library.Table.EventAction;
import library.Table.ModelAction;
import library.Table.ModelProfile;
import library.Table.Profile;

public class ModelNhap {
    private Icon icon;
    private String  name;
    private int tuoi;

    public ModelNhap(Icon icon, String name, int tuoi) {
        this.icon = icon;
        this.name = name;
        this.tuoi = tuoi;
    }

    public ModelNhap() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    
    public Object[] toRowTable(EventAction event){
        return new Object[]{new ModelProfile(this.icon, name), tuoi, new ModelAction(this, event)};
    }
}
