package graph;

import java.util.*;

class Node {
    private int id;
    private ArrayList<Node>connectNode;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Node(int id, ArrayList<Node> connectNode) {
        super();
        this.id = id;
        this.connectNode = connectNode;
    }
    public ArrayList<Node> getConnectNode() {
        return connectNode;
    }
    public void setConnectNode(ArrayList<Node> connectNode) {
        this.connectNode = connectNode;
    }
    public Node(int id) {
        super();
        this.id = id;
    }
    public Node() {
        super();
    }

}

public class Test {

    private ArrayList<Node> nodeList;
    private ArrayList<ArrayList<Node>> allNodeList;

    public Test() {

        nodeList=new ArrayList<Node>();
        allNodeList=new ArrayList<ArrayList<Node>>();

        List<Node> nodes = constructNodes();
        getPaths(nodes.get(0),null,nodes.get(0),nodes.get(3));
        getShortPath();

    }

    /**
     * 最小路径
     */
    private void getShortPath() {
        ArrayList<Node> minNode = allNodeList.get(0);
        for(int i=1;i<allNodeList.size();i++){
            if(allNodeList.get(i).size()<minNode.size()){
                minNode=allNodeList.get(i);
            }
        }
        System.out.print("最短路径是：");
        for (int i = 0; i < minNode.size(); i++) {
            Node nNode =minNode.get(i);

            if(i < (minNode.size() - 1))
                System.out.print(nNode.getId() + "->");
            else
                System.out.print(nNode.getId());
        }
    }

    /**
     *寻找所有可能的路径
     * @param cNode: current node
     * @param pNode: previous node
     * @param sNode: start node
     * @param eNode: end node
     */
    private boolean getPaths(Node cNode, Node pNode, Node sNode, Node eNode) {
        //定义一个中间节点
        Node centerNode;
        //先考虑回路
        if(cNode!=null&&pNode!=null&&cNode==pNode){
            return false;
        }
        if(cNode!=null){
            int i=0;
            nodeList.add(cNode);
            if(cNode==eNode){//is  finded
                savePath();
                return true;
            }else{
                centerNode=cNode.getConnectNode().get(i);
                while(centerNode!=null){

                    if (pNode != null
                            && (centerNode == sNode || centerNode == pNode || isNodeInStack(centerNode))) {
                        i++;
                        if (i >= cNode.getConnectNode().size()){
                            centerNode = null;
                        }
                        else{
                            centerNode = cNode.getConnectNode().get(i);
                        }
                        continue;
                    }
                    if(getPaths(centerNode, cNode, sNode, eNode)){
                        //得到满足条件的一条路径 移除路径的最后一个节点 下标下移 重新迭代计算
                        nodeList.remove(nodeList.size()-1);
                        //如果有return 表示找到一条路径边结束
                        //return true;
                    }
                    i++;
                    if(i>=cNode.getConnectNode().size()){
                        centerNode=null;
                    }else{
                        centerNode=cNode.getConnectNode().get(i);
                    }
                }
                //当前节点的所有相关联的节点遍历完的时候 移除最后一个节点
                nodeList.remove(nodeList.size()-1);
                return false;
            }
        }else{
            return false; 
        }
    }

    /**
     * 判断是够已有该节点
     * @param node
     * @return
     */
    private boolean isNodeInStack(Node node) {

        for(Node n:nodeList){
            if(n==node){
                return true;
            }
        } 
        return false;
    }

    /**
     * 保存节点路径
     */
    private void savePath() {

        for(Node node :nodeList){
            System.out.print(node.getId()+"  ");
        }
        System.out.println("\r\n ----------- "); 
        //转储
        ArrayList<Node> nodes=new ArrayList<>(nodeList);
        allNodeList.add(nodes);
    }

    /**
     * 构造数据
     * @return
     */
    private List<Node> constructNodes() 
    {
    	List<Node> nodelist=new ArrayList<Node>();
        
        ArrayList<Node> nodes1=new ArrayList<Node>();
        ArrayList<Node> nodes2=new ArrayList<Node>();
        ArrayList<Node> nodes3=new ArrayList<Node>();
        ArrayList<Node> nodes4=new ArrayList<Node>();
        
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n3=new Node(3);
        Node n4=new Node(4);      
        
        n1.setConnectNode(nodes1);
        nodes1.add(n2);
        nodes1.add(n3);
        
        n2.setConnectNode(nodes2);
        
        n3.setConnectNode(nodes3);
        nodes3.add(n4);
        
        n4.setConnectNode(nodes4);
   
        nodelist.add(n1);
        nodelist.add(n2);
        nodelist.add(n3);
        nodelist.add(n4);
        
        return nodelist;
    }
    public static void main(String[] args) {
        new Test();
    }
}