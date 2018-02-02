
package in.refort.batches;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;


public class PrintChemChart implements Printable
{	
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> rollArray = new ArrayList<String>();
	
	////--------------------------------------------------------------------
	int TOPLEFTX=74,TOPLEFTY=20;  ////THE STRATING PRINT SPOT AT TOP LEFT CORNER
	 int linespacing = 12;
    ////--------------------------------------------------------------------
	
	 int colwidth[]={40,85,70,70, 70, 70, 70};
	
	String filebeingprinted;
	int totalpages=0;
	int TotalBatches=0;
	int CurrentBatch=0;
	private String BoardName1="Maharashtra State Board of Secondary & Higher Secondary Education,";
    private String BoardName2="Mumbai Divisional Board, Vashi, Navi Mumbai - 400703";
    private String MonthYear="Feb-2018";

    private String School,Index,Stream,Standard,Subject,SubjectCode,Medium,Type,BatchNo,BatchCreator,
                   Email1,Email2,Det,Tyme,Session;

	
	  public void show(String msg) ///for debugging
		{JOptionPane.showMessageDialog(null, msg);}
	    
	

	  public void setArray(ArrayList<String> array) 
	   {
		  strArray=array;
		}
	  
	
	
	
	///// Here the whole  JAVA Printing Mechanism Starts 
	/////  Note 'implements Printable above', It includes the Print Mechanism to our Program
	/////
	  public void PrintBatch(String filepath,String printername,String filename)
              {
		  

		  SetAllHeaderFields();    //// School, Index,Subject,Batch etc
		  
		  filebeingprinted=filename;
		  PrintService ps = findPrintService(printername);
		  if(ps==null) ps = PrintServiceLookup.lookupDefaultPrintService(); 
		  if(ps==null) return;
		   
	         PrinterJob job = PrinterJob.getPrinterJob();
	         job.setJobName(filename+"-CHART");
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
		 
		 
		 Font MyFont = new Font("Liberation Serif", Font.PLAIN,11);
		 pg.setFont(MyFont); 
         
		  PrintIndexNumber(TOPLEFTX+120,TOPLEFTY,pg);
		 
		  PrintHeader(TOPLEFTX,TOPLEFTY+20,pg,pageno);
		  PrintGridTitle(TOPLEFTX,TOPLEFTY+118,pg);
		  PrintGridMain(TOPLEFTX,TOPLEFTY+118+linespacing,pg);
		  PrintFooter(TOPLEFTX,TOPLEFTY+760,pg);
		 
        
        
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
		
		String temp,stemp;
		for(int i=1;i<16;i++)
		{stemp=strArray.get(i).substring(15);
		//show
		temp=stemp.trim();
		strArray.set(i,temp);	
		}
		
		// line 0 is blank line
		School=strArray.get(1); 
		Index=strArray.get(2);
		Stream=strArray.get(3);
		Standard=strArray.get(4);
		Subject=strArray.get(5);
		SubjectCode=strArray.get(6);
		Medium=strArray.get(7);
		Type=strArray.get(8);
		BatchNo=strArray.get(9);
		BatchCreator=strArray.get(10);
		Email1=strArray.get(11); 
		Email2=strArray.get(12); 
		Det=strArray.get(13);
		Tyme=strArray.get(14);
		Session=strArray.get(15);
		
		rollArray.removeAll(rollArray);
		for(int i=21;i<strArray.size();i++) rollArray.add(strArray.get(i));
		
	}
	

	
	 private void Centre(String s, int width, int XPos, int YPos,Graphics g2d)
	 {  
	        int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
	        int start = width/2 - stringLen/2;  
	        g2d.drawString(s, start + XPos, YPos);  
	 }
	
	 
	 public void PrintIndexNumber(int tlx,int tly, Graphics gr)
	 {	   gr.drawString("College Index Number",tlx,tly);
	       int width=10;
	       int strlen=Index.length(); if(strlen==0) return;
	       for(int i=0;i<strlen;i++)
	       {PrintBoxedString(Index.substring(i, i+1),tlx+115,tly-10,width,linespacing,gr);
	        tlx+=width;
	       }
	 }
	 
	 public void PrintHeader(int topleftx,int toplefty,Graphics gr,int pageno)
	  {linespacing=12;
	     
	  String StartSeatNo=rollArray.get(0);
	  String EndSeatNo=rollArray.get(rollArray.size()-1);
	 
	  Font MyFont = new Font("Liberation Serif", Font.PLAIN,13);
	  gr.setFont(MyFont); 	   	   
	   Centre(BoardName1,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre(BoardName2,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   Centre(Standard+" - "+Type+" - "+MonthYear,460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
	   //Centre("Attendance Sheet",460,topleftx,toplefty,gr);
	   Centre("PRACTICAL EXAM CHART",460,topleftx,toplefty,gr);
	   toplefty+=linespacing;
//	   Centre("FORM No. 3A",460,topleftx,toplefty,gr);

	   Font MyFont1 = new Font("Liberation Serif", Font.PLAIN,11);
  	   gr.setFont(MyFont1); 	   	   
	   
	   toplefty+=linespacing;
	   gr.drawString("Jr College/School Name : "+School,topleftx,toplefty);
	   gr.drawString("Block No :  "+BatchNo,topleftx+380,toplefty);
	  toplefty+=linespacing;
	  gr.drawString("Subject : "+Subject,topleftx,toplefty);
	  gr.drawString("Subject No : "+SubjectCode,topleftx+240,toplefty);
	  gr.drawString("Medium : "+Medium,topleftx+380,toplefty);	 
	  toplefty+=linespacing;
	  gr.drawString("Seat No.s : From  "+StartSeatNo+"  TO  "+EndSeatNo,topleftx,toplefty);
	  gr.drawString("Date :  "+Det,topleftx+240,toplefty);
	  gr.drawString("Time :  "+Tyme,topleftx+380,toplefty);
	//  toplefty+=linespacing;
	//  gr.drawString("Extra Seat No.s :",topleftx,toplefty);
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
	 {   linespacing=18;
		 PrintBoxedString("Sr No",tlx,tly,colwidth[0],linespacing,pg);
		 tlx+=colwidth[0];
		 PrintBoxedString("Seat No",tlx,tly,colwidth[1],linespacing,pg);
		 tlx+=colwidth[1];
		 PrintBoxedString("Table No",tlx,tly,colwidth[2],linespacing,pg);
		 tlx+=colwidth[2];
		 PrintBoxedString("Journal",tlx,tly,colwidth[3],linespacing,pg);
		 tlx+=colwidth[3];
		 PrintBoxedString("Project",tlx,tly,colwidth[4],linespacing,pg);
		 tlx+=colwidth[4];
		 PrintBoxedString("Viva",tlx,tly,colwidth[5],linespacing,pg);
		 tlx+=colwidth[5];
		 PrintBoxedString("Total",tlx,tly,colwidth[6],linespacing,pg);

	 }
	 
	 void PrintGridMain(int tlx,int tly,Graphics pg)
	 {   String srno;
	     int leftx;
		 for(int i=0;i<32;i++)
	       { if(i>=rollArray.size())  ///print empty grid
	       {leftx=tlx;
			 
		     PrintBoxedString(" ",leftx,tly,colwidth[0],linespacing,pg);
             leftx+=colwidth[0];	       
		     PrintBoxedString(" ",leftx,tly,colwidth[1],linespacing,pg);
		     leftx+=colwidth[1];
		     PrintBoxedString(" ",leftx,tly,colwidth[2],linespacing,pg);
		     leftx+=colwidth[2];
		     PrintBoxedString("",leftx,tly,colwidth[3],linespacing,pg);
		     leftx+=colwidth[3];
		     PrintBoxedString("",leftx,tly,colwidth[4],linespacing,pg);
		     leftx+=colwidth[4];
		     PrintBoxedString("",leftx,tly,colwidth[5],linespacing,pg);
		     leftx+=colwidth[5];
		     PrintBoxedString("",leftx,tly,colwidth[6],linespacing,pg);      
		      
		      tly+=linespacing;
	    	   
	       }
	    	   
	       else   ///print filled Grid
	       {
			 leftx=tlx;
			 srno=String.format("%d",i+1);
		     PrintBoxedString(srno,leftx,tly,colwidth[0],linespacing,pg);
             leftx+=colwidth[0];	       
		     PrintBoxedString(rollArray.get(i),leftx,tly,colwidth[1],linespacing,pg);
		     leftx+=colwidth[1];
		     PrintBoxedString("",leftx,tly,colwidth[2],linespacing,pg);
		     leftx+=colwidth[2];
		     PrintBoxedString("",leftx,tly,colwidth[3],linespacing,pg);
			 leftx+=colwidth[3];
			 PrintBoxedString("",leftx,tly,colwidth[4],linespacing,pg);
			 leftx+=colwidth[4];
			 PrintBoxedString("",leftx,tly,colwidth[5],linespacing,pg);
			 leftx+=colwidth[5];
			 PrintBoxedString("",leftx,tly,colwidth[6],linespacing,pg);

		      
		      tly+=linespacing;
	       }
	    }
	 }
	 
	 
	 
	 public void PrintFooter(int topleftx,int toplefty,Graphics gr)
	  {
	   linespacing=12;
	   gr.drawString("Total Present :                    Total Absent :                    Total No. :",topleftx,toplefty - 30);  
	      
	   gr.drawString("Supervisor's/Teacher's Name :",topleftx,toplefty);
	   gr.drawString("Conductor's/Princpal's/Head Master's Signature : ",topleftx+250,toplefty);
	
	   toplefty+=linespacing;
	   
	   gr.drawString("Signature :",topleftx,toplefty); 
	   gr.drawString("And Stamp :",topleftx+250,toplefty);
	  
	   toplefty+=linespacing+10;
	   gr.drawString("Note : To be kept by Centre/School College conductor for one year after the declaration of the result",topleftx,toplefty);
	   
	  
	  }
	 
     
}
