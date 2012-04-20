package Clases;

import java.sql.*;
import dbcp.ConnectionManager;
import java.util.ArrayList;

public class Customer {

    private int _customerID;
    private String _firstName;
    private String _lastName;
    private String _address;
    private String _email;
    private String _phone;

    public Customer(int _customerID, String _firstName, String _lastName, String _address, String _email, String _phone) {
        this._customerID = _customerID;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._address = _address;
        this._email = _email;
        this._phone = _phone;
    }

    public int getCustomerID() {
        return this._customerID;
    }

    public void setCustomerID(int aCustomerID) {
        this._customerID = aCustomerID;
    }

    public String getFirstName() {
        return this._firstName;
    }

    public void setFirstName(String aFirstName) {
        this._firstName = aFirstName;
    }

    public String getLastName() {
        return this._lastName;
    }

    public void setLastName(String aLastName) {
        this._lastName = aLastName;
    }

    public String getAddress() {
        return this._address;
    }

    public void setAddress(String aAddress) {
        this._address = aAddress;
    }

    public String getEmail() {
        return this._email;
    }

    public void setEmail(String aEmail) {
        this._email = aEmail;
    }

    public String getPhone() {
        return this._phone;
    }

    public void setPhone(String aPhone) {
        this._phone = aPhone;
    }
    /**
     * Method that returns an object cus from customer that receives as paramater _customerID
     * @param _customerID that contains a primary key from customer
     * @return object cus of type Customer
     * @throws SQLException  
     */
    public static Customer getCustomer(int _customerID) throws SQLException{
            ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Customer_GroupNo", "CustomerID= "+_customerID);
            if(rs.next()){
            Customer cus = new Customer(rs.getInt("CustomerId"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("Email"), rs.getString("Phone"));
            return cus;
            }else{
                return null;
            }
    }


    /**
     * Method that returns an object cus from customer that receives as paramater _customerID
     * @param _email to take email
     * @return object cus of type Customer
     * @throws SQLException  
     */	
    public static Customer getCustomer(String _email) throws SQLException{
            ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Customer_GroupNo", "Email= "+_email);
            if(rs.next()){
            Customer cus = new Customer(rs.getInt("CustomerId"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("Email"), rs.getString("Phone"));
            return cus;
            }else{
                return null;
            }
    }
    
    public static Customer[] getCustomers() throws SQLException {
            ConnectionManager.init();
            ResultSet rs = ConnectionManager.selectAllColumns("Tbl_Customer_GroupNo", "");
            Customer[] customers = null;
            ArrayList<Customer> cL = new ArrayList<Customer>();
            while(rs.next()){
                Customer c = new Customer(rs.getInt("CustomerId"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("Email"), rs.getString("Phone"));
                cL.add(c);
            }
            if(cL.size()>0){
                customers = new Customer[cL.size()];
                cL.toArray(customers);
            }
            return customers;
    }
}