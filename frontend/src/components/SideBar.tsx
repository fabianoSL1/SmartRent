
import { Button } from "@/components/ui/button"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet"
import {
  Collapsible,
  CollapsibleContent,
  CollapsibleTrigger,
} from "@/components/ui/collapsible"
import { Menu, Car, Settings, LogOut, FileText, ChevronDown } from 'lucide-react'
import { useState } from "react"
import { NavLink, useNavigate } from "react-router"

interface SidebarProps {
  open: boolean
  setOpen: (open: boolean) => void
}

export function Sidebar({ open, setOpen }: SidebarProps) {
  return (
    <Sheet open={open} onOpenChange={setOpen}>
      <SheetTrigger asChild>
        <Button variant="outline" size="icon" className="fixed left-4 top-4 z-40 lg:hidden">
          <Menu className="h-6 w-6" />
        </Button>
      </SheetTrigger>
      <SheetContent side="left" className="w-64 bg-purple-50 p-0">
        <SidebarContent />
      </SheetContent>
      <div className="hidden w-64 flex-col bg-purple-50 lg:flex">
        <SidebarContent />
      </div>
    </Sheet>
  )
}

function SidebarContent() {
  const [proposalsOpen, setProposalsOpen] = useState(false)
  const [vehiclesOpen, setVehiclesOpen] = useState(false)
  const navigate = useNavigate();

  function logout() {
    sessionStorage.removeItem("token");
    navigate("/login")
  }
  
  return (
    <ScrollArea className="h-full py-6">
      <div className="px-3 py-2">
        <h2 className="mb-2 px-4 text-lg font-semibold text-purple-700">SMART RENT</h2>
        

        <div className="space-y-1">
        <Collapsible open={vehiclesOpen} onOpenChange={setVehiclesOpen}>
            <CollapsibleTrigger asChild>
              <Button variant="ghost" className="w-full justify-between hover:bg-purple-100 hover:text-purple-700">
                <div className="flex items-center">
                  <Car className="mr-2 h-4 w-4" />
                  Veículos
                </div>
                <ChevronDown className={`h-4 w-4 transition-transform ${vehiclesOpen ? "rotate-180" : ""}`} />
              </Button>
            </CollapsibleTrigger>
            <CollapsibleContent className="space-y-1 px-4 py-2">
              <Button variant="ghost" className="w-full justify-start hover:bg-purple-100 hover:text-purple-700" asChild>
                <NavLink to="/">
                  Meus veiculos
                </NavLink>
              </Button>
              <Button variant="ghost" className="w-full justify-start hover:bg-purple-100 hover:text-purple-700" asChild>
                <NavLink to="/disponiveis">
                  disponiveis
                </NavLink>
              </Button>
            </CollapsibleContent>
          </Collapsible>
          
          <Collapsible open={proposalsOpen} onOpenChange={setProposalsOpen}>
            <CollapsibleTrigger asChild>
              <Button variant="ghost" className="w-full justify-between hover:bg-purple-100 hover:text-purple-700">
                <div className="flex items-center">
                  <FileText className="mr-2 h-4 w-4" />
                  Propostas
                </div>
                <ChevronDown className={`h-4 w-4 transition-transform ${proposalsOpen ? "rotate-180" : ""}`} />
              </Button>
            </CollapsibleTrigger>
            <CollapsibleContent className="space-y-1 px-4 py-2">
              <Button variant="ghost" className="w-full justify-start hover:bg-purple-100 hover:text-purple-700" asChild>
                <NavLink to="/propostas/enviadas">
                  Enviadas
                </NavLink>
              </Button>
              <Button variant="ghost" className="w-full justify-start hover:bg-purple-100 hover:text-purple-700" asChild>
                <NavLink to="/propostas/recebidas">
                  Recebidas
                </NavLink>
              </Button>
            </CollapsibleContent>
          </Collapsible>
        </div>
      </div>
      
      <div className="absolute bottom-4 left-0 right-0 px-3">
        <Button onClick={logout} variant="ghost" className="w-full justify-start text-red-500 hover:bg-red-100 hover:text-red-700">
          <LogOut className="mr-2 h-4 w-4" />
          Sair
        </Button>
      </div>
    </ScrollArea>
  )
}

