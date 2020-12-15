package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import dataAccess.HibernateDataAccess;
import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		//Lelengo aldien bakarrik
		//HibernateDataAccess dbManager = new HibernateDataAccess();
		//dbManager.initializeDB();
		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	    //The minimum bed must be greater than 0
	    HibernateDataAccess dBManager=new HibernateDataAccess();
		Question qry=null;
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,betMinimum);		

		//dBManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date)  {
		HibernateDataAccess dbManager=new HibernateDataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		//dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		HibernateDataAccess dbManager=new HibernateDataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		//dbManager.close();
		return dates;
	}
	
	
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */		
	 public void initializeBD(){
		HibernateDataAccess dBManager=new HibernateDataAccess();
		dBManager.initializeDB();
		//dBManager.close();
	}

}

