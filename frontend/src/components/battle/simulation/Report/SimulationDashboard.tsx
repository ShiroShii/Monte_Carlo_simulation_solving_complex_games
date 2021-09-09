import styled from 'styled-components'
import IPlayerCharacterState from "../../IBattleCharacterState"
import ChartBlock from "../ChartBlock"
import { ISimulationResult } from "../interface"
import BattleOutcomeBarChart from "./chart/BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./chart/BattleOutcomeLineChart"
import BattleOutcomePieChart from "./chart/BattleOutcomePieChart"
import DownedPlayersBarChart from "./chart/DownedPlayersBarChart"
import DownedPlayersPieChart from "./chart/DownedPlayersPieChart"
import PlayerReport from "./chart/PlayerReport"
import WonBattleBarChart from "./chart/WonBattleBarChart"

type SimulationDashboardProps = {
    simulationResult: ISimulationResult,
    playerCharacterStates: IPlayerCharacterState[]
}

const DashboardRow = styled.div`
    margin-top: 20px;
    margin-bottom: 20px;
`
const DashboardBlock = styled.div`
    width: 1150px;
    margin: 0px auto 150px;
`

const HR = styled.hr`
    border-top: dashed 2px;
    color: lightgray;
`

function SimulationDashboard({ simulationResult, playerCharacterStates }: SimulationDashboardProps) {
    const {
        battleOutcomeConvergence,
        battleOutcomeSlices,
        battleOutcomeBars,
        simulationCount,
        downedPlayers,
        playerBoxPlot,
        initialPlayerCount,
        playerReports
    } = simulationResult

    return (
        <DashboardBlock>
            <HR />
            <DashboardRow>
                <ChartBlock>
                    <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
                </ChartBlock>
                <ChartBlock>
                    <BattleOutcomeLineChart battleOutcomeConvergence={battleOutcomeConvergence} />
                </ChartBlock>
            </DashboardRow>
            <HR />
            <DashboardRow>
                <ChartBlock>
                    <DownedPlayersPieChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </ChartBlock>
                <ChartBlock>
                    <DownedPlayersBarChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </ChartBlock>
            </DashboardRow>
            <HR />
            <DashboardRow>
                <ChartBlock>
                    <WonBattleBarChart healthData={playerBoxPlot.health} damageDealtData={playerBoxPlot.damageDealt} damageTakenData={playerBoxPlot.damageTaken} />
                </ChartBlock>
                <ChartBlock>
                    <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} simulationCount={simulationCount} />
                </ChartBlock>
            </DashboardRow>
            <HR />
            <DashboardRow>
                <PlayerReport playerCharacterStates={playerCharacterStates} simulationCount={simulationCount} playerReports={playerReports} />
            </DashboardRow>
            <HR />
        </DashboardBlock>
    )
}

export default SimulationDashboard
