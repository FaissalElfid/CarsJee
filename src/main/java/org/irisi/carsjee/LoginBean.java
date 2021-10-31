package org.irisi.carsjee;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -5433850275008415405L;
    private String login = "James";
    private String password = "007";

    public String getLogin() {
        System.out.println( "in getLogin" ); return login;
    }
    public void setLogin(String login) {
        System.out.println( "in setLogin with " + login );
        this.login = login;
    }
    public String getPassword() {
        System.out.println( "in getPassword" );
        return password;
    }
    public void setPassword(String password) {
        System.out.println( "in setPassword with " + password );
        this.password = password;
    }
    public String returnAction() {
        System.out.println( "in returnAction" );
        if(password.equals("007")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "False Email or Password !!"));
            return "fail";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login success."));
            return "success";
        }
//        return password.equals( "007" ) ? "success" : "failure";
    }
}
