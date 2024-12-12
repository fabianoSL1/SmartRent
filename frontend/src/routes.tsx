import { Route, Routes } from "react-router";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import ProposalReceived from "./pages/ProposalsReceived";
import Available from "./pages/Available";
import PrivateRouter from "./components/PrivateRouter";
import ProposalSend from "./pages/ProposalsSend";



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
                    <ProposalReceived />
                </PrivateRouter>
            } />
            <Route path="/propostas/enviadas" element={
                <PrivateRouter>
                    <ProposalSend />
                </PrivateRouter>
            } />
        </Routes>
    )
}