/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.CustomerFacade;
import entity.Customer;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author MyPC
 */
@Named(value = "adminBean")
@ManagedBean
@RequestScoped
public class AdminBean {
@Inject
private CustomerFacade customerFacade;
private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
   public List<Customer> doGetAllCustomer(){
       return customerFacade.findAll();
   }
   public void changeVerify(Integer customerID){
       
   }
}
