/**
 * 基础数据json
 */
var basicdata={
		apn:[
			["Name","string","apn名称"],
			["Name_CH","string","apn中文名称"],
			["Company_CH","string","apn所属企业中文名"],
			["industryName","string","apn所属行业"]
		],
		apn_ip:[
			["apnName","string","apn名称"],
			["IPStatus","int","ip状态"],
			["IPType","int","ip类型"],
			["StartIP","string","字符串类型的起始ip"],
			["nStartIP","bigint","长整型的起始ip"],
			["EndIP","string","字符串类型的结束ip"],
			["nEndIP","bigint","长整型的结束ip"]
		],
		area:[
			["AreaName","string","区域名称"],
			["CellName","string","小区名称"]
		],
		cell:[
			["CellName","string","小区名称"],
			["CellCity","string","小区所在城市"],
			["CellCountry","string","小区所在区县"],
			["Tac","int",""],
			["Pci","int",""],
			["EnodeName","string","基站名称"],
			["Lng","double","经度"],
			["Lat","double","纬度"]
		],
		imsi_isdn:[
			["IMSI","string","唯一标志SIM卡的编号"],
			["ISDN","string","电话号码"],
			["CreatTime","string","注册时间"],
			["nCreateTime","bigint","注册时间的长整型"],
			["StaticIP","string","该用户的静态IP，若没有则为“-”"],
			["ApnName","string","APN的名称"],
			["CityName","string","所属城市名称"]
		],
		
		level1:["apn","apn_ip","area","cell","imsi_isdn"]
		
}
