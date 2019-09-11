package com.utkarsh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.Queue;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
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

@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginRegister() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      Connection connection=null;
	      
	      try {
	    	 String ss= request.getParameter("message") ;
	    	 Context iniCtx=new InitialContext();
	    	 Queue que=(Queue) iniCtx.lookup("java:/Zensar_queue");
	    	 Destination dest =(Destination) que;
	    	  
	    	 
	    	 QueueConnectionFactory qcf= (QueueConnectionFactory) iniCtx.lookup("java:/ConnectionFactory");//stwp1
	    	  connection =qcf.createConnection();//step2
	    	  Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//step3
	    	  MessageProducer producer =session.createProducer(dest);//step4
	    	  TextMessage message = session.createTextMessage(ss);//step5
	    	  System.out.println("Sending message: " + message.getText());
	    	  producer.send(message);//step6
	    	  PrintWriter out = response.getWriter();
	    	  response.setContentType("text/html");
	  		out.println("<html><body>");
	  		out.println("message"+message.getText()+"sent successfully");
	  		out.println("to receive message please<a href=ReceiveServlet>click here</a>");
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
