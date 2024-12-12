import { Layout } from "@/components/Layout"
import { VehicleTable } from "@/components/available/VehicleTable"
import { listVehiclesAvailables, VehicleResponse } from "@/lib/vehicleService"
import { useEffect, useState } from "react"

export default function Available() {
  const [vehicles, setVehicles] = useState<VehicleResponse[]>([]);

  useEffect(() => {
    listVehiclesAvailables().then(vehiclesResponse => setVehicles(vehiclesResponse))
  }, [])

  return (
    <Layout>
      <h1 className="mb-4 text-2xl font-bold text-purple-700">Gerenciamento de Veículos</h1>
      <div className="rounded-lg bg-white p-6 shadow-md">

        {vehicles.length > 0 ?
          <VehicleTable vehicles={vehicles} />
          : <p className="text-center text-muted-foreground">Nenhum veículo disponivel no momento.</p>}
      </div>
    </Layout>
  )
}