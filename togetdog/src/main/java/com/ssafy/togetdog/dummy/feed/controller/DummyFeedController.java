package com.ssafy.togetdog.dummy.feed.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.togetdog.dummy.dog.domain.DogDTO;
import com.ssafy.togetdog.dummy.feed.domain.BoardDTO;
import com.ssafy.togetdog.dummy.feed.domain.CommentDTO;
import com.ssafy.togetdog.dummy.feed.domain.SimpleBoardDTO;
import com.ssafy.togetdog.dummy.user.domain.UserDTO;
import com.ssafy.togetdog.dummy.user.domain.UserIncludesDogsDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("�Խù� ���� ���� API : ���� ���� �ֵ� ���� dummy ����� ���ɴϴ�.")
public class DummyFeedController {

	private static final String SUCCESS = "success";
	// private static final String FAIL = "fail";

	@ApiOperation(value = "Ȩ ȭ�� ��ȸ", notes = "Ȩ ȭ�� ��ȸ�� �ʿ��� ���� �����մϴ�.")
	@GetMapping("/home")
	public ResponseEntity<?> getHomeInfo(@RequestParam String pageNo) {
		DogDTO dog = new DogDTO();
		dog.setDogId(123L);
		dog.setUserId(84L);
		dog.setDogName("ũ��");
		dog.setDogGender("female");
		dog.setDogType("���޶�Ͼ�");
		dog.setDogAge(24);
		dog.setDogWeight(3.4);
		dog.setDogNeutered(true);
		dog.setDogCharacter1("independent");
		dog.setDogCharacter2("active");
		dog.setDescription("Ȱ�����̰� ���ؿ�");
		dog.setDogProfile("asdfasdf.jpg");

		List<SimpleBoardDTO> boardList = new ArrayList<SimpleBoardDTO>();
		SimpleBoardDTO board = new SimpleBoardDTO();
		board.setDog(dog);
		board.setBoardId(1);
		board.setImage("cream.png");
		boardList.add(board);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("boardList", boardList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ǵ�", notes = "�ǵ� ������ �����մϴ�.")
	@GetMapping("/feed")
	public ResponseEntity<?> getFeedInfo(@RequestParam String pageNo) {

		// �ǵ� ���� ����
		UserIncludesDogsDTO user = new UserIncludesDogsDTO();

		// �ǵ� ������ ������ 3����
		DogDTO dog = new DogDTO();
		dog.setDogId(114L);
		dog.setUserId(123L);
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

		user.setUserId(123L);
		user.setNickName("�ǻ߾���");
		user.setUserAge(28);
		user.setAddress("����� ���۱� �漮��");
		user.setRegionCode("11455");
		user.setSocial("naver");
		user.setRating(3.41);
		user.getDog().add(dog);

		// �ΰ� ����
		user.setFollow(true);
		user.setFollowCnt(300);

		// feed �Խù� ����Ʈ
		List<SimpleBoardDTO> boardList = new ArrayList<SimpleBoardDTO>();
		SimpleBoardDTO board = new SimpleBoardDTO();
		board.setDog(dog);
		board.setBoardId(123);
		board.setImage("cream.png");
		boardList.add(board);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("user", user);
		resultMap.put("feed", boardList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ǵ� �ϴ� �Խù� ��ȸ", notes = "�ǵ� �ϴ� �Խù��� ������ ��ȣ�� ���� ��ȸ�մϴ�.")
	@GetMapping("/list/{dogId}")
	public ResponseEntity<?> getBoardListByDogId(@PathVariable String dogId) {
		// feed �Խù� ����Ʈ
		List<SimpleBoardDTO> boardList = new ArrayList<SimpleBoardDTO>();
		SimpleBoardDTO board = new SimpleBoardDTO();
		DogDTO dog = new DogDTO();
		dog.setDogId(Long.parseLong(dogId));
		board.setDog(dog);
		board.setBoardId(123);
		board.setImage("cream.png");
		boardList.add(board);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("feed", boardList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�Խù� ��ȸ", notes = "Ư�� �Խù��� ��ȸ�մϴ�.")
	@GetMapping("/board/{boardId}")
	public ResponseEntity<?> getBoardInfo(@PathVariable String boardId, @RequestParam String pageNo) {
		BoardDTO boardInfo = new BoardDTO();
		boardInfo.setDogId(24L);
		boardInfo.setDogName("ũ��");
		boardInfo.setDogType("���޶�Ͼ�");
		boardInfo.setDogGender("female");
		boardInfo.setDogAge(24);
		boardInfo.setDogImage("cream.png");
		boardInfo.setLiked(true);
		boardInfo.setFollwed(true);
		boardInfo.setLikeCnt(34);
		boardInfo.setContent("ũ���� ȭ���� �ƴ���?");
		
		CommentDTO comment = new CommentDTO();
		comment.setCommentId(2L);
		comment.setUserId(3L);
		comment.setUserName("�߻Ǿƺ�");
		comment.setContent("ũ���� ȭ������~");
		
		boardInfo.getComments().add(comment);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("board", boardInfo);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�Խù� ���", notes = "���ο� �Խù��� ����մϴ�.")
	@PostMapping("/board")
	public ResponseEntity<?> postingBoard(
			@RequestParam String dogId,
			@RequestParam(value="image", required = true) MultipartFile image,
			@RequestParam String content) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardId", 123);
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�Խù��� ��� �Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�Խù� ����", notes = "�ش� �Խù��� �����մϴ�.")
	@DeleteMapping("/board")
	public ResponseEntity<?> deleteBoard(
			@RequestParam String pageNo,
			@RequestParam String boardId
			) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�Խù��� ���� �Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�Խù� ����", notes = "�ش� �Խù��� �����մϴ�.")
	@PutMapping("/board")
	public ResponseEntity<?> updateBoard(
			@RequestParam String pageNo,
			@RequestParam String boardId,
			@RequestParam String content
			) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "�Խù��� ���� �Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��� ���", notes = "�Խñۿ� ���ο� ����� ����մϴ�.")
	@PostMapping("/board/comment")
	public ResponseEntity<?> postingComment(
			@RequestParam String boardId
			) {
		
		BoardDTO boardInfo = new BoardDTO();
		List<CommentDTO> commentList = boardInfo.getComments();
		
		CommentDTO comment = new CommentDTO();
		comment.setCommentId(1);
		comment.setUserId(24);
		comment.setUserName("�߻Ǿƺ�");
		comment.setContent("ȭ������");
		commentList.add(comment);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("comments", commentList);
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "����� ��� �Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "��� ����", notes = "�Խñۿ� �޸� Ư�� ����� �����մϴ�.")
	@DeleteMapping("/board/comment")
	public ResponseEntity<?> deleteComment(
			@RequestParam String commentId
			) {
		BoardDTO boardInfo = new BoardDTO();
		List<CommentDTO> commentList = boardInfo.getComments();
		
		CommentDTO comment = new CommentDTO();
		comment.setCommentId(1);
		comment.setUserId(24);
		comment.setUserName("�߻Ǿƺ�");
		comment.setContent("ȭ������");
		commentList.add(comment);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("comments", commentList);
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "����� ���� �Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "���ƿ�", notes = "Ư�� �Խù��� ���ƿ並 �մϴ�.")
	@PostMapping("/board/like")
	public ResponseEntity<?> doLike(
			@RequestParam String pageNo,
			@RequestParam String boardId
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("likeCnt", 255);
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "���ƿ䰡 �ݿ��Ǿ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "���ƿ� ���", notes = "Ư�� �Խù��� �ݿ��ߴ� ���ƿ並 ����մϴ�.")
	@DeleteMapping("/board/like")
	public ResponseEntity<?> cancelLike(
			@RequestParam String pageNo,
			@RequestParam String boardId
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("likeCnt", 255);
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "���ƿ䰡 ��ҵǾ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ȷο� ����Ʈ ��ȸ", notes = "������ �ȷο��ϴ� ������ ����Ʈ�� ��ȸ�մϴ�.")
	@GetMapping("/follow/following")
	public ResponseEntity<?> getFollowList(
			@RequestParam String userId
			) {
		List<DogDTO> dogList = new ArrayList<DogDTO>();
		
		DogDTO dog = new DogDTO();
		dog.setDogId(114);
		dog.setUserId(12414);
		dog.setDogName("�ǻ�");
		dog.setDogGender("female");
		dog.setDogType("��Ƽ��");
		dog.setDogAge(72);
		dog.setDogWeight(3.4);
		dog.setDogNeutered(true);
		dog.setDogCharacter1("independent");
		dog.setDogCharacter2("active");
		dog.setDescription("Ȱ�����̰� ���ؿ�.");
		dog.setDogProfile("adsfasd.jpg");
		
		dogList.add(dog);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("dogs", dogList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ȷο� ����Ʈ ��ȸ", notes = "�ش� �������� �ȷο��ϴ� ���� ����Ʈ�� ��ȸ�մϴ�.")
	@GetMapping("/follow/follower")
	public ResponseEntity<?> getFollwerList(@RequestParam String dogId) {
		List<UserDTO> userList = new ArrayList<UserDTO>();

		UserDTO user = new UserDTO();
		
		user.setUserId(12414);
		user.setNickName("ũ������");
		user.setUserAge(28);
		user.setUserGender("female");
		user.setAddress("����Ư���� ���۱� �漮��");
		user.setRegionCode(11439);
		user.setSocial("naver");
		user.setRating(3.14);
		
		userList.add(user);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("users", userList);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ȷο�", notes = "Ư�� �������� �ȷο� ó���մϴ�.")
	@PostMapping("/follow")
	public ResponseEntity<?> doFollow(
			@RequestParam String pageNo,
			@RequestParam String dogId
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "���ƿ並 �ݿ��߽��ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "�ȷο� ���", notes = "Ư�� ���������� �ߴ� �ȷο츦 ���ó���մϴ�.")
	@DeleteMapping("/follow")
	public ResponseEntity<?> cancelFollow(
			@RequestParam String pageNo,
			@RequestParam String dogId
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", SUCCESS);
		resultMap.put("msg", "���ƿ䰡 ��ҵǾ����ϴ�.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
