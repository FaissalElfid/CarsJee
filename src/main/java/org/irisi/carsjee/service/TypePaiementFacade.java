/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irisi.carsjee.service;

import org.irisi.carsjee.bean.TypePaiement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author faiss
 */
@jakarta.ejb.Stateless
public class TypePaiementFacade extends AbstractFacade<TypePaiement> {

    @PersistenceContext(unitName = "cars-projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypePaiementFacade() {
        super(TypePaiement.class);
    }
    
}
