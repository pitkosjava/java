package com.my.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestOsoba {
	/**serializacja zastępuje adresy pamięci numerami seryjnymi, możemy transportować
		zbiory danych z jednej maszyny na drugą**/
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
		
		Osoba osoba= new Osoba("Piotr", "Kosmala");
		Pracownik pracownik= new Pracownik("Marek", "Berlecki");
		pracownik.setWyplta(1000.00);
		
		Pracownik pracownik2= new Pracownik("Monika", "Berlecki");
		
		Szef szef = new Szef("Szef1", "nowy");
		szef.setAsystent(pracownik);
		Szef sze2f = new Szef("Szef2", "nowy");
		szef.setAsystent(pracownik);

		
			out.writeObject(osoba);
			out.writeObject(pracownik);
			out.writeObject(pracownik2);
			out.writeObject(szef);
			out.writeObject(sze2f);
			
		out.close();
		
			System.out.println();
			System.out.println();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"));
		
		Osoba osoba1=(Osoba)in.readObject();
		Osoba osoba2=(Pracownik)in.readObject();
		
			System.out.println(osoba1.toString());
			System.out.println(osoba2.toString());
			
		in.close();
		
		/**strumień obiektów zapisuje typy i pola składowe wszystkich obiektów,
		 każdemu obiektowi zostaje przypisany numer seryjny,
		 powtarzające się odwołania do tego samego obiektu są przechowywane jako
		referencje jego numeru seryjnego.*/
		
	}

}
