export async function callGet<T>(path: string, auth: boolean) {
    return await callApi<T>("GET", path, auth);
}

export async function callPost<T>(path: string, body: Record<string, string | number | boolean>, auth: boolean) {
    return await callApi<T>("POST", path, auth, body);
}

export async function callPatch<T>(path: string, body: Record<string, string | number | boolean>, auth: boolean) {
    return await callApi<T>("PATCH", path, auth, body);
}

async function callApi<T>(method: string, path: string, auth: boolean, body?: Record<string, string | number | boolean>) {
    const headers: Record<string, string> = {
        "Content-Type": "application/json"
    }
    if (auth) {
        headers["Authorization"] = `Bearer ${sessionStorage.getItem("token")}`
    }

    const response = await fetch(`http://localhost:8080${path}`, {
        headers: headers,
        ...(body && { body: JSON.stringify(body) }),
        method: method,

    });

    if (response.status >= 500) {
        throw new Error("internal server error");
    }

    if (response.status >= 400) {
        if (response.status == 403) {
            sessionStorage.removeItem("token");
            throw new Error("não autenticado");
        }

        const json = await response.json();
        
        throw new Error(json.message);
    }

    const json = await response.json();
    return json as T;
}