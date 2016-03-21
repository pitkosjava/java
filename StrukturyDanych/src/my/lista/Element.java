package my.lista;

public class Element <T>{
	
	private T data;
	private Element<T> parent;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Element<T> getParent() {
		return parent;
	}
	public void setParent(Element<T> parent) {
		this.parent = (Element<T>) parent;
	}
	public Element(T data, Element<T> parent) {
		this.data = data;
		this.parent = parent;
	}
	public Element(){
		 data=null;
		parent=null;
	}
	
}
