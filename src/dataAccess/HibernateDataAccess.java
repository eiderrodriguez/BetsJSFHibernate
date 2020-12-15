package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import eredua.HibernateUtil;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess {

	public HibernateDataAccess() {}
	
	public void initializeDB(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	    
		// DBekiko eragiketak (transakzioa osatzen dutenak)
		try {

			
			   Calendar today = Calendar.getInstance();
			   
			   int month=today.get(Calendar.MONTH);
			   month+=1;
			   int year=today.get(Calendar.YEAR);
			   if (month==12) { month=0; year+=1;}  
		    
				Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
				Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
				Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
				Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
				Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
				Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
				Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
				Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
				Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
				Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

				Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
				Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
				Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
				Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
				Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
				Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
				

				Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
				Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
				Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
				Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
				
				Question q1;
				Question q2;
				Question q3;
				Question q4;
				Question q5;
				Question q6;

				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
					
				session.save(q1);
				session.save(q2);
				session.save(q3);
				session.save(q4);
				session.save(q5);
				session.save(q6);
		
		        
				session.save(ev1);
				session.save(ev2);
				session.save(ev3);
				session.save(ev4);
				session.save(ev5);
				session.save(ev6);
				session.save(ev7);
				session.save(ev8);
				session.save(ev9);
				session.save(ev10);
				session.save(ev11);
				session.save(ev12);
				session.save(ev13);
				session.save(ev14);
				session.save(ev15);
				session.save(ev16);
				session.save(ev17);
				session.save(ev18);
				session.save(ev19);
				session.save(ev20);			
				
				session.getTransaction().commit();
				System.out.println("Db initialized");
			}
			catch (Exception e){
				e.printStackTrace();
			}
		
	}
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    // DBekiko eragiketak (transakzioa osatzen dutenak)
	    Query q = session.createQuery("from Event e where e.eventNumber= :number");
		q.setParameter("number", event.getEventNumber());
		//emaitza jasotzeko lista sortu
		List<Event> l = q.list();
		Event ev = l.get(0);
	    
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		
		Question ques = ev.addQuestion(question, betMinimum);
		session.save(ev); 
		session.getTransaction().commit();
		return ques;
	}
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	 // DBekiko eragiketak (transakzioa osatzen dutenak)
		Vector<Event> res = new Vector<Event>();	
		Query query = session.createQuery("FROM Event ev WHERE ev.eventDate= :eguna");   
		query.setParameter("eguna", date);
		List<Event> events = query.list();
	 	for (Event ev:events){
	 	   System.out.println(ev.toString());		 
	 	   res.add(ev);
		}
	 	session.getTransaction().commit();
	 	return res;
	}
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    // DBekiko eragiketak (transakzioa osatzen dutenak)
	    
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :d1 and :d2");   
		query.setParameter("d1", firstDayMonthDate);
		query.setParameter("d2", lastDayMonthDate);
		List<Date> dates = query.list();
	 	for (Date d:dates){
	 	   System.out.println(d.toString());	
	 	   res.add(d);
		}
	 	session.getTransaction().commit();
	 	return res;
	}
}
