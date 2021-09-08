import { Cell, Legend, Pie, PieChart, Tooltip } from "recharts";
import colorMixer from "../../../common/ColorMixer";
import IDownedPlayer from "../../interface/IDownedPlayer";

type DownedPlayersPieChartProps = {
    downedPlayers: IDownedPlayer[]
    initialPlayerCount: number
    simulationCount: number
}
const RADIAN = Math.PI / 180

const CustomTooltip = ({ active, payload, simulationCount }: any) => {
    if (active && payload && payload[0]) {
        return (
            <div style={{
                backgroundColor: "white",
                border: "1px solid gray",
                padding: "10px 10px 10px 10px",
                borderRadius: "2px"
            }}>
                <p style={{ marginBottom: "0px" }}><b>{payload[0].payload.simulationCount}</b> Simulations</p>
                <p style={{ marginBottom: "0px" }}>out of <b>{simulationCount}</b></p>
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

function renderCustomPieLegend(items: IDownedPlayer[], simulationCount: number, initialPlayerCount: number) {
    return (
        <div style={{ textAlign: "left", color: "gray" }}>
            <ul style={{ padding: 0, margin: 2 }}>
                {items.map((item, index) => {
                    const mixedColor = colorMixer([255, 70, 0], [90, 70, 255], index / items.length)
                    return (
                        <>
                            <li key={item.downedCount} style={{
                                listStyle: "none",
                                display: "flex",
                                flexDirection: "row",
                            }}>
                                <div
                                    style={{
                                        marginRight: "8px",
                                        color: "#333",
                                        width: "20px",
                                        height: "20px",
                                        backgroundColor: mixedColor
                                    }}
                                />
                                <p style={{ marginBottom: 2 }}>{item.downedCount} downed ({item.downedPercentage}% of Party)</p>
                            </li>
                        </>
                    );
                })}
            </ul>
            <div>
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
        <PieChart width={350} height={400} margin={{ top: 40 }}>
            <Tooltip content={<CustomTooltip simulationCount={simulationCount} />} />
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={renderCustomPieLegend(downedPlayers, simulationCount, initialPlayerCount)} />
            <Pie
                data={downedPlayers}
                dataKey="simulationCount"
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
        </PieChart >
    )
}

export default DownedPlayersPieChart