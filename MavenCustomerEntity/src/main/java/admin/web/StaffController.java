/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.web;

import admin.ejb.StaffManager;
import admin.entity.Staff;
import admin.web.*;
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
public class StaffController implements Serializable {

    @EJB
    
    private StaffManager staffManager;
    private DataModel items, user;  // row data of all admins
    Staff staff;  // current user
    boolean isLogin;
//    boolean isPendingDeleteConfirmation;

    public StaffController() {
        items = null;
        isLogin = false;
//        isPendingDeleteConfirmation = false;
    }
    
    public Staff getStaff() {
        // Either there is a current admin or we're adding a new one
        if (staff == null) {
            staff = new Staff();
            
        }
        return staff;
    }
    
    public boolean isLogin() {
        
        return isLogin;
    }
  
    public String save() {
       staffManager.add(staff);
       if(!isLogin){
           return prepareAdd();
       }
       return prepareList();
    }
    
    public DataModel getItems() {
  
        items = new ListDataModel(staffManager.getAll());
        
        return items;
    }
    
    public String login(){
        if(staffManager.getUser(staff).isEmpty()){
            return prepareAdd();
        }
        return prepareList();
    }

//    public Boolean getValidUser(){
//
//        if(staffManager.getUser(staff) != null){
//            isLogin = true;
//            return true;
//        }
//        isLogin = false;
//        return false;
//    }

    
    
    public String prepareAdd() {
        staff = new Staff();
        return "AdminLogin";
    }
    
    public String prepareList() {
        items = null;  // force data model to be recreated
        staff = null;
        return "List";
    }
 
//    public String delete() {
//        adminManager.delete(admin);
//        admin = null;
//        isPendingDeleteConfirmation = false;
//        JsfUtil.addSuccessMessage("Deletion successful");
//        return prepareList();
//    }
    

    
//    public String prepareView() {
//        admin = (Admin)getItems().getRowData();
//        isLogin = false;  // prepare for possible edit
//        return "View";
//    }
//    
//    public String prepareEdit() {
//        admin = (admin)getItems().getRowData();
//        isLogin = false;
//        return "Entry";
//    }
//    
//    public String prepareDelete() {
//        isPendingDeleteConfirmation = true;
//        JsfUtil.addWarningMessage("Do you want to delete this data permanently?");
//        return prepareView();
//    }
//    
//    public String cancelDelete() {
//        isPendingDeleteConfirmation = false;
//        return "List";
//    }
}
