import { callGet, callPatch, callPost } from "./api";
import { alertMessage } from "./alert";

export type ProposalResponse = {
    id: number;
    renterId: number;
    renterName: string;
    vehicleId: number;
    vehicleIdentifier: string;
    amount: number;
    beginDate: string;
    endDate: string;
    status: "PENDING" | "APPROVED" | "REJECTED" | "CANCELLED";
}

export type createProposalRequest = {
    vehicleId: number;
    amount: number;
    beginDate: string;
    endDate: string;
}

export async function listProposalsReceive() {
    return alertMessage(
        async () => await callGet<ProposalResponse[]>("/proposals/list/receive", true),
        "Falha ao listar propostas"
    );
}

export async function listProposalsSend() {
    return alertMessage(
        async () => await callGet<ProposalResponse[]>("/proposals/list/send", true),
        "Falha ao listar propostas"
    );
}

export async function createProposal(body: createProposalRequest) {
    return alertMessage(
        async () => await callPost<ProposalResponse>("/proposals", body, true),
        "Falha ao criar proposta"
    );
}

export async function createRent(proposalId: number) {
    return alertMessage(
        async () => await callPost<unknown>(`/proposals/${proposalId}/rent`, {}, true),
        "Falha ao gerar aluguel"
    );
}

export async function approveProposal(proposalId: number) {
    return alertMessage(
        async () => await callPatch<ProposalResponse>(`/proposals/${proposalId}/approve`, {}, true),
        "Falha ao aprovar proposta"
    );
}

export async function rejectProposal(proposalId: number) {
    return alertMessage(
        async () => await callPatch<ProposalResponse>(`/proposals/${proposalId}/reject`, {}, true),
        "Falha ao rejeitar proposta"
    );
}

export async function cancelProposal(proposalId: number) {
    return alertMessage(
        async () => await callPatch<ProposalResponse>(`/proposals/${proposalId}/cancel`, {}, true),
        "Falha ao cancelar proposta"
    );
}