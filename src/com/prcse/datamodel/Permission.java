package com.prcse.datamodel;

import java.util.ArrayList;

public class Permission {
	
	private ArrayList<Account> accounts;
	private String name;
	
	public Permission(String name)
	{
		this.accounts = new ArrayList<Account>();
		this.name = name;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void addAccount(Account account)
	{
		if (accounts == null)
		{
			accounts = new ArrayList<Account>();
		}
		
		accounts.add(account);
	}
	
	public void removeAccount(Account account)
	{
		accounts.remove(account);
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
