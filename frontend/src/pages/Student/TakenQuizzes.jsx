import React from "react";

const TakenQuizzes = () => {
  // Mock data for quizzes taken by the student
  const takenQuizzes = [
    {
      id: 1,
      title: "General Knowledge Quiz",
      score: "8/10",
      date: "2025-01-05",
      reviewLink: "/quiz/1/review",
      percentage: "80%",
    },
    {
      id: 2,
      title: "Math Quiz",
      score: "6/10",
      date: "2025-01-02",
      reviewLink: "/quiz/2/review",
      percentage: "60%",
    },
  ];

  return (
    <div className="p-6 bg-white shadow rounded-lg">
      <h1 className="text-2xl font-bold mb-4">Quizzes You Have Taken</h1>
      {takenQuizzes.length === 0 ? (
        <p className="text-gray-600">You haven’t taken any quizzes yet. Start by taking a quiz!</p>
      ) : (
        <table className="w-full border-collapse border border-gray-200">
          <thead>
            <tr className="bg-gray-100">
              <th className="border border-gray-200 px-4 py-2 text-left">Quiz Title</th>
              <th className="border border-gray-200 px-4 py-2 text-left">Score</th>
              <th className="border border-gray-200 px-4 py-2 text-left">Percentage</th>
              <th className="border border-gray-200 px-4 py-2 text-left">Date Taken</th>
              <th className="border border-gray-200 px-4 py-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {takenQuizzes.map((quiz) => (
              <tr key={quiz.id} className="hover:bg-gray-50">
                <td className="border border-gray-200 px-4 py-2">{quiz.title}</td>
                <td className="border border-gray-200 px-4 py-2">{quiz.score}</td>
                <td className="border border-gray-200 px-4 py-2">{quiz.percentage}</td>
                <td className="border border-gray-200 px-4 py-2">{quiz.date}</td>
                <td className="border border-gray-200 px-4 py-2">
                  <a
                    href={quiz.reviewLink}
                    className="text-blue-600 hover:underline"
                  >
                    Review
                  </a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default TakenQuizzes;
