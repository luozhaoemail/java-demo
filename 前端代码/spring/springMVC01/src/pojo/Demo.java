package pojo;

import java.util.List;

public class Demo {
	/*private People peo;
	
	public People getPeo() {
		return peo;
	}
	public void setPeo(People peo) {
		this.peo = peo;
	}
	@Override
	public String toString() {
		return "Demo [peo=" + peo + "]";
	}*/
	
	
	public List<People> peoList;

	public List<People> getPeo() {
		return peoList;
	}
	public void setPeo(List<People> peoList) {
		this.peoList = peoList;
	}
	@Override
	public String toString() {
		return "Demo [list=" + peoList + "]";
	}
}
