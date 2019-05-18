package dao.Imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import pojo.User;
import tools.MysqlDB;
import dao.UserDao;

public class UserDaoImp implements UserDao{
	
	@Override
	public List<User> getUserInfo(String name) {
		List<User> list = new ArrayList<>();
		String sql="select * from tb_user";
		try {
			ResultSet rs = MysqlDB.getResultSet(sql);
			while(rs.next()){
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setFav(rs.getString("fav"));
				list.add(user);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
}
