import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { BattleCreationPage, BattleDetailsPage, BattleListPage } from '../battle'
import {
    PlayerCharacterCreationPage,
    PlayerCharacterDetailsPage,
    PlayerCharacterListPage
} from '../playerCharacter'
import NavigationBar from './NavigationBar'

export function MainComponent() {
    return (
        <Router>
            <NavigationBar />
            <Switch>
                <Route exact path="/character"
                    component={PlayerCharacterListPage} />
                <Route exact path="/character/create"
                    component={PlayerCharacterCreationPage} />
                <Route exact path="/character/:id" render={routeProps =>
                    <PlayerCharacterDetailsPage id={routeProps.match.params.id} />
                } />
                <Route exact path="/battle" component={BattleListPage} />
                <Route exact path="/battle/create" component={BattleCreationPage} />
                <Route exact path="/battle/:id" render={routeProps =>
                    <BattleDetailsPage id={routeProps.match.params.id} />
                } />
            </Switch>
        </Router>
    )
}
