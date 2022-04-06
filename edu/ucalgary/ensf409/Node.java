/* Assignment 2 : Node.java
 * @Author: Steven Poon
 * UCID: 30094433
 * Date Created: 15/02/2021
 * 
 * @Version: 1.3
 * @Since 1.0
 */

package edu.ucalgary.ensf409;

public class Node{
/***********************Member Variables*******************************/
	private String word;
	private Node next;
	
/**************************Constructors********************************/
	Node(String w){
		word = w;
		next = null;
	}
	
	Node(String w, Node n){
		word = w;
		next = n;
	}
	
/********************************Setters*******************************/
	public void setWord(String word){
		this.word = word;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
	
/********************************Getters*******************************/
	public String getWord(){ return word; }
	public Node getNext(){ return this.next; }

}
