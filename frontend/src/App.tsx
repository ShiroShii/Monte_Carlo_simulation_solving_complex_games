import './App.css';
import BattleCreationPage from './components/page/BattleCreationPage';
import BattleDetailsPage from './components/page/BattleDetailsPage';
import PlayerCharacterDetailsPage from './components/page/PlayerCharacterDetailsPage';
import PlayerCharacterCreationPage from './components/page/PlayerCharacterCreationPage';
import BoardCreationPage from './components/page/BoardCreationPage';
import BoardDetailsPage from './components/page/BoardDetailsPage';
import NavigationBar from './components/NavigationBar';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import PlayerCharacterListPage from './components/page/PlayerCharacterListPage';
import BoardListPage from './components/page/BoardListPage';
import BattleListPage from './components/page/BattleListPage';

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
