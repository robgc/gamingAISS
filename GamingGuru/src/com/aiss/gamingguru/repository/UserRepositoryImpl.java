package com.aiss.gamingguru.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.aiss.gamingguru.domain.User;

public class UserRepositoryImpl implements UserRepository{
	
	Map<String, User> userMap;
	
	public UserRepositoryImpl() {
		userMap = new HashMap<String, User>();
	}

	@Override
	public Collection<User> getAll() {
		return userMap.values();
	}

	@Override
	public void put(User u) {
		userMap.put(u.getId(), u);
	}

	@Override
	public User get(String id) {
		return userMap.get(id);
	}

	@Override
	public void remove(User u) {
		userMap.remove(u.getId());		
	}

}
