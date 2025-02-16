package com.example.demo;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerRepository;
import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;
import com.example.demo.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	// jpaRepository<Question, Integer>를 상속 받은 reference class로 만든 variable에 instance주입
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("demo가 뭔가요");
		q1.setContent("demo에 대해서 알고 싶습니다");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void jpaTest2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(6, all.size());

		Question q = all.get(0);
		assertEquals("demo가 뭔가요", q.getSubject());
	}

	@Test
	void testJpa3() {
		Optional<Question> oq = this.questionRepository.findById(1L);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("demo가 뭔가요", q.getSubject());
		}
	}
	
	@Test
	void testJpa4() {
		List<Question> questions = this.questionRepository.findBySubject("demo가 뭔가요");

		// 결과 리스트의 크기 확인
		assertEquals(3, questions.size());

		// 각 질문의 ID 확인
		List<Long> expectedIds = Arrays.asList(1L, 3L, 5L);
	}

	@Test
	void testJpa5() {
		Optional<Question> oq = this.questionRepository.findById(1L);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목5");
		this.questionRepository.save(q);
	}

	@Test
	void testJpa6() {
		Optional<Question> oq = this.questionRepository.findById(2L);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("예 자동으로 생성 됩니다3");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		this.answerRepository.save(a);
	}

	@Transactional
	@Test
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(2L);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answers = q.getAnswers();
		assertEquals(3, answers.size());
		assertEquals("예 자동으로 생성 됩니다", answers.get(0).getContent());
	}

	@Test
	void testJpa8() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}
	}
}

