package in.refort.batches;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {

	private JFrame frame;
   
    private JLabel prname;
    private JButton setPrinterButton;
    private JButton chechartButton;
    private JButton engchartButton;
    private JButton theoryButton;
    private JButton helpButton;
    private TableModel tm = new MyTableModel();
    final JTable table = new JTable(tm);

    
    public View()
    {   
    	
        
        ButtonHeaderRenderer renderer = new ButtonHeaderRenderer();
        TableColumnModel cmodel = table.getColumnModel();
        
        table.getColumnModel().getColumn(1).setMinWidth(110);
        
        table.getColumnModel().getColumn(1).setMaxWidth(110);
    
        cmodel.getColumn(0).setHeaderRenderer(renderer);
        cmodel.getColumn(1).setHeaderRenderer(renderer);
    
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new HeaderListener(header, renderer));
        
        
        JScrollPane scrollPane = new JScrollPane(table);
       
        
        
        prname = new JLabel("");
        setPrinterButton = new JButton("Set Printer");        
        
        engchartButton = new JButton("Eng Chart");
        chechartButton = new JButton("Che chart");
        
        theoryButton = new JButton("Theory Attendance");
        helpButton = new JButton("Help");

        
        JPanel TopPanel = new JPanel();
        
        
        TopPanel.setLayout(new GridLayout(3,2));
    	TopPanel.add(setPrinterButton);
    	TopPanel.add(prname);
    	TopPanel.add(engchartButton);
    	TopPanel.add(chechartButton);
    	TopPanel.add(theoryButton);
    	TopPanel.add(helpButton);
    	
    	frame = new JFrame("View");                                    
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(TopPanel,BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        //   frame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("BathesPrint 1.0") ;   	
    }
    
 

   public JTable getTable()
   {
	   return table;
   }
    
        
    public JButton getPrinterButton(){
        return setPrinterButton;
    }
    
    public void setPrinterLabel(String text){
        prname.setText("   "+text);
    }
    

    public JButton getengchartButton(){
        return engchartButton;
    }

    public JButton getchechartButton(){
        return chechartButton;
    }
    
    public JButton gettheoryButton(){
        return theoryButton;
    }
    
    public JButton gethelpButton(){
        return helpButton;
    }
    
    

    
}



class HeaderListener extends MouseAdapter 
 {  static Boolean f=false;
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


///////Required for Pushbutton Effect
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
