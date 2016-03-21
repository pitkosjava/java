package my.lista;

public final class ListaJednoKierunkowa<T> implements InterfejLista<T> {

	private Element<T> pierwszy;
	private Element<T> ostatni;
	
	

	public ListaJednoKierunkowa() {
		pierwszy=null;
		ostatni=null;
		
	}

	
	// dodaje element na pocz¹tku listy
	@Override
	public void push_front(T data) {
		
		Element<T> front = new Element<T>(data,pierwszy);
		if (nullIs()) {
			pierwszy = front;
			ostatni = front;
		} else {
			pierwszy = front;
		}
	}

	// dodaje element na koñcu listy
	@Override
	public void push_back(T data) {
		Element<T> front = new Element<T>(data,ostatni);
		if (nullIs()) {
			pierwszy = front;
			ostatni = front;
		} else {
			ostatni = front;
		}
	}

	private boolean nullIs() {
		return pierwszy == null && ostatni == null;
	}

	@Override
	public void insert(int index, T datas) {
		// TODO Auto-generated method stub

	}

	@Override
	public T pop_front() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T pop_back() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		int count = 0;
		Element<T> index = ostatni;
		while (index != null) {
			count++;
		
			index=index.getParent();
		}

		return count;
	}

	@Override
	public int max_size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		System.out.println("Start");
		InterfejLista<String> add = new ListaJednoKierunkowa<String>();
		add.push_back("1");
		add.push_back("2");
		add.push_back("3");
		add.push_back("4");
		add.push_back("5");
		System.out.println("Wynik "+add.size());
	}

}
