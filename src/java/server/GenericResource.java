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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Nicolai
 */
@Path("generic")
public class GenericResource {
    String url = "rmi://dist.saluton.dk:5500/koereskolepriser";
    // String url = "rmi://dist.saluton.dk:5478/koereskolepriser";
    // String url = "rmi://dist.saluton.dk:1235/koereskolepriser";

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
   public String login(String string) throws RemoteException, NotBoundException, MalformedURLException {
     
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
                                                                                               
        svaret = this.gw.logInd(bn, ko);
        
        String returnString = "0";
        
        if(svaret==true){
            returnString = "1";
        }

        
        return returnString;
  }
   /*
  Tager i mod én string med brugernavn+" "+kodeord+" "+jsonTilbud
  */
  @Path("opretTilbud")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public String opretTilbud(String string) throws RemoteException, NotBoundException, MalformedURLException {
     
       System.out.println("OMG OMG OMG"+string);
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
       
         JsonParser jp = new JsonParser();
         JsonElement js = jp.parse(string);
         
         Gson g = new Gson();
         System.out.println("STRENGEN VI MODTAGER ER: " + string);
         
         String[] arr = g.fromJson(js, String[].class);
        
           System.out.println(arr[0]);
           System.out.println(arr[1]);
           System.out.println(arr[2]);
           
            Gson g2 = new Gson();
           JsonElement js2 = jp.parse(arr[2]);
            Tilbud tss = g2.fromJson(js2, Tilbud.class);
           
          svaret = this.gw.opretTilbud(arr[0], arr[1], tss);
        String returnString = "0";
        
        if(svaret==true){
            returnString = "1";
        }
         
        return returnString;
  }
 /*
  Tager i mod én string med brugernavn+" "+kodeord+" "+tilbudID
  */
  @Path("sletTilbud/{id}/{brugernavn}/{password}")
  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
   public String sletTilbud(@PathParam("id") int Id, @PathParam("brugernavn") String navn, @PathParam("password") String kode) throws RemoteException, NotBoundException, MalformedURLException {
     
       System.out.println("MODTAGET STRING I sletTILBUD : "+Id + " --- " + navn + " --- " + kode);
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
       
         JsonParser jp = new JsonParser();
        // JsonElement js = jp.parse(string);
         
         Gson g = new Gson();
        
           
           int[] tilbudID = new int[1];
           tilbudID[0] = Id;
           
          svaret = this.gw.sletTilbud(navn, kode, tilbudID);
          
          String returnString = "0";
        
        if(svaret==true){
            returnString = "1";
        }
         
        return returnString;
  }
   
   
   /*
  Tager i mod én string med brugernavn+" "+kodeord+" "+tilbudID+" "+jsonTilbud
  */
  @Path("aendreTilbud")
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
   public String ændreTilbud(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
       System.out.println("OMG OMG OMG"+string);
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
       
         JsonParser jp = new JsonParser();
         JsonElement js = jp.parse(string);
         
         Gson g = new Gson();
         
         String[] arr = g.fromJson(js, String[].class);
         
         String bn = arr[0];
         String ko = arr[1];
         String id = arr[2];
         String jsonTilbud = arr[3];
         System.out.println(bn);
         System.out.println(ko);
         System.out.println(id);
         System.out.println(jsonTilbud);
         
         Tilbud tilbud = g.fromJson(jsonTilbud, Tilbud.class);
         
         int idInt = Integer.parseInt(id);

          svaret = this.gw.aendreTilbud(bn, ko, idInt, tilbud);
          
          String returnString = "0";
        
        if(svaret==true){
            returnString = "1";
        }
         
        return returnString;
  }
     /*
  Tager i mod én string med brugernavn+" "+kodeord
   BURDE nok være get, men sjit happens 
  */
  @Path("tilbudKoreskole")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public String getTilbudKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        String svaret;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
         String[] result = string.split(" "); 
         String bn = result[0];
         String ko = result[1];
         
         System.out.println(bn);
         System.out.println(ko);
         

         svaret = this.gw.getTilbudKøreskole(bn, ko);
         
         System.out.println(svaret);
         
        return svaret;
  }
   
      /*
  Tager i mod én string med brugernavn+" "+kodeord
   ikke brugte lige nu, men skulle bruges i fremtiden
  */
  @Path("koreskole")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
   public String getKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
        String svaret;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
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
   public String opretKøreskole(String string) throws RemoteException, NotBoundException, MalformedURLException {
       
         System.out.println("INDE I OPRETKS"+string);
        Boolean svaret = false;
        
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
         this.gw = giv;
       
         JsonParser jp = new JsonParser();
         JsonElement js = jp.parse(string);
         
         Gson g = new Gson();
         
         String[] arr = g.fromJson(js, String[].class);        
           System.out.println(arr[0]);
           System.out.println(arr[1]);
           System.out.println(arr[2]);
           
            Gson g2 = new Gson();
           JsonElement js2 = jp.parse(arr[2]);
            Koreskole tss = g2.fromJson(js2, Koreskole.class);
           System.out.println(tss.adresse);
          svaret = this.gw.opretKøreskole(arr[0], arr[1], tss);
          
           String returnString = "0";
        
        if(svaret==true){
            returnString = "1";
        }
         
        return returnString;
  }
   
   //KØRESKOLE ELEVER
   
   @Path("tilbudMedGiventPostnummer/{postnummer}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getTilbud(@PathParam("postnummer") int postnummer) throws RemoteException, NotBoundException, MalformedURLException {
      System.out.println("Kommet ind i getTilbud");
        String svaret = ""; 
         
           KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
            this.gw = giv;
            System.out.println("Oprettet RMI");
      
            svaret = this.gw.getTilbudFraPostnummer(postnummer);
            System.out.println(svaret);                                                                     
            JsonElement je = jp.parse(svaret);                                                              
           //  String færdig = svaret;
            System.out.println(svaret);
            
        return svaret;
  }
  
   @Path("alleTilbud")
    @GET
     @Produces(MediaType.TEXT_PLAIN)
    public String hentAlleTilbud() throws RemoteException, NotBoundException, MalformedURLException, Exception {
        System.out.println("Kommet ind i alleTilbud");
        String str = ""; 
         
            System.out.println("Kommet ind i try-blok");
           // GalgeInterf giv  = (GalgeInterf) Naming.lookup("rmi://localhost:1235/kontotjeneste");
           KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
            this.gw = giv;
            System.out.println("Oprettet RMI");
           /* TilgangeligeDage tilg = new TilgangeligeDage();
            tilg.tilgangelig_fredag = 1;
            tilg.tilgangelig_lordag = 0;
            tilg.tilgangelig_mandag = 1;
            tilg.tilgangelig_onsdag = 0;
            tilg.tilgangelig_sondag = 1;
            tilg.tilgangelig_tirsdag = 0;
            tilg.tilgangelig_torsdag = 1;
            
            Tilbud tilbud = new Tilbud();
            tilbud.beskrivelse = "";
            tilbud.bilmarke = "Honda";
            tilbud.bilstorrelse = "stor";
            tilbud.kon = "mand";
            tilbud.korekort_type = "A";
            tilbud.lynkursus = 0;
            tilbud.pris = 12000;
            tilbud.tilgangeligeDage = tilg;
            tilbud.koreskole_id = "s175132";
            
            
            System.out.println("OPRET TILBUD-----" + this.gw.opretTilbud("s175132", "DS2019", tilbud));
                   
            */
           System.out.println(this.gw.logInd("s175132", "DS2019"));
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
    @Path("tilbudMellemPrisFraPostnummer")
    @GET
     @Produces(MediaType.TEXT_PLAIN)
    public String getTilbudMellemPrisFraPostnummer(String string) throws RemoteException, NotBoundException, MalformedURLException {
        KøreskolePriserInterface giv = (KøreskolePriserInterface) Naming.lookup(url);
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
