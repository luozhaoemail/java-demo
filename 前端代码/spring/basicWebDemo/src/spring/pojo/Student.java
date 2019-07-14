package spring.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/**
 * 符合嵌套类型
 * @author Administrator
 *
 */
public class Student {
	public String name;
	public Set<String> sets;
	public List<String> list;
	public String [] strs;
	public Map<String,String> map;
	public Properties props;
	public People ple;
	
	
	
		
	@Override
	public String toString() {
		return "Student [name=" + name + ", sets=" + sets + ", list=" + list
				+ ", strs=" + Arrays.toString(strs) + ", map=" + map
				+ ", props=" + props + ", ple=" + ple + "]";
	}


	public Properties getProps() {
		return props;
	}


	public void setProps(Properties props) {
		this.props = props;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<String> getSets() {
		return sets;
	}


	public void setSets(Set<String> sets) {
		this.sets = sets;
	}


	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public String[] getStrs() {
		return strs;
	}


	public void setStrs(String[] strs) {
		this.strs = strs;
	}


	public Map<String, String> getMap() {
		return map;
	}


	public void setMap(Map<String, String> map) {
		this.map = map;
	}


	public People getPle() {
		return ple;
	}


	public void setPle(People ple) {
		this.ple = ple;
	}
	
	
	
}
