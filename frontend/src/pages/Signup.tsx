import LoginForm from "@/components/signup/SignupForm";

export default function Signup() {
    return (
      <div className="flex h-screen">
        <div className="hidden w-1/2 bg-purple-600 lg:block" />        
        <div className="flex w-full items-center justify-center">
          <LoginForm />
        </div>
      </div>
    )
  }
  