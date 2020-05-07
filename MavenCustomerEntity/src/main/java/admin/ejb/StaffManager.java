/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.ejb;

import admin.entity.Staff;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johnsonwu
 */
@Stateless
public class StaffManager {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    public void add(Staff staff) {
        
        em.persist(staff);
    }
    
    
    
//    public Customer update(Customer customer) {
//        return em.merge(customer);
//    }
//    
//
//    public void delete(Customer customer) {
//        em.remove(em.merge(customer));  // entity must be managed to be removed
//    }
    
    public List<Staff> getAll() {
        return em.createNamedQuery("findAllUser", Staff.class).getResultList();
    }
    
    public List<Staff> getUser(Staff staff){
        
       return em.createNamedQuery("findUser", Staff.class)
                .setParameter("username", staff.getUsername())
                .setParameter("password", staff.getPassword())
                .getResultList();
    }
}
