import type { Metadata } from "next"
import { Inter } from "next/font/google"
import "./style/globals.css"
import "./style/createTeamForm.css"
import NavBar from "./components/NavBar"

const inter = Inter({ subsets: ["latin"] })

export const metadata: Metadata = {
  title: "mytp3",
  icons: {
    icon: "/icon.png",
  },
}

export default function RootLayout({ children }: { children: React.ReactNode }): JSX.Element {
  return (
    <html lang="en">
      <body className={inter.className}>
        <main>
          <NavBar />
          <div className="h-full w-full flex flex-col justify-center items-center">{children}</div>
        </main>
      </body>
    </html>
  )
}
