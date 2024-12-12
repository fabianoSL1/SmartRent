import { useEffect, useState } from "react"
import { Input } from "@/components/ui/input"
import { ProposalsTable } from "@/components/proposalSend/ProposalTable"
import { Layout } from "@/components/Layout"
import { cancelProposal, createRent, listProposalsSend, ProposalResponse } from "@/lib/proposalService"


export default function ProposalSend() {
  const [proposals, setProposals] = useState<ProposalResponse[]>([])
  const [searchTerm, setSearchTerm] = useState("")

  useEffect(() => {
    listProposalsSend().then(proposals => setProposals(proposals))
  }, [])

  const filteredProposals = proposals.filter((proposal) =>
    Object.values(proposal).some((value) =>
      value.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  async function fetchProposals() {
    listProposalsSend().then(proposals => setProposals(proposals))
  }
  async function handleCancel(id: number) {
    await cancelProposal(id);
    await fetchProposals();
  }

  async function handleGenerateRent(id: number) {
    await createRent(id);
    await fetchProposals()
  }

  return (
    <Layout>
      <div className="p-6">
        <h1 className="mb-4 text-2xl font-bold text-purple-700">Propostas enviadas</h1>
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
            onCancel={handleCancel}
            onGenerateRent={handleGenerateRent}
          />
        </div>
      </div>
    </Layout>
  )
}

