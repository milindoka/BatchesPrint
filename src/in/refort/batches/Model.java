package in.refort.batches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    
    private int x;
    public int TotalBatches=0;
    private String path;
    private String PrinterName;
    
	public  ArrayList<String> pathArray = new ArrayList<String>(); //array containing full paths
	public  ArrayList<String> nameArray = new ArrayList<String>(); //array containing file name
	public  ArrayList<String> strArray = new ArrayList<String>(); //array containing file name
    
    
    public Model()
    {
        PrinterName = "No Printer Selected";
    }
    
        
    public ArrayList<String> getPathArray()
    {
        return pathArray;
    }
    
    public ArrayList<String> getNameArray()
    {
        return nameArray;
    }
    
    public ArrayList<String> getBatchArray()
    {
        return strArray;
    }
    

    public String getPrinterName()
    {
        return PrinterName;
    }
    
    
    public void setPrinterName(String PrinterName)
    {
        this.PrinterName=PrinterName;
    }
    
    public String getJarPath()
    {
    	File f = new File(System.getProperty("java.class.path"));
     	File dir = f.getAbsoluteFile().getParentFile();
        path=dir.toString();
     	return  path;
    }
    
    
    public  void ReadFromDisk(String fnem)
    {   strArray.removeAll(strArray);
    	BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(fnem));
		} catch (FileNotFoundException e1) 
		{
		
			e1.printStackTrace();
		}
 				
		String line = null;
    	try { while ((line = reader.readLine()) != null) 
			{
			 
			 strArray.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     }
    
    public String getPrintType()
    {
    	return strArray.get(8);
    }
    
    
    
}