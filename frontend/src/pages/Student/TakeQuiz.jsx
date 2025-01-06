import React, { useState } from "react";

const TakeQuiz = () => {
  // Mock list of quizzes
  const quizzes = [
    { id: 1, title: "General Knowledge Quiz" },
    { id: 2, title: "Math Quiz" },
    { id: 3, title: "Science Quiz" },
  ];

  const [selectedQuiz, setSelectedQuiz] = useState(null);

  // Mock questions for quizzes (in real scenarios, fetch from an API)
  const quizQuestions = {
    1: [
      {
        question: "What is the capital of France?",
        options: ["Paris", "Berlin", "Madrid", "Rome"],
        correctAnswer: "Paris",
      },
      {
        question: "What is the capital of France?",
        options: ["Paris", "Berlin", "Madrid", "Rome"],
        correctAnswer: "Paris",
      },
      {
        question: "What is the capital of France?",
        options: ["Paris", "Berlin", "Madrid", "Rome"],
        correctAnswer: "Paris",
      },
      {
        question: "What is the capital of France?",
        options: ["Paris", "Berlin", "Madrid", "Rome"],
        correctAnswer: "Paris",
      },
    ],
    2: [
      {
        question: "What is 2 + 2?",
        options: ["3", "4", "5", "6"],
        correctAnswer: "4",
      },
    ],
    3: [
      {
        question: "Which planet is known as the Red Planet?",
        options: ["Mars", "Venus", "Jupiter", "Saturn"],
        correctAnswer: "Mars",
      },
    ],
  };

  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [selectedOption, setSelectedOption] = useState(null);
  const [score, setScore] = useState(0);
  const [isQuizCompleted, setIsQuizCompleted] = useState(false);

  const startQuiz = (quizId) => {
    setSelectedQuiz(quizId);
    setCurrentQuestionIndex(0);
    setSelectedOption(null);
    setScore(0);
    setIsQuizCompleted(false);
  };

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
  };

  const handleNext = () => {
    const currentQuiz = quizQuestions[selectedQuiz];
    if (selectedOption === currentQuiz[currentQuestionIndex].correctAnswer) {
      setScore(score + 1);
    }

    if (currentQuestionIndex < currentQuiz.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
      setSelectedOption(null);
    } else {
      setIsQuizCompleted(true);
    }
  };

  const handleRestart = () => {
    setSelectedQuiz(null);
    setCurrentQuestionIndex(0);
    setSelectedOption(null);
    setScore(0);
    setIsQuizCompleted(false);
  };

  return (
    <div className="p-6 bg-white shadow rounded-lg">
      {!selectedQuiz ? (
        // Show list of quizzes
        <div>
          <h1 className="text-2xl font-bold mb-4">Available Quizzes</h1>
          <ul className="space-y-4">
            {quizzes.map((quiz) => (
              <li key={quiz.id}>
                <button
                  onClick={() => startQuiz(quiz.id)}
                  className="w-full text-left py-2 px-4 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
                >
                  {quiz.title}
                </button>
              </li>
            ))}
          </ul>
        </div>
      ) : isQuizCompleted ? (
        // Show quiz results
        <div className="text-center">
          <h1 className="text-2xl font-bold mb-4">Quiz Completed!</h1>
          <p className="text-lg">Your score: {score} / {quizQuestions[selectedQuiz].length}</p>
          <button
            onClick={handleRestart}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
          >
            Back to Quizzes
          </button>
        </div>
      ) : (
        // Show quiz questions
        <div>
          <h2 className="text-xl font-semibold mb-4">
            Question {currentQuestionIndex + 1} of {quizQuestions[selectedQuiz].length}
          </h2>
          <p className="mb-6">{quizQuestions[selectedQuiz][currentQuestionIndex].question}</p>
          <ul className="space-y-2">
            {quizQuestions[selectedQuiz][currentQuestionIndex].options.map((option, index) => (
              <li key={index}>
                <button
                  onClick={() => handleOptionSelect(option)}
                  className={`block w-full text-left py-2 px-4 rounded ${
                    selectedOption === option
                      ? "bg-blue-600 text-white"
                      : "bg-gray-200 hover:bg-gray-300"
                  }`}
                >
                  {option}
                </button>
              </li>
            ))}
          </ul>
          <button
            onClick={handleNext}
            disabled={!selectedOption}
            className={`mt-4 px-4 py-2 rounded ${
              selectedOption
                ? "bg-blue-600 text-white hover:bg-blue-700"
                : "bg-gray-400 text-gray-700 cursor-not-allowed"
            }`}
          >
            {currentQuestionIndex === quizQuestions[selectedQuiz].length - 1 ? "Finish" : "Next"}
          </button>
        </div>
      )}
    </div>
  );
};

export default TakeQuiz;
