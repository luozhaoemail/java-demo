package service.impl;


import java.nio.file.Files;
import java.util.List;

import javax.annotation.Resource;

import mapper.FilesMapper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import pojo.File;
import pojo.User;
import service.FilesService;


@Service
public class FileServiceImpl implements FilesService {
	@Resource
	private FilesMapper filesMapper;

	@Override
	public List<File> show() {
		return filesMapper.selAll();
	}

	@Override
	public int updCount(int id,User users,String name) {
		Logger logger = Logger.getLogger(FileServiceImpl.class);
		logger.info(users.getUsername()+"下载了"+name);
		return filesMapper.updCountById(id);
	}

	
	
	
}
