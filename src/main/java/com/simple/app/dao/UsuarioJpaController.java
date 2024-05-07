/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.simple.app.dao;

import com.simple.app.config.HibernateConfig;
import com.simple.app.dao.exceptions.NonexistentEntityException;
import com.simple.app.dao.exceptions.PreexistingEntityException;
import com.simple.app.modelo.Usuario;
import jakarta.persistence.EntityManager;
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
public class UsuarioJpaController implements Serializable {

    private HibernateConfig hibernateConfig = null;
    
    public UsuarioJpaController() {
        this.hibernateConfig = new HibernateConfig();        
    }    

    public EntityManager getEntityManager() {
        return this.hibernateConfig.getEm();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            /*if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " ya existe.", ex);
            }*/
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    
    public Usuario findUsuario(String userName, String password) {
        EntityManager em = getEntityManager();
        try {            
            
            Usuario usuario = em.createQuery(
            "SELECT u from Usuario u WHERE u.usuario = :usuario and u.password = :password", Usuario.class).
            setParameter("usuario", userName).
            setParameter("password", password).getSingleResult();
            
            return usuario;
        }catch(NoResultException ex){
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public boolean isExisteUsuario(String userName){
            EntityManager em = getEntityManager();
        try {            
            
            Query query = em.createQuery(
            "SELECT COUNT(u) from Usuario u WHERE u.usuario = :usuario", Usuario.class).
            setParameter("usuario", userName);
            Long cantidad = (Long)query.getSingleResult();
            return cantidad > 0;
        }catch(NoResultException ex){
            return false;
        }
        finally {
            em.close();
        }
    }
    
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
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
