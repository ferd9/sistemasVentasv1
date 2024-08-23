/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.dao;

import com.simple.app.config.HibernateConfig;
import com.simple.app.dao.exceptions.NonexistentEntityException;
import com.simple.app.modelo.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Lynkos
 */
public class ProductoJpaController implements Serializable {

    
    private HibernateConfig hibernateConfig = null;
    
    public ProductoJpaController() {
        this.hibernateConfig = new HibernateConfig(); 
    }    

    public EntityManager getEntityManager() {
        return this.hibernateConfig.getEm();
    }

    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            producto = em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }
    
    public Producto findProductoByName(String nombreProducto){
                EntityManager em = getEntityManager();
        
        try{            
        
            Producto producto = em.createQuery("SELECT u from Producto u WHERE u.nombre = :nombreProducto",Producto.class)
                    .setParameter("nombreProducto", nombreProducto)
                    .setMaxResults(1)
                    .getSingleResult();
            return producto;
        }catch(NoResultException ex){
            System.err.println(ex.getMessage());
            return null;
        }finally {
            em.close();
        }        
    }
    
    public List<Producto> findProductosByName(String nombreProducto) {
        EntityManager em = getEntityManager();

        try {

            List<Producto> productos = em.createQuery("SELECT u from Producto u WHERE u.nombre = :nombreProducto", Producto.class)
                    .setParameter("nombreProducto", nombreProducto)
                    .getResultList();
            return productos;
        } catch (NoResultException ex) {
            System.err.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
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
