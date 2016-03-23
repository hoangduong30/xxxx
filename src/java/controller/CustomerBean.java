/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Util.MailUtilGmail;
import botdetect.web.jsf.JsfCaptcha;
import data.CustomerFacade;
import entity.Customer;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;

/**
 *
 * @author MyPC
 */
@Named(value = "customerBean")
@ManagedBean
@SessionScoped
public class CustomerBean {

    @Inject
    private CustomerFacade customerEJB;
    private JsfCaptcha captcha;
    private String captchaCode;
    private String errorMsg = "";
    private Customer customer = new Customer();
    private int login = 0;


    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public JsfCaptcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }

    public String submit() {
        if (validateCaptcha()) {
            if (validateLogin()) {
                if (customerEJB.checkVerify(customer.getUserName())) {
                    if (customerEJB.checkStatus(customer.getUserName())) {
                        login = 1;
                        if (customerEJB.findbyUserName(customer.getUserName()) != null) {
                            customer = customerEJB.findbyUserName(customer.getUserName());
                        }
                        return "/index.xhtml";
                    } else {
                        errorMsg = "Tài khoản đã bị khóa xin vui lòng liên hệ tại chi nhánh ngân hàng!";
                        return "/Customer/loginPage.xhtml";
                    }
                } else {
                    errorMsg = "Tài khoản chưa xác thực vui lòng xác thực!";
                    return "/Customer/loginPage.xhtml";
                }
            } else {
                errorMsg = "Mật khẩu hoặc tên đăng nhập không đúng!";
                return "/Customer/loginPage.xhtml";
            }
        } else {
            return "/Customer/loginPage.xhtml";
        }
    }

    public boolean validateLogin() {
        if (customerEJB.checkUser(customer.getUserName(), customer.getUserPass())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateCaptcha() {
        if (!this.captcha.validate(captchaCode)) {
            errorMsg = "Invalid Captcha code!";
            return false;
        }
        return true;
    }

    public String checkLogout() {
        this.customer = new Customer();
        this.login = 0;
        return "/index.xhtml";
    }
}
