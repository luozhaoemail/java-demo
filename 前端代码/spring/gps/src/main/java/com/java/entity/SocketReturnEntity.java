package com.java.entity;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: SocketReturnEntity 
 * <p>Description: 主要用于封装前台界面要显示的字段,以及传输命令</p>
 * @date 2017年10月16日 下午3:47:25 
 * @
 */
public class SocketReturnEntity extends CommandEntity{
	//关于pve所需的参数有 ip 端口  用户名 密码
	protected String pveIp;
	protected String pvePort;
	protected String pveUserName;
	protected String pvePassWord;
	
	
	//云共享管理---信令元数据---所需参数   查询字段是临时的自己封装
	protected String shareStartTime;//开始时间
	protected String shareEndTime;//结束时间
	protected String shareUserName;//用户名
	protected String shareStartIntervaltime;//间隔时间

	protected List<String> pveids;//PVE所需ID
	protected HashMap<String,String> pvenodes;//pve节点信息包含PVE名称和对应PVEID
	protected String nodename;//pve下节点名称
	
	protected int vmid;//PVE虚拟机ID
	
	//关于pve的get\set方法
	public String getPveIp() {
		return pveIp;
	}

	public void setPveIp(String pveIp) {
		this.pveIp = pveIp;
	}

	public String getPvePort() {
		return pvePort;
	}

	public void setPvePort(String pvePort) {
		this.pvePort = pvePort;
	}

	public String getPveUserName() {
		return pveUserName;
	}

	public void setPveUserName(String pveUserName) {
		this.pveUserName = pveUserName;
	}

	public String getPvePassWord() {
		return pvePassWord;
	}

	public void setPvePassWord(String pvePassWord) {
		this.pvePassWord = pvePassWord;
	}
	
	//关于数据元共享的get\set方法

	public String getShareStartTime() {
		return shareStartTime;
	}

	public void setShareStartTime(String shareStartTime) {
		this.shareStartTime = shareStartTime;
	}

	public String getShareEndTime() {
		return shareEndTime;
	}

	public void setShareEndTime(String shareEndTime) {
		this.shareEndTime = shareEndTime;
	}

	public String getShareUserName() {
		return shareUserName;
	}

	public void setShareUserName(String shareUserName) {
		this.shareUserName = shareUserName;
	}

	public String getShareStartIntervaltime() {
		return shareStartIntervaltime;
	}

	public void setShareStartIntervaltime(String shareStartIntervaltime) {
		this.shareStartIntervaltime = shareStartIntervaltime;
	}

	public List<String> getPveids() {
		return pveids;
	}

	public void setPveids(List<String> pveids) {
		this.pveids = pveids;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public int getVmid() {
		return vmid;
	}

	public void setVmid(int vmid) {
		this.vmid = vmid;
	}

	public HashMap<String, String> getPvenodes() {
		return pvenodes;
	}

	public void setPvenodes(HashMap<String, String> pvenodes) {
		this.pvenodes = pvenodes;
	}

}
