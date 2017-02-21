package in.refort.batches;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {

    private Model model;
    private View view;
    private ActionListener actionListener;
    private MouseAdapter mouseAdapter;
   
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
     
	     System.out.println(model.getJarPath()); ///set JAR path in model variable path;
                          
    }
    
    public void contol()
    {        
        actionListener = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) {                  
                  linkBtnAndLabel();
              }
        };                
        view.getButton().addActionListener(actionListener);   
    
    
       
        mouseAdapter=new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            
            	int col = view.getTable().columnAtPoint(e.getPoint());
            
                 
            	if(col==0) TablePrintButtonClicked();
            	
            }
        };

        view.getTable().getTableHeader().addMouseListener(mouseAdapter);
     
      
    }
    
    private void linkBtnAndLabel()
    {
        model.incX();                
        view.setText(Integer.toString(model.getX()));
        
    }
    
    private void TablePrintButtonClicked()
    {
        System.out.println("Add Print Routine");
    	
    
    }
}