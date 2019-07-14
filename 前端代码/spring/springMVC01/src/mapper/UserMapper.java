package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import pojo.User;

//第2步，建立数据库操作接口，这里写相关的sql语句
public interface UserMapper {

	//数据插入
	@Insert("insert into users values (default,#{username},#{password},#{photo})")
	int insertUser(User user);
	
	@Select("select * from users where username=#{0} and password=#{1}")
	User selectUser(String name, String pwd);
}
