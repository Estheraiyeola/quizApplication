import React, { useState } from "react";

const CreateQuiz = () => {
  const [quizTitle, setQuizTitle] = useState("");
  const [questions, setQuestions] = useState([
    {
      questionText: "",
      options: [
        { optionText: "", isCorrect: false },
        { optionText: "", isCorrect: false },
        { optionText: "", isCorrect: false },
        { optionText: "", isCorrect: false },
      ],
    },
  ]);

  const handleTitleChange = (e) => {
    setQuizTitle(e.target.value);
  };

  const handleQuestionChange = (index, e) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index].questionText = e.target.value;
    setQuestions(updatedQuestions);
  };

  const handleOptionChange = (qIndex, oIndex, e) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options[oIndex].optionText = e.target.value;
    setQuestions(updatedQuestions);
  };

  const handleOptionCorrectChange = (qIndex, oIndex, e) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options[oIndex].isCorrect = e.target.checked;
    setQuestions(updatedQuestions);
  };

  const handleAddQuestion = () => {
    setQuestions([
      ...questions,
      {
        questionText: "",
        options: [
          { optionText: "", isCorrect: false },
          { optionText: "", isCorrect: false },
          { optionText: "", isCorrect: false },
          { optionText: "", isCorrect: false },
        ],
      },
    ]);
  };

  const handleSaveQuiz = () => {
    // This is where you would handle quiz submission (e.g., send the data to the backend)
    console.log("Quiz Title:", quizTitle);
    console.log("Questions:", questions);
    alert("Quiz saved!");
  };

  return (
    <div className="p-6 bg-white shadow rounded-lg">
      <h1 className="text-2xl font-bold mb-4">Create a New Quiz</h1>

      <div className="mb-6">
        <label htmlFor="quizTitle" className="block text-lg font-semibold">
          Quiz Title:
        </label>
        <input
          id="quizTitle"
          type="text"
          value={quizTitle}
          onChange={handleTitleChange}
          className="mt-2 p-2 w-full border border-gray-300 rounded"
          placeholder="Enter quiz title"
        />
      </div>

      <h2 className="text-xl font-semibold mb-4">Questions</h2>
      {questions.map((question, qIndex) => (
        <div key={qIndex} className="mb-6">
          <label className="block text-lg font-semibold">Question {qIndex + 1}</label>
          <input
            type="text"
            value={question.questionText}
            onChange={(e) => handleQuestionChange(qIndex, e)}
            className="mt-2 p-2 w-full border border-gray-300 rounded"
            placeholder="Enter the question"
          />
          <div className="mt-4 space-y-2">
            {question.options.map((option, oIndex) => (
              <div key={oIndex} className="flex items-center space-x-2">
                <input
                  type="text"
                  value={option.optionText}
                  onChange={(e) => handleOptionChange(qIndex, oIndex, e)}
                  className="p-2 w-full border border-gray-300 rounded"
                  placeholder={`Option ${oIndex + 1}`}
                />
                <input
                  type="checkbox"
                  checked={option.isCorrect}
                  onChange={(e) => handleOptionCorrectChange(qIndex, oIndex, e)}
                  className="h-5 w-5"
                />
                <span className="text-sm">Correct</span>
              </div>
            ))}
          </div>
        </div>
      ))}

      <div className="mt-6">
        <button
          onClick={handleAddQuestion}
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
        >
          Add Question
        </button>
      </div>

      <div className="mt-6">
        <button
          onClick={handleSaveQuiz}
          className="px-6 py-3 bg-green-600 text-white rounded hover:bg-green-700"
        >
          Save Quiz
        </button>
      </div>
    </div>
  );
};

export default CreateQuiz;
