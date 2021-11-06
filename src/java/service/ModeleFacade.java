/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Modele;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author faiss
 */
@javax.ejb.Stateless
public class ModeleFacade extends AbstractFacade<Modele> {

    @PersistenceContext(unitName = "cars-projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeleFacade() {
        super(Modele.class);
    }
    
}
