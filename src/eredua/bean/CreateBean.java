package eredua.bean;

import java.util.Date;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateBean {

	private BLFacade facadeBL;
	private Vector<Event> gertaerak;
	private Event aukEv;
	private Date data;
	private float minbet;
	public String galdera;

	
	public CreateBean(){
		facadeBL=FacadeBean.getBusinessLogic();
		gertaerak=facadeBL.getEvents(data);
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getMinbet() {
		return minbet;
	}
	public void setMinbet(float minbet) {
		this.minbet = minbet;
	}
	public String getGaldera() {
		return galdera;
	}
	public void setGaldera(String galdera) {
		this.galdera = galdera;
	}
	public void onDateSelect(SelectEvent eguna) {
		data = (Date) eguna.getObject();
		gertaerak = facadeBL.getEvents(data);
	}

	public void onEventSelect(SelectEvent ev) {
		aukEv = (Event) ev.getObject();
	}

	public void galderaSortu() throws EventFinished, QuestionAlreadyExist {
		if(aukEv==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: gertaera aukeratu behar da."));
		}
		else if(galdera.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: galdera ezin da hutsik egon."));
			}
		else if(minbet<0.0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: apustua ezin da negatibo izan."));
		}
		else {
			facadeBL.createQuestion(aukEv, galdera, minbet);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Galdera ondo sortu da!"));
		}
	}

	public void hustuDatuak(ActionEvent e) {
		data = null;
		gertaerak = null;
		aukEv = null;
		minbet = 0;
		galdera = null;
	}
}