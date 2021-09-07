import { Cell, Legend, Pie, PieChart } from "recharts"
import IBattleOutcomeSlice from "./IBattleOutcomeSlice"

const COLORS = ["#8BC24A", "#E6AB09", "#C4261B"]
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

function renderCustomPieLegend(simulationCount: number, items: IBattleOutcomeSlice[]) {
    return (
        <div style={{ textAlign: "left", color: "gray" }}>
            <ul style={{ padding: 0, margin: 2 }}>
                {items.map((item, index) => {
                    return (
                        <>{
                            item.value !== 0 &&
                            <li key={item.name} style={{
                                display: "flex",
                                flexDirection: "row"
                            }}>
                                <div
                                    style={{
                                        marginRight: "8px",
                                        width: "20px",
                                        height: "20px",
                                        backgroundColor: COLORS[index]
                                    }}
                                />
                                <p style={{ marginBottom: 2 }}>{item.name} ({item.value})</p>
                            </li>
                        }</>
                    );
                })}
            </ul>
            <hr style={{ margin: 2 }} />
            <p style={{ marginBottom: 2 }}>Total: {simulationCount}</p>
        </div>
    );
}

type BattleOutcomePieChartProps = {
    battleOutcomeSlices: IBattleOutcomeSlice[]
    simulationCount: number
}

function BattleOutcomePieChart(props: BattleOutcomePieChartProps) {
    const { battleOutcomeSlices, simulationCount } = props

    return (
        <PieChart width={350} height={250}  margin={{ top: 70 }}>
            <Pie
                data={battleOutcomeSlices}
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
                {battleOutcomeSlices.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index]} />
                ))}
            </Pie>
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={renderCustomPieLegend(simulationCount, battleOutcomeSlices)} />
        </PieChart>
    )
}

export default BattleOutcomePieChart