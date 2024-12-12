import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { MoreHorizontal, Check, X } from 'lucide-react'
import { ProposalResponse } from "@/lib/proposalService"

interface ProposalsTableProps {
  proposals: ProposalResponse[]
  onAccept: (id: number) => void
  onReject: (id: number) => void
}

export function ProposalsTable({ proposals, onAccept, onReject }: ProposalsTableProps) {
  return (
    <Table>
      <TableHeader>
        <TableRow className="bg-purple-50">
          <TableHead className="text-purple-700">Placa do Veículo</TableHead>
          <TableHead className="text-purple-700">Proponente</TableHead>
          <TableHead className="text-purple-700">Valor</TableHead>
          <TableHead className="text-purple-700">Inicio</TableHead>
          <TableHead className="text-purple-700">Fim</TableHead>
          <TableHead className="text-purple-700">Status</TableHead>
          <TableHead className="text-purple-700">Ações</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {proposals.map((proposal) => (
          <TableRow key={proposal.id} className="hover:bg-purple-50">
            <TableCell>{proposal.vehicleIdentifier}</TableCell>
            <TableCell>{proposal.renterName}</TableCell>
            <TableCell>{(proposal.amount / 100).toLocaleString('pt-BR', { style: "currency", currency: "BRL" })}</TableCell>
            <TableCell>{new Date(proposal.beginDate).toLocaleDateString('pt-BR')}</TableCell>
            <TableCell>{new Date(proposal.endDate).toLocaleDateString('pt-BR')}</TableCell>
            <TableCell>
              {proposal.status}
            </TableCell>
            <TableCell>
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" className="h-8 w-8 p-0">
                    <span className="sr-only">Abrir menu</span>
                    <MoreHorizontal className="h-4 w-4" />
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end">
                  <DropdownMenuItem onClick={() => onAccept(proposal.id)} disabled={proposal.status !== 'PENDING'}>
                    <Check className="mr-2 h-4 w-4 text-green-600" />
                    <span>Aceitar</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onReject(proposal.id)} disabled={proposal.status !== 'PENDING'}>
                    <X className="mr-2 h-4 w-4 text-red-600" />
                    <span>Recusar</span>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}
