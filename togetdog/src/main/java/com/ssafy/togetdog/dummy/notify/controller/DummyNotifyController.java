package com.ssafy.togetdog.dummy.notify.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.notify.domain.NoticeDTO;
import com.ssafy.togetdog.dummy.notify.domain.NotifyDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notify")
@Api("�˸� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyNotifyController {
	
	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "�˸� ����Ʈ ��ȸ", notes = "�˸� �ǿ� ������ �� ������ ���� ��ü�� ��ȯ�մϴ�.")
	@GetMapping
	public ResponseEntity<?> getNotifyList(
			) {
		NotifyDTO notifyInfo = new NotifyDTO();
		notifyInfo.setMeetingCnt(244);
		notifyInfo.setMeetingCancel(true);
		
		List<NoticeDTO> noticeList = new ArrayList<NoticeDTO>();
		NoticeDTO noticeInfo = new NoticeDTO();
		noticeInfo.setType("���ƿ�");
		noticeInfo.setNickName("ũ������");
		noticeInfo.setDogName("�ǻ�");
		noticeInfo.setId(1435);
		noticeList.add(noticeInfo);
		notifyInfo.setNotice(noticeList);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("notifyInfo", notifyInfo);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "��� ��� �˸� Ȯ��", notes = "��� ��� �˸��� ������ �� Ȯ�� ���θ� ������Ʈ�մϴ�.")
	@PutMapping("/cancel")
	public ResponseEntity<?> confirmCancelNotify(
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��� ��� �˸��� Ȯ��ó���߽��ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
