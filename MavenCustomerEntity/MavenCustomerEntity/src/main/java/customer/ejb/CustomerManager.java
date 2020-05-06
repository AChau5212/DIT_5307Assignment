/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.ejb;

import customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johnsonwu
 */
@Stateless
public class CustomerManager {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    public void add(Customer customer) {
        em.persist(customer);
    }
    
    public Customer update(Customer customer) {
        return em.merge(customer);
    }
    

    public void delete(Customer customer) {
        em.remove(em.merge(customer));  // entity must be managed to be removed
    }
    
    public List<Customer> getAll() {
        return em.createNamedQuery("findAllCustomer", Customer.class).getResultList();
    }
}
