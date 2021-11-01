/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irisi.carsjee.service;

import org.irisi.carsjee.bean.DemandeLocation;
import org.irisi.carsjee.bean.Paiement;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author faiss
 */
@jakarta.ejb.Stateless
public class PaiementFacade extends AbstractFacade<Paiement> {

    @PersistenceContext(unitName = "cars-projectPU")
    private EntityManager em;
    @EJB
    private DemandeLocationFacade demandeLocationFacade;

    public int payer(Paiement paiement) {
        final DemandeLocation demandeLocation = paiement.getDemandeLocation();
        if (findBy("reference", paiement.getReference()) != null) {
            return -1;
        } else if (paiement.getMontant() + demandeLocation.getTotalPaye() > demandeLocation.getTotal()) {
            return -2;
        } else {
            double nvTotalPaye = paiement.getMontant() + demandeLocation.getTotalPaye();
            demandeLocation.setTotalPaye(nvTotalPaye);
            create(paiement);
            demandeLocationFacade.edit(demandeLocation);
            return 1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaiementFacade() {
        super(Paiement.class);
    }

}
