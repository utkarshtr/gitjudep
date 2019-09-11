package com.manas;

import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MessageDriven
 */
@javax.ejb.MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
						propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
				@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "Zensar_queue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "Zensar_queue")
public class MessageDriven implements MessageListener {

    /**
     * Default constructor. 
     */
    public MessageDriven() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    // synchronus communication
    public void onMessage(Message message) {
        TextMessage tt= (TextMessage)message;
        try {
        	System.out.println("ReadingMessage"+tt.getText());
        }catch(JMSException e) {
        	e.printStackTrace();
        }
        
    }

}
