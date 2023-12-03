import type { AxiosResponse } from "axios"
import type { Team } from "@/app/interface/team.interface"
import { api } from "./api"

export const getAllTeams = async (): Promise<AxiosResponse<Team[]>> => {
  return await api().get("/teams")
}

export const getTeam = async (teamId: number): Promise<AxiosResponse<Team>> => {
  return await api().get(`/teams/${teamId}`)
}

export const addTeam = async (team: Team): Promise<AxiosResponse> => {
  return await api().post("/teams", team)
}

export const deleteTeam = async (teamId: number): Promise<AxiosResponse> => {
  return await api().delete(`/teams/${teamId}`)
}

export const updateTeam = async (teamId: number, team: Team): Promise<AxiosResponse<Team>> => {
  return await api().put(`/teams/${teamId}`, team)
}
