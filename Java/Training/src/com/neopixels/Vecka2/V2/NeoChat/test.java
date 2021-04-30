/*
 * Created by JFormDesigner on Sun Apr 25 20:27:19 CEST 2021
 */

package V2.NeoChat;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class test extends JPanel {
    public test() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ResourceBundle bundle = ResourceBundle.getBundle("V2.NeoChat.pp");
        label1 = new JLabel();
        Exitbutton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
        javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax
        . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
        .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
        PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .
        equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("label1.text"));
        label1.setIcon(new ImageIcon("/Users/neopixel/Documents/Skola/Java/NeoChat/Gui-img/neoChatLogo3mini.png"));
        add(label1);
        label1.setBounds(55, 45, 155, 125);

        //---- Exitbutton ----
        Exitbutton.setIcon(new ImageIcon("/Users/neopixel/Documents/Skola/Java/NeoChat/Gui-img/exit/1x/outline_close_black_24dp.png"));
        add(Exitbutton);
        Exitbutton.setBounds(195, 10, 40, Exitbutton.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JButton Exitbutton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
