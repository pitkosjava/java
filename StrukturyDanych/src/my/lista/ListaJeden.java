package my.lista;

public class ListaJeden <T>  {
	
	private  Element<T> glowa;
	private  Element<T> ogon;

	
	public ListaJeden(){
		glowa=null;
		ogon=glowa;
	}
	
	public void dodajZPrzodu(T data){
		Element<T> element= new Element<T>(data,glowa);
		if(ogon==null){
			ogon=element;
		}
		glowa=element;
	}
	
	
	public T pobierzZgory(){
		return glowa.getData();
	}
	
	public void dodajZTylu(T data){
		/** Nowy element*/
		Element<T> element= new Element<T>(data,null);
		/* Aktualnie ostatniemu przypsujemy nowy**/
		if(ogon==null){
			ogon=element;
			if(glowa==null){
				 glowa=ogon;	
			}
		}
		else {
			ogon.setParent(element);
			ogon=element;
		}
	}

	
	public void wypisz(){
		 Element<T> index=glowa;
		 while(index!=null){
			 System.out.println(index.getData());
			 index=index.getParent();
		 }
		
	}
	public void insert(int index,T datas){
		/** Najprostszy przypadek **/
		if(index==0){
				 Element<T> temp= new Element<>(datas,glowa);
				 glowa=temp;
		}
		/** Teraz pobieramy elementy ale zawsze jest nastepny i pamietamy ze zmiana polega na historycznym przypisaniu tam
		 * wstawic nowe dziecko a nowe dziecko zawiera element */ 
		else if(index<=size()){
			 int count=0;
			 Element<T> element=glowa;
			 Element<T> history=null;
			 while(element!=null){
				 if(count==index){
					 // 1           3
					 // 1   2el     3
					 Element<T> temp= new Element<>(datas,element);
					 history.setParent(temp);
				 }
				 history=element;
				 element=element.getParent();
				count++;
			 }
		}
	}
	
	public int size(){
		 Element<T> index=glowa;
		 int count=0;
		 while(index!=null){
			 index=index.getParent();
			 count++;
		 }
		 return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaJeden<String> list = new ListaJeden<>();
		list.dodajZTylu("1"); //1012
		list.dodajZTylu("2");
		list.dodajZTylu("4");
		list.dodajZTylu("4");
		
		list.wypisz();
		System.out.println("Koniec");
		list.insert(8,"AA");
		list.wypisz();
		
		String napis=list.pobierzZgory();
	}

}
