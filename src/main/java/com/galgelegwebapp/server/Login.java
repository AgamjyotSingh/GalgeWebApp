package com.galgelegwebapp.server;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import javax.ws.rs.Path;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import galgeleg.GalgeI;
import game.GameI;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

@Path("login")
public class Login {
    String REMOTEURL = "rmi://130.225.170.246/gameCalls";
    GameI gameCalls;
    GalgeI galgeI;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String postLogin(@FormParam("usrname") String userName,
                            @FormParam("psswrd") String passWord) throws IOException, NotBoundException {
        Game.userName = userName;
        //Initialize Mustache renderer
        if(login(userName, passWord).equals("Success")) {
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache m = mf.compile("loginsuccess.mustache");
            //Set some data
            HashMap<String, Object> mustacheData = new HashMap<String, Object>();
            mustacheData.put("username", userName);
            //render template with data
            StringWriter writer = new StringWriter();
            m.execute(writer, mustacheData).flush();
            return writer.toString();
        }
        
        else if(login(userName, passWord).equals("Login error")){
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache m = mf.compile("loginError.mustache");
            //Set some data
            HashMap<String, Object> mustacheData = new HashMap<String, Object>();
            mustacheData.put("username", userName);
            //render template with data
            StringWriter writer = new StringWriter();
            m.execute(writer, mustacheData).flush();
            return writer.toString();    
        }
        return null;
    }
    
    public String login(String userName, String passWord) throws NotBoundException, RemoteException, MalformedURLException{
        Bruger user;
        try{
        Brugeradmin userAdmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        user = userAdmin.hentBruger(userName, passWord);
        System.out.println("Fik bruger = " + user);
       
        } catch (IllegalArgumentException loginFejl) {
            System.out.println("loginfejl " + loginFejl.getMessage());
            return "Login error";
            
           
        } catch (MalformedURLException ex) {
            System.out.println("Error in Login: "+ex.getLocalizedMessage());
        } 
        return "Success";
    
        
    }
   
}
