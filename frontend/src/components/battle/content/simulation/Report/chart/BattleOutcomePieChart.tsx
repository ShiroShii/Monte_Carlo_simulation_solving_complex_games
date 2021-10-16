import { Cell, Legend, Pie, PieChart } from "recharts"
import { Paragraph } from "../../../../../_common"
import { IBattleOutcomeSlice } from "../../interface"
import CustomPieLabel from "./CustomPieLabel"
import { CustomPieLegend } from "./CustomPieLegend"

const COLORS = ["#8BC24A", "#E6AB09", "#C4261B"]

function formatLegendItem(item: IBattleOutcomeSlice) {
    return (
        `${item.name} (${item.value})`
    )
}

type BattleOutcomePieChartProps = {
    battleOutcomeSlices: IBattleOutcomeSlice[]
    simulationCount: number
}

function BattleOutcomePieChart({ battleOutcomeSlices, simulationCount }: BattleOutcomePieChartProps) {
    const items = [
        { color: COLORS[0], value: formatLegendItem(battleOutcomeSlices[0]) },
        { color: COLORS[1], value: formatLegendItem(battleOutcomeSlices[1]) },
        { color: COLORS[2], value: formatLegendItem(battleOutcomeSlices[2]) }
    ]

    return (
        <PieChart width={350} height={300} margin={{ top: 70 }}>
            <Pie
                data={battleOutcomeSlices}
                startAngle={180}
                endAngle={0}
                label={CustomPieLabel}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                paddingAngle={4}
                dataKey="value"
            >
                {items.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                ))}
            </Pie>
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={
                <CustomPieLegend items={items}>
                    <Paragraph>Total: {simulationCount}</Paragraph>
                </CustomPieLegend>
            } />
        </PieChart>
    )
}

export default BattleOutcomePieChart
