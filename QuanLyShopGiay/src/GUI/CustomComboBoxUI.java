/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import static java.util.Collections.list;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import library.Swing.ScrollBarCustom;

public class CustomComboBoxUI extends BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        return new JButton() {
            @Override
            public int getWidth() {
                return 0;
            }
        };
    }

    @Override
    protected ComboPopup createPopup() {
        return new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroller = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scroller.setHorizontalScrollBar(new ScrollBarCustom());
                return scroller;
            }
        };
    }
}
