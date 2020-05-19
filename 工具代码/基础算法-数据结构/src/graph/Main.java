package graph;

import java.util.*;

public class Main {
	public static int count=0;

    // 存储节点信息
    private int[] vertices;

    // 存储边信息（邻接矩阵）
    private  int[][] arcs;

    // 图的节点数
    private int vexnum;

    // 记录节点是否已被遍历
    private boolean[] visited;

    // 初始化
    public Main(int n) {
          vexnum = n;
          vertices = new int[n];
          arcs = new int[n][n];
          visited = new boolean[n];
          for (int i = 0; i < vexnum; i++) {
             for (int j = 0; j < vexnum; j++) {
                 arcs[i][j] = 0;
             }
          }
    }

    // 添加边(无向图)
    public void addEdge(int i, int j) {
          // 边的头尾不能为同一节点
          if (i == j)return;

          arcs[i][j] = 1;
          arcs[j][i] = 1;
    }

    // 设置节点集
    public void setVertices(int[] vertices) {
        this.vertices = vertices;
    }

    // 设置节点访问标记
    public void setVisited(boolean[] visited) {
        this.visited = visited;
    }

    // 打印遍历节点
    public void visit(int i)
    {
    	count++;
//      System.out.print(vertices[i] + " ");
    }

    // 从第i个节点开始深度优先遍历
    private void traverse(int i){
        // 标记第i个节点已遍历
        visited[i] = true;
        // 打印当前遍历的节点
        visit(i);

        // 遍历邻接矩阵中第i个节点的直接联通关系
        for(int j=0;j<vexnum;j++){
            // 目标节点与当前节点直接联通，并且该节点还没有被访问，递归
            if(arcs[i][j]==1 && visited[j]==false){
                traverse(j);
            }
        }
    }

    // 图的深度优先遍历（递归）
    public void DFSTraverse(){
        // 初始化节点遍历标记
        for (int i = 0; i < vexnum; i++) {
            visited[i] = false;
        }

        // 从没有被遍历的节点开始深度遍历
        for(int i=0;i<vexnum;i++){
            if(visited[i]==false){
                // 若是连通图，只会执行一次
                traverse(i);
//                count++;
            }
        }
    }
    
 // 图的深度优先遍历（非递归）
    public void DFSTraverse2(){
        // 初始化节点遍历标记
        for (int i = 0; i < vexnum; i++) {
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();
        for(int i=0;i<vexnum;i++){
            if(!visited[i]){
                //连通子图起始节点
                s.add(i);
                do{ 
                    // 出栈
                    int curr = s.pop();

                    // 如果该节点还没有被遍历，则遍历该节点并将子节点入栈
                    if(visited[curr]==false){
                        // 遍历并打印
                        visit(curr);
                        visited[curr] = true;

                        // 没遍历的子节点入栈
                        for(int j=vexnum-1; j>=0 ; j-- ){
                            if(arcs[curr][j]==1 && visited[j]==false){
                                s.add(j);
                            }
                        }
                    }
                }while(!s.isEmpty());
            }
        }
    }
   

    public static void main(String[] args) {
    	Scanner sc =new Scanner(System.in);
		int n = sc.nextInt();
		Main g = new Main(n);
		
		int[] vertices = new int[n];
		for(int i=0; i<n; i++)
		{
			vertices[i] = i+1;
		}
		g.setVertices(vertices);

    	
		for(int j=1; j<n; j++)
		{
			g.addEdge(sc.nextInt()-1,sc.nextInt()-1);
		}
		g.DFSTraverse2();
		System.out.println(count);
		 
/*
4
1 2
1 3
3 4
 */

      
    }

}
