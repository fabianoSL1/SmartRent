import { useState } from "react"
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { Input } from "@/components/ui/input"
import { VehicleResponse } from "@/lib/vehicleService"
import { VehicleProposalModal } from "../proposalReceive/VehicleProposalModal"

export function VehicleTable({ vehicles }: { vehicles: VehicleResponse[] }) {
  const [searchTerm, setSearchTerm] = useState("")

  const filteredVehicles = vehicles.filter(({ identifier, model, color, year }) =>
    Object.values({ identifier, model, color, year }).some((value) =>
      value?.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

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
            <TableHead className="text-purple-700"></TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filteredVehicles.map((vehicle) => (
            <TableRow key={vehicle.id} className="hover:bg-purple-50">
              <TableCell>{vehicle.identifier}</TableCell>
              <TableCell>{vehicle.model}</TableCell>
              <TableCell>{vehicle.year}</TableCell>
              <TableCell>
                <VehicleProposalModal vehicleId={vehicle.id} />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}

