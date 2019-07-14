package com.java.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * 依赖jackson-mapper-lgpl、jackson-core-lgpl (82) 包
 * 
 * @ClassName: JsonBinder 
 * <p>Description: 不同方式处理json数据</p>
 * @
 */
public class JsonBinder {

	private static Logger log = Logger.getLogger(JsonBinder.class);
	private ObjectMapper mapper;
	
	@SuppressWarnings("deprecation")
	public JsonBinder(Inclusion inclusion) {
		 mapper = new ObjectMapper(); 
		//设置输出包含的属性  
		 mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		 mapper.getDeserializationConfig().set(  
	            org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	/** 
     * 创建输出全部属性到Json字符串的Binder. 
     */ 
	public static JsonBinder buildNormalBinder() {  
        return new JsonBinder(Inclusion.ALWAYS);  
    }
	/** 
     * 创建只输出非空属性到Json字符串的Binder. 
     */  
    public static JsonBinder buildNonNullBinder() {  
        return new JsonBinder(Inclusion.NON_NULL);  
    }
    /** 
     * 创建只输出初始值被改变的属性到Json字符串的Binder. 
     */  
    public static JsonBinder buildNonDefaultBinder() {  
        return new JsonBinder(Inclusion.NON_DEFAULT);  
    }
    /** 
     * 如果JSON字符串为Null或"null"字符串,返回Null. 
     * 如果JSON字符串为"[]",返回空集合. 
     *  
     * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: 
     * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {}); 
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {  
        if (StringUtil.isEmpty(jsonString)) {  
            return null;  
        }  
  
        try {  
            return mapper.readValue(jsonString, clazz);  
        } catch (IOException e) {  
            log.warn("parse json string error:" + jsonString, e);  
            return null;  
        }  
    }
    /** 
     * 如果对象为Null,返回"null". 
     * 如果集合为空集合,返回"[]". 
     */  
    public String toJson(Object object) {  
  
        try {  
            return mapper.writeValueAsString(object);  
        } catch (IOException e) {  
            log.warn("write to json string error:" + object, e);  
            return null;  
        }  
    }
    /** 
     * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数. 
     */  
    @SuppressWarnings("deprecation")
	public void setDateFormat(String pattern) {
        if (StringUtil.isNotEmpty(pattern)) {  
            DateFormat df = new SimpleDateFormat(pattern);  
            mapper.getSerializationConfig().setDateFormat(df);  
            mapper.getDeserializationConfig().setDateFormat(df);  
        } else if(StringUtils.isBlank(pattern)){
        	pattern = "yyyy-MM-dd HH:mm:ss";
        } 
    }
    /** 
     * 取出Mapper做进一步的设置或使用其他序列化API. 
     */  
    public ObjectMapper getMapper() {  
        return mapper;  
    }
}
