package in.refort.batches;



import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;





public class View {
	
	
      
    private JFrame frame;
    private JLabel label;
    private JButton button;
    private JLabel prname;
    private JButton setPrinterButton;
    private JButton printButton;
    private JButton browseButton;
    private JTable table;

    
    
    
    
    public View(String text)
    {   
    	TableModel model = new ColorTableModel();
        JTable table = new JTable(model);
        
        ButtonHeaderRenderer renderer = new ButtonHeaderRenderer();
        TableColumnModel cmodel = table.getColumnModel();
        
        table.getColumnModel().getColumn(1).setMinWidth(100);
        
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        
        
        //int n=cmodel.getColumnCount();
        //int n = 2;//headerStr.length;
        //for (int i = 0; i < n; i++) {
          cmodel.getColumn(1).setHeaderRenderer(renderer);
     //   }

        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new HeaderListener(header, renderer));
        
        
        
        JScrollPane scrollPane = new JScrollPane(table);
       
    	
        
        label = new JLabel(text);
       // frame.getContentPane().add(label, BorderLayout.CENTER);
        
        button = new JButton("Button");        
       // frame.getContentPane().add(button, BorderLayout.SOUTH);
        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new GridLayout(1,2));
    	TopPanel.add(button);
    	TopPanel.add(label);
    	
    	
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
    
 

    
    
        
    public JButton getButton(){
        return button;
    }
    
    public void setText(String text){
        label.setText(text);
    }
    
    
    
    
    
    
}


