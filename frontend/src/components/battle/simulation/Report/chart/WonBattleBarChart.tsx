import { CartesianGrid, Scatter, ScatterChart, Tooltip, XAxis, YAxis } from "recharts";
import { NoMarginParagraph } from "../../../../_common";
import ICategoryData from "../../interface/ICategoryData";
import CustomTooltip from "../../../../_common/CustomTooltip";

const CustomLine = ({
    points
}: any) => {
    return (
        <>
            <rect x={points[3].x - 25} y={points[3].y} height={points[1].y - points[3].y} width={50} stroke="black" strokeWidth={4} />

            <rect x={points[3].x - 25} y={points[3].y} height={points[2].y - points[3].y} width={50} fill="#8884d8" />
            <rect x={points[2].x - 25} y={points[2].y} height={points[1].y - points[2].y} width={50} fill="#82ca9d" />

            <line x1={points[0].x} x2={points[0].x} y1={points[0].y} y2={points[1].y} strokeWidth={2} stroke="black" />
            <line x1={points[0].x} x2={points[0].x} y1={points[3].y} y2={points[4].y} strokeWidth={2} stroke="black" />

            <rect x={points[0].x - 25 - 1} y={points[0].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="#82ca9d" stroke="black" />
            <rect x={points[2].x - 25 - 1} y={points[2].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="yellow" stroke="black" />
            <rect x={points[4].x - 25 - 1} y={points[4].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="#8884d8" stroke="black" />
        </>
    );
}

const CustomShape = ({
    cx, cy
}: any) => {
    return (
        <rect x={cx - 25} y={cy - 5} width={50} height={10} opacity="0" />
    );
}

const CustomTick = ({ x, y, payload }: any) => {
    console.log(payload)
    return (
        <text x={x} y={y} stroke="grey" fontWeight="lighter" fontSize={16}>
            {
                payload.value
                    .split(/(\s+)/)
                    .filter((x: string) => { return (x.trim().length > 0) })
                    .map(
                        (item: number, index: number) => {
                            return (
                                <tspan textAnchor="middle" x={payload.coordinate} dy={20}>{item}</tspan>
                            )
                        })
            }
        </text>
    )
}

type WonBattleBarChartProps = {
    healthData: ICategoryData[]
    damageTakenData: ICategoryData[]
    damageDealtData: ICategoryData[]
}

function WonBattleBarChart({ healthData, damageTakenData, damageDealtData }: WonBattleBarChartProps) {

    function formatPayload(payload: any) {
        return (
            <>
                <NoMarginParagraph><b>{payload[0].payload.category}</b></NoMarginParagraph>
                <NoMarginParagraph>{payload[0].payload.label}: {payload[0].payload.value}</NoMarginParagraph>
            </>
        )
    }

    return (
        <ScatterChart
            width={350}
            height={400}
            margin={{
                top: 20,
                right: 5,
                bottom: 20,
                left: 0,
            }}
        >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis type="category" allowDuplicatedCategory={false} interval={0} dataKey="category" tick={<CustomTick />} />
            <YAxis type="number" dataKey="value" />
            <Tooltip content={<CustomTooltip formatPayload={formatPayload} />} />
            <Scatter data={healthData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageTakenData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageDealtData} shape={<CustomShape />} line={<CustomLine />} />
        </ScatterChart>
    )
}

export default WonBattleBarChart
