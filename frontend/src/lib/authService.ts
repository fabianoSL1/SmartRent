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
    const response = await callPost<LoginResponse>("/auth/login", body, false);
    sessionStorage.setItem("token", response.token);
}

export async function register(body: RegisterRequest) {
    await callPost("/auth/register", body, false);
}