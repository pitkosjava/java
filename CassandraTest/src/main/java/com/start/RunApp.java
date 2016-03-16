package com.start;

import org.apache.commons.lang3.RandomStringUtils;

import pit.kos.cassandra.main.Menadzer;
import pit.kos.cassandra.utils.ContainerEntityMapping;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.encje.MojaEncja;
import com.encje.MojaEncjaDruga;
public class RunApp {
	
	public static void main(String[] args) throws Exception {
	    //insetJDBCCassandra(args,100);
	    //new LoginCRUD().insertLogin(new Login());
		Menadzer manadzer=new Menadzer();
		manadzer.open();
		// dwie encje
		long start,stop;
	
		MojaEncjaDruga druga= new MojaEncjaDruga();
		start= System.currentTimeMillis();
		
		MojaEncja mojaencja=(MojaEncja) manadzer.find(MojaEncja.class, 0);
			//mojaencja.setId_encja(0);
			//mojaencja.setEmail(RandomStringUtils.randomAlphabetic(10));
		//	manadzer.persist(mojaencja);
			System.out.println("Startowa"+mojaencja.toString());
			
			 stop=System.currentTimeMillis();
				System.out.println("Czas moj 2 " +(start-stop));
			
		/*	mojaencja.setEmail("((((((");
			System.out.println("Po zmianie"+mojaencja.toString());
			manadzer.refresh(mojaencja);
			System.out.println("Refresh "+mojaencja.toString());*/
			//manadzer.remove(mojaencja);
		
	
		
		
		/*Session sesja=manadzer.getSession();
		ContainerEntityMapping em=manadzer.getCoontainer(mojaencja);
		 em.setSession(sesja);
		 
		Mapper<MojaEncja> mapper= new	MappingManager(sesja).mapper(MojaEncja.class);
		//Mapper<MojaEncjaDruga> mapper2= new	MappingManager(sesja).mapper(MojaEncjaDruga.class);
		
	
		start= System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			mojaencja.setId_encja(i);
			mojaencja.setEmail(RandomStringUtils.randomAlphabetic(10));
			
			mapper.save(mojaencja);
			
		}
		stop=System.currentTimeMillis();
		System.out.println("orginalny " +(start-stop));
		
	  
		
		
		
				 start= System.currentTimeMillis();
					
					for(int i=0;i<10000;i++){
						mojaencja.setId_encja(i);
						mojaencja.setEmail(RandomStringUtils.randomAlphabetic(10));
						em.persistObject(mojaencja);
					}
				
					 stop=System.currentTimeMillis();
					System.out.println("Czas moj 1" +(start-stop));
					  start= System.currentTimeMillis();
						
						for(int i=0;i<10000;i++){
							mojaencja.setId_encja(i);
							mojaencja.setEmail(RandomStringUtils.randomAlphabetic(10));
							em.persistObject(mojaencja);
						}
					
						 stop=System.currentTimeMillis();
						System.out.println("Czas moj 1" +(start-stop));
	
			
		*/
			
		
		
		
		
		
		
	
		
	
		
		
		
		/*Login as=new Login();
		as.setHaslo("12345678");
		as.setEmail("nowe@wp.pl");
		as.setId_login("131234a6789");
		as.setData_rejestracji(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		as.setKod_weryfikacji("1234567890");
		as.setRola("USER");*/
		
		
		
		
		
		manadzer.close();
	}

}
