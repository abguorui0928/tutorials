package com.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.demo.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String username, String password) {
		String sql = "select count(*) from t_user where user_name=? and password=? ";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, new Object[] { username, password });
		return result.size();
	}

	public User findUserbyUsername(final String username) {
		String sql = "select user_id,user_name from t_user where user_name=?";
		final User user = new User();
		jdbcTemplate.query(sql, new Object[] { username }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
			}
		});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sql = "update t_user set last_visit=?,last_ip=? where user_id=?";
		jdbcTemplate.update(sql, new Object[] { user.getLastvisit(), user.getLastip(), user.getUserid() });
	}
}