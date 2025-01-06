import Dashboard from "./pages/Dashboard/Dashboard";
import AllQuizzes from "./pages/Facilitator/AllQuizzes";
import FacilitatorHome from "./pages/Facilitator/FacilitatorHome";
import StudentScores from "./pages/Facilitator/StudentScores";
import CreateQuiz from "./pages/Facilitator/CreateQuiz";
import Login from "./pages/Login/Login";
import StudentHome from "./pages/Student/StudentHome";
import TakenQuizzes from "./pages/Student/TakenQuizzes";
import TakeQuiz from "./pages/Student/TakeQuiz";

export const ROUTES = [
    {
        path: '/',
        element: <Login />,
    },
    {
        path: '',
        element: <Dashboard role={"student"} />,
        children: [
            {
                path: '/dashboard/student/home',
                element: <StudentHome  />,
            },
            {
                path: '/dashboard/student/take-quiz',
                element: <TakeQuiz  />,
            },
            {
                path: '/dashboard/student/taken-quizzes',
                element: <TakenQuizzes  />,
            },
            
        ]
    },
    {
        path: '',
        element: <Dashboard  role={"facilitator"}/>,
        children: [
            {
                path: '/dashboard/facilitator/home',
                element: <FacilitatorHome  />,
            },
            {
                path: '/dashboard/facilitator/all-quizzes',
                element: <AllQuizzes  />,
            },
            {
                path: '/dashboard/facilitator/student-scores',
                element: <StudentScores  />,
            },
            {
                path: '/dashboard/facilitator/create-quiz',
                element: <CreateQuiz  />,
            }
        
        ]
    }
    
]
