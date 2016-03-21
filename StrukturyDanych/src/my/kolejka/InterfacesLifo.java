package my.kolejka;

public interface InterfacesLifo <T> {
	
	boolean empty();
	T pop();
	void push(T data);
	int size();
	 Element<T> top();
	/** empty() zwraca true je¿eli stos jest pusty
		pop() usuwa element znajduj¹cy siê na szczycie stosu
		push() dodaje element na szczycie stosu
		size() zwraca liczbê elementów stosu
		top() zwraca referencjê do elementu na szczycie stosu**/
}
