/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeLocation;
import bean.Paiement;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author faiss
 */
@javax.ejb.Stateless
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
