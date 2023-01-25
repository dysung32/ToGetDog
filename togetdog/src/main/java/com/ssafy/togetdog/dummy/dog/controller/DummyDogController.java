package com.ssafy.togetdog.dummy.dog.controller;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dog")
@Api("������ ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyDogController {
	
	private static final String SUCCESS = "success";
	//private static final String FAIL = "fail";
	
	@ApiOperation(value = "������ ���� ��ȸ", notes = "�ش� �������� ������ ��ȸ�մϴ�.")
	@GetMapping("/{dogid}")
	public ResponseEntity<?> getDogInfo(@PathVariable String dogid) {
		DogDTO dog = new DogDTO();
		dog.setDogId(Long.parseLong(dogid));
		dog.setUserId(84L);
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
		
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("dpg", dog);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "������ ���� ���", notes = "���ο� �������� ����մϴ�.")
	@PostMapping
	public ResponseEntity<?> registDog(@RequestParam Map<String, Object> dogInfo) {
		DogDTO dog = new DogDTO();
		dog.setUserId(84L);
		dog.setDogName((String) dogInfo.get("dogName"));
		dog.setDogGender((String) dogInfo.get("dogGender"));
		dog.setDogType((String) dogInfo.get("dogType"));
		dog.setDogAge((int) dogInfo.get("dogAge"));
		dog.setDogWeight((double) dogInfo.get("dogWeight"));
		dog.setDogNeutered((boolean) dogInfo.get("dogNeutered"));
		dog.setDogCharacter1((String) dogInfo.get("dogCharacter1"));
		dog.setDogCharacter2((String) dogInfo.get("dogCharacter2"));
		dog.setDescription((String) dogInfo.get("description"));
		dog.setDogProfile((String) dogInfo.get("dogProfile"));
		
		System.out.println("���� �� : " + dog);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�ش� �������� ������ ����߽��ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "������ ���� ����", notes = "�ش� �������� �����մϴ�.")
	@DeleteMapping("/{dogid}")
	public ResponseEntity<?> deleteDog(@PathVariable String dogid) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�ش� �������� ������ �����߽��ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "������ ���� ����", notes = "�ش� �������� �����մϴ�.")
	@PutMapping
	public ResponseEntity<?> updateDog(@RequestParam Map<String, Object> dogInfo) {
		
		DogDTO dog = new DogDTO();
		dog.setDogId((Long) dogInfo.get("dogId"));
		dog.setUserId(84L);
		dog.setDogName((String) dogInfo.get("dogName"));
		dog.setDogGender((String) dogInfo.get("dogGender"));
		dog.setDogType((String) dogInfo.get("dogType"));
		dog.setDogAge((int) dogInfo.get("dogAge"));
		dog.setDogWeight((double) dogInfo.get("dogWeight"));
		dog.setDogNeutered((boolean) dogInfo.get("dogNeutered"));
		dog.setDogCharacter1((String) dogInfo.get("dogCharacter1"));
		dog.setDogCharacter2((String) dogInfo.get("dogCharacter2"));
		dog.setDescription((String) dogInfo.get("description"));
		dog.setDogProfile((String) dogInfo.get("dogProfile"));
		
		System.out.println("���� �� : " + dog);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�ش� �������� ������ �����߽��ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}
}
