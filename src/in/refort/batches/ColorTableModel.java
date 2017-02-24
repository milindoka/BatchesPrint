package in.refort.batches;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


class ColorTableModel extends AbstractTableModel {
	
	
	

Object rowData[][] = { { "No Files In Jar Folder", Boolean.FALSE} };

  String columnNames[] = { "Click To Print Batches", "(Un)Select All" };

  public int getColumnCount() {
    return columnNames.length;
  }

  public String getColumnName(int column) {
    return columnNames[column];
  }

  public int getRowCount() {
    return rowData.length;
  }

  public Object getValueAt(int row, int column) {
    return rowData[row][column];
  }

  public Class getColumnClass(int column) {
    return (getValueAt(0, column).getClass());
  }

  public void setValueAt(Object value, int row, int column) {
    rowData[row][column] = value;
  }

  public boolean isCellEditable(int row, int column) {
    return (column != 0);
  }

  
  

}

class ButtonHeaderRenderer extends JButton implements TableCellRenderer
{
    int pushedColumn;

    public ButtonHeaderRenderer() {
      pushedColumn = -1;
      setMargin(new Insets(0, 0, 0, 0));
    }

    public Component getTableCellRendererComponent(JTable table,
        Object value, boolean isSelected, boolean hasFocus, int row,
        int column) {
      setText((value == null) ? "" : value.toString());
      boolean isPressed = (column == pushedColumn);
      getModel().setPressed(isPressed);
      getModel().setArmed(isPressed);
      return this;
    }

    public void setPressedColumn(int col) {
      pushedColumn = col;
    }
    
  
  }

class HeaderListener extends MouseAdapter 
 {  static Boolean f=true;
    JTableHeader header;

    ButtonHeaderRenderer renderer;

    HeaderListener(JTableHeader header, ButtonHeaderRenderer renderer) {
      this.header = header;
      this.renderer = renderer;
    }

    public void mousePressed(MouseEvent e) 
    {
      int col = header.columnAtPoint(e.getPoint());
      renderer.setPressedColumn(col);
      header.repaint();
      
      JTable table = ((JTableHeader) e.getSource()).getTable();
      TableColumnModel columnModel = table.getColumnModel();
      int viewColumn = columnModel.getColumnIndexAtX(e.getX());
      int modelColumn = table.convertColumnIndexToModel(viewColumn);
      if ( modelColumn == 1) {
         // check.setSelected(!check.isSelected());
          TableModel m = table.getModel();
         // Boolean f = check.isSelected();
     //     Boolean f=true;
         
          for (int i = 0; i < m.getRowCount(); i++) {
              m.setValueAt(f, i, modelColumn);
          }
          ((JTableHeader) e.getSource()).repaint();
         f=!f;
      }
            
      
      
    }

    
    public void mouseReleased(MouseEvent e) 
    {
      int col = header.columnAtPoint(e.getPoint());
      renderer.setPressedColumn(-1); // clear
      header.repaint();
    }
  }
