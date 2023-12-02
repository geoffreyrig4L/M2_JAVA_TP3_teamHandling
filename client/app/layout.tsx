import type { Metadata } from "next"
import { Inter } from "next/font/google"
import "./style/globals.css"
import "./style/createTeamForm.css"
import Image from "next/image"
import Link from "next/link"

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
          <nav className="absolute left_0 top-0 flex flex-col bg-menu-color w-[12%] h-full text-text-menu p-8 items-center space-y-6 border-r-[10px] border-secondary-color">
            <h1 className="text-xl font-[500]">Équipes</h1>
            <Link className="hover:scale-[170%] transition-transform" href="/createTeam">
              <Image src="/add.png" width={27} height={27} alt="Ajouter une équipe" />
            </Link>
          </nav>
          <div className="h-full w-full flex flex-col justify-center items-center">{children}</div>
        </main>
      </body>
    </html>
  )
}
