import { CartesianGrid, Scatter, ScatterChart, XAxis, YAxis } from "recharts";

const CustomLine = ({
    points
}: any) => {
    return (
        <>
            <rect x={points[3].x - 25} y={points[3].y} height={points[1].y - points[3].y} width={50} stroke="darkgray" strokeWidth={4} />
            <rect x={points[3].x - 25} y={points[3].y} height={points[2].y - points[3].y} width={50} fill="darkgray" />
            <rect x={points[2].x - 25} y={points[2].y} height={points[1].y - points[2].y} width={50} fill="darkgray" />

            <line x1={points[0].x} x2={points[0].x} y1={points[0].y} y2={points[1].y} strokeWidth={2} stroke="darkgray" />
            <line x1={points[0].x} x2={points[0].x} y1={points[3].y} y2={points[4].y} strokeWidth={2} stroke="darkgray" />

            <rect x={points[0].x - 25 - 1} y={points[0].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="darkgray" stroke="darkgray" />
            <rect x={points[2].x - 25 - 1} y={points[2].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="darkgray" stroke="darkgray" />
            <rect x={points[4].x - 25 - 1} y={points[4].y - 1} height={4} width={50 + 2} strokeWidth={2} fill="darkgray" stroke="darkgray" />
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

function DisabledBoxChart() {
    const healthData = [
        {
            "category": "Health",
            "label": "Lower Extreme",
            "value": 3.0
        },
        {
            "category": "Health",
            "label": "Lower Quartile",
            "value": 6.0
        },
        {
            "category": "Health",
            "label": "Median",
            "value": 7.0
        },
        {
            "category": "Health",
            "label": "Upper Quartile",
            "value": 8.0
        },
        {
            "category": "Health",
            "label": "Upper Extreme",
            "value": 10.0
        }
    ]

    const damageDealtData = [
        {
            "category": "Damage Dealt",
            "label": "Lower Extreme",
            "value": 2.0
        },
        {
            "category": "Damage Dealt",
            "label": "Lower Quartile",
            "value": 10.0
        },
        {
            "category": "Damage Dealt",
            "label": "Median",
            "value": 14.0
        },
        {
            "category": "Damage Dealt",
            "label": "Upper Quartile",
            "value": 15.0
        },
        {
            "category": "Damage Dealt",
            "label": "Upper Extreme",
            "value": 20.0
        }
    ]

    const damageTakenData = [
        {
            "category": "Damage Taken",
            "label": "Lower Extreme",
            "value": 1.0
        },
        {
            "category": "Damage Taken",
            "label": "Lower Quartile",
            "value": 2.0
        },
        {
            "category": "Damage Taken",
            "label": "Median",
            "value": 5.0
        },
        {
            "category": "Damage Taken",
            "label": "Upper Quartile",
            "value": 7.0
        },
        {
            "category": "Damage Taken",
            "label": "Upper Extreme",
            "value": 11.0
        }
    ]
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
            <YAxis type="number" tick={false} dataKey="value" />
            <Scatter data={healthData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageTakenData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageDealtData} shape={<CustomShape />} line={<CustomLine />} />
        </ScatterChart>
    )
}

export default DisabledBoxChart