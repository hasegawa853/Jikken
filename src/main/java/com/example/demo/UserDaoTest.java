package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

//テスト用アノテーション
@RunWith(SpringRunner.class) // ここと
@SpringBootTest // ここのアノテーションがClass<SpringRunner> cannot be resolved to a typeとなる
@Transactional // ライブラリがダウンロードされていない、されているが壊れている可能性はあるため
				// jarのフォルダを一度消して再インストールをしたい。
public class UserDaoTest {
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;

	// カウントメソッドのテスト
	@Test
	public void countTest1() {
		// カウントメソッドの結果が2件であることをテスト
		assertEquals(dao.count(), 2);
	}

	// カウントメソッドのテスト2
	// @Sql
	@Test
	@Sql("/testdata.sql") // @SQLと同様にcannot be resolved to a typeとなる
	public void countTest2() {
		// カウントメソッドの結果が3件であることをテスト
		assertEquals(dao.count(), 3);
	}

}
