import type { AxiosResponse } from "axios"
import type { Team } from "@/app/interface/team.interface"
import { api } from "./api"

export const getAllTeams = async (): Promise<AxiosResponse<Team[]>> => {
  return await api().get("/teams")
}
