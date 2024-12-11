import LoginForm from "@/components/signup/SignupForm";

export default function Signup() {
    return (
      <div className="flex h-screen">
        {/* Left side with solid purple color */}
        <div className="hidden w-1/2 bg-purple-600 lg:block" />
        
        {/* Right side with login form */}
        <div className="flex w-full items-center justify-center">
          <LoginForm />
        </div>
      </div>
    )
  }
  