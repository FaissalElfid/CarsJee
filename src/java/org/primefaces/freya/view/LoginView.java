/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.freya.view;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserFacade;

/**
 *
 * @author faiss
 */
@Named
@ViewScoped
public class LoginView implements Serializable{
    private static final long serialVersionUID = -5433850275008415405L;
    private String login;
    private String password;
    @Inject
    private UserFacade userFacade;

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
        userFacade.seConnecter(login, password);
        if(userFacade.seConnecter(login, password) < 0 ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "False Email or Password !!"));
            return "failure";
        }else{
            return "/dashboard?faces-redirect=true";
        }
    }
}
