package service;

import java.util.List;

import pojo.File;
import pojo.User;

public interface FilesService {
	/**
	 * 显示全部
	 * @return
	 */
	List<File> show();
	/**
	 * 根据id修改资料下载次数
	 * @param id
	 * @return
	 */
	int updCount(int id,User users,String name);
}
