
public class QuickSort {
	
	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length-1);
	}
	
	private static void quickSort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int pIndex = partition(array, start, end);
		quickSort(array, start, pIndex-1);
		quickSort(array, pIndex, end);
	}
	
	private static int partition(int[] array, int start, int end) {
		int pivot = (start + end)/2;
		while (start <= end) {
			while (array[start] < array[pivot]) {
				start++;
			}
			while (array[end] > array[pivot]) {
				end--;
			}
			if (start <= end) {
				swap(array, start, end);
				start++;
				end--;
			}
		}
		return start;
	}
	
	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static void main(String args[]) {
		int[] array = {8,-19,01,-22,1,2,-100,8643,-98};
		quickSort(array);
		for (Integer i : array) {
			System.out.println(i);
		}
	}
}
