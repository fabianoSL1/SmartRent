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
import { MoreHorizontal, Edit } from 'lucide-react'
import { disableVehicle, enableVehicle, VehicleResponse } from "@/lib/vehicleService"

const labels = {
  "AVAILABLE": "Disponivel",
  "UNAVAILABLE": "Indisponivel",
  "IN_RENT": "Alugado",
  "RESERVED": "Reservado"
}

export function VehicleTable({ vehicles, updateVehicle }: { vehicles: VehicleResponse[], updateVehicle: (vehicle: VehicleResponse) => void }) {
  const [searchTerm, setSearchTerm] = useState("")

  const filteredVehicles = vehicles.filter(({identifier, model, color, year}) =>
    Object.values({identifier, model, color, year}).some((value) =>
      value?.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  const toogleAvailable = async (vehicle: VehicleResponse) => {
    let response;
    if (vehicle.status == "AVAILABLE") {
      response = await disableVehicle(vehicle.id);
    } else {
      response = await enableVehicle(vehicle.id);
    }

    updateVehicle(response);
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
            <TableHead className="text-purple-700">Status</TableHead>
            <TableHead className="text-purple-700">Ações</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filteredVehicles.map((vehicle) => (
            <TableRow key={vehicle.id} className="hover:bg-purple-50">
              <TableCell>{vehicle.identifier}</TableCell>
              <TableCell>{vehicle.model}</TableCell>
              <TableCell>{vehicle.year}</TableCell>
              <TableCell>{labels[vehicle.status as keyof typeof labels]}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger asChild>
                    <Button variant="ghost" className="h-8 w-8 p-0">
                      <span className="sr-only">Abrir menu</span>
                      <MoreHorizontal className="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem onClick={() => toogleAvailable(vehicle)}>
                      <Edit className="mr-2 h-4 w-4" />
                      <span>{vehicle.status != "AVAILABLE" ? "Habilitar" : "Desabilitar"}</span>
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

