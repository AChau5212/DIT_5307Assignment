/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.web;

import admin.ejb.AdminManager;
import admin.entity.Admin;
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
public class AdminController implements Serializable {

    @EJB
    private AdminManager adminManager;
    private DataModel items;  // row data of all admins
    Admin admin;  // current admin
    boolean isLogin;
    boolean isPendingDeleteConfirmation;

    public AdminController() {
        items = null;
        isLogin = false;
        isPendingDeleteConfirmation = false;
    }
    
    public Admin getAdmin() {
        // Either there is a current admin or we're adding a new one
        if (admin == null) {
            admin = new Admin();
            
        }
        return admin;
    }
    
    public boolean isLogin() {
        return isLogin;
    }
    
    public boolean isIsPendingDeleteConfirmation() {
        return isPendingDeleteConfirmation;
    }
    
    public String save() {
        if (!isLogin) {
            adminManager.add(admin);
            JsfUtil.addSuccessMessage("Addition successful");
            return prepareList();
        } else {
//            adminManager.update(admin);
//            JsfUtil.addSuccessMessage("Update successful");
            return "List";
        }
    }
 
//    public String delete() {
//        adminManager.delete(admin);
//        admin = null;
//        isPendingDeleteConfirmation = false;
//        JsfUtil.addSuccessMessage("Deletion successful");
//        return prepareList();
//    }
    
    public DataModel getItems() {
        if (items == null) {  // data model need to be created
            items = new ListDataModel(adminManager.getAll());
        }
        return items;
    }
    
    
    public String prepareAdd() {
        admin = new Admin();
        return "Entry";
    }
    
    public String prepareList() {
        items = null;  // force data model to be recreated
        return "List";
    }
    
//    public String prepareView() {
//        admin = (admin)getItems().getRowData();
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
