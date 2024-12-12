import { toast } from "@/hooks/use-toast";

export async function alertMessage<T>(handler: () => Promise<T>, message: string) {
    try {
        return await handler() as T;
    } catch (err) {
        if (err instanceof Error) {
            toast({
                title: message,
                description: err.message
            })
        }

        throw err
    }
}