import { useEffect, useState } from "react"
import { Input } from "@/components/ui/input"
import { ProposalsTable } from "@/components/proposalReceive/ProposalTable"
import { Layout } from "@/components/Layout"
import { approveProposal, listProposalsReceive, ProposalResponse, rejectProposal } from "@/lib/proposalService"


export default function ProposalReceived() {
  const [proposals, setProposals] = useState<ProposalResponse[]>([])
  const [searchTerm, setSearchTerm] = useState("")

  useEffect(() => {
    listProposalsReceive().then(proposals => setProposals(proposals))
  }, [])

  const filteredProposals = proposals.filter((proposal) =>
    Object.values(proposal).some((value) =>
      value.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  async function fetchProposals() {
    listProposalsReceive().then(proposals => setProposals(proposals))
  }
  const handleAccept = async (id: number) => {
    await approveProposal(id)
    await fetchProposals()
  }

  const handleReject = async (id: number) => {
    await rejectProposal(id)
    await fetchProposals()
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

