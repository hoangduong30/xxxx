/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Util.MailUtilGmail;
import botdetect.web.jsf.JsfCaptcha;
import data.AccountFacade;
import data.CustomerFacade;
import data.InternalTranferLogFacade;
import entity.Account;
import entity.Customer;
import entity.InternalTranferLog;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;
import Util.convertMoney;

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
     @Inject
    private InternalTranferLogFacade internalTranferLogEJB;
    @Inject
    private AccountFacade accountEJB;
    private JsfCaptcha captcha;
    private String captchaCode;
    private String errorMsg = "";
    private Customer customer = new Customer();
    private int login = 0;
    private Account account = new Account();
    private Account account1 = new Account();
    private String phiType;
    private double amount;
    private InternalTranferLog internalTranferLog = new InternalTranferLog();
    private String mssg;
    private String emailOtpSend;
    private String emailOtpGet;
    private String amountString;

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }
    public String getEmailOtpSend() {
        return emailOtpSend;
    }

    public void setEmailOtpSend(String emailOtpSend) {
        this.emailOtpSend = emailOtpSend;
    }

    public String getEmailOtpGet() {
        return emailOtpGet;
    }

    public void setEmailOtpGet(String emailOtpGet) {
        this.emailOtpGet = emailOtpGet;
    }
    
    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }
    
    public InternalTranferLog getInternalTranferLog() {
        return internalTranferLog;
    }

    public void setInternalTranferLog(InternalTranferLog internalTranferLog) {
        this.internalTranferLog = internalTranferLog;
    }


    
    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhiType() {
        return phiType;
    }

    public void setPhiType(String phiType) {
        this.phiType = phiType;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

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
                            for (Account acc : customer.getAccountList()) {
                                if(acc.getAccountType().getAccountType().equals("normal")){
                                    account=acc;
                                }
                            }
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
    public String checkTranfer(){
        BigDecimal amountBig = new BigDecimal(amount);
        BigDecimal subAmount = account.getAcountAmount().subtract(amountBig);
        if(accountEJB.checkAccount(account1.getAccountID())){
            if(subAmount.compareTo(BigDecimal.ZERO)>0){
               account1= accountEJB.find(account1.getAccountID());
             account1.setAcountAmount(amountBig);
              account.setAcountAmount(subAmount);
             amountString = convertMoney.convertValue(amount);
               return  "/Customer/tranferInformation.xhtml";
            }else {mssg = "tài khoản quí khách không đủ thực hiên giao dịch";
             return  "/Customer/internalTranferMoney.xhtml";}
        }else {mssg = "tài khoản ghi có không tồn tài trong hệ thống";
         return  "/Customer/internalTranferMoney.xhtml";}
        
    }
    public String checkMailSecret()
    {
        if(emailOtpSend.equals(emailOtpGet))
        {
            accountEJB.edit(account);
            accountEJB.edit(account1);
            return "completeTranfer.xhtml";
        }else{
            mssg = "Mã OTP không khớp";
             emailOtpSend = UUID.randomUUID().toString();
            return "mailSecret.xhtml";
        }
    }
    public String confirmTranfer(){
               if(validateCaptcha()){
                emailOtpSend = UUID.randomUUID().toString();
                String to = customer.getCustomerEmail();
                String from = "ginmafia.dn@gmail.com";
                String subject = "Xác thực giao dịch";
                String body = "Mã xác nhận giao dịch của bạn là: " + emailOtpSend;
                boolean bodyIsHTML = false;
                try {
                    MailUtilGmail.sendMail(to, from, subject, body, bodyIsHTML);
                } catch (MessagingException e) {
                    System.out.println(e);
                }
               return "/Customer/mailSecret.xhtml";
               }
              return "/Customer/internalTranferMoney.xhtml";
    }
 
}
