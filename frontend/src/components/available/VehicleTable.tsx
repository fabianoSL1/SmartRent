import { useState } from "react"
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { MoreHorizontal, Plus } from 'lucide-react'
import { VehicleResponse } from "@/lib/vehicleService"

export function VehicleTable({ vehicles }: { vehicles: VehicleResponse[]}) {
  const [searchTerm, setSearchTerm] = useState("")

  const filteredVehicles = vehicles.filter(({identifier, model, color, year}) =>
    Object.values({identifier, model, color, year}).some((value) =>
      value?.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  async function createProposal(vehicle: VehicleResponse) {
    console.log(vehicle)
  }

  return (
    <div>
      <div className="mb-4">
        <Input
          placeholder="Pesquisar veículos..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border-purple-200 focus:border-purple-500 focus:ring-purple-500"
        />
      </div>
      <Table>
        <TableHeader>
          <TableRow className="bg-purple-50">
            <TableHead className="text-purple-700">Placa</TableHead>
            <TableHead className="text-purple-700">Modelo</TableHead>
            <TableHead className="text-purple-700">Ano</TableHead>
            <TableHead className="text-purple-700">Ações</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filteredVehicles.map((vehicle) => (
            <TableRow key={vehicle.id} className="hover:bg-purple-50">
              <TableCell>{vehicle.identifier}</TableCell>
              <TableCell>{vehicle.model}</TableCell>
              <TableCell>{vehicle.year}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger asChild>
                    <Button variant="ghost" className="h-8 w-8 p-0">
                      <span className="sr-only">Abrir menu</span>
                      <MoreHorizontal className="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem onClick={() => createProposal(vehicle)}>
                      <Plus className="mr-2 h-4 w-4" />
                      <span>Criar proposta</span>
                    </DropdownMenuItem>
                  </DropdownMenuContent>
                </DropdownMenu>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}

