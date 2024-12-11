"use client"

import { useState } from "react"
import { Input } from "@/components/ui/input"
import { ProposalsTable } from "@/components/proposal/ProposalTable"
import { Layout } from "@/components/Layout"

interface Proposal {
  id: number
  vehiclePlate: string
  proposerName: string
  amount: number
  date: string
  status: 'Pendente' | 'Aceita' | 'Recusada'
}

const initialProposals: Proposal[] = [
  { id: 1, vehiclePlate: "ABC-1234", proposerName: "João Silva", amount: 25000, date: "2023-05-15", status: 'Pendente' },
  { id: 2, vehiclePlate: "DEF-5678", proposerName: "Maria Oliveira", amount: 30000, date: "2023-05-14", status: 'Pendente' },
  { id: 3, vehiclePlate: "GHI-9012", proposerName: "Carlos Santos", amount: 28000, date: "2023-05-13", status: 'Aceita' },
]


export default function Proposal() {
  const [proposals, setProposals] = useState<Proposal[]>(initialProposals)
  const [searchTerm, setSearchTerm] = useState("")

  const filteredProposals = proposals.filter((proposal) =>
    Object.values(proposal).some((value) =>
      value.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  const handleAccept = (id: number) => {
    setProposals(proposals.map(proposal => 
      proposal.id === id ? { ...proposal, status: 'Aceita' } : proposal
    ))
  }

  const handleReject = (id: number) => {
    setProposals(proposals.map(proposal => 
      proposal.id === id ? { ...proposal, status: 'Recusada' } : proposal
    ))
  }

  return (
    <Layout>
    <div className="p-6">
      <h1 className="mb-4 text-2xl font-bold text-purple-700">Propostas recebidas</h1>
      <div className="rounded-lg bg-white p-6 shadow-md">
        <div className="mb-4">
          <Input
            placeholder="Pesquisar propostas..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="border-purple-200 focus:border-purple-500 focus:ring-purple-500"
          />
        </div>
        <ProposalsTable 
          proposals={filteredProposals}
          onAccept={handleAccept}
          onReject={handleReject}
        />
      </div>
    </div>
    </Layout>
  )
}

