package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//接口名要与MyMapper.xml对应, 接口包名和接口名与<mapper>namespace相同
public interface MyMapper {
	List<User> mySelect();
	
	//方法名要与MyMapper.xml的 select id对应
	List<User> selAll();
	
	//多参数		 #{}中使用 0,1,2 或 param1,param2
	List<User> selByName(int id, String name);
	
	//注解方式 	把参数转换为map了,其中@Param("key") 参数内容就是map的value
	List<User> selByName2(@Param("id") int id, @Param("name") String name);
	
	@Select("select * from users")
	List<User> selUsers();
}
