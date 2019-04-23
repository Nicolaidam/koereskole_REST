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
    
    @Path("loginnn")
    @GET
     @Produces(MediaType.TEXT_PLAIN)
    public String login(String string) {
        System.out.println("KOMMET IND!!!!!!");
        System.out.println("Den modtagne string er: " +string);
    return "inf fra rest hehehe FORHELF: "+string;
  }
    
     @Path("alleTilbud")
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
                                                                                    
                                                                                     
            
       
       
        
}}
