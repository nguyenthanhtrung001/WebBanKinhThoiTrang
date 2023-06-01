package management.dao;

import java.util.List;

import management.entity.Account;
import management.entity.Customer;


public interface ICustomerDao {
	Customer createCustomer(Customer customer);

	Long getNumberOfCustomer();

	List<Customer> getListCustomer();
	public boolean addCustomer(Customer customer,Account account);
	
	Customer getCustomerByEmail(String email);
	void updateCustomer(Customer customer);

}
