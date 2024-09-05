/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.dao;

import com.simple.app.config.HibernateConfig;
import com.simple.app.dao.exceptions.NonexistentEntityException;
import com.simple.app.modelo.CabeceraVenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Lynkos
 */
public class CabeceraVentaJpaController implements Serializable {
    
    private HibernateConfig hibernateConfig = null;

    public CabeceraVentaJpaController() {
        this.hibernateConfig = new HibernateConfig(); 
    }   

    public EntityManager getEntityManager() {
        return this.hibernateConfig.getEm();
    }

    public void create(CabeceraVenta cabeceraVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabeceraVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CabeceraVenta cabeceraVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabeceraVenta = em.merge(cabeceraVenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cabeceraVenta.getIdCabeceraventa();
                if (findCabeceraVenta(id) == null) {
                    throw new NonexistentEntityException("The cabeceraVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CabeceraVenta cabeceraVenta;
            try {
                cabeceraVenta = em.getReference(CabeceraVenta.class, id);
                cabeceraVenta.getIdCabeceraventa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabeceraVenta with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabeceraVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CabeceraVenta> findCabeceraVentaEntities() {
        return findCabeceraVentaEntities(true, -1, -1);
    }

    public List<CabeceraVenta> findCabeceraVentaEntities(int maxResults, int firstResult) {
        return findCabeceraVentaEntities(false, maxResults, firstResult);
    }

    private List<CabeceraVenta> findCabeceraVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CabeceraVenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CabeceraVenta findCabeceraVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CabeceraVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabeceraVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CabeceraVenta> rt = cq.from(CabeceraVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void close()
    {
        hibernateConfig.close();
    }
    
}
