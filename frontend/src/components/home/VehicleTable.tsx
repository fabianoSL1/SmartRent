"use client"

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
import { MoreHorizontal, Edit, Trash2 } from 'lucide-react'

interface Vehicle {
  id: number
  placa: string
  modelo: string
  ano: number
  status: string
}

const initialVehicles: Vehicle[] = [
  { id: 1, placa: "ABC-1234", modelo: "Toyota Corolla", ano: 2020, status: "Disponível" },
  { id: 2, placa: "DEF-5678", modelo: "Honda Civic", ano: 2019, status: "Em uso" },
  { id: 3, placa: "GHI-9012", modelo: "Ford Focus", ano: 2021, status: "Em manutenção" },
]

export function VehicleTable() {
  const [vehicles, setVehicles] = useState<Vehicle[]>(initialVehicles)
  const [searchTerm, setSearchTerm] = useState("")

  const filteredVehicles = vehicles.filter((vehicle) =>
    Object.values(vehicle).some((value) =>
      value.toString().toLowerCase().includes(searchTerm.toLowerCase())
    )
  )

  const handleDelete = (id: number) => {
    setVehicles(vehicles.filter((vehicle) => vehicle.id !== id))
  }

  const handleEdit = (id: number) => {
    // Implement edit functionality
    console.log(`Edit vehicle with id: ${id}`)
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
              <TableCell>{vehicle.placa}</TableCell>
              <TableCell>{vehicle.modelo}</TableCell>
              <TableCell>{vehicle.ano}</TableCell>
              <TableCell>{vehicle.status}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger asChild>
                    <Button variant="ghost" className="h-8 w-8 p-0">
                      <span className="sr-only">Abrir menu</span>
                      <MoreHorizontal className="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem onClick={() => handleEdit(vehicle.id)}>
                      <Edit className="mr-2 h-4 w-4" />
                      <span>Editar</span>
                    </DropdownMenuItem>
                    <DropdownMenuItem onClick={() => handleDelete(vehicle.id)}>
                      <Trash2 className="mr-2 h-4 w-4" />
                      <span>Excluir</span>
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

