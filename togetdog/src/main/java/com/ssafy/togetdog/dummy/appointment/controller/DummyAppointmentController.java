package com.ssafy.togetdog.dummy.appointment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.togetdog.dummy.appointment.domain.MeetingDTO;
import com.ssafy.togetdog.dummy.dog.domain.DogDTO;
import com.ssafy.togetdog.dummy.dog.domain.DogForMeetingDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/meeting")
@Api("��å ��� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyAppointmentController {

	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "��å ��� ����Ʈ ��ȸ", notes = "���� ���� ��� ��å ��� ����Ʈ�� ��ȯ�մϴ�. ����� Authorization ��ſ� ���� userId�� �Է¹޵��� �Ǿ��ֽ��ϴ�.")
	@GetMapping
	public ResponseEntity<?> getMeetingList(@RequestParam String userId) {

		List<MeetingDTO> meetingList = new ArrayList<MeetingDTO>();
		MeetingDTO meeting = new MeetingDTO();
		meeting.setPartnerName("Ŀ���ߵ���");
		meeting.setRating(4.7);
		meeting.setAppointmentId(12345);
		meeting.setPlace("���� ���� ����");
		meeting.setDate(new Date());

		List<DogForMeetingDTO> myDogs = new ArrayList<DogForMeetingDTO>();
		myDogs.add(new DogForMeetingDTO("choco.png", "choco"));
		myDogs.add(new DogForMeetingDTO("choco.png", "choco"));
		myDogs.add(new DogForMeetingDTO("choco.png", "choco"));
		meeting.setMyDogs(myDogs);

		List<DogForMeetingDTO> partnerDogs = new ArrayList<DogForMeetingDTO>();
		partnerDogs.add(new DogForMeetingDTO("choco.png", "choco", "independence", "active", true));
		partnerDogs.add(new DogForMeetingDTO("choco.png", "choco", "independence", "active", true));
		partnerDogs.add(new DogForMeetingDTO("choco.png", "choco", "independence", "active", true));
		meeting.setPartnerDogs(partnerDogs);

		meeting.setStatus("done");
		meeting.setRated(true);

		meetingList.add(meeting);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("meetingList", meetingList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ��� ��û", notes = "���ο� ��å ����� ��û�մϴ�.")
	@PostMapping
	public ResponseEntity<?> postingNewAppointment(@RequestParam Map<String, Object> appointmentInfo) {

		// ������ ���� �ϸ� ���� ���
		long userId = Long.parseLong((String) appointmentInfo.get("userId"));
		@SuppressWarnings("unchecked")
		List<DogDTO> myDogs = (List<DogDTO>) appointmentInfo.get("myDogs");
		@SuppressWarnings("unchecked")
		List<DogDTO> partnerDogs = (List<DogDTO>) appointmentInfo.get("partnerDogs");
		Date date = (Date) appointmentInfo.get("date");
		String place = (String) appointmentInfo.get("place");

		// print check section /////////////////////////////////////////////////
		System.out.println("param ���� �� Ȯ��:");
		System.out.println("��û ��� id : " + userId);
		System.out.println("my dog list: ");
		for (DogDTO dog : myDogs) {
			System.out.println(dog);
		}
		System.out.println("partner's dog list");
		for (DogDTO dog : partnerDogs) {
			System.out.println(dog);
		}
		System.out.println("��û �ð� : " + date);
		System.out.println("��û ��� : " + place);
		////////////////////////////////////////////////////

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��å ��û�� ���������� �̷�������ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ��� ���", notes = "��û �ߴ� ����� ��� ó���մϴ�.")
	@PutMapping("/cancel")
	public ResponseEntity<?> cancelAppointment(@RequestParam String appointmentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��å ��û�� ��ҵǾ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ��� ����", notes = "��û ���� ����� ���� ó���մϴ�.")
	@PutMapping("/accept")
	public ResponseEntity<?> acceptAppointment(@RequestParam String appointmentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��å ��û�� �����Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ��� ����", notes = "��û ���� ����� ���� ó���մϴ�.")
	@DeleteMapping
	public ResponseEntity<?> denyAppointment(@RequestParam String appointmentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "��å ��û�� �����Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ���� �ű��", notes = "�Ϸ��� ��å ��ӿ� ������ �ο��մϴ�.")
	@PostMapping("/rating")
	public ResponseEntity<?> ratingAppointment(@RequestParam String appointmentId, @RequestParam String rating) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "������ �ݿ��Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��å ģ�� ��õ�ޱ�", notes = "Ư�� ���������� �´� ��å ģ���� ��õ���ݴϴ�.")
	@PostMapping("/recommend")
	public ResponseEntity<?> recommendDog(@RequestParam String dogId) {

		//////////////////////// HARD CODING//////////////////////
		List<DogDTO> dogList = new ArrayList<DogDTO>();
		DogDTO dog1 = new DogDTO();
		dog1.setDogId(23);
		dog1.setDogProfile("choco.png");
		dog1.setDogName("����");
		dog1.setDogCharacter1("independent");
		dog1.setDogCharacter2("active");
		dog1.setDogGender("female");
		dog1.setDogType("Ǫ��");
		dog1.setDogAge(2);
		dog1.setDogNeutered(true);
		dog1.setUserId(42);
		dog1.setNickName("���");
		dog1.setAddress("����Ư���� ������ ���ﵿ");
		dogList.add(dog1);

		DogDTO dog2 = new DogDTO();
		dog2.setDogId(23);
		dog2.setDogProfile("choco.png");
		dog2.setDogName("����");
		dog2.setDogCharacter1("independent");
		dog2.setDogCharacter2("active");
		dog2.setDogGender("female");
		dog2.setDogType("Ǫ��");
		dog2.setDogAge(2);
		dog2.setDogNeutered(true);
		dog2.setUserId(42);
		dog2.setNickName("���");
		dog2.setAddress("����Ư���� ������ ���ﵿ");
		dogList.add(dog2);

		DogDTO dog3 = new DogDTO();
		dog3.setDogId(23);
		dog3.setDogProfile("choco.png");
		dog3.setDogName("����");
		dog3.setDogCharacter1("independent");
		dog3.setDogCharacter2("active");
		dog3.setDogGender("female");
		dog3.setDogType("Ǫ��");
		dog3.setDogAge(2);
		dog3.setDogNeutered(true);
		dog3.setUserId(42);
		dog3.setNickName("���");
		dog3.setAddress("����Ư���� ������ ���ﵿ");
		dogList.add(dog3);
		////////////////////////HARD CODING//////////////////////

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("dogs", dogList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
