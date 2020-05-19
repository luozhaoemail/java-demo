package tree;

import java.util.*;

public class TrieDemo {
	public static void main(String[] args) 
	{
		Trie trie = new Trie();
		trie.addString("树状信息");
		trie.addString("信息检索");
		trie.addString("字典树");
		trie.addString("字符串搜索");
		trie.addString("字符串");
		trie.addString("abcdefgh");
		List<String> list = trie.find("abc");
		System.out.println(list);
		
		List<String> list2 = trie.find("字");
		System.out.println(list2);
		
		List<String> list3 = trie.find("息检");////不能从中间模糊匹配
		System.out.println(list3);
		
		match("裙子");//可以模糊匹配
		match("键盘");
		match("键");
		
	}
	
	public static void match(String str)
	{
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		TreeMap<Integer,String> result = new TreeMap<Integer,String>(
				new Comparator<Integer>(){
					@Override
					public int compare(Integer o1, Integer o2){
						return -(o1.compareTo(o2));//默认为升序，这里加上负号改为降序
					}					
				}
		);
		map.put(1,"连衣裙");
		map.put(2,"连裙衣");
		map.put(3,"衣裙裤子");
		map.put(4,"键盘");
		map.put(5,"电脑");
		
		
		for(Map.Entry<Integer,String> entry: map.entrySet())
		{
			int key = entry.getKey();
			String value = entry.getValue();
			
			for(int i=0; i<str.length(); i++)
			{
				if(value.contains(str.charAt(i)+""))
				{
					result.put(key,value);
				}
			}
		}
				

		Iterator<Integer> it = result.keySet().iterator();
		while(it.hasNext())
		{
			int ky = it.next();
			System.out.println(result.get(ky)+"  "+ky);
		}
	}
	
}
/**
字典树与字典很相似,当你要查一个单词是不是在字典树中,首先看单词的第一个字母是不是在字典的第一层,
如果不在,说明字典树里没有该单词,如果在就在该字母的孩子节点里找是不是有单词的第二个字母,没有说明没有该单词,有的话用同样的方法继续查找.
字典树不仅可以用来储存字母,也可以储存数字等其它数据。
 */
class Trie
{
	private TrieNode root;
	
	public Trie()
	{
		root = new TrieNode();
	}
	
	public void addString(String str)
	{
		if(str.length()<0 || str==null)
			return;
		
		TrieNode current = root;
		for(int i=0; i<str.length(); i++)
		{
			Character ch = new Character(str.charAt(i));//拆解字符串
			if(!current.child.containsKey(ch))
			{
				current.child.put(ch, new TrieNode());
			}
			current = current.child.get(ch);//指针下移
		}
		current.wordEnd=true;//标记结束
	}
	
	//前缀匹配查找--即关键词自动补全
	public List<String> find(String str)
	{
		String rst="";
		List<String> suggestList = new ArrayList<String>();
		TrieNode current = root;
		boolean find = false;
		for(int i=0;i<str.length(); i++)
		{			
			Character ch = new Character(str.charAt(i));//拆解字符串
			rst +=ch;
			if(!current.child.containsKey(ch))
			{	
				break;
			}
			else
			{
				current = current.child.get(ch);//指针下移
//				System.out.println(rst);
				find = true;
			}			

		}
		if(current.wordEnd)//查完一个单词
		{			
			suggestList.add(str);
		}
		else//如果单词不完整，则需要完整匹配
		{   
			if(find==true)
				getAllSubNodes(current,rst,suggestList);
		}
		
		return suggestList;
	}	
	
	//根据前缀获取完整关键词
	private void getAllSubNodes(TrieNode node,String prefix,List<String> suggestList)
	{
        Map<Character,TrieNode> map = node.child;//获取当前节点的所有子节点<key[i],node[i]>
        if (map!=null)
        {
        	for(Map.Entry<Character,TrieNode> entry: map.entrySet())//遍历所有的子节点
        	{
        		Character ccc = entry.getKey();//key[i] 
        		TrieNode subNode = entry.getValue();//node[i] 
        		
        		String word = prefix+ccc;
        		if(subNode.wordEnd)
        			suggestList.add(word);
        		//对于每一个子节点都要递归的检查它的子节点,直到某个子节点为空(叶子的子节点为空)
        		getAllSubNodes(subNode,word,suggestList);
        	}           
        }       	

    }
			
	public void showTree()
	{		
		TrieNode current = root;		
	}
}

class TrieNode
{	
	Map<Character,TrieNode> child;//可能有多个子节点，即<c[i],Node[i]>多叉树
	boolean wordEnd;
	
	public TrieNode()
	{
		child =new HashMap<Character,TrieNode>();
		wordEnd=false;
	}
}
