import { callGet, callPatch, callPost } from "./api";
import { alertMessage } from "./alert";

export type RegisterVehicleRequest = {
    identifier: string;
    model: string;
    brand: string;
    color: string;
    year: string;
}

export type VehicleResponse = {
    id: number;
    identifier: string;
    model: string;
    brand: string;
    color: string;
    year: string;
    ownerId: number;
    status: string;
    createdAt: string;
}

export async function registerVehicle(body: RegisterVehicleRequest) {
    return alertMessage(
        async () => await callPost<VehicleResponse>("/vehicles", body, true),
        "Falha ao cadastrar veiculo"
    );
}

export async function listVehiclesCurrentOwner() {
    return alertMessage(
        async () => await callGet<VehicleResponse[]>("/vehicles/list/owner", true),
        "Falha ao listar veiculos"
    );
}

export async function listVehiclesAvailables() {
    return alertMessage(
        async () => await callGet<VehicleResponse[]>("/vehicles/list/available", true),
        "Falha ao listar veiculos disponiveis"
    );
}

export async function enableVehicle(vehicleId: number) {
    return alertMessage(
        async () => await callPatch<VehicleResponse>(`/vehicles/${vehicleId}/enable`, {}, true),
        "Falha ao habilitar veiculo"
    );
}

export async function disableVehicle(vehicleId: number) {
    return alertMessage(
        async () => await callPatch<VehicleResponse>(`/vehicles/${vehicleId}/disable`, {}, true),
        "Falha ao desabilitar veiculo"
    );
}