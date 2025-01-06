import React from "react";
import { Link } from "react-router-dom";

const FacilitatorHome = () => {
  return (
    <div className="p-6 bg-white shadow rounded-lg">
      {/* Dashboard Header */}
      <div className="mb-6">
        <h1 className="text-2xl font-bold">Facilitator Dashboard</h1>
        <p className="mt-2 text-gray-600">
          Welcome back! Here's a summary of your quizzes and student performance.
        </p>
      </div>

      {/* Quick Actions Section */}
      <div className="mb-6">
        <h2 className="text-xl font-semibold mb-2">Quick Actions</h2>
        <div className=" flex justify-between space-x-4">
          <Link
            to="/dashboard/facilitator/create-quiz"
            className="block bg-blue-600 text-white text-center p-10 px-16 rounded hover:bg-blue-700"
          >
            Create a New Quiz
          </Link>
          <Link
            to="/dashboard/facilitator/all-quizzes"
            className="block bg-gray-600 text-white text-center p-10 px-16 rounded hover:bg-gray-700"
          >
            Manage Quizzes
          </Link>
          <Link
            to="/dashboard/facilitator/student-scores"
            className="block bg-green-600 text-white text-center p-10 px-16 rounded hover:bg-green-700"
          >
            View Student Scores
          </Link>
        </div>
      </div>

      {/* Overview Section */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
        <div className="bg-blue-100 p-4 rounded-lg">
          <h2 className="font-semibold text-lg">Total Quizzes</h2>
          <p className="text-2xl">5</p>
        </div>
        <div className="bg-green-100 p-4 rounded-lg">
          <h2 className="font-semibold text-lg">Students Scored</h2>
          <p className="text-2xl">15</p>
        </div>
        <div className="bg-yellow-100 p-4 rounded-lg">
          <h2 className="font-semibold text-lg">Pending Quizzes</h2>
          <p className="text-2xl">1</p>
        </div>
      </div>

      {/* Other Components will be rendered in here based on the route */}
      {/* Routes for individual components are handled in the router */}
    </div>
  );
};

export default FacilitatorHome;
