import { Layout } from "@/components/Layout"
import RegisterVehicle from "@/components/home/RegisterVehicle";
import { VehicleTable } from "@/components/home/VehicleTable"
import { listVehicles, VehicleResponse } from "@/lib/vehicleService"
import { useEffect, useState } from "react"

export function Home() {
  const [vehicles, setVehicles] = useState<VehicleResponse[]>([]);

  useEffect(() => {
    listVehicles().then(vehiclesResponse => setVehicles(vehiclesResponse))
  }, [])

  function updateVehicle(vehicle: VehicleResponse) {
    setVehicles(vehicles.map(item => {
      if (vehicle.id == item.id) {
        return vehicle
      }
      return item
    }))
  }

  return (
    <Layout>
      <h1 className="mb-4 text-2xl font-bold text-purple-700">Gerenciamento de Veículos</h1>
      <div className="rounded-lg bg-white p-6 shadow-md">

        <RegisterVehicle />

        {vehicles.length > 0 ?
          <VehicleTable vehicles={vehicles} updateVehicle={updateVehicle} />
          : <p className="text-center text-muted-foreground">Nenhum veículo disponivel no momento.</p>}
      </div>
    </Layout>
  )
}