
package Models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Renderito extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      if(!String.valueOf(table.getValueAt(row,9)).equals("0")){
         this.setBackground(Color.WHITE);
         this.setForeground(Color.RED); 
      } else {
         this.setOpaque(true);
         this.setBackground(Color.WHITE);
         this.setForeground(Color.DARK_GRAY);
      }
     return this;
   }
}
