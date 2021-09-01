import { CartesianGrid, Scatter, ScatterChart, Tooltip, XAxis, YAxis } from "recharts";

const healthData = [
    {
        category: "Health",
        label: "Lower Extreme",
        value: 10
    },
    {
        category: "Health",
        label: "Lower Quartile",
        value: 20
    },
    {
        category: "Health",
        label: "Median",
        value: 30
    },
    {
        category: "Health",
        label: "Upper Quartile",
        value: 40
    },
    {
        category: "Health",
        label: "Upper Extreme",
        value: 50
    }
];

const damageTakenData = [
    {
        category: "Damage Taken",
        label: "Lower Extreme",
        value: 3
    },
    {
        category: "Damage Taken",
        label: "Lower Quartile",
        value: 20
    },
    {
        category: "Damage Taken",
        label: "Median",
        value: 30
    },
    {
        category: "Damage Taken",
        label: "Upper Quartile",
        value: 32
    },
    {
        category: "Damage Taken",
        label: "Upper Extreme",
        value: 78
    }
];

const damageDeltData = [
    {
        category: "Damage Delt",
        label: "Lower Extreme",
        value: 8
    },
    {
        category: "Damage Delt",
        label: "Lower Quartile",
        value: 20
    },
    {
        category: "Damage Delt",
        label: "Median",
        value: 30
    },
    {
        category: "Damage Delt",
        label: "Upper Quartile",
        value: 50
    },
    {
        category: "Damage Delt",
        label: "Upper Extreme",
        value: 100
    }
];

const CustomLine = ({
    points
}: any) => {
    return (
        <>
            <rect x={points[3].x - 25} y={points[3].y} height={points[1].y - points[3].y} width={50} stroke="black" strokeWidth="3" />
            <rect x={points[3].x - 25} y={points[3].y} height={points[2].y - points[3].y} width={50} fill="#8884d8" />
            <rect x={points[2].x - 25} y={points[2].y} height={points[1].y - points[2].y} width={50} fill="#82ca9d" />

            <line x1={points[0].x} x2={points[0].x} y1={points[0].y} y2={points[1].y} strokeWidth={2} stroke="black" />
            <line x1={points[0].x} x2={points[0].x} y1={points[3].y} y2={points[4].y} strokeWidth={2} stroke="black" />

            <line x1={points[0].x - 25} x2={points[0].x + 25} y1={points[0].y} y2={points[0].y} strokeWidth={3} stroke="#82ca9d" />
            <line x1={points[2].x - 25} x2={points[2].x + 25} y1={points[2].y} y2={points[2].y} strokeWidth={3} stroke="yellow" />
            <line x1={points[4].x - 25} x2={points[4].x + 25} y1={points[4].y} y2={points[4].y} strokeWidth={3} stroke="#8884d8" />
        </>
    );
}

const CustomShape = ({
    cx, cy
}: any) => {
    console.log(cx, cy)
    return (
        <rect x={cx - 25 } y={cy -5} width={50} height={10} opacity="0"/>
    );
}

const CustomTooltip = ({ active, payload }: any) => {
    if (active && payload && payload) {
        return (
            <div style={{
                backgroundColor: "white",
                border: "2px solid black",
                padding: "10px 10px 1px 10px",
                borderRadius: "10px",
                lineHeight: "0.5",
                textAlign: "center"
            }}>
                <p style={{ fontWeight: "bold" }}>{payload[0].payload.category}</p>
                <p>{payload[0].payload.label}: {payload[0].payload.value}</p>
            </div>
        );
    }

    return null;
};

function WonBattleBarChart() {
    return (
        <ScatterChart
            width={500}
            height={400}
            margin={{
                top: 20,
                right: 20,
                bottom: 20,
                left: 20,
            }}
        >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis type="category" allowDuplicatedCategory={false} dataKey="category" />
            <YAxis type="number" dataKey="value" />
            <Tooltip content={<CustomTooltip />} />
            <Scatter data={healthData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageTakenData} shape={<CustomShape />} line={<CustomLine />} />
            <Scatter data={damageDeltData} shape={<CustomShape />} line={<CustomLine />} />
        </ScatterChart>
    )
}

export default WonBattleBarChart