package com.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;

	private Map<String, String> radioMarriage;

	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();

		//
		radio.put("既婚", "true");
		radio.put("未婚", "false");

		return radio;
	}

//ユーザー一覧画面のGET用メソッド
	@GetMapping("/home")
	public String getHome(Model model) {
//コンテンツ部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/home::home_contents");

		return "login/homeLayout";
	}

	// ユーザー一覧の画面用のGET用メソッド
	@GetMapping("/userList")
	public String getUserList(Model model) {
		// コンテンツ部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/userList::userList_contents");
		// ユーザー一覧の生成
		List<User> userList = userService.selectMany();
		// modelにユーザーリストを登録
		model.addAttribute("userList", userList);
		// データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount", count);

		return "login/homeLayout";
	}

	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		// ユーザ確認
		System.out.println("userId=" + userId);
		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");

		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		if (userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			// Userクラスをformクラスに変換
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());

			model.addAttribute("signupForm", form);

			return "login/homeLayout";

		}
		return userId;/// エラーの自動追加で解決した部分のため、要注意
	}

	// ボタン名によるメソッド判定
	// ユーザー更新用処理
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");
		// Userインスタンスの生成
		User user = new User();
		// フォームクラスをUserクラスに変換
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		try {

			boolean result = userService.updateOne(user);
			if (result == true) {
				model.addAttribute("result", "更新成功");
			} else {
				model.addAttribute("result", "更新失敗");
			}

		} catch (DataAccessException e) {
			model.addAttribute("result", "更新失敗（トランザクションテスト）");
		}

		// ユーザー一覧画面を表示
		return getUserList(model);
	}

	// ユーザー削除用処理
	@PostMapping(value = "/userDetail", params = "delete")
	public String getUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
		// 削除実行
		boolean result = userService.deleteOne(form.getUserId());// 使用しているため、黄色波線はバグ
		if (result = true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		return getUserList(model);
	}

	// ログアウト用メソッド
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}

	// ユーザー一覧のcsv出力用メソッド
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model) {
		userService.userCsvOut();
		byte[] bytes = null;
		try {
			bytes = userService.getFile("sample.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// HTTPヘッダーの設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample.csv");

		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}

	// アドミン権限専用画面のGet用メソッド
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/admin::admin_contents");
		// レイアウト用テンプレート
		return "login/homeLayout";
	}

}
