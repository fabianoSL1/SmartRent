import { toast } from "@/hooks/use-toast";
import { callPost } from "./api";

type LoginRequest = {
    username: string,
    password: string
}

type RegisterRequest = {
    username: string,
    password: string
}

type LoginResponse = {
    token: string;
}

export async function login(body: LoginRequest) {
    try {
        const response = await callPost<LoginResponse>("/auth/login", body, false);
        sessionStorage.setItem("token", response.token);
    } catch (err) {
        if (err instanceof Error) {
            toast({
                title: "Falha ao realizar login",
            })
        }

        throw err
    }
}

export async function register(body: RegisterRequest) {
    try {
        await callPost("/auth/register", body, false);
    } catch (err) {
        if (err instanceof Error) {
            toast({
                title: "Falha ao realizar login",
                description: err.message
            })
        }

        throw err
    }
}