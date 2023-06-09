package management.dao;

import management.entity.Account;
import management.entity.Customer;


public interface IAccountDao {
	
	
	Account checkEmail(String em);
	public void addAccount (Account account);
	int deleteAccount(String em);
	public Account getSingleAccount(String email); 
	public void updateAccount(Account account);
	public Account getSingleAccount(String email,String pass); 
	public Account getSingleAccountNoPass(String email); 
	
}
