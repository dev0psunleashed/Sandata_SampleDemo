package com.sandata.one.drools.bean;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.sandata.one.drools.utils.SandataConstants;



public class JmsMessageSender {
	
	private static Logger logger = LoggerFactory.getLogger(JmsMessageSender.class);
	private JmsTemplate jmsTemplate;

	private static JmsMessageSender instance = new JmsMessageSender();
	
	private JmsMessageSender(){
		logger.info("init JmsMessageSender");
		
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(SandataConstants.AMQ_USER_NAME_VALUE, 
				SandataConstants.AMQ_PASSWORD_VALUE, SandataConstants.AMQ_URI_VALUE);
		
		//failover:(tcp://dev-lab-amq01:61616,tcp://dev-lab-amq02:61616) //for dev
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
		
	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(pooledConnectionFactory);
		
	    this.jmsTemplate = new JmsTemplate(cachingConnectionFactory);
	}
	
	
	 public static JmsMessageSender getInstance(){
		 logger.info("get instance of JmsMessageSender");
	     return instance;
	 }
	    

	/**
	   * send text to default destination
	   * @param text
	   */
	  public void send(final String text) {
	   
		this.jmsTemplate.send(new MessageCreator() {
	      public Message createMessage(Session session) throws JMSException {
	        Message message = session.createTextMessage(text);     
	        //set ReplyTo header of Message, pretty much like the concept of email.
	        message.setJMSReplyTo(new ActiveMQQueue("Recv2Send"));
	        return message;
	      }
	    });
	  }
	    
	  /**
	   * Simplify the send by using convertAndSend
	   * @param text
	   */
	  public void sendText(final String text) {
		  this.jmsTemplate.convertAndSend(text);
	  }
	    
	  /**
	   * Send text message to a specified destination
	   * @param text
	   */
	  public void send(final Destination dest,final String text) {
	      
		  this.jmsTemplate.send(dest,new MessageCreator() {
	      public Message createMessage(Session session) throws JMSException {
	        Message message = session.createTextMessage(text);
	        return message;
	      }
	    });
	  }
	  
	  public void sendObject(final Destination dest,final Object object) {
	      
		  this.jmsTemplate.send(dest,new MessageCreator() {

			  public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				Message message = session.createObjectMessage((Serializable) object);
				return message;
			}
		    });
		    
		    logger.info("already sent message to fuse " );
		  }
}
