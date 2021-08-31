import { Bar, BarChart, CartesianGrid, LabelProps, Tooltip, XAxis, YAxis } from "recharts";
import IBattleOutcomeSlice from "./IBattleOutcomeSlice";

type BattleOutcomeBarChartProps = {
    battleOutcomeBars: [IBattleOutcomeSlice]
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

function BattleOutcomeBarChart(props: BattleOutcomeBarChartProps) {
    const { battleOutcomeBars } = props
    return (
        <BarChart width={730} height={250} data={battleOutcomeBars} margin={{ top: 5, right: 150, left: 20, bottom: 50 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" label={{ position: 'bottom', value: "Players Left Standing : Enemies Left Standing", fill: "gray", fontSize: 14 }} />
            <YAxis />
            <Tooltip />
            <Bar dataKey="value" fill="#8884d8" label={<CustomLabel />} />
        </BarChart>
    )
}

export default BattleOutcomeBarChart