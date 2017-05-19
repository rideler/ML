package view;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Gateway class extends Observable
 */
public class Gateway extends Observable {

	private ArrayList<String> commands;
	
	/**
	 * ctor
	 */
	public Gateway() {
		this.setCommands(new ArrayList<String>());
		//setting the print for the menu
		//commands.add("exit");
	}
	
	//getters and setters
	public ArrayList<String> getCommands() {
		return commands;
	}
	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}
	
	/**
	 * 
	 * @param args
	 */
	public void sender(Object[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * print the erroe to log.
	 * @param string the error
	 */
	public void error(String string) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		Logger logger = Logger.getLogger("log");
        logger.setUseParentHandlers(false);
	    FileHandler fh;  

	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("ML.log" , true);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.warning(nowDate + " : " + string); 
	        fh.close();
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  		
	}

}
