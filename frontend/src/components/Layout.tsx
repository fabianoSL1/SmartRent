import { Sidebar } from "@/components/SideBar"
import { useState } from "react"

export function Layout({children}: {children: React.ReactNode}) {
  const [sidebarOpen, setSidebarOpen] = useState(false)

  return (
    <div className="flex h-screen overflow-hidden bg-gray-100">
      <Sidebar open={sidebarOpen} setOpen={setSidebarOpen} />
      <div className="flex-1 overflow-auto">
        <div className="p-6">
            {children}
        </div>
      </div>
    </div>
  )
}