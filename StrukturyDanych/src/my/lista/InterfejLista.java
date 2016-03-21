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
	 * push_front() dodaje element na pocz¹tku listy
		push_back() dodaje element na koñcu listy
		insert() dodaje element we wskazanym miejscu listy
		pop_front() usuwa element z pocz¹tku listy
		pop_back() usuwa element z koñca listy
		size() zwraca liczbê elementów na liœcie
		max_size() zwraca maks. liczbê elementów jakie mo¿e zmieœciæ lista
		empty() sprawdza czy lista jest pusta
		remove() usuwa z listy wszystkie elementy maj¹ce dan¹ wartoœæ
		sort() uk³ada elementy na liœcie rosn¹co
		reverse() odwraca kolejnoœæ elementów na liœcie**/
}
