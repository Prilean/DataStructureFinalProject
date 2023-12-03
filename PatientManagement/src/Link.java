class Node {
	Patient p;
	Doctor d;
	Room r;
	int val = 99;
	int avail = 0;
	Node next = null;
	Node(){
	}
}

class LinkedList {
	Node head = null;
	Node sorted;
	LinkedList(){
		
	}
	LinkedList(Node head) {
		this.head = head;
	}
	boolean isEmpty() {
		return head == null;
	}
	void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.val);
			current = current.next;
		}
	}
	// Implementation of insertionNode
	void insertionSort() {
		Node current = head;
		sorted = null;
		while (current != null) {
			Node next = current.next;
			insert(current);
			current = next;
		}
		head = sorted;
	}
	void insert(Node newnode) {
		if (sorted == null || sorted.val >= newnode.val) {
			newnode.next = sorted;
			sorted = newnode;
		}
		else {
			Node current = sorted;
			while (current.next != null && current.next.val < newnode.val) {
				current = current.next;
			}
			newnode.next = current.next;
			current.next = newnode;
		}
	}
}