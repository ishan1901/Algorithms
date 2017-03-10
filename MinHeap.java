import java.util.Arrays;

public class MinHeap {
	private int size = 0;
	private int capacity = 10;
	private int[] elements = new int[capacity];
	
	private int getLeftChildIndex (int index) {return (2 * index + 1);}
	private int getRightChildIndex (int index) {return (2 * index + 2);}
	private int getParentIndex (int index) {return (index - 1) / 2;}
	
	private boolean hasLeftChild(int index) {return getLeftChildIndex(index) < size;}
	private boolean hasRightChild(int index) {return getRightChildIndex(index) < size;}
	private boolean hasParent(int index) {return getParentIndex(index) >= 0;}
	
	private int getLeftChild(int index) {return elements[getLeftChildIndex(index)];}
	private int getRightChild(int index) {return elements[getRightChildIndex(index)];}
	private int getParent(int index) {return elements[getParentIndex(index)];}
	
	private void checkCapacity() {
		if (size == capacity) {
			capacity *= 2;
			elements = Arrays.copyOf(elements, capacity);
		}
	}
	
	private void swap(int index1, int index2) {
		int temp = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = temp;
	}
	
	public void insert(int value) {
		checkCapacity();
		elements[size] = value;
		size++;
		heapifyUp();
	}
	
	private void heapifyUp() {
		int index = size - 1;
		while(hasParent(index) && getParent(index) > elements[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}	
	}
	
	public int poll() {
		if (size == 0) throw new IllegalStateException();
		int holdValue = elements[0];
		elements[0] = elements[size - 1];
		size--;
		heapifyDown();
		return holdValue;
	}
	
	private void heapifyDown() {
		int index = 0;
		while(hasLeftChild(index)) {
			int smallestIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && getLeftChild(index) > getRightChild(index)) {
				smallestIndex = getRightChildIndex(index);
			}
			if (elements[index] < elements[smallestIndex]) {
				break;
			}
			swap(index, smallestIndex);
			index = smallestIndex;
		}
	}
	
	public void printHeap() {
		for (int i = 0; i < size; i++) {
			System.out.println(elements[i]);
		}
	}
	
	public void heapSort() {
		int maxIndex = size;
		while(size != 0) {
			swap(0, size-1);
			size--;
			heapifyDown();
		}
		size = maxIndex;
	}
	
	public static void main(String args[]) {
		MinHeap h1 = new MinHeap();
		h1.insert(100);
		h1.insert(-1);
		h1.insert(26);
		h1.insert(-80);
		h1.insert(10);
		h1.insert(101);
		h1.heapSort();
		h1.printHeap();
	}
}
