package my.kolejka;

/**
 * Implementacja wewnetrzna est ukryta
 ***/
public class MyFifo<T> implements InterfcesFifo<T> {

	private Element<T> glowa;
	private Element<T> ogon; // inaczej rekursywanie albo w petli przemierzanie
								// i dodanie tam gdzie nie ma

	//
	public MyFifo() {
		glowa = null;
		ogon = glowa;
	}

	@Override
	public void push(T data) {
		if(glowa==null){
			/* Pierwszy start na wszystko*/
			Element<T> zmienna = new Element<T>(data, null);
			glowa = zmienna;
			ogon = glowa;
		}
		else if(glowa.getParent()==null){
			/* tu podmiana ustawiamy glowe by miala dostep do dlaszych tworzymu nowy*/
			Element<T> zmienna = new Element<T>(data, null);
			/* Ustawiamy dziecko glowie by moc sie odwolywac*/
			glowa.setParent(zmienna);
			/* Ustawiamy ogon by nie przemierzac calej koleji w lifo jest to inazej bo zawsze najnowszy piererszy*/
			ogon = zmienna;
		}
		else{
			/** Z poprzednch zawsze juz mamy dostep**/
			Element<T> zmienna = new Element<T>(data, null);
			/* aktualnemu otatniemu przypisujemy nowe dziecko*/
			ogon.setParent(zmienna);
			/* ustawimy dziecko jako ostatne z*/
			ogon=zmienna;
		}
	
	}

	@Override
	public T pop() {
		if(glowa!=null){
			Element<T> aglowa = glowa;
			glowa = glowa.getParent();
			return aglowa.getData();
		}
		return null;
		
	}

	public static void main(String[] args) {
		
		MyFifo<String> kolejka= new MyFifo<String>();
		
		kolejka.push("Piotr");
		kolejka.push("Kosmala");
		kolejka.push("Kosm3ala");
		kolejka.push("Kosma3la");
		kolejka.push("Kosm4ala");
		
		System.out.println(kolejka.pop());
		System.out.println(kolejka.pop());
		System.out.println(kolejka.pop());
		System.out.println(kolejka.pop());
		System.out.println(kolejka.pop());
		

	}

}
