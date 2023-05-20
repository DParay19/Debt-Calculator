import java.util.HashMap;

// class that contains bubbleSort method
public class BubbleSort
{
    // bubbleSort method
    // change the parameter of the method do double[] so it can take in rates[]
    public static void bubbleSort(double[] intArray) {
        int n = intArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (intArray[j - 1] > intArray[j]) {
                    //swap the elements!
                    temp = (int) intArray[j - 1];
                    intArray[j - 1] = intArray[j];
                    intArray[j] = temp;
                }
            }
        }
    }
}
