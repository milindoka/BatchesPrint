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
    private ActionListener actionListener;
    private MouseAdapter mouseAdapter;
    private boolean f=true;
   
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
     
	    
                          
    }
    
    public void contol()
    {        
        actionListener = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
            	  setPrinterButtonClicked();
              }
        };                
        view.getPrinterButton().addActionListener(actionListener);   
    
        
    
       
        mouseAdapter=new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            
            	int col = view.getTable().columnAtPoint(e.getPoint());
            
                 
            	if(col==0) TablePrintButtonClicked();
            	
            	if(col==1)
            	{f=!f;
                for (int i = 0; i < view.getTable().getRowCount(); i++)
                
                    view.getTable().getModel().setValueAt(f, i,1);
                
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
    	String frametitle=String.format("Total Marklists : %d", model.TotalMarklists);
    	//view.setTitle(frametitle);
        System.out.println(JarPath);
        System.out.println(frametitle);
        
        
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
        System.out.println("Add Print Routine");
   //   	((DefaultTableModel) view.table.getModel()).addRow(new Object[]{ "", false});
    
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
  	  model.TotalMarklists=model.pathArray.size();
  	  //show(TotalMarklists);
       for(int i=0;i<model.TotalMarklists;i++)
    	   ((DefaultTableModel) view.table.getModel()).addRow(new Object[]{model.nameArray.get(i),true});
    	 //  System.out.println(model.nameArray.get(i));
       //  {if(i>0) AddRow(); // since one row is already there
       //   SetData(" "+model.nameArray.get(i),i,0);}
  	   
  	      
  	   
  	    return model.TotalMarklists;
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