export default function createTeam(): JSX.Element {
  return (
    <div>
      <h1>Créer une équipe</h1>
      <div className="form-container">
        <form className="form">
          <div className="form-group space-y-3">
            <div>
              <label htmlFor="name">Nom</label>
              <input required={true} name="name" id="name" type="text" autoComplete="off" />
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
