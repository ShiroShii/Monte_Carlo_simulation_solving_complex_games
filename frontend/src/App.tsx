import React from 'react';
import './App.css';
import SimulationCreationPage from './components/pages/SimulationCreationPage';
import SimulationDetailsPage from './components/pages/SimulationDetailsPage';
import CharacterDetailsPage from './components/pages/CharacterDetailsPage';
import CharacterCreationPage from './components/pages/CharacterCreationPage';
import BoardCreationPage from './components/pages/BoardCreationPage';
import BoardDetailsPage from './components/pages/BoardDetailsPage';
import NavigationBar from './components/NavigationBar';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import CharacterListPage from './components/pages/CharacterListPage';
import BoardListPage from './components/pages/BoardListPage';
import SimulationListPage from './components/pages/SimulationListPage';

function App() {
  return (
    <div className="App">
      <header className="DnD">
        <Router>
          <NavigationBar />
          <Switch>
            <Route exact path="/character" component={CharacterListPage} />
            <Route exact path="/character/create" component={CharacterCreationPage} />
            <Route exact path="/character/:id" render={routeProps => <CharacterDetailsPage id={routeProps.match.params.id} />} />
            <Route exact path="/board" component={BoardListPage} />
            <Route exact path="/board/create" component={BoardCreationPage} />
            <Route exact path="/board/:id" render={routeProps => <BoardDetailsPage id={routeProps.match.params.id} />} />
            <Route exact path="/simulation" component={SimulationListPage} />
            <Route exact path="/simulation/create" component={SimulationCreationPage} />
            <Route exact path="/simulation/:id" render={routeProps => <SimulationDetailsPage id={routeProps.match.params.id} />} />
          </Switch>
        </Router>
      </header>
    </div>
  );
}

export default App;
