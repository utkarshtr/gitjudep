package com.facebookweb.service;

import com.facebookweb.dao.FacebookDAO;
import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookEmployee;

public class FacebookService implements FacebookServiceInterface{
	
	private FacebookService(){}

	public static FacebookServiceInterface createServiceObject(String string) {
		// TODO Auto-generated method stub
		return new FacebookService();
	}

	@Override
	public int createProfileService(FacebookEmployee fe) {
		FacebookDAOInterface fs = FacebookDAO
				.createDaoObject("f");
		int i = fs.createProfileDao(fe);
		return i;
	}

}
