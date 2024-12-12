import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import AppRoutes from './routes.tsx'
import { BrowserRouter } from 'react-router'
import { Toaster } from './components/ui/toaster.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
    <Toaster />
  </StrictMode>,
)
