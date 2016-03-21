package my.kolejka;

public interface InterfacesLifo <T> {
	
	boolean empty();
	T pop();
	void push(T data);
	int size();
	 Element<T> top();
	/** empty() zwraca true je�eli stos jest pusty
		pop() usuwa element znajduj�cy si� na szczycie stosu
		push() dodaje element na szczycie stosu
		size() zwraca liczb� element�w stosu
		top() zwraca referencj� do elementu na szczycie stosu**/
}
