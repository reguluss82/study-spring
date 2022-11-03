package com.oracle.oBootSecurity02.service;

import com.oracle.oBootSecurity02.entity.User;

public interface SecurityService {
	void save(User user);
	User findByUsername(String username);
}
