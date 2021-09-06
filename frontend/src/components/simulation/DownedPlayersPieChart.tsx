import { Cell, Legend, Pie, PieChart, Tooltip } from "recharts";
import colorMixer from "./ColorMixer";
import IDownedPlayer from "./IDownedPlayer";

type DownedPlayersPieChartProps = {
    downedPlayers: [IDownedPlayer]
    initialPlayerCount: number
    simulationCount: number
}
const RADIAN = Math.PI / 180

const CustomTooltip = ({ active, payload, simulationCount }: any) => {
    if (active && payload && payload[0]) {
        console.log(payload)
        return (
            < div style={{
                backgroundColor: "white",
                border: "2px solid black",
                padding: "10px 10px 1px 10px",
                borderRadius: "10px",
                lineHeight: "0.5",
                textAlign: "center"
            }
            }>
                <p><b>{payload[0].payload.simulationCount}</b> Simulations</p>
                <p>out of <b>{simulationCount}</b></p>
            </div >
        );
    }

    return null;
};

const renderCustomPieLabel = (
    { cx, cy, midAngle, innerRadius, outerRadius, percent }: any) => {
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

function renderCustomPieLegend(items: [IDownedPlayer], simulationCount: number, initialPlayerCount: number) {
    return (
        <div style={{ width: "250px", textAlign: "left" }}>
            <ul style={{ padding: 0, margin: 2 }}>
                {items.map((item, index) => {
                    const mixedColor = colorMixer([255, 70, 0], [90, 70, 255], index / items.length)
                    return (
                        <>
                            <li key={item.downedCount} style={{
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
                                        backgroundColor: mixedColor
                                    }}
                                />
                                {item.downedCount} downed ({item.downedPercentage}% of Party)
                            </li>
                        </>
                    );
                })}
            </ul>
            <div style={{ width: "225px"}}>
                <hr style={{ margin: 2 }} />
                <p style={{ marginBottom: 2 }}>Simulation count: {simulationCount}</p>
                <p style={{ marginBottom: 2 }}>Initial player count: {initialPlayerCount}</p>
            </div>
        </div>
    );
}

function DownedPlayersPieChart(props: DownedPlayersPieChartProps) {
    const { downedPlayers, initialPlayerCount, simulationCount } = props
    return (
        <PieChart width={600} height={400}>
            <Tooltip content={<CustomTooltip simulationCount={simulationCount} />} />
            <Legend layout="vertical" verticalAlign="top" align="right" content={renderCustomPieLegend(downedPlayers, simulationCount, initialPlayerCount)} />
            <Pie
                data={downedPlayers}
                dataKey="simulationCount"
                cx={200}
                cy={200}
                label={renderCustomPieLabel}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                fill="#8884D8"
                paddingAngle={1}
            >
                {downedPlayers.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={colorMixer([255, 70, 0], [90, 70, 255], index / downedPlayers.length)} />
                ))}
            </Pie>
        </PieChart>
    )
}

export default DownedPlayersPieChart