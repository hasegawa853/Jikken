//package com.example.demo;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
////ポイント１　Springのモック
//@runwith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class LoginControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	publc void ログイン画面表示() throws Exception{
//		//画面表示内容の確認
//		mockMvc.perform(get("/login"))
//		.andExpect(status().isOk())
//		.andExpect(content().string(containsString("ユーザーID ")));
//	}
//
//}
