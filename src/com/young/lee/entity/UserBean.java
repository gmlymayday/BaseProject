package com.young.lee.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_user")
public class UserBean {
	@DatabaseField(generatedId = true)
	public int id;
	@DatabaseField(columnName = "username")
	public String username;

	public UserBean() {
		super();
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + "]";
	}

}
