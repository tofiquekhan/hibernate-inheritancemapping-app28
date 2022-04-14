package myproject.inheritancemapping.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import myproject.inheritancemapping.entity.EmployeeAccount;
import myproject.inheritancemapping.entity.StudentAccount;

public class Test {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/myproject/inheritancemapping/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			StudentAccount sa = new StudentAccount();
			sa.setAccNo("a111");
			sa.setAccName("AAA");
			sa.setAccType("Saving");
			sa.setSid("S-111");
			sa.setSbranch("CS");
			sa.setSmarks(88);
			
			EmployeeAccount ea = new EmployeeAccount();
			ea.setAccNo("a222");
			ea.setAccName("BBB");
			ea.setAccType("Saving");
			ea.setEid("E-111");
			ea.setEsal(5000);
			ea.setEaddr("Indore");
			
			String spk = (String)session.save(sa);
			String epk = (String)session.save(ea);
			System.out.println("Student Primary Key : "+spk);
			System.out.println("Employee Primary Key : "+epk);
			tx.commit();
			
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}

}
