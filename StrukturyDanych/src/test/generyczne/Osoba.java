package test.generyczne;

public class Osoba <T>{

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Osoba(T data) {
		this.data = data;
	}

	public Osoba() {
		
	}
	
	public void printMy(T data){
		System.out.print("Osoba -"+data);
	}
	
	
}
