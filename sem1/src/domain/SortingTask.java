package domain;

public class SortingTask extends Task{
    int[] arr;
    SortingStrategy strategy;

    public SortingTask(String taskId, String desc, int[] arr, SortingStrategy strategy) {
        super(taskId, desc);
        this.arr = arr;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        if (SortingStrategy.BUBBLE == strategy) {
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.abstractSort(arr);
        }
        if(SortingStrategy.QUICK == strategy){
            QuickSort quickSort = new QuickSort();
            quickSort.abstractSort(arr);
        }

    }


    public interface Sort{
        int[] sort(int[] arr);
        void swap(int e1, int e2);
    }


    public static abstract class AbstractSort implements Sort{
        @Override
        public int[] sort(int[] arr){
            abstractSort(arr);
            return arr;
        }

        @Override
        public void swap(int e1, int e2){
            int aux = e1;
            e1 = e2;
            e2 = aux;
        }

        protected abstract void abstractSort(int[] arr);
    }

    public static class BubbleSort extends AbstractSort{

        @Override
        protected void abstractSort(int[] arr) {
            for(int i = 0; i < arr.length - 1; i++)
            {
                for (int j = 0; j < arr.length-i-1; j++)
                    if(arr[j] > arr[j+1]) {
                        int temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
            }
            for(int el: arr)
                System.out.println(el);
        }
    }


    public static class QuickSort extends AbstractSort{

        int partition(int arr[], int low, int high)
        {
            int pivot = arr[high];
            int i = (low-1);
            for (int j=low; j<high; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    swap(arr[i], arr[j]);
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i+1];
            arr[i+1] = arr[high];
            arr[high] = temp;
            return i+1;
        }

        private void quickSort(int[] arr, int low, int high)
        {
            if (low < high)
            {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi-1);
                quickSort(arr, pi+1, high);
            }
        }

        @Override
        protected void abstractSort(int[] arr) {
            int low = 0;
            int high = arr.length - 1;
            quickSort(arr, low , high);
            for(int el: arr)
                System.out.println(el);
        }
    }



}
