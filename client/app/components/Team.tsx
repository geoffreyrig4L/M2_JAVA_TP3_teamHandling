"use client"
import type { AxiosResponse } from "axios"
import type { Team } from "../interface/team.interface"
import { useEffect, useState } from "react"
import { getTeam } from "./api/routes"

interface TeamProps {
  teamId: number
}

const TeamPage: React.FC<TeamProps> = ({ teamId }) => {
  const [team, setTeam] = useState<Team | undefined>(undefined)

  useEffect(() => {
    async function fetchTeams(): Promise<void> {
      await getTeam(teamId).then((results: AxiosResponse<Team>) => {
        setTeam(results.data)
      })
    }
    void fetchTeams()
  }, [])

  return (
    <div>
      {team !== undefined && (
        <div className="text-text-menu flex flex-col items-start space-y-3">
          <div className="flex flex-row items-center">
            <div className="text-black">Nom</div>
            <h1>{team.name}</h1>
          </div>
          <div className="flex flex-row items-center">
            <div className="text-black">Slogan</div>
            <h1>{team.slogan}</h1>
          </div>
        </div>
      )}
    </div>
  )
}

export default TeamPage
