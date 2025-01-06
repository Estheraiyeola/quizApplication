package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Question;
import com.essiecodes.databasequizapplication.data.model.Quiz;
import com.essiecodes.databasequizapplication.data.model.User;
import com.essiecodes.databasequizapplication.data.model.UserRole;
import com.essiecodes.databasequizapplication.data.repository.QuizRepo;
import com.essiecodes.databasequizapplication.data.repository.QuizResultRepo;
import com.essiecodes.databasequizapplication.dto.request.*;
import com.essiecodes.databasequizapplication.dto.response.CreatedQuizResponse;
import com.essiecodes.databasequizapplication.dto.response.QuizScoreResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;
import com.essiecodes.databasequizapplication.exception.QuizException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuizServiceTest {

    @Autowired
    private QuizRepo quizRepository;

    @Autowired
    private QuizService quizService;

    private CreateQuizRequest createQuizRequest;
    private User createdUser;
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private QuizResultRepo quizResultRepo;


    @BeforeEach
    void setUp() {

        courseService.deleteAll();
        quizResultRepo.deleteAll();
        quizRepository.deleteAll();
        userService.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setSemicolonEmail("student@semicolon.africa");
        registerUserRequest.setUserRole(UserRole.STUDENT);
        registerUserRequest.setPassword("password");

        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getMessage(), is("Student registered"));
        createdUser = response.getUser();


        CreateOptionRequest createOptionRequest1 = new CreateOptionRequest();
        createOptionRequest1.setOption("A unique identifier");

        CreateOptionRequest createOptionRequest2 = new CreateOptionRequest();
        createOptionRequest2.setOption("A foreign key");

        CreateOptionRequest createOptionRequest3 = new CreateOptionRequest();
        createOptionRequest3.setOption("A database column");

        CreateOptionRequest createOptionRequest4 = new CreateOptionRequest();
        createOptionRequest4.setOption("A table");

        List<CreateOptionRequest> optionRequestList = new ArrayList<>(List.of(createOptionRequest1, createOptionRequest2, createOptionRequest3, createOptionRequest4));
        // Create mock data for testing
        QuestionRequest question1 = new QuestionRequest();
        question1.setQuestion("What is a primary key?");
        question1.setAnswer("A unique identifier");
        question1.setOptions(optionRequestList);
        question1.setMarks(5);

        CreateOptionRequest createOptionRequest5 = new CreateOptionRequest();
        createOptionRequest5.setOption("A unique identifier");

        CreateOptionRequest createOptionRequest6 = new CreateOptionRequest();
        createOptionRequest6.setOption("A reference to another table");

        CreateOptionRequest createOptionRequest7 = new CreateOptionRequest();
        createOptionRequest7.setOption("A database column");

        CreateOptionRequest createOptionRequest8 = new CreateOptionRequest();
        createOptionRequest8.setOption("A table");

        List<CreateOptionRequest> optionRequestList2 = new ArrayList<>(List.of(createOptionRequest5, createOptionRequest6, createOptionRequest7, createOptionRequest8));



        QuestionRequest question2 = new QuestionRequest();
        question2.setQuestion("What is a foreign key?");
        question2.setOptions(optionRequestList2);
        question2.setAnswer("A unique identifier");
        question2.setMarks(5);

        createQuizRequest = new CreateQuizRequest();
        createQuizRequest.setTitle("Database Basics");
        createQuizRequest.setQuestions(Arrays.asList(question1, question2));
    }

    @Test
    void testCreateQuiz() {
        // Act
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Assert
        assertNotNull(savedQuiz);
        assertNotNull(savedQuiz.getId());
        assertEquals("Database Basics", savedQuiz.getTitle());
        assertEquals(2, savedQuiz.getQuestionList().size());

        Question question1 = savedQuiz.getQuestionList().get(0);
        assertEquals("What is a primary key?", question1.getQuestion());
        assertEquals(4, question1.getOptions().size());
        assertEquals("A unique identifier", question1.getAnswer());

        Question question2 = savedQuiz.getQuestionList().get(1);
        assertEquals("What is a foreign key?", question2.getQuestion());
        assertEquals(4, question2.getOptions().size());
        assertEquals("A unique identifier", question2.getAnswer());
    }

    @Test
    void testSaveAndRetrieveQuiz() {
        // Save the quiz
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Retrieve the quiz
        Quiz retrievedQuiz = quizService.findQuizBy(savedQuiz.getId());

        // Assert
        assertNotNull(retrievedQuiz);
        assertEquals(savedQuiz.getId(), retrievedQuiz.getId());
        assertEquals(savedQuiz.getTitle(), retrievedQuiz.getTitle());
    }

    @Test
    void testEvaluateQuiz_Success() {
        // Arrange: Create and save Quiz
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Prepare answers for the quiz

        SubmitQuizRequest submitQuizRequest = createASubmitQuizRequest(createdUser.getId(), savedQuiz);

        // Act: Evaluate the quiz
        QuizScoreResponse response = quizService.evaluateQuiz(savedQuiz.getId(), submitQuizRequest);

        // Assert: Verify the response
        assertNotNull(response);
        assertEquals(2, response.getTotalQuestions());
        assertEquals(2, response.getCorrectAnswers());
        assertEquals(100.0, response.getPercentage(), 0.01);
    }


    @Test
    void testEvaluateQuiz_PartialCorrectAnswers() {
        // Arrange: Create and save Quiz
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Prepare answers for the quiz with one incorrect answer
       List<SubmitQuizRequest.SubmittedAnswer> answers = List.of(
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(0).getId(), "A unique identifier"),    // Correct
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(1).getId(), "London") // Incorrect
        );


        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(createdUser.getId());
        request.setAnswers(answers);

        // Act: Evaluate the quiz
        QuizScoreResponse response = quizService.evaluateQuiz(savedQuiz.getId(), request);



        // Assert: Verify the response
        assertNotNull(response);
        assertEquals(2, response.getTotalQuestions());
        assertEquals(1, response.getCorrectAnswers());
        assertEquals(50.0, response.getPercentage(), 0.01);
    }

    @Test
    void testEvaluateQuiz_NoCorrectAnswers() {
        // Arrange: Create and save Quiz
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Prepare answers for the quiz with all incorrect answers
        List<SubmitQuizRequest.SubmittedAnswer> answers = List.of(
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(0).getId(), "London"),    // Correct
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(1).getId(), "London") // Incorrect
        );

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(createdUser.getId());
        request.setAnswers(answers);
        // Act: Evaluate the quiz
        QuizScoreResponse response = quizService.evaluateQuiz(savedQuiz.getId(), request);

        // Assert: Verify the response
        assertNotNull(response);
        assertEquals(2, response.getTotalQuestions());
        assertEquals(0, response.getCorrectAnswers());
        assertEquals(0.0, response.getPercentage(), 0.01);
        assertEquals(0, response.getScore());
    }

    @Test
    void testEvaluateQuiz_UserCannotTakeTheSameQuizTwice() {
        // Arrange: Create and save Quiz
        CreatedQuizResponse savedQuiz = quizService.createQuiz(createQuizRequest);

        // Prepare answers for the quiz with all incorrect answers
        List<SubmitQuizRequest.SubmittedAnswer> answers = List.of(
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(0).getId(), "London"),    // Correct
                new SubmitQuizRequest.SubmittedAnswer(savedQuiz.getQuestionList().get(1).getId(), "London") // Incorrect
        );

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(createdUser.getId());
        request.setAnswers(answers);
        // Act: Evaluate the quiz
        QuizScoreResponse response = quizService.evaluateQuiz(savedQuiz.getId(), request);

        // Assert: Verify the response
        assertNotNull(response);
        assertEquals(2, response.getTotalQuestions());
        assertEquals(0, response.getCorrectAnswers());
        assertEquals(0.0, response.getPercentage(), 0.01);
        assertEquals(0, response.getScore());

        assertThrows(QuizException.class, ()->quizService.evaluateQuiz(savedQuiz.getId(), request));
    }

    private SubmitQuizRequest createASubmitQuizRequest(Long createdUser, CreatedQuizResponse savedQuiz) {
        List<SubmitQuizRequest.SubmittedAnswer> answers = savedQuiz.getQuestionList().stream()
                .map(question -> new SubmitQuizRequest.SubmittedAnswer(question.getId(), "A unique identifier"))
                .collect(Collectors.toList());

        SubmitQuizRequest request = new SubmitQuizRequest();
        request.setUserId(createdUser);
        request.setAnswers(answers);
        return request;
    }
}
