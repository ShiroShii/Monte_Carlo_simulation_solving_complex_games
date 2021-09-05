import { Switch } from "@material-ui/core"
import { useState } from "react"
import BattleOutcomeBarChart from "./BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./BattleOutcomeLineChart"
import BattleOutcomePieChart from "./BattleOutcomePieChart"
import DownedPlayersBarChart from "./DownedPlayersBarChart"
import DownedPlayersPieChart from "./DownedPlayersPieChart"
import ISimulationResult from "./ISimulationResult"
import WonBattleBarChart from "./WonBattleBarChart"

type SimulationDashboardProps = {
    simulationResult: ISimulationResult
}

function SimulationDashboard(props: SimulationDashboardProps) {
    const [showPieChart, setShowPieChart] = useState(false);

    const {
        battleOutcomeConvergence,
        battleOutcomeSlices,
        battleOutcomeBars,
        simulationCount,
        downedPlayers,
        playerBoxPlot,
        initialPlayerCount,
    } = props.simulationResult

    const winRate = battleOutcomeConvergence[simulationCount - 1].winRate
    const drawRate = battleOutcomeConvergence[simulationCount - 1].drawRate

    return (
        <>
            <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
            <BattleOutcomeLineChart winRate={winRate} drawRate={drawRate} battleOutcomeConvergence={battleOutcomeConvergence} />
            <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} />
            <Switch checked={showPieChart} onChange={(event, checked: boolean) => { setShowPieChart(checked) }} />
            <DownedPlayersBarChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
            <DownedPlayersPieChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
            <WonBattleBarChart healthData={playerBoxPlot.health} damageDeltData={playerBoxPlot.damageDelt} damageTakenData={playerBoxPlot.damageTaken} />
        </>
    )
}

export default SimulationDashboard