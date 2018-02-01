package in.refort.batches;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.table.DefaultTableModel;

public class Controller {

    private Model model;
    private View view;
    private ActionListener printAttendanceAL,printengChartAL,printcheChartAL;
    private MouseAdapter mouseAdapter;
    private boolean f=true;
    private PrintOral po=new PrintOral();
    private PrintPractical pp=new PrintPractical();
    private PrintOralChart oc=new PrintOralChart();
    private PrintChemChart cc=new PrintChemChart();
  
    public Controller(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }
    
    public void contol()
    {        
        printAttendanceAL = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
            	  setPrinterButtonClicked();
              }
        };                
        view.getPrinterButton().addActionListener(printAttendanceAL);   
    
        
        printengChartAL = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
            	  engchartButtonClicked();
              }
        };                
        view.getengchartButton().addActionListener(printengChartAL); 
        
        printcheChartAL = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
            	  chechartButtonClicked();
              }
        };                
        view.getchechartButton().addActionListener(printcheChartAL); 
        
    
       
        mouseAdapter=new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            
            	int col = view.getTable().columnAtPoint(e.getPoint());
            
                 
            	if(col==0) TablePrintButtonClicked();
            	
            	if(col==1)
            	{f=!f;
                for (int i = 0; i < view.getTable().getRowCount(); i++) SetData(f,i,1);
            	}
            }
        };
        view.getTable().getTableHeader().addMouseListener(mouseAdapter);
       
        
        SetPrinter sp=new SetPrinter();
        String printername=sp.LoadPreferences();
        model.setPrinterName(printername);
        view.setPrinterLabel(printername);
        
        
        String JarPath=model.getJarPath(); ///set JAR path in model variable path;
        
        listfiles(JarPath);   ///collect all mrk filenames with path in file array
    	String frametitle=String.format("Total Batches : %d", model.TotalBatches);
        System.out.println(JarPath);
        System.out.println(frametitle);
        
        /////End of Initial Update
    }
    
    private void engchartButtonClicked()
    {
        Boolean printthis;
        
    	for (int i = 0; i < view.getTable().getRowCount(); i++)
    	{	printthis=(Boolean) GetData(i,1);
    	    if(!printthis) continue;
    	    model.ReadFromDisk(model.pathArray.get(i)); /// read the batch file from disk
    	    String print_type=model.getPrintType();
    	    System.out.print(print_type);
    	    print_type=print_type.toUpperCase();
    	    	
    	       System.out.print("oralchart");
 	    	   oc.setArray(model.strArray);
 	    	   oc.PrintBatch(model.pathArray.get(i),model.getPrinterName(),model.nameArray.get(i));
    	}
    	
    }
    
    
    private void chechartButtonClicked()
    {Boolean printthis;
    
	for (int i = 0; i < view.getTable().getRowCount(); i++)
	{	printthis=(Boolean) GetData(i,1);
	    if(!printthis) continue;
	    model.ReadFromDisk(model.pathArray.get(i)); /// read the batch file from disk
	    String print_type=model.getPrintType();
	    System.out.print(print_type);
	    print_type=print_type.toUpperCase();
	    	
	       System.out.print("che chart");
	    	   cc.setArray(model.strArray);
	    	   cc.PrintBatch(model.pathArray.get(i),model.getPrinterName(),model.nameArray.get(i));
	}
    	
    }
    
    
    private void setPrinterButtonClicked()
    {
    	SetPrinter sp=new SetPrinter();
        String printername=sp.SelectPrinter();
        model.setPrinterName(printername);
        view.setPrinterLabel(printername);
    }
    
    private void TablePrintButtonClicked()
    {
        Boolean printthis;
        
    	for (int i = 0; i < view.getTable().getRowCount(); i++)
    	{	printthis=(Boolean) GetData(i,1);
    	    if(!printthis) continue;
    	    model.ReadFromDisk(model.pathArray.get(i)); /// read the batch file from disk
    	    String print_type=model.getPrintType();
    	    System.out.print(print_type);
    	    print_type=print_type.toUpperCase();
    	    if(print_type.contains("ORAL") || print_type.contains("PROJ")) 
    	    	{ 
    	    	  System.out.print("oral");
    	    	   po.setArray(model.strArray);
    	    	   po.PrintBatch(model.pathArray.get(i),model.getPrinterName(),model.nameArray.get(i));
    	    	  
    	    	}
    	    
    	    if(print_type.contains("PRACT"))
    	    	{ System.out.print("practical");
    	         pp.setArray(model.strArray);
    	    	 pp.PrintBatch(model.pathArray.get(i),model.getPrinterName(),model.nameArray.get(i));  
    	    	  
    	    	}
    	     	
    	}
    	
    }
    
    

    int listfiles(String path)
    { 
  	  FilenameFilter mrkFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".bch")) {
					return true;
				} else {
					return false;
				}
			}
		};
  	  
  	  model.pathArray.removeAll(model.pathArray);
  	  model.nameArray.removeAll(model.nameArray);
  	  File folder = new File(path);
  	  File[] listOfFiles = folder.listFiles(mrkFilter);
  	      for (int i = 0; i < listOfFiles.length; i++) {
  	        if (listOfFiles[i].isFile()) 
  	        {  
  	           model.pathArray.add(listOfFiles[i].getAbsolutePath());
  	           model.nameArray.add(listOfFiles[i].getName());
  	           
  	         } 
  	      }
  	        
  	   SortArrays();
  	  model.TotalBatches=model.pathArray.size();
  	  //show(TotalMarklists);
       for(int i=0;i<model.TotalBatches;i++)
    	   ((DefaultTableModel) view.table.getModel()).addRow(new Object[]{"  "+model.nameArray.get(i),true});
    	
  	    return model.TotalBatches;
    }

    public void SortArrays()
    {String temp;
     int i,j;
     int arrsize=model.nameArray.size();
     for(i=0;i<arrsize-1;i++)
    	 for(j=i+1;j<arrsize;j++)
    	 { if(model.nameArray.get(i).compareTo(model.nameArray.get(j)) > 0)
    	     { temp=model.nameArray.get(i);model.nameArray.set(i,model.nameArray.get(j));model.nameArray.set(j,temp);
    	       temp=model.pathArray.get(i);model.pathArray.set(i,model.pathArray.get(j));model.pathArray.set(j,temp);
    	     
    	     }
    	 }
    	
    }

    
    public void SetData(Object obj, int row_index, int col_index)
    {  view.table.getModel().setValueAt(obj,row_index,col_index);  }

   
    
    public Object GetData(int row_index, int col_index)
	{ return view.table.getModel().getValueAt(row_index, col_index); }  

    
}