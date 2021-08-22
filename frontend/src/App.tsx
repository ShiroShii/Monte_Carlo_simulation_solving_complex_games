import './App.css';
import BattleCreationPage from './components/pages/BattleCreationPage';
import BattleDetailsPage from './components/pages/BattleDetailsPage';
import PlayerCharacterDetailsPage from './components/pages/PlayerCharacterDetailsPage';
import PlayerCharacterCreationPage from './components/pages/PlayerCharacterCreationPage';
import BoardCreationPage from './components/pages/BoardCreationPage';
import BoardDetailsPage from './components/pages/BoardDetailsPage';
import NavigationBar from './components/NavigationBar';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import PlayerCharacterListPage from './components/pages/PlayerCharacterListPage';
import BoardListPage from './components/pages/BoardListPage';
import BattleListPage from './components/pages/BattleListPage';

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
