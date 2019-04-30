package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Tilbud implements Serializable {
	public Tilbud() throws Exception {
		tilgangeligeDage = new TilgangeligeDage();
	}
	
	static final long serialVersionUID = 1234567890;
	
	String koreskole_id;
	int pris;
	String korekort_type;
	int lynkursus;
	String bilmarke;
	String bilstorrelse;
	String kon;
	String beskrivelse;
	TilgangeligeDage tilgangeligeDage;
	int id;
	
	@Override
	public String toString() {
		String s = "";
		s+="køreskoleid: "+koreskole_id;
		s+="\npris: "+pris;
		s+="\nkørekort: type: "+korekort_type;
		s+="\nlynkursus: "+lynkursus;
		s+="\nbilmærke: "+bilmarke;
		s+="\nbilstørrelse: "+bilstorrelse;
		s+="\nkøn: "+kon;
		s+="\nbeskrivelse: "+beskrivelse;
		
		return s;
	}

}