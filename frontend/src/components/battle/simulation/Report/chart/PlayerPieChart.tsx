import { Cell, Legend, Pie, PieChart } from "recharts";
import { Paragraph } from "../../../../_common";
import CustomPieLabel from "./CustomPieLabel";
import { CustomPieLegend } from "./CustomPieLegend";

type ChartData = {
    name: string
    value: number
}

type PlayerPieChartProps = {
    downCount: number
    simulationCount: number
}

function formatLegendItem(item: ChartData) {
    return (
        `${item.name} (${item.value})`
    )
}

function PlayerPieChart({ downCount, simulationCount }: PlayerPieChartProps) {
    const data = [
        { name: "Survives", value: simulationCount - downCount } as ChartData,
        { name: "Downs", value: downCount } as ChartData
    ]

    const legendItems = [
        { color: "#8BC24A", value: formatLegendItem(data[0]) },
        { color: "#C4261B", value: formatLegendItem(data[1]) }
    ]

    return (
        <PieChart width={350} height={400} margin={{ top: 70 }}>
            <Pie
                data={data}
                dataKey="value"
                label={CustomPieLabel}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                paddingAngle={4}

                startAngle={180}
                endAngle={0}
            >
                {legendItems.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                ))}
            </Pie>
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={
                <CustomPieLegend items={legendItems}>
                    <Paragraph>Total: {simulationCount}</Paragraph>
                </CustomPieLegend>
            } />
        </PieChart>
    )
}

export default PlayerPieChart
