package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pojo.File;


public interface FilesMapper {
	@Select("select * from files")
	List<File> selAll();
	@Update("update files set filecount=filecount+1 where id=#{0}")
	int updCountById(int id);
}
