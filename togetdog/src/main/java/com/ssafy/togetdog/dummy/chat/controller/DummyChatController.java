package com.ssafy.togetdog.dummy.chat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.chat.domain.ChatDTO;
import com.ssafy.togetdog.dummy.chat.domain.ChatListDTO;
import com.ssafy.togetdog.dummy.chat.domain.ChatRoomDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/chat")
@Api("ä�� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyChatController {
	
	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "ä�� �� ��� ��ȸ", notes = "����ڰ� ���� ���� ä�� �� ����� ��ȸ�մϴ�.")
	@GetMapping("/list")
	public ResponseEntity<?> getChatList(
			) {
		List<ChatListDTO> dmRoomList = new ArrayList<ChatListDTO>();
		ChatListDTO dmRoomInfo = new ChatListDTO();
		dmRoomInfo.setUserId(1234);
		dmRoomInfo.setUserAge(24);
		dmRoomInfo.setGender("female");
		dmRoomInfo.setAddress("��⵵ ������ �д籸");
		dmRoomInfo.setChatRoomId(123);
		dmRoomInfo.setLastChatContent("�ȳ��ϼ���!!");
		dmRoomList.add(dmRoomInfo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("dm", dmRoomList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "ä�� �� ��ȸ", notes = "����ڰ� ���� ���� Ư�� ä���� ��ȸ�մϴ�.")
	@GetMapping
	public ResponseEntity<?> getChat(
			@RequestParam String userId //ä�� ���� id
			) {
		
		ChatRoomDTO chatRoomInfo = new ChatRoomDTO();
		chatRoomInfo.setUserId(14);
		chatRoomInfo.setNickName("�ǻ߾���");
		chatRoomInfo.setUserAge(28);
		chatRoomInfo.setGender("female");
		chatRoomInfo.setAddress("��⵵ ������ �д籸");
		
		List<ChatDTO> chats = new ArrayList<ChatDTO>();
		ChatDTO chat = new ChatDTO();
		chat.setWriter("�Ǻ����");
		chat.setContent("�ȳ��ϼ���");
		chat.setTime(new Date());
		chats.add(chat);
		
		chatRoomInfo.setChats(chats);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("chatRoomInfo", chatRoomInfo);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "ä�� �� ����", notes = "����ڰ� ���� ���� Ư�� ä�ù��� �����մϴ�.")
	@PutMapping
	public ResponseEntity<?> deleteChat(
			@RequestParam String userId //ä�� ���� id
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
