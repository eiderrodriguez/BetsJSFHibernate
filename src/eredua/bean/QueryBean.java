package eredua.bean;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;


public class QueryBean {
	private BLFacade facadeBL;
	private Vector<Event> gertaerak;
	private Event aukEv;
	private Vector<Question> galderak;
	private Date data;

	
	public QueryBean(){
		facadeBL=FacadeBean.getBusinessLogic();
		gertaerak=facadeBL.getEvents(data);
	}
	
	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}
	public Vector<Event> getGertaerak() {
		return gertaerak;
	}
	public void setGertaerak(Vector<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}
	public Event getAukEv() {
		return aukEv;
	}
	public void setAukEv(Event aukEv) {
		this.aukEv = aukEv;
	}
	public Vector<Question> getGalderak() {
		return galderak;
	}
	public void setGalderak(Vector<Question> galderak) {
		this.galderak = galderak;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public void onDateSelect(SelectEvent eguna) {
		galderak = null;
		data = (Date) eguna.getObject();
		gertaerak = facadeBL.getEvents(data);
	}
	
	public void onEventSelect(SelectEvent ev) {
		aukEv = (Event) ev.getObject();
		//cast ez
		Vector<Question> g = new Vector<Question>();
		for (Question gal:aukEv.getQuestions()) {
			g.add(gal);
		}
		Collections.sort(g);
		galderak = g;
	}
	
	//beste bat klikatzerakoan hustu lehenengo
	public void updateEguna(ActionEvent e) {
		data = null;
		gertaerak = null;
		galderak = null;
		aukEv = null;
	}
	
}