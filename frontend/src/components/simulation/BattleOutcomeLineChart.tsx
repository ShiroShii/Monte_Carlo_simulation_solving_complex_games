import { CartesianGrid, Line, LineChart, ReferenceLine, Tooltip, XAxis, YAxis } from "recharts"
import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"

type BattleOutcomeLineChartProps = {
    battleOutcomeConvergence: [IBattleOutcomeConvergence]
    winRate: number
    drawRate: number
}
function BattleOutcomeLineChart(props: BattleOutcomeLineChartProps) {
    const { battleOutcomeConvergence, winRate, drawRate } = props
    const fixedWinRate = winRate.toFixed(2)
    const fixedDrawRate = drawRate.toFixed(2)

    return (
        <LineChart width={750} height={250} data={battleOutcomeConvergence}
            margin={{ top: 5, right: 150, left: 20, bottom: 50 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="count" scale="log" label={{ position: 'bottom', value: "Simulation Count", fill: "gray", fontSize: 14 }} />
            <YAxis />
            <Tooltip />
            <Line type="monotone" name="Win Rate" dataKey="winRate" dot={false} stroke="#8884d8" />
            <ReferenceLine y={fixedWinRate} label={{ position: 'right', value: `Win Rate: ${fixedWinRate}`, fill: "#8884d8", fontSize: 14 }} stroke="#8884d8" />
            <Line type="monotone" name="Draw Rate" dataKey="drawRate" dot={false} stroke="#82ca9d" />
            <ReferenceLine y={fixedDrawRate} label={{ position: 'right', value: `Draw Rate: ${fixedDrawRate}`, fill: "#82ca9d", fontSize: 14 }} stroke="#82ca9d" />
        </LineChart>
    )
}

export default BattleOutcomeLineChart