package server;

import java.io.Serializable;

public class TilbudTilBrugere implements Serializable{

    public Tilbud tilbud;
    public Koreskole køreskole;
    
    public TilbudTilBrugere() throws Exception {
    	tilbud = new Tilbud();
    	køreskole = new Koreskole();
	}

    @Override
    public String toString() {
        String s = "";
        s+="køreskoleid: "+ tilbud.koreskole_id;
        s+="\npris: "+tilbud.pris;
        s+="\nkørekort: type: "+ tilbud.korekort_type;
        s+="\nlynkursus: "+tilbud.lynkursus;
        s+="\nbilmærke: "+tilbud.bilmarke;
        s+="\nbilstørrelse: "+tilbud.bilstorrelse;
        s+="\nkøn: "+tilbud.kon;
        s+="\nbeskrivelse: "+tilbud.beskrivelse;

        return s;
    }

}
