import { CartesianGrid, Line, LineChart, ReferenceLine, Tooltip, XAxis, YAxis } from "recharts"
import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"

const COLORS = ["#8BC24A", "#E6AB09", "#C4261B"]

type BattleOutcomeLineChartProps = {
    battleOutcomeConvergence: IBattleOutcomeConvergence[]
    winRate: number
    drawRate: number
}
function BattleOutcomeLineChart(props: BattleOutcomeLineChartProps) {
    const { battleOutcomeConvergence, winRate, drawRate } = props
    const fixedWinRate = winRate.toFixed(2)
    const fixedDrawRate = drawRate.toFixed(2)

    return (
        <LineChart width={750} height={300} data={battleOutcomeConvergence}
            margin={{ top: 5, right: 150, left: 20, bottom: 50 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="count" scale="log" label={{ position: 'bottom', value: "Simulation Count", fill: "gray", fontSize: 14 }} />
            <YAxis />
            <Tooltip />
            <Line type="monotone" name="Win Rate" dataKey="winRate" dot={false} stroke={COLORS[0]} />
            <ReferenceLine y={fixedWinRate} label={{ position: 'right', value: `Win Rate: ${fixedWinRate}`, fill: COLORS[0], fontSize: 14 }} stroke={COLORS[0]} />
            <Line type="monotone" name="Draw Rate" dataKey="drawRate" dot={false} stroke={COLORS[1]} />
            <ReferenceLine y={fixedDrawRate} label={{ position: 'right', value: `Draw Rate: ${fixedDrawRate}`, fill: COLORS[1], fontSize: 14 }} stroke={COLORS[1]} />
        </LineChart>
    )
}

export default BattleOutcomeLineChart