package com.ssafy.togetdog.dummy.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.dog.domain.DogDTO;
import com.ssafy.togetdog.dummy.user.domain.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("�˻� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummySearchController {

	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "", notes = "")
	@GetMapping()
	public ResponseEntity<?> methodName(
			@RequestParam String pageNo,
			@RequestParam boolean isDog 
			) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		if(isDog) {
			List<DogDTO> dogList = new ArrayList<DogDTO>();
			resultMap.put("list", dogList);
		} else {
			List<UserDTO> userList = new ArrayList<UserDTO>();
			resultMap.put("list", userList);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
