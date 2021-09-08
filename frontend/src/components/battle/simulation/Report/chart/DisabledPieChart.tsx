import { Legend, Pie, PieChart } from "recharts";

function renderCustomPieLegend() {
    return (
        <div>
            <div
                style={{
                    marginRight: "8px",
                    width: "200px",
                    height: "60px",
                    backgroundColor: "gray"
                }}
            />
            <div><hr /></div>
            <div
                style={{
                    marginRight: "8px",
                    width: "200px",
                    height: "20px",
                    backgroundColor: "gray"
                }}
            />
        </div>
    );
}

function PlayerPieChart() {
    const data = [
        { name: "Survives", value: 70 },
        { name: "Downs", value: 30 }
    ]

    return (
        <PieChart width={350} height={400} margin={{ top: 70 }}>
            <Pie
                data={data}
                startAngle={180}
                endAngle={0}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                paddingAngle={1}
                dataKey="value"
            >
            </Pie>
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={renderCustomPieLegend()} />
        </PieChart>
    )
}

export default PlayerPieChart
