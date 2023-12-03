"use client"
import type { Team } from "../interface/team.interface"
import type { AxiosResponse } from "axios"
import Image from "next/image"
import Link from "next/link"
import { useEffect, useState } from "react"
import { getAllTeams } from "./api/routes"

const NavBar = (): JSX.Element => {
  const [teams, setTeams] = useState<Team[]>([])

  useEffect(() => {
    async function fetchTeams(): Promise<void> {
      await getAllTeams().then((results: AxiosResponse<Team[]>) => {
        setTeams(results.data)
      })
    }
    void fetchTeams()
  }, [])

  return (
    <nav className="absolute left-0 top-0 flex flex-col bg-menu-color w-[12%] min-w-[250px] h-full text-text-menu p-12 items-start space-y-6 border-r-[10px] border-secondary-color">
      <h1 className="place-self-center text-secondary-color text-xl font-[500]">Équipes</h1>
      {teams.map((team: Team) => (
        <Link key={team.id} className="hover:scale-[110%] duration-300 transition-transform" href={`/teams/${team.id}`}>
          <p>{team.name}</p>
        </Link>
      ))}
      <Link className="hover:scale-[120%] duration-300 transition-transform place-self-center" href="/createTeam">
        <Image src="/add.png" width={27} height={27} alt="Ajouter une équipe" />
      </Link>
    </nav>
  )
}

export default NavBar
