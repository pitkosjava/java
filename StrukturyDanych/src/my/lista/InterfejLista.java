package my.lista;

public interface InterfejLista <T> {

	void push_front(T data);
	void push_back(T data);
	void insert(int index,T datas);
	T pop_front();
	T pop_back();
	int size();
	int max_size();
	boolean empty();
	void remove(T data);
	void sort();
	void reverse();
	
	/**
	 * push_front() dodaje element na pocz�tku listy
		push_back() dodaje element na ko�cu listy
		insert() dodaje element we wskazanym miejscu listy
		pop_front() usuwa element z pocz�tku listy
		pop_back() usuwa element z ko�ca listy
		size() zwraca liczb� element�w na li�cie
		max_size() zwraca maks. liczb� element�w jakie mo�e zmie�ci� lista
		empty() sprawdza czy lista jest pusta
		remove() usuwa z listy wszystkie elementy maj�ce dan� warto��
		sort() uk�ada elementy na li�cie rosn�co
		reverse() odwraca kolejno�� element�w na li�cie**/
}
