import { ReactNode, useEffect } from 'react';
import { useNavigate } from 'react-router';

export default function PrivateRouter({ children }: {children: ReactNode}) {
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem('token');

    if (!token) {
      navigate('/login');
    }
  }, [navigate]);

  return children;
}
