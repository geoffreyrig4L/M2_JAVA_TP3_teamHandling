"use client"
import type { AxiosResponse } from "axios"
import { useEffect, useState } from "react"
import { PiTrashSimpleFill } from "react-icons/pi"
import { RiPencilFill } from "react-icons/ri"
import type { Team } from "../interface/team.interface"
import { deleteTeam, getTeam, updateTeam } from "./api/routes"

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

  async function requestApiToDeleteTeam(teamId: number): Promise<void> {
    try {
      await deleteTeam(teamId)
      window.location.href = "/"
    } catch (error) {
      console.error("Erreur lors de la suppression de l'équipe :", error)
    }
  }

  async function requestApiToUpdateTeam(teamId: number): Promise<void> {
    const inputName: HTMLInputElement = document.getElementById("inputName") as HTMLInputElement
    const name: string = inputName.value

    const inputSlogan: HTMLInputElement = document.getElementById("inputSlogan") as HTMLInputElement
    const slogan: string = inputSlogan.value

    const teamModified: Team = { name: name, slogan: slogan }
    try {
      await updateTeam(teamId, teamModified).then((results): void => {
        setTeam(results.data)
      })
      window.location.reload()
    } catch (error) {
      console.error("Erreur lors de la suppression de l'équipe :", error)
    }
  }

  return (
    <div>
      {team !== undefined && (
        <div className="text-text-menu flex flex-col items-start space-y-3">
          <div className="flex flex-row items-center space-x-4">
            <div className="text-black w-[50px]">Nom</div>
            <input
              className="inputInTeamPage pointer-events-none text-3xl text-secondary-color"
              type="text"
              id="inputName"
              name="name"
              defaultValue={team.name}
            />
          </div>
          <div className="flex flex-row items-center space-x-4">
            <div className="text-black w-[50px]">Slogan</div>
            <input
              className="inputInTeamPage pointer-events-none text-xl"
              type="text"
              name="slogan"
              id="inputSlogan"
              defaultValue={team.slogan}
            />
          </div>
          <div className="flex flex-row items-start space-x-4">
            <button
              onClick={() => {
                void requestApiToDeleteTeam(team.id as number)
              }}>
              <PiTrashSimpleFill className="text-orange-200 hover:text-orange-500 text-2xl hover:scale-[130%] transition-transformation duration-300" />
            </button>
            <button
              title="Cliquer ici pour modifier cette équipe"
              onClick={() => {
                const updateTeamButton: HTMLButtonElement = document.getElementById(
                  "updateTeamButton",
                ) as HTMLButtonElement
                updateTeamButton.classList.remove("hidden", "pointer-events-none")
                const inputs: any = document.getElementsByClassName("inputInTeamPage")
                for (const input of inputs) {
                  input.classList.remove("pointer-events-none")
                  input.classList.add("opacity-30")
                }
                document.getElementById("inputName")?.focus()
              }}>
              <RiPencilFill className="text-orange-200 hover:text-orange-500 text-2xl hover:scale-[130%] transition-transformation duration-300" />
            </button>
            <button
              type="submit"
              id="updateTeamButton"
              className="truncate underline underline-offset-1 text-primary-color hover:text-secondary-color hover:scale-[102%] transition-transformation duration-300 hidden pointer-events-none"
              // eslint-disable-next-line no-void
              onClick={() => void requestApiToUpdateTeam(team.id as number)}>
              Modifier cette équipe
            </button>
          </div>
        </div>
      )}
    </div>
  )
}

export default TeamPage
