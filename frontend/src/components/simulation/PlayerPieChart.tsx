import { Cell, Legend, Pie, PieChart } from "recharts";

const COLORS = ["#8BC24A", "#C4261B"]
const RADIAN = Math.PI / 180

type RenderCustomPieLabelProps = {
    cx: number
    cy: number
    midAngle: number
    innerRadius: number
    outerRadius: number
    percent: number
}

const renderCustomPieLabel = (props: RenderCustomPieLabelProps) => {
    const { cx, cy, midAngle, innerRadius, outerRadius, percent } = props
    const radius = innerRadius + (outerRadius - innerRadius) * 0.5
    const x = cx + radius * Math.cos(-midAngle * RADIAN)
    const y = cy + radius * Math.sin(-midAngle * RADIAN)

    return (
        <>{percent !== 0 &&
            <>
                <rect
                    x={x - 25}
                    y={y - 11}
                    width="50"
                    height="20"
                    rx="3"
                    fill="black"
                    opacity="0.4"
                />
                <text
                    style={{ fontWeight: 'bold' }}
                    x={x}
                    y={y}
                    fill="white"
                    textAnchor="middle"
                    dominantBaseline="middle"
                >
                    {`${(percent * 100).toFixed(1)}%`}
                </text>
            </>
        }
        </>
    );
};

function renderCustomPieLegend(simulationCount: number, items: ChartData[]) {
    return (
        <div>
            <ul>
                {items.map((item, index) => {
                    return (
                        <>{
                            item.value !== 0 &&
                            <li key={item.name} style={{
                                color: "#333",
                                listStyle: "none",
                                display: "flex",
                                flexDirection: "row",
                            }}>
                                <div
                                    style={{
                                        marginRight: "8px",
                                        width: "20px",
                                        height: "20px",
                                        backgroundColor: COLORS[index]
                                    }}
                                />
                                {item.name} ({item.value})
                            </li>
                        }</>
                    );
                })}
            </ul>
            <div style={{ width: "120px" }}><hr /></div>
            <p>Total: {simulationCount}</p>
        </div>
    );
}

type ChartData = {
    name: string
    value: number
}

type PlayerPieChartProps = {
    downCount: number
    simulationCount: number
}

function PlayerPieChart(props: PlayerPieChartProps) {
    const { downCount, simulationCount } = props
    const data = [
        { name: "Survivals", value: simulationCount - downCount } as ChartData,
        { name: "Downs", value: downCount } as ChartData
    ]

    return (
        <PieChart width={300} height={300}>
            <Pie
                data={data}
                cx={110}
                cy={200}
                startAngle={180}
                endAngle={0}
                label={renderCustomPieLabel}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                fill="#8884D8"
                paddingAngle={1}
                dataKey="value"
            >
                {data.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index]} />
                ))}
            </Pie>
            <Legend layout="vertical" verticalAlign="top" align="right" content={renderCustomPieLegend(simulationCount, data)} />
        </PieChart>
    )
}

export default PlayerPieChart