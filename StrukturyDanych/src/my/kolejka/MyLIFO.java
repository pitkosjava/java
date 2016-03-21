package my.kolejka;

public class MyLIFO<T> implements InterfacesLifo<T> {

	private Element<T> element;

	public MyLIFO() {
		this.element = new Element<T>(null, null);
	}

	@Override
	public boolean empty() {
		return element.getParent() == null;
	}

	@Override
	public T pop() {
		if (!empty()) {
			Element<T> data = element;
			element = element.getParent();
			return data.getData();
		}
		return null;
	}

	@Override
	public void push(T data) {
		this.element = new Element<T>(data, element);
	}

	@Override
	public int size() {
		int count = 0;
		Element<T> data = element;
		while (data.getParent() != null) {
			count++;
			data = data.getParent();
		}
		return count;
	}

	@Override
	public Element<T> top() {
		return element;
	}

	public static void main(String[] args) {
	/*	MyLIFO<String> nazwiskoa = new MyLIFO<String>();
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		System.out.println("" + nazwiskoa.size());
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		System.out.println("" + nazwiskoa.size());
		nazwiskoa.pop();
		nazwiskoa.pop();
		nazwiskoa.pop();
		System.out.println("" + nazwiskoa.size());*/
		
		
		
		InterfacesLifo<String> nazwiskoa = new MyLIFO<String>();
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		System.out.println("" + nazwiskoa.size());
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		nazwiskoa.push("Jogi");
		System.out.println("" + nazwiskoa.size());
		nazwiskoa.pop();
		nazwiskoa.pop();
		nazwiskoa.pop();
		System.out.println("" + nazwiskoa.size());

	}
}
