package eredua.bean;

import java.util.Date;
import java.util.Vector;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import domain.Event;
import domain.Question;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean( );
	private static BLFacade facadeInterface;
	
	
	public static FacadeBean getSingleton() {
		return singleton;
	}
	public static void setSingleton(FacadeBean singleton) {
		FacadeBean.singleton = singleton;
	}
	public static BLFacade getFacadeInterface() {
		return facadeInterface;
	}
	public static void setFacadeInterface(BLFacade facadeInterface) {
		FacadeBean.facadeInterface = facadeInterface;
	}

	
	private FacadeBean(){
		try { 
			facadeInterface=new BLFacadeImplementation(); 
		} catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: "+e.getMessage()); 
		}
	}
	
	public static BLFacade getBusinessLogic( ) { 
		return facadeInterface;
	} 
}