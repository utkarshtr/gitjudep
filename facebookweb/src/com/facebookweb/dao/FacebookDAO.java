package com.facebookweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.facebookweb.entity.FacebookEmployee;

public class FacebookDAO implements FacebookDAOInterface{
	private FacebookDAO(){}

	public static FacebookDAOInterface createDaoObject(String string) {
		// TODO Auto-generated method stub
		return new FacebookDAO();
	}

	@Override
	public int createProfileDao(FacebookEmployee fe) {
		int i=0;
		try{
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Zensar123");
			//for connecting the particular serverc
			
			Properties props=new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(Context.PROVIDER_URL, "localhost:8080");
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			
			DataSource ds;
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:/OracleDS");
			Connection con = ds.getConnection();
			
			PreparedStatement ps=con.prepareStatement("insert into facebookemployee values(?,?,?,?)");
			ps.setString(1, fe.getName());
			ps.setString(2, fe.getPass());
			ps.setString(3, fe.getEmail());
			ps.setString(4, fe.getAddress());
			i=ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

}
