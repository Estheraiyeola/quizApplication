package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.*;
import com.essiecodes.databasequizapplication.data.repository.QuizRepo;
import com.essiecodes.databasequizapplication.data.repository.QuizResultRepo;
import com.essiecodes.databasequizapplication.dto.request.*;
import com.essiecodes.databasequizapplication.dto.response.CreatedQuizResponse;
import com.essiecodes.databasequizapplication.dto.response.QuizScoreResponse;
import com.essiecodes.databasequizapplication.exception.QuizException;
import com.essiecodes.databasequizapplication.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepo quizRepository;
    private final QuestionService questionService;
    private final OptionsService optionsService;
    private final UserService userService;
    private final QuizResultRepo quizResultRepo;

    @Override
    @Transactional
    public CreatedQuizResponse createQuiz(CreateQuizRequest createQuizRequest) {
        Quiz quiz = new Quiz();
        quiz.setTitle(createQuizRequest.getTitle());

        List<Question> questionList = new ArrayList<>();
        for (QuestionRequest questionRequest : createQuizRequest.getQuestions()) {
            Question question = createQuestion(questionRequest);
            questionList.add(question);
        }
        quiz.setQuestionList(questionList);

        Quiz savedQuiz = quizRepository.save(quiz);
        return buildResponse(savedQuiz);
    }

    @Override
    public Quiz findQuizBy(Long id) {
        return quizRepository.findById(id).orElseThrow(()-> new QuizException("Quiz not found"));
    }

    @Override
    public Quiz takeQuiz(TakeQuizRequest request) {
        validateIfUserHasTakenTheQuiz(request.getUserId(), request.getQuizId());
        return quizRepository.findById(request.getQuizId()).orElseThrow(()-> new QuizException("Quiz not found"));
    }
    @Transactional
    public Question createQuestion(QuestionRequest questionRequest) {
        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        question.setAnswer(questionRequest.getAnswer());
        question.setMarks(questionRequest.getMarks());

        // Save the question first
        Question savedQuestion = questionService.saveQuestion(question);

        // Now create options and associate them with the saved question
        List<Options> createdOptions = createOption(questionRequest, savedQuestion);

        // Set the options for the question and save them
        savedQuestion.setOptions(createdOptions);

        return savedQuestion;
    }

    @Transactional
    public List<Options> createOption(QuestionRequest questionRequest, Question savedQuestion) {
        List<Options> optionsList = new ArrayList<>();
        for (CreateOptionRequest optionText : questionRequest.getOptions()) {
            Options option = new Options();
            option.setOption(optionText.getOption());
            option.setQuestion(savedQuestion); // Associate the option with the saved question
            optionsList.add(option);
        }
        // Save the options after associating them with the saved question
        optionsService.saveOption(optionsList); // Assuming `saveOption` can handle a list of options.
        return optionsList;
    }

    private CreatedQuizResponse buildResponse(Quiz savedQuiz) {
         CreatedQuizResponse createdQuizResponse = new CreatedQuizResponse();
         createdQuizResponse.setId(savedQuiz.getId());
         createdQuizResponse.setMessage("Quiz successfully created");
         createdQuizResponse.setQuestionList(savedQuiz.getQuestionList());
         createdQuizResponse.setTitle(savedQuiz.getTitle());
         return createdQuizResponse;
    }

    @Override
    @Transactional
    public QuizScoreResponse evaluateQuiz(Long quizId, SubmitQuizRequest submitQuizRequest) {
        validateIfUserHasTakenTheQuiz(submitQuizRequest.getUserId(), quizId);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizException("Quiz not found"));
        User user = userService.findById(submitQuizRequest.getUserId())
                .orElseThrow(() -> new QuizException("User not found"));

        int totalMarks = quiz.getQuestionList().stream().mapToInt(Question::getMarks).sum();
        int userScore = 0;
        int correctAnswers = 0;

        for (Question question : quiz.getQuestionList()) {
            for (SubmitQuizRequest.SubmittedAnswer answer : submitQuizRequest.getAnswers()) {
                if (answer.getQuestionId().equals(question.getId()) &&
                        answer.getAnswer().equalsIgnoreCase(question.getAnswer())) {
                    userScore += question.getMarks();
                    correctAnswers++;
                }
            }
        }

        if (totalMarks <= 0) {
            throw new IllegalArgumentException("Total marks must be greater than zero.");
        }
        System.out.println(totalMarks);
        System.out.println(userScore);

        double percentage = (double) userScore / totalMarks * 100;
        System.out.println(percentage);

        if (Double.isNaN(percentage) || Double.isInfinite(percentage)) {
            throw new IllegalStateException("Percentage calculation resulted in an invalid value: " + percentage);
        }

        QuizResult result = new QuizResult();
        result.setQuiz(quiz);
        result.setUser(user);
        result.setScore(userScore);
        result.setTotalMarks(totalMarks);
        result.setPercentage(percentage);

        quizResultRepo.save(result);

        String feedback = generateFeedback(userScore, totalMarks);

        return new QuizScoreResponse(userScore, quiz.getQuestionList().size(), percentage, feedback, correctAnswers, totalMarks);
    }

    private void validateIfUserHasTakenTheQuiz(Long userId, Long quizId) {
        Quiz foundQuiz = findQuizBy(quizId);
        User user = userService.findById(userId).orElseThrow(() -> new UserException("User not found"));
        boolean quizAlreadyTaken = quizResultRepo.existsByQuizAndUser(foundQuiz, user);
        if (quizAlreadyTaken){
            throw new QuizException("Quiz Already Taken");
        }
    }

    private String generateFeedback(int score, int totalMarks) {
        double percentage = ((double) score / totalMarks) * 100;
        if (percentage >= 90) {
            return "Excellent work!";
        } else if (percentage >= 70) {
            return "Good job! Keep practicing.";
        } else {
            return "Don't give up! Try again.";
        }
    }


}
