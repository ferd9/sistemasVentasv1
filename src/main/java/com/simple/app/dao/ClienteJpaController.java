/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.dao;

import com.simple.app.config.HibernateConfig;
import com.simple.app.dao.exceptions.NonexistentEntityException;
import com.simple.app.modelo.Cliente;
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
public class ClienteJpaController implements Serializable {

    private HibernateConfig hibernateConfig = null;

    public ClienteJpaController() {
        this.hibernateConfig = new HibernateConfig();
    }   
       

    public EntityManager getEntityManager() {
        return this.hibernateConfig.getEm();
    }

    public void create(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }
    
    public Cliente findClienteByCedula(String cedula){
        EntityManager em = getEntityManager();

        try {

            Cliente cliente = em.createQuery("SELECT u from Cliente u WHERE u.cedula = :cedula", Cliente.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
            return cliente;
        } catch (NoResultException ex) {
            System.err.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }        
    }
    
    /**
     * 
     * @param data el valor a buscar en la tabla clientes
     * @param campo es el nombre del campo en la tabla de la base de datos.
     * @return 
     */
    public List<Cliente> findClientesByOptions(String data, String campo) {
        EntityManager em = getEntityManager();

        try {

            List<Cliente> clientes = em.createQuery("SELECT u from Cliente u WHERE u." + campo + " like :data", Cliente.class)
                    .setParameter("data", data+"%")
                    .getResultList();
            return clientes;
        } catch (NoResultException ex) {
            System.err.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Object[]> findClientesByMasCompras(int from) {
        EntityManager em = getEntityManager();

        try {

            List<Object[]> clientes = em.createQuery("SELECT u.idCliente, u.nombre, u.apellido, COUNT(cv.idCabeceraventa) AS total_ventas "
                    + "from Cliente u JOIN CabeceraVenta cv ON u.idCliente = cv.idCliente "
                    + "group by u.idCliente, u.nombre, u.apellido "
                    + "having count(cv.idCabeceraventa) > :from "
                    + "order by total_ventas")
                    .setParameter("from", from)
                    .getResultList();
            return clientes;
        } catch (NoResultException ex) {
            System.err.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
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
