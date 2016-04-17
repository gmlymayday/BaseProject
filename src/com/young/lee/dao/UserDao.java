package com.young.lee.dao;

import java.sql.SQLException;

import com.young.lee.db.DatabaseHelper;
import com.young.lee.entity.UserBean;

public class UserDao {
	private DatabaseHelper databaseHelper;

	public void add() {
		try {
			databaseHelper.getDao(UserBean.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
