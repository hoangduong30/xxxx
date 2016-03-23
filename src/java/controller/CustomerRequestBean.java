/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Util.MailUtilGmail;
import data.CustomerFacade;
import entity.Customer;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;

/**
 *
 * @author MyPC
 */
@Named(value = "customerRequestBean")
@ManagedBean
@RequestScoped
public class CustomerRequestBean {

    @Inject
    private CustomerFacade customerEJB;
    private Customer customer = new Customer();
    private String regMsg = "";

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRegMsg() {
        return regMsg;
    }

    public void setRegMsg(String regMsg) {
        this.regMsg = regMsg;
    }

    public void addCustomer() {
        if (!customerEJB.checkID(customer.getIdentityNumber())) {
            if (!customerEJB.checkExistUserName(customer.getUserName())) {
                customer.setCustomerID(0);
                customer.setIsVerify(0);
                customer.setIsStatus(1);
                Date date = new Date();
                customer.setDateRegister(date);
                customerEJB.create(customer);
                regMsg = "Chúc mừng quí khách đã đăng kí thành công vui lòng check mail để kích hoạt !";
                String to = customer.getCustomerEmail();
                String from = "ginmafia.dn@gmail.com";
                String subject = "Check email";
                String body = "http://localhost:8080/Project2_hoang/faces/Customer/thanks.xhtml?customerEmail=" + to;
                boolean bodyIsHTML = false;
                try {
                    MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
                } catch (MessagingException e) {
                    System.out.println(e);
                }
            } else {
                regMsg = "Tài khoản này đã tồn tại vui lòng chọn tên khác !";
            }
        } else {
            regMsg = "Số chứng minh nhân dân đã được đăng kí !";
        }
    }

    public void doSetVerify() {
        customer = customerEJB.findbyCustomerEmail(customer.getCustomerEmail());
        customer.setIsVerify(1);
        customerEJB.edit(customer);
        regMsg = "Chúc mừng " + customer.getCustomerName() + " đã xác nhận thành công!";
    }
}
