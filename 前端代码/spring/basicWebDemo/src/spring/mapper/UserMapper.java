package spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import spring.pojo.User;

/**
 * 使用mybatis注解,j就不需要配置UserMapper.xml了，程序会自动配置
 * @author Administrator
 *
 */
public interface UserMapper {
	
	@Select("select * from users")
	List<User> selAll();
}
