package view;
import java.util.*;
/**
 * 
小T最近迷上一款跳板小游戏 
已知空中有N个高度互不相同的跳板，小T刚开始在高度为0的地方，每次跳跃可以选择与自己高度绝对值小于等于H的跳板，
跳跃过后到达以跳板为轴的镜像位置（起跳位置距离跳板，与跳板距离停下位置相等），
问小T在最多跳K次的情况下最高能跳多高？(任意时刻，高度不能为负)
 输入：
     n  k  h
     3 	3  2
     1
     3
     6
  输出：
     8
   公式：结束高度=初始高度+(跳板高度-初始高度)*2
   	   H(i+1)=H(i)+[X-H(i)]*2
   比如：第一次跳跃，选择高度为1的跳板，，结束后到达高度为2的地方。即： y=0+(1-0)*2=2
   因为X不确定，并且可能是一个集合，所以每个阶段的最优状态是由之前所有状态组合得到的，用BFS
 */
public class JumpDemo
{
	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt();//跳板个数
		int k=sc.nextInt();//最多跳k次
		int h=sc.nextInt();//每次选择距离为h的跳板
		int[] j = new int[n];
		for(int i=0;i<n;i++)
			j[i]=sc.nextInt();//每个跳板的离地高度
		
		LinkedList<int[]> que= new LinkedList<int[]>();//队列
		int p[]={0,0};//初始状态：{当前高度H(i),已经跳过的次数，要小于k}
		que.offer(p);//先入队
		
		int max=0;
		while(!que.isEmpty())
		{
			p = que.poll();//再出队，处理当前节点p，把当前层的所有可能都遍历
			
			for(int i=0;i<n;i++)//遍历所有的跳板
			{
				if(j[i]-p[0]<=h && p[1]<=k)//j[i]表示第i个跳板的高度-初始高度p[0]<最大跳板距离h && 跳数<k
				{
					int[] q=new int[2];//q表示下一节点
					q[0] = p[0]+(j[i]-p[0])*2;//H(i+1)=H(i)+[X-H(i)]*2
					q[1] = p[1]+1;//跳数+1
					
					que.offer(q);//当前节点入队，准备处理下个节点
					
					if(q[0]>max)//更新状态
						max=q[0];
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	
	
}
