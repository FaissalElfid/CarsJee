/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irisi.carsjee.service;

import org.irisi.carsjee.bean.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author faiss
 */
@jakarta.ejb.Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "cars-projectPU")
    private EntityManager em;

    public User findBylogin(String login) {
        return findBy("login", login);
    }

    public int seConnecter(String login, String password) {
        User loadedUser = findBylogin(login);
        if (loadedUser == null) {
            return -1;
        } else if (!loadedUser.getPassword().equals(password)) {
            return -2;
        } else {
            return 1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

}
