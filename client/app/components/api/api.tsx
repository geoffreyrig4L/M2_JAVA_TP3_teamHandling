import axios from "axios"
import type { AxiosInstance } from "axios"

export const api = (): AxiosInstance => {
  return axios.create({
    baseURL: "http://localhost:8080",
    transformRequest: [
      (data, headers) => {
        headers["Content-Type"] = "application/json"
        return JSON.stringify(data)
      },
    ],
  })
}
