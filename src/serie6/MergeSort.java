package serie6;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6
 * 
 * Salim Hermidas
 * 11-125-382
 * 
 */

public class MergeSort {

	public static void sort(Comparable[] array) {
		mergeSort(array);
	}

	private static void mergeSort(Comparable[] array) {
		if (array.length > 1) {

			int middle = array.length / 2;
			int size1 = middle;
			int size2 = array.length - middle;

			Comparable[] arr1 = new Comparable[size1];
			int x = 0;
			for (int i = 0; i < middle; i++) {
				arr1[x] = array[i];
				x++;
			}

			Comparable[] arr2 = new Comparable[size2];
			int y = 0;
			for (int i = middle; i < array.length; i++) {
				arr2[y] = array[i];
				y++;
			}

			mergeSort(arr1);
			mergeSort(arr2);
			merge(arr1, arr2, array);
		}
	}
	
    private static void merge(Comparable[] arr1, Comparable[] arr2, Comparable[] array){
        int i = 0, j = 0, k = 0;
        while (arr1.length != j && arr2.length != k) {
            if (0 > arr1[j].compareTo(arr2[k])) {
                array[i] = arr1[j];
                i++;
                j++;
            } else {
                array[i] = arr2[k];
                i++;
                k++;
            }
        }
        while (arr1.length != j) {
            array[i] = arr1[j];
            i++;
            j++;
        }
        while (arr2.length != k) {
            array[i] = arr2[k];
            i++;
            k++;
        }
    }
}
