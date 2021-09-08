import { Bar, BarChart, CartesianGrid, Cell, Tooltip, XAxis, YAxis } from "recharts";
import colorMixer from "../../../../_common/ColorMixer";
import IDownedPlayer from "../../interface/IDownedPlayer";

type DownedPlayersBarChartProps = {
    downedPlayers: IDownedPlayer[]
    initialPlayerCount: number
    simulationCount: number
}

const CustomLabel = ({
    x, y, value, width, height, index, itemCount
}: any) => {
    const fontSize = 16
    const labelHeight = 21
    const yOffset = - 8
    const yTextMargin = 6
    const xPadding = 20

    const outside = height as number < labelHeight
    const valueLength = `${value}`.length
    const rectWidth = valueLength * fontSize / 2 + xPadding

    const mixedColor = colorMixer([120, 0, 0], [50, 0, 150], index / itemCount)

    return (
        <>
            <rect
                x={x as number + (width as number / 2) - (rectWidth) / 2}
                y={y as number + (outside ? yOffset : (height as number / 2) + fontSize / 2) - labelHeight + yTextMargin}
                width={rectWidth}
                height={labelHeight}
                rx="3"
                fill={outside ? "white" : mixedColor}
                opacity="1"
                stroke={outside ? mixedColor : "white"}
                strokeWidth="1"
            />
            <text
                x={x as number + (width as number / 2)}
                y={y as number + (outside ? yOffset : (height as number / 2) + fontSize / 2)}
                fontSize={fontSize}
                fontFamily='sans-serif'
                fill={outside ? "black" : "white"}
                enableBackground='true'
                textDecoration="underline"
                textAnchor="middle">
                {value}</text>
        </>
    );
}


const CustomTooltip = ({ active, payload, simulationCount }: any) => {
    if (active && payload && payload[0]) {
        return (
            <div style={{
                backgroundColor: "white",
                border: "1px solid gray",
                padding: "10px 10px 10px 10px",
                borderRadius: "2px"
            }}>
                <p style={{ marginBottom: "0px" }}><b>{100.0 * payload[0].payload.simulationCount / simulationCount}%</b> Simulations</p>
            </div >
        );
    }

    return null;
};

function DownedPlayersBarChart(props: DownedPlayersBarChartProps) {
    const { downedPlayers, initialPlayerCount, simulationCount } = props
    return (
        <BarChart width={800} height={400} data={downedPlayers} margin={{ top: 20, right: 5, left: 50, bottom: 30 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis tickFormatter={(value, index) => `${downedPlayers[index].downedCount} (${downedPlayers[index].downedPercentage}% of Party)`} dataKey="downedCount" label={{ position: 'bottom', value: `Players downed (out of ${initialPlayerCount})`, fill: "gray", fontSize: 16 }} />
            <YAxis
                label={
                    <text
                        x={0}
                        y={0}
                        dx={-60}
                        dy={50}
                        offset={0}
                        fill="gray"
                        fontSize={16}
                        transform="rotate(-90,100,100)"
                    >Simulation count (out of {simulationCount})</text>
                }
            />
            <Tooltip content={<CustomTooltip simulationCount={simulationCount} />} />
            <Bar dataKey="simulationCount" label={<CustomLabel itemCount={downedPlayers.length} />}>
                {downedPlayers.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={colorMixer([255, 70, 0], [90, 70, 255], index / downedPlayers.length)} />
                ))}
            </Bar>
        </BarChart>
    )
}

export default DownedPlayersBarChart