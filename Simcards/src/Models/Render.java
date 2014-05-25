
package Models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
//      if ( row%2==0 )
//      {
//         this.setOpaque(true);
//         this.setBackground(Color.LIGHT_GRAY);
//         this.setForeground(Color.DARK_GRAY);
//      } else {
//         this.setOpaque(true);
//         this.setBackground(Color.WHITE);
//         this.setForeground(Color.DARK_GRAY);
//      }
//      if(String.valueOf(table.getValueAt(row,1)).equals("COMCEL")){
//         
//         this.setBackground(Color.WHITE);
//         this.setForeground(Color.RED); 
//      } else {
//         this.setOpaque(true);
//         this.setBackground(Color.WHITE);
//         this.setForeground(Color.DARK_GRAY);
//      }
     return this;
   }
}
