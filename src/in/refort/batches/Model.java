package in.refort.batches;

import java.io.File;
import java.util.ArrayList;

public class Model {
    
    private int x;
    public int TotalBatches=0;
    private String path;
    private String PrinterName;
    
	public  ArrayList<String> pathArray = new ArrayList<String>(); //array containing full paths
	public  ArrayList<String> nameArray = new ArrayList<String>(); //array containing file name

    
    
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
}