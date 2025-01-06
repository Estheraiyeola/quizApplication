import React from "react";
import { Outlet } from "react-router-dom";
import Sidebar from "../../component/Sidebar";

const Dashboard = ({ role }) => {
  return (
    <div className="flex h-screen">
      <Sidebar role={role} />
      <main className="flex-1 bg-gray-100 p-6 overflow-y-auto">
        {/* Outlet renders the matched child route */}
        <Outlet />
      </main>
    </div>
  );
};

export default Dashboard;
