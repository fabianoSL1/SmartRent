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
import { MoreHorizontal, Check } from 'lucide-react'
import { ProposalResponse } from "@/lib/proposalService"

interface ProposalsTableProps {
  proposals: ProposalResponse[]
  onCancel: (id: number) => void,
  onGenerateRent: (id: number) => void
}

export function ProposalsTable({ proposals, onCancel, onGenerateRent }: ProposalsTableProps) {
  return (
    <Table>
      <TableHeader>
        <TableRow className="bg-purple-50">
          <TableHead className="text-purple-700">Placa do Veículo</TableHead>
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
                  <DropdownMenuItem onClick={() => onCancel(proposal.id)} disabled={!['PENDING', 'APPROVED'].includes(proposal.status)}>
                    <Check className="mr-2 h-4 w-4 text-green-600" />
                    <span>Cancelar</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onGenerateRent(proposal.id)}>
                    <Check className="mr-2 h-4 w-4 text-green-600" />
                    <span>Gerar aluguel</span>
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
