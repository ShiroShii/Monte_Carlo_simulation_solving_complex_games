import { CartesianGrid, Line, LineChart, ReferenceLine, Tooltip, XAxis, YAxis } from "recharts"
import { IBattleOutcomeConvergence } from "../../interface"

const COLORS = ["#8BC24A", "#E6AB09", "#C4261B"]

type BattleOutcomeLineChartProps = {
    battleOutcomeConvergence: IBattleOutcomeConvergence[]
}

function BattleOutcomeLineChart(props: BattleOutcomeLineChartProps) {
    const { battleOutcomeConvergence } = props

    const lastEntry = battleOutcomeConvergence[battleOutcomeConvergence.length - 1]

    const fixedWinRate = lastEntry.winRate.toFixed(2)
    const fixedDrawRate = lastEntry.drawRate.toFixed(2)
    const fixedLossRate = lastEntry.lossRate.toFixed(2)

    return (
        <LineChart width={800} height={300} data={battleOutcomeConvergence}
            margin={{ top: 5, right: 125, left: 50, bottom: 20 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="count" scale="log" label={{ position: 'bottom', value: "Simulation Count", fill: "gray", fontSize: 16 }} />
            <YAxis />
            <Tooltip />
            <Line type="monotone" name="Win Rate" dataKey="winRate" dot={false} stroke={COLORS[0]} />
            <ReferenceLine y={fixedWinRate} label={{ position: 'right', value: `Win Rate: ${fixedWinRate}`, fill: COLORS[0], fontSize: 16 }} stroke={COLORS[0]} />
            <Line type="monotone" name="Draw Rate" dataKey="drawRate" dot={false} stroke={COLORS[1]} />
            <ReferenceLine y={fixedDrawRate} label={{ position: 'right', value: `Draw Rate: ${fixedDrawRate}`, fill: COLORS[1], fontSize: 16 }} stroke={COLORS[1]} />
            <Line type="monotone" name="Loss Rate" dataKey="lossRate" dot={false} stroke={COLORS[2]} />
            <ReferenceLine y={fixedLossRate} label={{ position: 'right', value: `Loss Rate: ${fixedLossRate}`, fill: COLORS[2], fontSize: 16 }} stroke={COLORS[2]} />
        </LineChart>
    )
}

export default BattleOutcomeLineChart
