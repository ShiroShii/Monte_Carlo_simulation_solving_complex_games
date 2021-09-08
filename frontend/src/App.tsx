import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import { BattleCreationPage, BattleDetailsPage, BattleListPage } from './components/battle';
import { BoardCreationPage, BoardDetailsPage, BoardListPage } from './components/board';
import { PlayerCharacterCreationPage, PlayerCharacterDetailsPage, PlayerCharacterListPage } from './components/playerCharacter';
import { NavigationBar } from './components/_common';

function App() {
  return (
    <div className="App">
      <header className="DnD">
        <Router>
          <NavigationBar />
          <Switch>
            <Route exact path="/character" component={PlayerCharacterListPage} />
            <Route exact path="/character/create" component={PlayerCharacterCreationPage} />
            <Route exact path="/character/:id" render={routeProps => <PlayerCharacterDetailsPage id={routeProps.match.params.id} />} />
            <Route exact path="/board" component={BoardListPage} />
            <Route exact path="/board/create" component={BoardCreationPage} />
            <Route exact path="/board/:id" render={routeProps => <BoardDetailsPage id={routeProps.match.params.id} />} />
            <Route exact path="/battle" component={BattleListPage} />
            <Route exact path="/battle/create" component={BattleCreationPage} />
            <Route exact path="/battle/:id" render={routeProps => <BattleDetailsPage id={routeProps.match.params.id} />} />
          </Switch>
        </Router>
      </header>
    </div>
  );
}

export default App;
