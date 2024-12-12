import { Route, Routes } from "react-router";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Proposal from "./pages/Proposal";
import Available from "./pages/Available";

export default function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/disponiveis" element={<Available/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/cadastro" element={<Signup/>} />
            <Route path="/propostas/recebidas" element={<Proposal/>} />
        </Routes>
    )
}