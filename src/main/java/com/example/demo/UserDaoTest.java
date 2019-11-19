package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.repository.UserDao;

//テスト用アノテーション
@RunWith(SpringRunner.class) // pom.xmlを見るとライブラリの記述はある。
@SpringBootTest // Junitだけ使用できないことがわかる。参照できないのはverをどこかで
@Transactional // 意図せず変えてしまったからか。JunitはEcripseで標準搭載だからpomに使う宣言を
				// すれば使えるはず。pomの記述が何かしらおかしい以外の原因が思いつかないが、
				// その解決法を探す必要あり。POMにJunitの記述がそもそもない？
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

	@Test
	@Sql("/testdata.sql") // @SQLと同様にcannot be resolved to a typeとなる
	public void countTest2() {
		// カウントメソッドの結果が3件であることをテスト
		assertEquals(dao.count(), 3);
	}

}
