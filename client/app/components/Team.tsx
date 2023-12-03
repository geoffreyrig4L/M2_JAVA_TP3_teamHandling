"use client"
import type { AxiosResponse } from "axios"
import type { Team } from "../interface/team.interface"
import { useEffect, useState } from "react"
import { deleteTeam, getTeam } from "./api/routes"
import { PiTrashSimpleFill } from "react-icons/pi"
import { RiPencilFill } from "react-icons/ri"
import { useRouter } from "next/navigation"

interface TeamProps {
  teamId: number
}

const TeamPage: React.FC<TeamProps> = ({ teamId }) => {
  const router = useRouter()
  const [team, setTeam] = useState<Team | undefined>(undefined)

  useEffect(() => {
    async function fetchTeams(): Promise<void> {
      await getTeam(teamId).then((results: AxiosResponse<Team>) => {
        setTeam(results.data)
      })
    }
    void fetchTeams()
  }, [])

  async function requestApiToDeleteTeam(teamId: number): Promise<void> {
    try {
      await deleteTeam(teamId)
      router.push("/")
    } catch (error) {
      console.error("Erreur lors de la suppression de l'équipe :", error)
    }
  }

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
          <div className="flex flex-row items-start space-x-4">
            <button
              onClick={() => {
                void requestApiToDeleteTeam(team.id as number)
              }}>
              <PiTrashSimpleFill className="text-orange-200 hover:text-orange-500 text-xl hover:scale-[130%]" />
            </button>
            <button
              onClick={() => {
                // putTeam()
              }}>
              <RiPencilFill className="text-orange-200 hover:text-orange-500 text-xl hover:scale-[130%]" />
            </button>
          </div>
        </div>
      )}
    </div>
  )
}

export default TeamPage
