package com.example.demo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//テスト用アノテーション
@RunWith(SpringRunner.class) // http://javatechnology.net/java/eclipse-junit5/参考hp
@SpringBootTest //
@Transactional
class FirstJUnit5Tests {

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
