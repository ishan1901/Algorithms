
public class MergeSort {
	
	public static void mergeSort(int[] array) {
		mergeSort(array, 0, array.length-1);
	}
	
	private static void mergeSort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = (start + end)/2;
		mergeSort(array, start, mid);
		mergeSort(array, mid+1, end);
		merging(array, start, mid, end);
	}
	
	private static void merging(int[] array, int start, int mid, int end) {
		int[] l1 = new int[mid-start+1];
		int[] l2 = new int[end - mid];
		
		for (int i = 0; i < l1.length; i++) {
			l1[i] = array[start + i];
		}
		
		for (int i = 0; i < l2.length; i++) {
			l2[i] = array[mid+1 + i];
		}
		
		int i = 0, j =0;
		while (i < l1.length && j < l2.length) {
			if (l1[i] <= l2[j]) {
				array[start] = l1[i];
				i++;
			} else {
				array[start] = l2[j];
				j++;
			}
			start++;
		}
		while (i < l1.length) {
			array[start] = l1[i];
			start++;i++;
		}
		while (j < l2.length) {
			array[start] = l2[j];
			start++;j++;
		}
	}
	
	public static void main(String args[]) {
		int[] array = {8,19,01,22,1,2,-100};
		mergeSort(array);
		for (Integer i : array) {
			System.out.println(i);
		}
	}
}
