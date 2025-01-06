import React from "react";
import { Link } from "react-router-dom";

const Sidebar = ({ role }) => {
  const studentLinks = [
    { name: "Home", href: "/dashboard/student/home" },
    { name: "Take Quiz", href: "/dashboard/student/take-quiz" },
    { name: "Taken Quizzes", href: "/dashboard/student/taken-quizzes" },
  ];

  const facilitatorLinks = [
    { name: "Home", href: "/dashboard/facilitator/home" },
    { name: "All Quizzes", href: "/dashboard/facilitator/all-quizzes" },
    { name: "Student Scores", href: "/dashboard/facilitator/student-scores" },
    { name: "Create Quiz", href: "/dashboard/facilitator/create-quiz" },
  ];

  const links = role === "facilitator" ? facilitatorLinks : studentLinks;

  return (
    <div className="w-64 bg-blue-900 text-white flex flex-col">
      <div className="p-4 text-2xl font-bold border-b border-blue-700">
        {role === "facilitator" ? "Facilitator" : "Student"}
      </div>
      <nav className="flex-1 p-4">
        <ul className="space-y-2">
          {links.map((link) => (
            <li key={link.name}>
              <Link
                to={link.href}
                className="block py-2 px-4 rounded hover:bg-blue-700 transition"
              >
                {link.name}
              </Link>
            </li>
          ))}
        </ul>
      </nav>
      <div className="p-4 border-t border-blue-700">
        <button className="w-full bg-red-500 py-2 rounded text-white hover:bg-red-600 transition">
          Logout
        </button>
      </div>
    </div>
  );
};

export default Sidebar;
