package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

//テスト用アノテーション
@RunWith(SpringRunner.class) // http://javatechnology.net/java/eclipse-junit5/参考hp
@SpringBootTest //
@Transactional
class FirstJUnit5Tests {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// カウントメソッドのテスト１
	@Test
	public void countTest1() {
		// カウントメソッドの結果が2件であることをテスト
		// staticインポートはEcripseで自動でつけてくれないため、注意する
		assertEquals(dao.count(), 2);
	}// 最初のメソッドは正常

	// カウントメソッドのテスト２
	// ポイント＠sql
	@Test
	@Sql("/testdata.sql") // scriptは書いて、すぐに実行できるプログラム
	public void countTest2() {// 実行結果が'script' must not be null or emptyのエラー
		// カウントメソッドの結果が3件であることをテスト
		assertEquals(dao.count(), 3);
	}

}
