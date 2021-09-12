import { Bar, BarChart, CartesianGrid, Cell, Tooltip, XAxis, YAxis } from "recharts";
import { NoMarginParagraph } from "../../../../_common";
import colorMixer from "../../../../_common/ColorMixer";
import IDownedPlayer from "../../interface/IDownedPlayer";
import CustomTooltip from "../../../../_common/CustomTooltip";
import { color1, color2, darkColor1, darkColor2 } from "./DownedPlayersColors";

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

    const mixedColor = colorMixer(darkColor1, darkColor2, index / itemCount)

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

function DownedPlayersBarChart({ downedPlayers, initialPlayerCount, simulationCount }: DownedPlayersBarChartProps) {
    function formatPayload(payload: any) {
        return (
            <>
                <NoMarginParagraph><b>{100.0 * payload[0].payload.simulationCount / simulationCount}%</b> Simulations</NoMarginParagraph>
            </>
        )
    }

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
            <Tooltip content={<CustomTooltip formatPayload={formatPayload} />} />
            <Bar dataKey="simulationCount" label={<CustomLabel itemCount={downedPlayers.length} />}>
                {downedPlayers.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={colorMixer(color1, color2, index / downedPlayers.length)} />
                ))}
            </Bar>
        </BarChart>
    )
}

export default DownedPlayersBarChart
