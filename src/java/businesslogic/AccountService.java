/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    
    public User checkLogin(String username, String password, String path) {
        User user;
        
        UserDB userDB = new UserDB();
        try {
            user = userDB.getUser(username);
            
            if (user.getPassword().equals(password)) {
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, 
                        "A user logged in: {0}",
                        username);
                try {
                    //WebMailService.sendMail(user.getEmail(), "NotesKeepr Logged in", "<h2>Congrats!  You just loggedin successfully.</h2>" , true);
                    
                    HashMap<String, String> contents = new HashMap<>();
                    
                    contents.put("firstname", user.getFirstname());
                    contents.put("date", (new java.util.Date()).toString());
                    
                    String template = path + "/emailtemplates/login.html";
                    WebMailService.sendMail(user.getEmail(), "NotesKeepr Login", template, contents);
                    
                } catch (Exception ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return user;
            }
            
        } catch (NotesDBException ex) {
        }
        
        return null;
    }
    
    public boolean forgotPassword(String email, String path) {
        UserService us = new UserService();
        //WebMailService wms = new WebMailService();
        //List<User> users = null;
        User user = null;
        HashMap<String, String> contents = new HashMap<>();
        boolean status = false;
        
        try {
            user = us.getByEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user != null) {
            contents.put("firstname", user.getFirstname());
            contents.put("lastname", user.getLastname());
            contents.put("username", user.getUsername());
            contents.put("password", user.getPassword());

            String template = path + "\\emailtemplates\\resetPassword.html";
            try {
                WebMailService.sendMail(user.getEmail(), "NotesKeepr Reset", template, contents);
            } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
            status = true;
        }
        
       /* try {
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (users != null) {
            for (User temp: users) {
                if (temp.getEmail().equals(email)) {
                    contents.put("firstname", user.getFirstname());
                    contents.put("lastname", user.getLastname());
                    contents.put("username", user.getUsername());
                    
                    String template = path + "/emailtemplates/resetpassword.html";
                    try {
                        WebMailService.sendMail(user.getEmail(), "NotesKeepr Reset", template, contents);
                    } catch (Exception ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    status = true;
                }
            }
        }*/
        
        return status;
    }
    
}
