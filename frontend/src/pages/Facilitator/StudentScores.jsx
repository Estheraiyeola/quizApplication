import React, { useState, useEffect } from "react";

const StudentScores = () => {
  // State to hold student scores
  const [studentScores, setStudentScores] = useState([]);

  // States for search and filters
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredScores, setFilteredScores] = useState([]);

  // Example data - should come from backend API
  useEffect(() => {
    const fetchedScores = [
      { studentName: "John Doe", quizTitle: "JavaScript Basics", score: 80 },
      { studentName: "Jane Smith", quizTitle: "React Fundamentals", score: 90 },
      { studentName: "Alice Johnson", quizTitle: "CSS Advanced", score: 75 },
      { studentName: "Bob Lee", quizTitle: "JavaScript Basics", score: 85 },
      { studentName: "Charlie Brown", quizTitle: "React Fundamentals", score: 88 },
    ];

    setStudentScores(fetchedScores);
    setFilteredScores(fetchedScores);
  }, []);

  // Handle search input and filter data
  const handleSearch = (event) => {
    const term = event.target.value.toLowerCase();
    setSearchTerm(term);

    // Filter by student name or quiz title
    const filtered = studentScores.filter(
      (score) =>
        score.studentName.toLowerCase().includes(term) ||
        score.quizTitle.toLowerCase().includes(term)
    );
    setFilteredScores(filtered);
  };

  return (
    <div className="p-6 bg-white shadow rounded-lg">
      <h1 className="text-2xl font-bold mb-4">Student Scores</h1>

      {/* Search bar */}
      <div className="mb-4">
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearch}
          placeholder="Search by student or quiz title"
          className="px-4 py-2 border border-gray-300 rounded-md w-full"
        />
      </div>

      {/* Table for displaying student scores */}
      <table className="min-w-full table-auto border-collapse">
        <thead>
          <tr className="bg-gray-100 border-b">
            <th className="px-4 py-2 text-left">Student Name</th>
            <th className="px-4 py-2 text-left">Quiz Title</th>
            <th className="px-4 py-2 text-left">Score (%)</th>
          </tr>
        </thead>
        <tbody>
          {filteredScores.length === 0 ? (
            <tr>
              <td colSpan="3" className="text-center py-4">
                No scores available.
              </td>
            </tr>
          ) : (
            filteredScores.map((score, index) => (
              <tr key={index} className="border-b">
                <td className="px-4 py-2">{score.studentName}</td>
                <td className="px-4 py-2">{score.quizTitle}</td>
                <td className="px-4 py-2">{score.score}%</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default StudentScores;
