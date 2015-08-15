package com.easylearnjava.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	public Session getSession(){
		
		Session session = null;
		SessionFactory sf = null;
		
		try{
			
			Configuration cfg = new Configuration();
			cfg.configure("hibernate-cfg.xml");
			
			ServiceRegistryBuilder srb = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
			sf = cfg.buildSessionFactory(srb.buildServiceRegistry());
			
			session = sf.openSession();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return session;
	}
	
	
}
