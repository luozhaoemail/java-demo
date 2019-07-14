/**
 * 业务数据json
 */
var metadata={
		s1_mme:[
			["IMSI","string","唯一标志SIM卡的编号"],
			["Cell","int","小区ID"],
			["ProcedureType","int","流程类型"],
			["FailureCause","int","故障原因"]
		],
		s6a:[
			["IMSI","string","唯一标志SIM卡的编号"],
			["Cell","int","小区ID"],
			["ProcedureType","int","流程类型"],
			["Cause","int","故障原因"]
		],
		s11:[
			["IMSI","string","唯一标志SIM卡的编号"],
			["Cell","int","小区ID"],
			["ProcedureType","int","流程类型"],
			["FailureCause","int","故障原因"],
			["APN","string","使用的APN"]
		],

		level1:["s1_mme","s6a","s11"]
		
}
