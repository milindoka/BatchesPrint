package in.refort.batches;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {

	private JFrame frame;
   
    private JLabel prname;
    private JButton setPrinterButton;
    TableModel model = new ColorTableModel();
    final JTable table = new JTable(model);

    
    public View(String title)
    {   
    	
        
        ButtonHeaderRenderer renderer = new ButtonHeaderRenderer();
        TableColumnModel cmodel = table.getColumnModel();
        
        table.getColumnModel().getColumn(1).setMinWidth(100);
        
        table.getColumnModel().getColumn(1).setMaxWidth(100);
    
        cmodel.getColumn(0).setHeaderRenderer(renderer);
        cmodel.getColumn(1).setHeaderRenderer(renderer);
    
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new HeaderListener(header, renderer));
        
        
        JScrollPane scrollPane = new JScrollPane(table);
       
        
        
        prname = new JLabel("");
       // frame.getContentPane().add(label, BorderLayout.CENTER);
        
        setPrinterButton = new JButton("Set Printer");        
       // frame.getContentPane().add(button, BorderLayout.SOUTH);
        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new GridLayout(1,2));
    	TopPanel.add(setPrinterButton);
    	TopPanel.add(prname);
    	
    	
    	frame = new JFrame("View");                                    
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(TopPanel,BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        //   frame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    	frame.setSize(400,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
    /*    
               

        setPrinterButton=new JButton("Set Printer");
        printButton=new JButton("Print");
        browseButton=new JButton("Browse");
    
//    
        
        
        
        
        TopPanel.setSize(50,50);
        TopPanel.add(browseButton);
        TopPanel.add(printButton);
        TopPanel.add(setPrinterButton);
        TopPanel.add(prname);
        TopPanel.add(button);
        TopPanel.add(label);
        
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
       
       // getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //frame.getContentPane().setLayout(new BorderLayout());
        //frame.getContentPane().add(TopPanel,BorderLayout.NORTH);
       frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        
       // getContentPane().add(TopPanel);
       // getContentPane().add(scrollPane);
        
        File f = new File(System.getProperty("java.class.path"));
    	File dir = f.getAbsoluteFile().getParentFile();
    	String path = dir.toString();

    	listfiles(path);   ///collect all mrk filenames with path in file array
    	String frametitle=String.format("Total Marklists : %d", TotalMarklists);
    	setTitle(frametitle);
     //   show(path);

  */  
    	
    
    
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
    
    
    
}


