import Team from "@/app/components/Team"

export default function TeamPage({ params }: { params: { teamId: number } }): any {
  return <Team teamId={params.teamId} />
}
