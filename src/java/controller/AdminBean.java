/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.AccountFacade;
import data.CustomerFacade;
import entity.Account;
import entity.Customer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author MyPC
 */
@Named(value = "adminBean")
@ManagedBean
@SessionScoped
public class AdminBean {

    @Inject
    private CustomerFacade customerFacade;
    @Inject
    private AccountFacade accountFacade;
    private Customer customer = new Customer();
    private String mssg = "";
    private List<Customer> customers = new ArrayList<>();
    private String customerType ="customerID";
    private String searchString;
    private int change = 0;
    private Account account = new Account();
    private double transactionAmount = 0.0;
    private String action;
    private String accountType="accountID";
    private List<Account> accounts= new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }



    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Customer> getCustomers() {

        return customers;

    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> doGetAllCustomer() {
        return customerFacade.findAll();

    }
  public List<Account> doGetAllAccount() {
        return accountFacade.findAll();

    }
    public String changeVerify() {
        String customerIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID");
        Integer customerID = Integer.parseInt(customerIDString);
        customer.setCustomerID(customerID);
        customer = customerFacade.find(customer.getCustomerID());
        if (customer.getIsVerify() == 1) {
            customer.setIsVerify(0);
        } else {
            customer.setIsVerify(1);
        }
        customerFacade.edit(customer);
        return "/admin/managerCustomer.xhtml";
    }

    public String changeStatus() {
        String customerIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID");
        Integer customerID = Integer.parseInt(customerIDString);
        customer.setCustomerID(customerID);
        customer = customerFacade.find(customer.getCustomerID());
        if (customer.getIsStatus() == 1) {
            customer.setIsStatus(0);
        } else {
            customer.setIsStatus(1);
        }
        customerFacade.edit(customer);
        return "/admin/managerCustomer.xhtml";
    }

    public String quickUpdateCustomer() {
        String customerIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID");
        Integer customerID = Integer.parseInt(customerIDString);
        for (Customer c : customers) {
            if (c.getCustomerID() == customerID) {
                System.out.println(c.getCustomerPhone());
                customer = c;
            }
        }
        customerFacade.edit(customer);
        mssg = "Thông tin của khách hàng có Mã khách hàng " + customer.getCustomerID() + " đã được cập nhật";
        return "/admin/managerCustomer.xhtml";
    }

    public void doGetDetail() {
        mssg = "";
        change = 0;
        customer = customerFacade.find(customer.getCustomerID());
    }

    public String updateCustomer() {

        mssg = "Thông tin của khách hàng có Mã khách hàng " + customer.getCustomerID() + " đã được cập nhật";
        customerFacade.edit(customer);
        return "/admin/detail.xhtml";
    }

    public String doGetSearch(AjaxBehaviorEvent e) {
        change = 1;
        if ((customerType.equals("customerID")) || (customerType.equals("isVerify")) || (customerType.equals("isStatus"))) {
            Integer searchInt = Integer.parseInt(searchString);
            customers = customerFacade.getCustomerBySearchID(customerType, searchInt);
        } else if (customerType.equals("identityNumber")) {
            long searchLong = Long.parseLong(searchString);
            BigInteger searchInt = BigInteger.valueOf(searchLong);
            customers = customerFacade.getCustomerBySearchBigInt(customerType, searchInt);
        } else  {
            customers = customerFacade.getCustomerBySearch(customerType, searchString);
        }
        searchString = "";
        return "/admin/managerCustomer.xhtml";
    }
    
public void doGetSearchByAcc(AjaxBehaviorEvent e) {
        change = 1;
        if ((accountType.equals("accountID")) || (accountType.equals("isStatus"))) {
            Integer searchInt = Integer.parseInt(searchString);
            accounts = accountFacade.getAccountBySearchInt(accountType, searchInt);
        } else if (accountType.equals("acountAmount")) {
            long searchLong = Long.parseLong(searchString);
            BigDecimal searchInt = new BigDecimal(searchLong);
            accounts = accountFacade.getAccountBySearchBigInt(accountType, searchInt);
        } else  {
            accounts = accountFacade.getAccountBySearch(accountType, searchString);
        }
    }
    public String changeStatusAcc() {
        String customerIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("accountID");
        Integer accountID = Integer.parseInt(customerIDString);
        account.setAccountID(accountID);
        account = accountFacade.find(account.getAccountID());
        if (account.getIsStatus() == 1) {
            account.setIsStatus(0);
        } else {
            account.setIsStatus(1);
        }
        accountFacade.edit(account);
        return "/admin/datail.xhtml";
    }
    public void changeActionAcc(ValueChangeEvent e)
    {
      action = (String) e.getNewValue();
    }
    public String updateAcc() {
        System.out.println("dkm may "+ transactionAmount);
        if (transactionAmount > 0) {
            BigDecimal transAmout = new BigDecimal(transactionAmount);
            BigDecimal addAmount = account.getAcountAmount().add(transAmout);
            account.setAcountAmount(addAmount);
            accountFacade.edit(account);
             mssg= "Giao dịch thành công tài khoản "+ account.getAccountID()+" đã nạp thêm "+transAmout+ " đồng";
        } else {
             BigDecimal transAmout = new BigDecimal(transactionAmount);
            BigDecimal subAmount = account.getAcountAmount().add(transAmout);
            if (subAmount.compareTo(BigDecimal.ZERO) > 0) {
                account.setAcountAmount(subAmount);
                 accountFacade.edit(account);
                mssg= "Giao dịch thành công tài khoản "+ account.getAccountID()+" đã rút "+transAmout+ " đồng";
            } else {
                mssg = "Tài khoản không đủ tiền để thực hiên giao dịch";
            }
        }
        return "/admin/detail.xhtml";
    }
    public String addAccount(){
            account.setAccountID(0);
            accountFacade.create(account);
            return "/admin/detail.xhtml";
    }
    public void changeValueSelect(ValueChangeEvent e){
       String accountType = (String)e.getNewValue();
        for (Account acc : customer.getAccountList()) {
            if(acc.getAccountType().getAccountType().equals(accountType))
            {
                account=acc;
            }
        }
    }
    public void changeValueCustomer(AjaxBehaviorEvent e){
    }
    
     public void changeValueAccType(AjaxBehaviorEvent e){
    }
    public void delCustomer(){
         String customerIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerID");
        Integer customerID = Integer.parseInt(customerIDString);
         customer.setCustomerID(customerID);
         customerFacade.find(customer.getCustomerID());
         customerFacade.remove(customer);
         mssg= "Khách hàng đã được xóa khỏi hê thống";
    }
       public void delAccount(){
         String accountIDString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("accountID");
        Integer accountID = Integer.parseInt(accountIDString);
         account.setAccountID(accountID);
         accountFacade.find(account.getAccountID());
         accountFacade.remove(account);
         mssg= "Khách hàng đã được xóa khỏi hê thống";
    }
}
