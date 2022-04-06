/* Assignment 2 : Linked List.java
 * @Author: Steven Poon
 * UCID: 30094433
 * Date Created: 15/02/2021
 * 
 * @Version: 1.5
 * @Since 1.0
 */

package edu.ucalgary.ensf409;

public class LinkedList<T>{
    /***************************Member Variables***************************/
        private Node head;
        private Node sorted;
        private int size;
        
    /***************************Constructors*******************************/
        LinkedList(){
            head = null;
            size = 0;
        }
        
    /**************************Methods*************************************/
        //Algorithm implemented from https://www.geeksforgeeks.org/insertion-sort-for-singly-linked-list/.
        public void insertionSort(Node headRef){
            sorted = null;
            Node current = headRef;
            while(current != null){
                Node next = current.getNext();
                sortedInsert(current);
                current = next;
            }
            head = sorted;
        }
        
        //Algorithm implemented from https://www.geeksforgeeks.org/insertion-sort-for-singly-linked-list/.
        public void sortedInsert(Node newnode){
            if (    sorted == null || 
                    sorted.getWord().compareTo(newnode.getWord()) >= 0) {
                newnode.setNext(sorted);
                sorted = newnode;
            }else{
                Node current = sorted;
                while (current.getNext() != null && current.getNext().
                                getWord().compareTo(newnode.getWord()) < 0){
                    current = current.getNext();
                }
                newnode.setNext(current.getNext());
                current.setNext(newnode);
            }
        }
        
        public void insert(String word){
            if(head == null){
                head = new Node(word);
                size ++;
            }else{
                Node temp = head;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(new Node(word));
                size++;
            }
        }
        
    /********************************Setters*******************************/
        public void setHead(Node head){
            this.head = head;
        }
        
    /********************************Getters*******************************/
        public int getSize(){ return this.size; }
        public Node getHead(){ return this.head; }
    }
            
    