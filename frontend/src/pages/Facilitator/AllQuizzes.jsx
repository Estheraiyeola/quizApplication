import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const AllQuizzes = () => {
  const [quizzes, setQuizzes] = useState([]);

  // Fetch quizzes from an API or load them from local data
  useEffect(() => {
    // Example data - this should come from your backend/API
    const fetchedQuizzes = [
      {
        id: 1,
        title: "JavaScript Basics",
        questionsCount: 5,
      },
      {
        id: 2,
        title: "React Fundamentals",
        questionsCount: 8,
      },
      {
        id: 3,
        title: "CSS Advanced",
        questionsCount: 6,
      },
    ];

    setQuizzes(fetchedQuizzes);
  }, []);

  const handleDeleteQuiz = (quizId) => {
    // Delete the quiz from the state (you would replace this with a backend API call)
    setQuizzes(quizzes.filter((quiz) => quiz.id !== quizId));
  };

  return (
    <div className="p-6 bg-white shadow rounded-lg">
      <h1 className="text-2xl font-bold mb-4">All Quizzes</h1>

      {quizzes.length === 0 ? (
        <p>No quizzes available. Please create a quiz.</p>
      ) : (
        <div className="space-y-4">
          {quizzes.map((quiz) => (
            <div key={quiz.id} className="flex items-center justify-between p-4 bg-gray-100 rounded-lg shadow-md">
              <div>
                <h2 className="text-xl font-semibold">{quiz.title}</h2>
                <p className="text-gray-600">Number of Questions: {quiz.questionsCount}</p>
              </div>
              <div className="flex space-x-4">
                <Link
                  to={`/quizzes/${quiz.id}`}
                  className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                >
                  View
                </Link>
                <button
                  onClick={() => handleDeleteQuiz(quiz.id)}
                  className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default AllQuizzes;
