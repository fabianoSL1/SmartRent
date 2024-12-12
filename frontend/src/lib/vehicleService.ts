import { callGet, callPatch, callPost } from "./api";

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
    return await callPost<VehicleResponse>("/vehicles", body, true);
}

export async function listVehiclesCurrentOwner() {
    return await callGet<VehicleResponse[]>("/vehicles/list/owner", true);
}

export async function listVehiclesAvailables() {
    return await callGet<VehicleResponse[]>("/vehicles/list/available", true);
}

export async function enableVehicle(vehicleId: number) {
    return await callPatch<VehicleResponse>(`/vehicles/${vehicleId}/enable`, {}, true);
}

export async function disableVehicle(vehicleId: number) {
    return await callPatch<VehicleResponse>(`/vehicles/${vehicleId}/disable`, {}, true);
}