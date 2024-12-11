"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { registerVehicle, RegisterVehicleRequest } from "@/lib/vehicleService"
import { Plus } from "lucide-react"

export default function RegisterVehicle() {
    const [vehicle, setVehicle] = useState<RegisterVehicleRequest>({
        brand: "",
        model: "",
        year: "",
        identifier: "",
        color: ""
    })
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [isOpen, setIsOpen] = useState(false)

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = e.target
        setVehicle(prev => ({ ...prev, [id]: value }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setIsSubmitting(true)

        try {
            await registerVehicle(vehicle)
            setIsOpen(false)
        } catch (error) {
            console.error('Erro ao enviar dados:', error)
            // Aqui você pode adicionar uma notificação de erro
        } finally {
            setIsSubmitting(false)
        }
    }

    return (
        <Dialog open={isOpen} onOpenChange={setIsOpen}>
            <DialogTrigger asChild>
                <Button variant="ghost" className="justify-start hover:bg-purple-100 hover:text-purple-700" >
                    <Plus className="h-4 w-4" />
                    Registrar Veículo
                </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Registrar Veículo</DialogTitle>
                    <DialogDescription>
                        Preencha os detalhes do veículo aqui. Clique em salvar quando terminar.
                    </DialogDescription>
                </DialogHeader>
                <form onSubmit={handleSubmit}>
                    <div className="grid gap-4 py-4">
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="brand" className="text-right">
                                Marca
                            </Label>
                            <Input
                                id="brand"
                                placeholder="Ex: Toyota"
                                className="col-span-3"
                                value={vehicle.brand}
                                onChange={handleInputChange}
                                required
                            />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="model" className="text-right">
                                Modelo
                            </Label>
                            <Input
                                id="model"
                                placeholder="Ex: Corolla"
                                className="col-span-3"
                                value={vehicle.model}
                                onChange={handleInputChange}
                                required
                            />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="year" className="text-right">
                                Ano
                            </Label>
                            <Input
                                id="year"
                                type="number"
                                placeholder="Ex: 2023"
                                className="col-span-3"
                                value={vehicle.year}
                                onChange={handleInputChange}
                                required
                            />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="identifier" className="text-right">
                                Placa
                            </Label>
                            <Input
                                id="identifier"
                                placeholder="Ex: ABC-1234"
                                className="col-span-3"
                                value={vehicle.identifier}
                                onChange={handleInputChange}
                                required
                            />
                        </div>
                    </div>
                    <DialogFooter>
                        <Button type="submit" disabled={isSubmitting} >
                            {isSubmitting ? "Salvando..." : "Salvar Registro"}
                        </Button>
                    </DialogFooter>
                </form>
            </DialogContent>
        </Dialog>
    )
}

