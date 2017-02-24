package in.refort.batches;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;


public class Print implements Printable
{	
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> rollArray = new ArrayList<String>();
	
	
	String filebeingprinted;
	int totalpages=0;
	int TotalBatches=0;
	int CurrentBatch=0;
	String CollegeName1;
	String Clas;
	String Stream;// = new String[TL];
	String Subject;// = new String[TL];
	String Examination;// = new String[TL];
	String Examiner;
	String Date;// = new String[TL];
	String FirstRoll;
	String LastRoll;
	
	  public void show(String msg) ///for debugging
		{JOptionPane.showMessageDialog(null, msg);}
	    
	
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
	
	
	
	///// Here the whole  JAVA Printing Mechanism Starts 
	/////  Note 'implements Printable above', It includes the Print Mechanism to our Program
	/////
	  public void PrintBatch(String filepath,String printername,String filename)
              {
		  
		  filebeingprinted=filename;
		  PrintService ps = findPrintService(printername);
		  if(ps==null) ps = PrintServiceLookup.lookupDefaultPrintService(); 
		  if(ps==null) return;
		   
	         PrinterJob job = PrinterJob.getPrinterJob();
	         job.setJobName(filename);
	         try {
				job.setPrintService(ps);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         job.setPrintable(this);
	        
	         ////Widening the print AREA.
	         //Java Keeps preset Margins of about 1 inch on all corners
                 //Top Left Corner is  (0,0), then width and height
	         HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
	         pattribs.add(new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM));
	         // 210 x 297  A4 size paper in Millimiters.
	         
	         
	         
	         
	  
	         boolean ok = true; ///job.printDialog();
	         if (ok) 
	             try {
	                  job.print(pattribs);
	             } catch (PrinterException ex) {}
	             
	          
              
	         }
	
	  public void PrintHeader(Graphics pg,int px,int py,int pageno)
	  {//Centre(CollegeName1,450,px,py,pg);
	 /*
	  pg.drawString("Subject :"+Subject,px+200, py+17);
	  pg.drawString("Examiner : "+(Examiner.length()>9 ? Examiner.substring(0, 8) : Examiner),px+400,py+17);
      pg.drawString("Examination : "+Examination,px, py+34);
      
      pg.drawString("Date : "+Date,px+400, py+34);
		*/ 
		  
		  pg.drawString(filebeingprinted,px,py);
	///	  CurrentBatch++;
	  }
	  
	  
	public int print(Graphics pg, PageFormat pf, int pageno)
			throws PrinterException
	{
		
		
		 if (pageno>totalpages)             // We have only one page, and 'page no' is zero-based
		    {  return NO_SUCH_PAGE;  // After NO_SUCH_PAGE, printer will stop printing.
	        }
		 
		 
		 Font MyFont = new Font("Liberation Serif", Font.PLAIN,10);
		 pg.setFont(MyFont); 
         
	//     ReadFromDisk(fileArray.get(pageno));
	//	 Fillrollmarks();
		 
		 
         int tlx=70,tly=28,cellheight=15,cellwidth=64;
               
         PrintHeader(pg,tlx,tly,pageno);
        
  //       PrintGrid(tlx,tly+48,cellheight,cellwidth,10,5,pg);   
        
        System.out.println(pageno);
		return PAGE_EXISTS;
	 }
///// Here the JAVA Printing Mechanism Ends
/////////////////////////////////////////////////
///////////////////////////////////////////////

	
	
/////Supporting Functions for Prining //////////////////
	
	
	public PrintService findPrintService(String printerName)
    {
        for (PrintService service : PrinterJob.lookupPrintServices())
        {
            if (service.getName().equalsIgnoreCase(printerName))
                return service;
        }

        return null;
    }

}