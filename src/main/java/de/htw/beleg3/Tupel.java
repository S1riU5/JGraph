package de.htw.beleg3;

// Source: http://www.java-forum.org/chat-nopaste/9606-generics-tupel.html


public class Tupel {
	private Object object1;
	private Object object2;
	
	public Tupel(Object o1, Object o2){
		object1 = o1;
		object2 = o2;
	}
	
	public Object get01(){
		return object1;
	}
	
	public Object get02(){
		return object2;
	}
	

}
