import { Bar, BarChart, CartesianGrid, LabelProps, Tooltip, XAxis, YAxis } from "recharts";
import IDownedPlayer from "./IDownedPlayer";

type DownedPlayersChartProps = {
    downedPlayers: [IDownedPlayer]
    initialPlayerCount: number
}

const CustomLabel = ({
    x, y, value, width, height
}: LabelProps) => {
    const fontSize = 16
    const labelHeight = 21
    const yOffset = - 8
    const yTextMargin = 6
    const xPadding = 20

    const outside = height as number < labelHeight
    const valueLength = `${value}`.length
    const rectWidth = valueLength * fontSize / 2 + xPadding
    return (
        <>
            <rect
                x={x as number + (width as number / 2) - (rectWidth) / 2}
                y={y as number + (outside ? yOffset : (height as number / 2) + fontSize / 2) - labelHeight + yTextMargin}
                width={rectWidth}
                height={labelHeight}
                rx="3"
                fill={outside ? "white" : "#565399"}
                opacity="1"
                stroke={outside ? "#565399" : "white"}
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


const CustomTooltip = ({ active, payload, initialPlayerCount }: any) => {
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
                <p style={{ fontWeight: "bold" }}>{payload[0].payload.downedCount} out of {initialPlayerCount} Players Downed</p>
                <p>{payload[0].payload.downedPercentage} % of Party Downed</p>
            </div >
        );
    }

    return null;
};

function DownedPlayersChart(props: DownedPlayersChartProps) {
    const { downedPlayers, initialPlayerCount } = props
    return (
        <BarChart width={730} height={250} data={downedPlayers} margin={{ top: 5, right: 150, left: 20, bottom: 50 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="downedCount" label={{ position: 'bottom', value: "Players Downed", fill: "gray", fontSize: 14 }} />
            <YAxis />
            <Tooltip content={<CustomTooltip initialPlayerCount={initialPlayerCount} />} />
            <Bar dataKey="simulationCount" fill="#8884d8" label={<CustomLabel />} />
        </BarChart>
    )
}

export default DownedPlayersChart