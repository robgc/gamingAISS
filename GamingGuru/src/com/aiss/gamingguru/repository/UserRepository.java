package com.aiss.gamingguru.repository;

import java.util.Collection;

import com.aiss.gamingguru.domain.User;

public interface UserRepository {
	public Collection<User> getAll();
	public void put(User u);
	public User get(String id);
	public void remove(User u);
}
