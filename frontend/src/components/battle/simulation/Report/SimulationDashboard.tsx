import styled from 'styled-components'
import IPlayerCharacterState from "../../IBattleCharacterState"
import { InlineBlock } from "../../../_common"
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
                <InlineBlock>
                    <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
                </InlineBlock>
                <InlineBlock>
                    <BattleOutcomeLineChart battleOutcomeConvergence={battleOutcomeConvergence} />
                </InlineBlock>
            </DashboardRow>
            <HR />
            <DashboardRow>
                <InlineBlock>
                    <DownedPlayersPieChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </InlineBlock>
                <InlineBlock>
                    <DownedPlayersBarChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </InlineBlock>
            </DashboardRow>
            <HR />
            <DashboardRow>
                <InlineBlock>
                    <WonBattleBarChart healthData={playerBoxPlot.health} damageDealtData={playerBoxPlot.damageDealt} damageTakenData={playerBoxPlot.damageTaken} />
                </InlineBlock>
                <InlineBlock>
                    <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} simulationCount={simulationCount} />
                </InlineBlock>
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
