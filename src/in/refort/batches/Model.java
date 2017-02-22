package in.refort.batches;

import java.io.File;

public class Model {
    
    private int x;
    private String path;
    private String PrinterName;
    public Model()
    {
        PrinterName = "No Printer Selected";
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