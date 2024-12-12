import { useState } from 'react'
import { format } from 'date-fns'
import { CalendarIcon } from 'lucide-react'
import { cn } from '@/lib/utils'
import { Button } from '@/components/ui/button'
import { Calendar } from '@/components/ui/calendar'
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from '@/components/ui/popover'
import { createProposal, createProposalRequest } from '@/lib/proposalService'

export function VehicleProposalModal({ vehicleId }: { vehicleId: number }) {
    const [startDate, setStartDate] = useState<Date>()
    const [endDate, setEndDate] = useState<Date>()
    const [value, setValue] = useState('')
    const [isOpen, setIsOpen] = useState(false)
    
    const handleValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const rawValue = e.target.value.replace(/[^\d]/g, '')
        const numberValue = parseInt(rawValue, 10) / 100
        setValue(numberValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }))
    }

    const handleSubmit = async () => {
        if (!startDate || !endDate || !value) {
            alert('Por favor, preencha todos os campos')
            return
        }

        const valueInCents = Math.round(parseFloat(value.replace(/[^\d,]/g, '').replace(',', '.')) * 100)

        const proposalRequest: createProposalRequest = {
            beginDate: format(startDate, 'yyyy-MM-dd'),
            endDate: format(endDate, 'yyyy-MM-dd'),
            amount: valueInCents,
            vehicleId: vehicleId
        }

        await createProposal(proposalRequest)
        setIsOpen(false)
    }

    return (
        <Dialog open={isOpen} onOpenChange={setIsOpen}>
            <DialogTrigger asChild>
                <Button className="hover:text-purple-500" variant="ghost">Enviar Proposta</Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Enviar Proposta</DialogTitle>
                    <DialogDescription>
                        Preencha os detalhes da sua proposta para o veículo.
                    </DialogDescription>
                </DialogHeader>
                <div className="grid gap-4 py-4">
                    <div className="grid grid-cols-4 items-center gap-4">
                        <Label htmlFor="start-date" className="text-right">
                            Data Inicial
                        </Label>
                        <Popover>
                            <PopoverTrigger asChild>
                                <Button
                                    id="start-date"
                                    variant={"outline"}
                                    className={cn(
                                        "w-[280px] justify-start text-left font-normal",
                                        !startDate && "text-muted-foreground"
                                    )}
                                >
                                    <CalendarIcon className="mr-2 h-4 w-4" />
                                    {startDate ? format(startDate, "PPP") : <span>Selecione a data</span>}
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0">
                                <Calendar
                                    mode="single"
                                    selected={startDate}
                                    onSelect={setStartDate}
                                    initialFocus
                                />
                            </PopoverContent>
                        </Popover>
                    </div>
                    <div className="grid grid-cols-4 items-center gap-4">
                        <Label htmlFor="end-date" className="text-right">
                            Data Final
                        </Label>
                        <Popover>
                            <PopoverTrigger asChild>
                                <Button
                                    id="end-date"
                                    variant={"outline"}
                                    className={cn(
                                        "w-[280px] justify-start text-left font-normal",
                                        !endDate && "text-muted-foreground"
                                    )}
                                >
                                    <CalendarIcon className="mr-2 h-4 w-4" />
                                    {endDate ? format(endDate, "PPP") : <span>Selecione a data</span>}
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0">
                                <Calendar
                                    mode="single"
                                    selected={endDate}
                                    onSelect={setEndDate}
                                    initialFocus
                                />
                            </PopoverContent>
                        </Popover>
                    </div>
                    <div className="grid grid-cols-4 items-center gap-4">
                        <Label htmlFor="value" className="text-right">
                            Valor
                        </Label>
                        <Input
                            id="value"
                            value={value}
                            onChange={handleValueChange}
                            className="col-span-3"
                            placeholder="R$ 0,00"
                        />
                    </div>
                </div>
                <DialogFooter>
                    <Button type="submit" onClick={handleSubmit}>Enviar Proposta</Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    )
}

