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

interface Proposal {
  id: number
  vehiclePlate: string
  proposerName: string
  amount: number
  date: string
  status: 'Pendente' | 'Aceita' | 'Recusada'
}

interface ProposalsTableProps {
  proposals: Proposal[]
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
          <TableHead className="text-purple-700">Data</TableHead>
          <TableHead className="text-purple-700">Status</TableHead>
          <TableHead className="text-purple-700">Ações</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {proposals.map((proposal) => (
          <TableRow key={proposal.id} className="hover:bg-purple-50">
            <TableCell>{proposal.vehiclePlate}</TableCell>
            <TableCell>{proposal.proposerName}</TableCell>
            <TableCell>R$ {proposal.amount.toLocaleString('pt-BR')}</TableCell>
            <TableCell>{new Date(proposal.date).toLocaleDateString('pt-BR')}</TableCell>
            <TableCell>
              <span className={`inline-block rounded-full px-2 py-1 text-xs font-semibold ${
                proposal.status === 'Pendente' ? 'bg-yellow-200 text-yellow-800' :
                proposal.status === 'Aceita' ? 'bg-green-200 text-green-800' :
                'bg-red-200 text-red-800'
              }`}>
                {proposal.status}
              </span>
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
                  <DropdownMenuItem onClick={() => onAccept(proposal.id)} disabled={proposal.status !== 'Pendente'}>
                    <Check className="mr-2 h-4 w-4 text-green-600" />
                    <span>Aceitar</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onReject(proposal.id)} disabled={proposal.status !== 'Pendente'}>
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
