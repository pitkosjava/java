package test.generyczne;

public class OsobaNew extends Osoba<String> {
	
	
	public void printMy(String data){
		System.out.println("Osoba nowa  "+data);
	}
	public static void main(String[] args) {
		OsobaNew osoba= new OsobaNew();
		osoba.printMy("  helo");
		
		Osoba<String> nowa2=osoba;
		nowa2.printMy("   helo");
	}
	
}
