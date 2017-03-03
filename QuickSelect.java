/**
 * 
 * @author Ishan Sharma
 * The QuickSelect class provides static methods for sorting an
 *  array and selecting the median element in an array using quick select median finding.
 *
 */
import java.util.Arrays;
import java.util.Random;

public class QuickSelect {
	/**
	 * Quick Select Algorithm
	 * @param array of random integers
	 * @param k the desired rank in the entire array
	 * @return kth smallest element
	 */
	public static int findKSmallest(int array[], int k) {
		if (array.length == 0) {
			return 0;
		} else if (array.length == 1) {
			return array[0];
		}
		return findKSmallestUtil(array, 0 , array.length - 1, k);		
	}
	
	/**
	 * Method to get the kth smallest element through divide and conquer approach
	 * @param array of random integers
	 * @param start 
	 * @param end
	 * @param k the desired rank in the entire array
	 * @return kth smallest element
	 */
	public static int findKSmallestUtil(int[] array, int start, int end, int k) {
		if (start > end) {
			return 0;
		}
		int pivot = medianOfMedians(array,start,end);
		int	partitionIndex  = partition(array, start, end, pivot);

		if (partitionIndex == k) {
			return array[partitionIndex];
		} else if (partitionIndex < k) {
			return findKSmallestUtil(array, partitionIndex + 1, end, k);
		} else {
			return findKSmallestUtil(array, start, partitionIndex - 1, k);
		}
	}
	/**
	 * Method to divide the array into groups of 5.
	 * @param array
	 * @param start
	 * @param end
	 * @return median of medians
	 */
	private static int medianOfMedians(int[] array, int start, int end) {
		int length  = end- start + 1;
		double temp = (length/5.0);
		int size = (int) Math.ceil(temp);
		int left = start, right = end;
		int median[] = new int[size];
		for (int i = 0; i < size; i++) {
			left = left + i* 5;
			right  = left + 5;
			if (left > end) {
				left = end;
			}
			if (right > end) {
				right = end;
			}
			median[i] = getMedian(array, left, right);
		}
		int medianOfMedian = (size == 1) ? median[0] : medianOfMedians(median,0,size-1);
		return medianOfMedian;
	}
	/**
	 * Method to sort the small groups using insertion sort and get median of array.
	 * @param median
	 * @param start
	 * @param end
	 * @return median of the given array.
	 */
	private static int getMedian(int[] median, int start, int end) {
		int length = end - start + 1;
		//insertion sort
		for(int i = start; i <= end; i++) {
			int key = median[i];
			int j = i -1;
			while(j>= start && median[j] > key) {
				median[j+1] = median[j];
				j = j-1;
			}
			median[j+1] = key;
		}
		if (length %2 == 0) {
			return median[length/2 + start -1];
		}
		return median[length/2 + start];
	}
	/**
	 * Method to partition the array by taking median of medians as pivot
	 * @param array
	 * @param start
	 * @param end
	 * @param x
	 * @return correct position of pivot.
	 */
	private static int partition(int[] array, int start, int end, int pivot) {
		int i;
		for(i = start; i <= end; i++) {
			if (array[i] == pivot) { break;}
		}
		swap(array, i , end);
		i = start;
		for (int j = start; j <= end; j++) {
			if (array[j] < pivot) {
				swap(array, i, j);
				i++;
			}
		}
		swap(array, i, end);
		return i;
	}
	
	/**
    * Method to swap to elements in an array.
    * @param an array of integers.
    * @param index1 the index of the first integer.
    * @param index2 the index of the second integer.
    */
	private static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	/**
	 * Method to initialize array with random numbers
	 * @param required size of array.
	 * @return array stored with random numbers
	 */
	public static int[] initializeArray(int n) {
		int[] array = new int[n];
		Random random = new Random();
		for (int i = 0; i<  array.length; i++) {
			array[i] = random.nextInt(n) + 1; 
		}
		return array;
	}
	
	/**
	 * Method to get Median in an array.
	 * @param an array of integers.
	 * @return median from sorted array.
	 */
	public static double checkMedian(int[] array) {
		if (array.length == 0) {return 0;}
		double median;
		Arrays.sort(array);
		if(array.length%2 ==  0) {
			int median1 = array[array.length/2];
			int median2 = array[array.length/2-1];
			return (median1 + median2)/2.0;
		}
		return (double)array[array.length/2];
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		int[] input =  {1001, 2001, 5001, 10001, 20001, 30001, 50001, 100001, 200001, 400001, 800001,
				1600001, 3200001, 6400001, 12800001, 25600001, 51200001};
		// To check for even inputs uncomment the following line and comment out the line above.
		/* int[] input = {1000,2000,5000,10000,20000,30000,50000,100000,200000,400000};*/
				
		double median = 0;
		int k;
		for(int n : input) {
			int[] array = initializeArray(n);
			//Uncomment following line to give your own values.
			/*int[] array = {};*/
			
			k = array.length;
			long startTime = System.nanoTime();
			if (array.length%2 == 0) {
				int median1 = findKSmallest(array, k / 2);
				int median2 = findKSmallest(array, (k / 2) - 1);
				median = (median1 + median2)/2.0;
			} else {
				median = findKSmallest(array, k / 2);
			}
			long stopTime = System.nanoTime();
			long elapsedTime = stopTime - startTime;
			System.out.println("n = " + n + " : "+ elapsedTime);
			System.out.println("Median is :" + median);
			
			//Uncomment following lines to compare medians
			/*
			if(checkMedian(array) == median) {
				System.out.println("Median matched : Correct Output");
			}*/
			
		}
	}
	
}
