package view.acm;

import java.util.*;

public class Main5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++)
			a[i] = sc.nextInt();
		System.out.println(jump(a,n));
	}
	
	public static int jump(int a[], int n) {
	    if(n <= 2)
	        return 0;
	    
	    int[] b = new int[n];
	    
	    b[0] = 0;
	    for(int i=2;i<n-1;i++)
	    {
	        int m = a[i];
	        if(i + m >= n-1)
	            return b[i] + 1;
	        
	        for(int j=1;j<=m;j++)
	        {
	            if(b[i+j] == 0 || b[i+j] > b[i] + 1)
	                b[i+j] = b[i] + 1;
	        }
	    }
	    return b[n-1];
	}

	

}
