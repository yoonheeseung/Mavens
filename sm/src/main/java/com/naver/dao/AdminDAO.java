package com.naver.dao;

import com.naver.model.AdminBean;

public interface AdminDAO {

	void insertAdmin(AdminBean ab);

	AdminBean find(String admin_id);
	

	
}
