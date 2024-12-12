import { Route, Routes } from "react-router";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Proposal from "./pages/Proposal";
import Available from "./pages/Available";
import PrivateRouter from "./components/PrivateRouter";



export default function AppRoutes() {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/cadastro" element={<Signup />} />

            <Route path="/" element={
                <PrivateRouter>
                    <Home />
                </PrivateRouter>
            } />
            <Route path="/disponiveis" element={
                <PrivateRouter>
                    <Available />
                </PrivateRouter>
            } />
            <Route path="/propostas/recebidas" element={
                <PrivateRouter>
                    <Proposal />
                </PrivateRouter>
            } />
        </Routes>
    )
}