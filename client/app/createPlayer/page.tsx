export default function createPlayer(): JSX.Element {
  return (
    <div>
      <h1>Créer un joueur</h1>
      <div className="form-container">
        <form className="form">
          <div className="form-group space-y-3">
            <div>
              <label htmlFor="name">Nom</label>
              <input required={true} name="name" id="name" type="text" autoComplete="off" />
            </div>
            <div>
              <label htmlFor="firstname">Prénom</label>
              <input required={true} name="firstname" id="firstname" type="text" autoComplete="off" />
            </div>
            <div>
              <label htmlFor="jerseyNumber">Numéro de maillot</label>
              <input required={true} name="jerseyNumber" id="jerseyNumber" type="text" autoComplete="off" />
            </div>
            <div>
              <label htmlFor="position">Poste</label>
              <input required={true} name="position" id="position" type="text" autoComplete="off" />
            </div>
          </div>
          <button type="submit" className="form-submit-btn truncate">
            Créer ce joueur
          </button>
        </form>
      </div>
    </div>
  )
}
