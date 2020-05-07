/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.web;

import customer.ejb.CustomerManager;
import customer.entity.Customer;
import customer.web.util.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author johnsonwu
 */
@Named
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private CustomerManager customerManager;
    private DataModel items;  // row data of all customers
    Customer customer;  // current customer
    boolean isNew;  // true if adding a new customer
    boolean isPendingDeleteConfirmation;

    public CustomerController() {
        items = null;
        isNew = false;
        isPendingDeleteConfirmation = false;
    }
    
    public Customer getCustomer() {
        // Either there is a current customer or we're adding a new one
        if (customer == null) {
            customer = new Customer();
            isNew = true;
        }
        return customer;
    }
    
    public boolean isIsNew() {
        return isNew;
    }
    
    public boolean isIsPendingDeleteConfirmation() {
        return isPendingDeleteConfirmation;
    }
    
    public String save() {
        if (isNew) {
            customerManager.add(customer);
            JsfUtil.addSuccessMessage("Addition successful");
            return prepareAdd();
        } else {
            customerManager.update(customer);
            JsfUtil.addSuccessMessage("Update successful");
            return "View";
        }
    }
 
    public String delete() {
        customerManager.delete(customer);
        customer = null;
        isPendingDeleteConfirmation = false;
        JsfUtil.addSuccessMessage("Deletion successful");
        return prepareList();
    }
    
    public DataModel getItems() {
        if (items == null) {  // data model need to be created
            items = new ListDataModel(customerManager.getAll());
        }
        return items;
    }
    
    
    public String prepareAdd() {
        customer = new Customer();
        isNew = true;
        return "Entry";
    }
    
    public String prepareList() {
        items = null;  // force data model to be recreated
        return "List";
    }
    
    public String prepareView() {
        customer = (Customer)getItems().getRowData();
        isNew = false;  // prepare for possible edit
        return "View";
    }
    
    public String prepareEdit() {
        customer = (Customer)getItems().getRowData();
        isNew = false;
        return "Entry";
    }
    
    public String prepareDelete() {
        isPendingDeleteConfirmation = true;
        JsfUtil.addWarningMessage("Do you want to delete this data permanently?");
        return prepareView();
    }
    
    public String cancelDelete() {
        isPendingDeleteConfirmation = false;
        return "List";
    }
}
