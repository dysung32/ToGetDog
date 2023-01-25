package com.ssafy.togetdog.dummy.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.dog.domain.DogDTO;
import com.ssafy.togetdog.dummy.user.domain.LoginUser;
import com.ssafy.togetdog.dummy.user.domain.UserDTO;
import com.ssafy.togetdog.dummy.user.domain.UserIncludesDogsDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("���� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyUserController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "�α���", notes = "�Ϲ� �α����� �����մϴ�.")
	@PostMapping("/login")
	public ResponseEntity<?> generalLogin(@RequestParam String email, @RequestParam String password) {
		System.out.println("���� ��:" + email + ", " + password);
		LoginUser loginUser = new LoginUser(1245L, "ũ����", "����� ���۱� �漮��");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("user", loginUser);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "�Ҽ� �α���", notes = "�Ҽ� �α���(���̹�, īī��, ����)�� �����մϴ�.")
	@PostMapping("/sociallogin")
	public ResponseEntity<?> socialLogin(@RequestParam String code, @RequestParam String type) {
		System.out.println("���� ��:" + code + ", " + type);
		LoginUser loginUser = new LoginUser(1245L, "ũ����", "����� ���۱� �漮��");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("user", loginUser);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "ȸ������", notes = "ȸ�������� �����մϴ�.")
	@PostMapping()
	public ResponseEntity<?> registration(@RequestParam Map<String, Object> loginInfo) {
		String email = (String) loginInfo.get("email");
		String password = (String) loginInfo.get("password");
		String nickname = (String) loginInfo.get("nickname");
		String gender = (String) loginInfo.get("gender");
		int age = (int) loginInfo.get("age");
		String address = (String) loginInfo.get("address");
		String regionCode = (String) loginInfo.get("regionCode");
		
		System.out.println("���� ��:" + email + ", " + password + ", " + nickname);
		System.out.println("���� ��:" + gender + ", " + age + ", " + address + ", " + regionCode);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "ȸ������ �Ǿ����ϴ�..");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "�̸��� �ߺ� Ȯ��", notes = "�̸����� �ߺ��Ǵ� �� ���θ� Ȯ�����ݴϴ�. ssafy@naver.com�� ������ �ߺ��Ǿ��ٰ� �������� �Ǿ��ֽ��ϴ�.")
	@GetMapping("/email")
	public ResponseEntity<?> emailCheck(@RequestParam String email) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (email.equals("ssafy@naver.com")) {
			resultMap.put("result", FAIL);
			resultMap.put("msg", "�ߺ��� �̸��� ���Դϴ�.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.CONFLICT);
		} else {
			resultMap.put("result", SUCCESS);
			resultMap.put("msg", "��� ������ �̸����Դϴ�.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "�г��� �ߺ� Ȯ��", notes = "�г����� �ߺ��Ǵ� �� ���θ� Ȯ�����ݴϴ�. ssafy�� ������ �ߺ��Ǿ��ٰ� �������� �Ǿ��ֽ��ϴ�.")
	@GetMapping("/nickname")
	public ResponseEntity<?> nicknameCheck(@RequestParam String nickname) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (nickname.equals("ssafy")) {
			resultMap.put("result", FAIL);
			resultMap.put("msg", "�ߺ��� �г��� ���Դϴ�.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.CONFLICT);
		} else {
			resultMap.put("result", SUCCESS);
			resultMap.put("msg", "��� ������ �г����Դϴ�.");
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "ȸ�� ���� ��ȸ", notes = "�ش� ������ ������ ��ȸ�մϴ�.")
	@GetMapping("/{userid}")
	public ResponseEntity<?> findUser(@PathVariable String userid) {
		// dummy �̹Ƿ� userId�� ������� ���� return �մϴ�.
		int searchUserId = Integer.parseInt(userid);
		UserDTO result = new UserDTO();
		result.setUserId(searchUserId);
		result.setNickName("ũ������");
		result.setUserAge(23);
		result.setUserGender("female");
		result.setAddress("����� ���۱� �漮��");
		result.setRegionCode(11439);
		result.setSocial("naver");
		result.setRating(3.42);
		return new ResponseEntity<UserDTO>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "ȸ�� ���� ����", notes = "�ش� ������ ������ �����մϴ�.")
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestParam Map<String, Object> updateInfo) {
		String nickname = (String) updateInfo.get("nickname");
		String gender = (String) updateInfo.get("gender");
		int age = (int) updateInfo.get("age");
		String address = (String) updateInfo.get("address");
		String region_code = (String) updateInfo.get("region_code");
		
		System.out.println("���� ��:" + nickname + ", " + gender + ", " + age + ", " + address + ", " + region_code);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "���� ������ �����Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "��й�ȣ ����", notes = "�ش� ������ ��й�ȣ�� �����մϴ�.")
	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestParam String password, @RequestParam String newPassword) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��й�ȣ�� �����Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "ȸ�� Ż��", notes = "ȸ�� Ż�� �����մϴ�.")
	@DeleteMapping()
	public ResponseEntity<?> deleteUser() {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "ȸ�� Ż��Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "��й�ȣ ã��", notes = "�ش� ������ ��й�ȣ�� �缳���Ͽ� �̸��Ϸ� �ۺ��մϴ�.")
	@GetMapping("/password")
	public ResponseEntity<?> findPassword(@RequestParam String email) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�̸����� �ۺ��߽��ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "�α׾ƿ�", notes = "�α׾ƿ��� �����մϴ�.")
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�α׾ƿ� ó�� �߽��ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "������ ������ ������ ���� ���� ��ȸ", notes = "�ش� ������ ����� ������ ������ ������ ������ ������ ��ȯ�մϴ�.")
	@GetMapping("/includesDog/{userid}")
	public ResponseEntity<?> logout(@PathVariable long userid) {
		UserIncludesDogsDTO result = new UserIncludesDogsDTO();
		
		DogDTO dog = new DogDTO();
		dog.setDogId(114L);
		dog.setUserId(userid);
		dog.setDogName("�ǻ�");
		dog.setDogGender("female");
		dog.setDogType("��Ƽ��");
		dog.setDogAge(72);
		dog.setDogWeight(3.4);
		dog.setDogNeutered(true);
		dog.setDogCharacter1("independent");
		dog.setDogCharacter2("active");
		dog.setDescription("Ȱ�����̰� ���ؿ�");
		dog.setDogProfile("asdfasdf.jpg");
		
		result.setUserId(userid);
		result.setNickName("�ǻ߾���");
		result.setUserAge(28);
		result.setAddress("����� ���۱� �漮��");
		result.setRegionCode("11455");
		result.setSocial("naver");
		result.setRating(3.41);
		
		// ������ ���� �߰�
		result.getDog().add(dog);
		
		return new ResponseEntity<UserIncludesDogsDTO>(result, HttpStatus.OK);
	}
}
