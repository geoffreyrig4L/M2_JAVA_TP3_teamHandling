"use client"
import type { Team } from "../interface/team.interface"
import { useCallback } from "react"
import { addTeam } from "./api/routes"
import { useRouter } from "next/navigation"

const CreateTeamForm = (): JSX.Element => {
  const router = useRouter()

  const handleSubmit = useCallback(async (event: any) => {
    event.preventDefault()
    const formData = new FormData(event.target)
    const name: string = formData.get("name") as string
    const slogan: string = formData.get("slogan") as string
    const newTeam: Team = { name: name, slogan: slogan }
    try {
      await addTeam(newTeam).then((results): void => {
        console.log(results.data)
        router.push("/")
      })
    } catch (error) {
      console.log(error)
    }
  }, [])

  return (
    <div>
      <h1>Créer une équipe</h1>
      <div className="form-container">
        <form
          className="form"
          onSubmit={(event) => {
            void handleSubmit(event)
          }}>
          <div className="form-group space-y-3">
            <div>
              <label htmlFor="name">Nom</label>
              <input required={true} name="name" id="name" type="text" autoComplete="off" />
            </div>
            <div>
              <label htmlFor="slogan">Slogan</label>
              <input required={true} name="slogan" id="slogan" type="text" autoComplete="off" />
            </div>
          </div>
          <button type="submit" className="form-submit-btn truncate">
            Créer cette équipe
          </button>
        </form>
      </div>
    </div>
  )
}

export default CreateTeamForm
