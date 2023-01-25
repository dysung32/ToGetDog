package com.ssafy.togetdog.dummy.facility.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.facility.domain.storeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/facility")
@Api("�ְ� �ü� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyFacilityController {
	
	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "�� ��ġ ��� �ü� ����Ʈ ��ȸ ", notes = "�� ��ġ�� ������� �����̿� �ִ� �ְ� �ü��� ��ȸ�մϴ�.")
	@GetMapping
	public ResponseEntity<?> getHomeInfo(
			@RequestParam String latitude,
			@RequestParam String longitude
			) {
		List<storeDTO> storeList = new ArrayList<storeDTO>();
		storeDTO storeInfo = new storeDTO();
		storeInfo.setFacilityId(7);
		storeInfo.setFacilityName("1�����´����౹");
		storeInfo.setFacilityAddress("������ ��â�� ���θ� �����θ� 101���� 74 - 2");
		storeInfo.setType("�ݷ��Ƿ�");
		storeInfo.setLongitude(37.6376755);
		storeInfo.setLatitude(128.559285);
		storeInfo.setDistance(450.23432);
		storeInfo.setPhone("033-333-7300");
		
		boolean[] closedDays = new boolean[8];
		closedDays[6] = true;
		closedDays[7] = true;
		
		String[] openingHours = new String[7];
		openingHours[0] = "09:00~18:00";
		openingHours[1] = "09:00~18:00";
		openingHours[2] = "09:00~18:00";
		openingHours[3] = "09:00~18:00";
		openingHours[4] = "09:00~18:00";
		openingHours[5] = "10:00~17:00";
		openingHours[6] = "10:00~14:00";
		
		storeInfo.setClosedDays(closedDays);
		storeInfo.setOpeningHours(openingHours);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("storeList", storeList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
