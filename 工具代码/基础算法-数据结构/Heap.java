package view;

public class Heap {
	 public static void heapSort(Long []arr){
        if(arr==null||arr.length<2){
            return;
        }
        for (int i=0;i<arr.length;i++)
        {//将数字插入堆中
            heapInsert(arr,i);
        }

        int size=arr.length;
        //因为已经构建好了大顶堆，所以最顶端的值是整个堆中最大的值，根据堆排序的原理，可以得知需要将堆顶元素与数组内下标最大的元素交换一次位置。
        swap(arr,0,--size);
        while(size>0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }

    private static void heapify(Long[] arr, int index, int size) {
        //调整堆，使其构成大顶堆。
        int left=index*2+1;//index的左子树，left+1代表index的右子树
        while(left<size) {
            int large = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;//将较大的值得下标赋值给large
            large = arr[large] > arr[index] ? large : index;//将左右子树中的较大值与index位置的值比较，获取较大值得下标
            if (large == index){//若当前值代表整个堆中的最大值，则堆排序实现完毕
                break;
            }
            swap(arr,large,index);
            index=large;//准备遍历子树，继续向下进行堆排序。
            left=index*2+1;
        }
    }
    private static void swap(Long[] arr, int index1, int index2) {
        //交换两个数的过程。
//	       arr[index1]=arr[index1]^arr[index2];
//	       arr[index2]=arr[index1]^arr[index2];
//	       arr[index1]=arr[index1]^arr[index2];
    	Long temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }
    private static void heapInsert(Long[] arr, int index) {
        //这里是构造大顶堆的过程。若需要修改为小顶堆，则需要将while内的判断条件修改一下即可。
        //得到的结果是根节点的值要大于其左右子树的值。
        while(arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void main(String args[])
    {
    	Long[]newarr=new Long[]{111L,32L,32L,13L,5L,67L,9L};
        heapSort(newarr);
        for (int i=0;i<newarr.length;i++) {
            System.out.print(newarr[i]+" ");
        }
        System.out.println();
    }
}
