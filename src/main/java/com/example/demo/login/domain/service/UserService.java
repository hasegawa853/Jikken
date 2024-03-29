package com.example.demo.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// insert用メソッド
	public boolean insert(User user) {
		int rowNumber = dao.insertOne(user);

		// 判定用メソッド
		boolean result = false;

		if (rowNumber > 0) {
			// insert成功
			result = true;
		}
		return result;
	}

	// カウント用メソッド
	public int count() {
		return dao.count();
	}

	// 全体取得用メソッド
	public List<User> selectMany() {
		return dao.selectMany();
	}

	// 1件取得用メソッド
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	// 1件更新用メソッド
	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		// 判定用変数
		boolean result = false;

		if (rowNumber > 0) {
			// update成功
			result = true;
		}
		return result;
	}

	public boolean deleteOne(String userId) {
		// 1件削除
		int rowNumber = dao.deleteOne(userId);
		// 判定用変数
		boolean result = false;

		if (rowNumber > 0) {
			// delete成功
			result = true;
		}
		return result;
	}

	public void userCsvOut() throws DataAccessException {
		dao.userCsvOut();
	}

	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
}
