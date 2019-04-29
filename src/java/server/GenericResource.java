/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Nicolai
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    
     private KøreskolePriserInterface gw;
    Gson gson = new Gson();           
    JsonParser jp = new JsonParser(); 
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of angular.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    //KØRESKOLER
    
  /*
  Tager i mod én string med brugernavn+" "+kodeord
  */
  @Path("login")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public boolean login(String string) throws RemoteException, NotBoundException, MalformedURLException {
     
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
                                                                                               
        svaret = this.gw.logInd(bn, ko);
        
        return svaret;
  }
   /*
  Tager i mod én string med brugernavn+" "+kodeord+" "+jsonTilbud
  */
  @Path("opretTilbud")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public boolean opretTilbud(String string) throws RemoteException, NotBoundException, MalformedURLException {
     
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
         String jsonTilbud = result[2];
         
         Gson g = new Gson();
         Tilbud tilbud = g.fromJson(jsonTilbud, Tilbud.class);

         svaret = this.gw.opretTilbud(bn, ko, tilbud);
         
        return svaret;
  }
    /*
  Tager i mod én string med brugernavn+" "+kodeord+" "+tilbudID+" "+jsonTilbud
  */
  @Path("aendreTilbud")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public boolean ændreTilbud(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
         String id = result[2];
         String jsonTilbud = result[3];
         
         int idInt = Integer.parseInt(id);
         
         Gson g = new Gson();
         Tilbud tilbud = g.fromJson(jsonTilbud, Tilbud.class);

         svaret = this.gw.aendreTilbud(bn, ko, idInt, tilbud);
         
        return svaret;
  }
     /*
  Tager i mod én string med brugernavn+" "+kodeord
  */
  @Path("getTilbudKøreskole")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public String getTilbudKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        String svaret;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];

         svaret = this.gw.getTilbudKøreskole(bn, ko);
         
        return svaret;
  }
   
      /*
  Tager i mod én string med brugernavn+" "+kodeord
  */
  @Path("getKøreskole")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public String getKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        String svaret;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];

         svaret = this.gw.getKøreskole(bn, ko);
         
        return svaret;
  }
   
       /*
  Tager i mod én string med brugernavn+" "+kodeord
  */
  @Path("opretKøreskole")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public boolean opretKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        boolean svaret;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
         String jsonKøreskole = result[2];
         
         Gson g = new Gson();
         Køreskole køreskole = g.fromJson(jsonKøreskole, Køreskole.class);

         svaret = this.gw.opretKøreskole(bn, ko, køreskole);
         
        return svaret;
  }
   
   //KØRESKOLE ELEVER
   
   @Path("tilbudMedGiventPostnummer")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String getTilbud(int postnummer) throws RemoteException, NotBoundException, MalformedURLException {
      System.out.println("Kommet ind i getTilbud");
        String svaret = ""; 
         
           KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
            this.gw = giv;
            System.out.println("Oprettet RMI");
      
            svaret = this.gw.getTilbudFraPostnummer(postnummer);
            System.out.println(svaret);                                                                     
            JsonElement je = jp.parse(svaret);                                                              
            String færdig = svaret;
            System.out.println(svaret);
            
        return svaret;
  }
  
   @Path("getAlleTilbud")
    @GET
     @Produces(MediaType.TEXT_PLAIN)
    public String hentAlleTilbud() throws RemoteException, NotBoundException, MalformedURLException {
        System.out.println("Kommet ind i alleTilbud");
        String str = ""; 
         
            System.out.println("Kommet ind i try-blok");
           // GalgeInterf giv  = (GalgeInterf) Naming.lookup("rmi://localhost:1235/kontotjeneste");
           KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
            this.gw = giv;
            System.out.println("Oprettet RMI");
            
           ArrayList<Tilbud> til;                                                                       
            ArrayList<TilbudTilBrugere> tilb;                                                            
                                                                                             
                                                                            
        str = this.gw.getAlleTilbud();                                                                
        System.out.println(str);                                                                     
        JsonElement je = jp.parse(str);                                                              
        //com.google.gson.JsonArray ja = je.getAsJsonArray(); 
        String færdig = str;
         System.out.println(færdig);
        return færdig;
}
    @Path("getTilbudMellemPrisFraPostnummer")
    @GET
     @Produces(MediaType.TEXT_PLAIN)
    public String getTilbudMellemPrisFraPostnummer(String string) throws RemoteException, NotBoundException, MalformedURLException {
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup("rmi://130.225.170.204:5478/koereskolepriser");
        String svaret;
         this.gw = giv;
         String[] result = string.split(" "); 
         String post = result[0];
         String min = result[1];
         String max = result[2]; 
         
         int postInt = Integer.parseInt(post);
         int minInt = Integer.parseInt(min);
         int maxInt = Integer.parseInt(max);
                                    
        svaret = gw.getTilbudMellemPrisFraPostnummer(postInt, minInt, maxInt);
        return svaret;
}
    
   

}
