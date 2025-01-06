import React from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate

const StudentHome = () => {
  const navigate = useNavigate(); // Initialize useNavigate hook

  // Handle navigation on quiz start
  const handleStartQuiz = () => {
    navigate("/dashboard/student/take-quiz"); // Navigate to the "Take Quiz" page or desired route
  };

  const handleViewHistory = () => {
    navigate("/dashboard/student/taken-quizzes"); // Navigate to the "Take Quiz" page or desired route
  };
  return (
    <div className="p-6 bg-white shadow rounded-lg">
      <h1 className="text-3xl font-bold mb-4">Welcome to the Student Dashboard</h1>
      <p className="text-gray-700 mb-6">
        Here you can take quizzes, review your scores, and track your progress.
      </p>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div className="bg-blue-100 p-4 rounded shadow">
          <h2 className="text-xl font-semibold">Take a Quiz</h2>
          <p className="text-gray-600">Start a new quiz to test your knowledge.</p>
          <button className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700" onClick={() => handleStartQuiz()}>
            Start Now
          </button>
        </div>
        <div className="bg-green-100 p-4 rounded shadow">
          <h2 className="text-xl font-semibold">View Taken Quizzes</h2>
          <p className="text-gray-600">Check your previous quiz attempts and scores.</p>
          <button className="mt-4 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700" onClick={() => handleViewHistory()}>
            View History
          </button>
        </div>
      </div>
    </div>
  );
};

export default StudentHome;
