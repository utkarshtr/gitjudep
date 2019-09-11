package com.utkarsh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ReceiverServlet")
public class ReceiveServlet extends HttpServlet{


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      Connection connection=null;
	      
	      try {
	    	
	    	 Context iniCtx=new InitialContext();
	    	 Queue que=(Queue) iniCtx.lookup("java:/Zensar_queue");
	    	 Destination dest =(Destination) que;
	    	  
	    	 
	    	 QueueConnectionFactory qcf= (QueueConnectionFactory) iniCtx.lookup("java:/ConnectionFactory");//stwp1
	    	  connection =qcf.createConnection();//step2
	    	  Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//step3
	    	 
	    	  MessageConsumer consumer =session.createConsumer(dest);//step4
	    	  connection.start();
	    	  PrintWriter out = response.getWriter();
	    	  response.setContentType("text/html");
	  		out.println("<html><body>");
	  		
	  		while (true) {
	  		Message m=consumer.receive(1);
	  		if(m!=null) {
	  			if(m instanceof TextMessage) {
	  				TextMessage message= (TextMessage)m;
	  				out.println("reading message :"+ message.getText());
	  			}else {
	  				break;
	  			}
	  			
	  		}
	  		
	  			
	  			
	  			
	  			
	  		}
	    	  
	    	 
	  
	  		out.println("to send  message please<a href=mm.html>click here</a>");
	  		out.println("</body></html>");
	    	  
	      }catch(Exception e) {
	    	  System.out.println("Exception occured: " + e.toString());
	      }
	      finally {
	    	  try {
	    		  connection.close();
	    	  }catch(JMSException e) {
	    		  
	    		 e.printStackTrace();
	    	  }
		
	}

	}
}
	
	
	
	

