package in.refort.batches;

import java.awt.Font;
import java.awt.FontMetrics;
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
	
	////--------------------------------------------------------------------
	int TOPLEFTX=74,TOPLEFTY=10;  ////THE STRATING PRINT SPOT AT TOP LEFT CORNER
	 int linespacing = 16;
    ////--------------------------------------------------------------------
	
	String filebeingprinted;
	int totalpages=0;
	int TotalBatches=0;
	int CurrentBatch=0;
	private String BoardName1="Maharashtra State Board of Secondary & Higher Secondary Education,";
    private String BoardName2="Mumbai Divisional Board, Vashi, Navi Mumbai - 400703";
    private String MonthYear="Feb/March-2018";

    private String School,Index,Stream,Standard,Subject,SubjectCode,Type,BatchNo,BatchCreator,
                   Email1,Email2,Det,Tyme,Session;

	
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
		  
		  ReadFromDisk(filepath);  ////Read all text lines  in strArray
		  SetAllHeaderFields();    //// School, Index,Subject,Batch etc
		  
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
	  
	  
	public int print(Graphics pg, PageFormat pf, int pageno)
			throws PrinterException
	{
		
		
		 if (pageno>totalpages)             // We have only one page, and 'page no' is zero-based
		    {  return NO_SUCH_PAGE;  // After NO_SUCH_PAGE, printer will stop printing.
	        }
		 
		 
		 Font MyFont = new Font("Liberation Serif", Font.PLAIN,10);
		 pg.setFont(MyFont); 
         
		  PrintHeader(TOPLEFTX,TOPLEFTY,pg,pageno);
		   PrintGridTitle(TOPLEFTX,TOPLEFTY+92,pg);
		  TOPLEFTY+=linespacing;
		 
        
        
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
	
	
	public void SetAllHeaderFields()
	{   
		
		String temp,stemp[];
		for(int i=1;i<15;i++)
		{stemp=strArray.get(i).split(":");
		temp=stemp[1].trim();
		strArray.set(i,temp);	
		}
		
		
		// line 0 is blank line
		School=strArray.get(1); 
		Index=strArray.get(2);
		Stream=strArray.get(3);
		Standard=strArray.get(4);
		Subject=strArray.get(5);
		SubjectCode=strArray.get(6);
		Type=strArray.get(7);
		BatchNo=strArray.get(8);
		BatchCreator=strArray.get(9);
		Email1=strArray.get(10); 
		Email2=strArray.get(11); 
		Det=strArray.get(12);
		Tyme=strArray.get(13);
		Session=strArray.get(14);
		
		rollArray.removeAll(rollArray);
		for(int i=20;i<strArray.size();i++) rollArray.add(strArray.get(i));
		
	}
	

	
	 private void Centre(String s, int width, int XPos, int YPos,Graphics g2d){  
	        int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
	        int start = width/2 - stringLen/2;  
	        g2d.drawString(s, start + XPos, YPos);  
	 }
	
	 public void PrintHeader(int topleftx,int toplefty,Graphics gr,int pageno)
	  {
	     
	  String StartSeatNo=rollArray.get(0);
	  String EndSeatNo=rollArray.get(rollArray.size()-1);
	   
	 
	   Centre(BoardName1,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre(BoardName2,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre(Standard+" - "+Type+" - "+MonthYear,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre("Attendance Sheet",460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	  linespacing=12;
	   
	   gr.drawString("School/College/Centre : "+School,topleftx,toplefty);
	  gr.drawString("Batch No :  "+BatchNo,topleftx+340,toplefty);
	  toplefty+=linespacing;
	  gr.drawString("Subject : "+Subject,topleftx,toplefty);
	  gr.drawString("Date :  "+Det,topleftx+340,toplefty);
	  toplefty+=linespacing;
	  gr.drawString("Seat No.s : From  "+StartSeatNo+"  TO  "+EndSeatNo,topleftx,toplefty);
	  
	  gr.drawString("Time :  "+Tyme,topleftx+340,toplefty);
	  toplefty+=linespacing;
	  gr.drawString("Extra Seat No.s :",topleftx,toplefty);
	  }
	 
	 
	 
	 void PrintBoxedString(String str,int tlx,int tly, int boxwidth, int boxheight, Graphics pg)
	 {
		 pg.drawRect(tlx, tly, boxwidth, boxheight);
	 
		 int stringLen = (int)  pg.getFontMetrics().getStringBounds(str, pg).getWidth(); 
		 int stringHeight=(int) pg.getFontMetrics().getStringBounds(str, pg).getHeight();
		 
	        int start = boxwidth/2 - stringLen/2;  
	        pg.drawString(str, start + tlx, tly+(boxheight-stringHeight)/2 +stringHeight-2);
	        
	 }

	 
	 void PrintGridTitle(int tlx,int tly,Graphics pg)
	 {
		 PrintBoxedString("Sr",tlx,tly,40,linespacing,pg);
	 }
	 
	 
}