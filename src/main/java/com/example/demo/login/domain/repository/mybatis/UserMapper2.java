package com.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper2 {
	// 登録用メソッド
	public boolean insert(User user);

	// 1件検索用メソッド
	public User selectOne(String userId);

	// 全件検索
	public List<User> selectMany();

	// 1件更新用
	public boolean updateOne(User user);

	// 1件削除用
	public boolean deleteOne(String userId);

}
