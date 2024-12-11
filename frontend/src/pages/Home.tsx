import { Layout } from "@/components/Layout"
import { VehicleTable } from "@/components/home/VehicleTable"

export function Home() {
  return (
    <Layout>
      <h1 className="mb-4 text-2xl font-bold text-purple-700">Gerenciamento de Veículos</h1>
      <div className="rounded-lg bg-white p-6 shadow-md">
        <VehicleTable />
      </div>
    </Layout>
  )
}