import { Legend, Pie, PieChart } from "recharts";
import styled from "styled-components";

const LegendBlock = styled.div`
    margin-right: 8px;
    width: 200px;
    height: 60px;
    background-color: gray;
`

function CustomPieLegend() {
    return (
        <div>
            <LegendBlock />
            <div><hr /></div>
            <LegendBlock />
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
                dataKey="value"
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                paddingAngle={4}

                startAngle={180}
                endAngle={0}
            >
            </Pie>
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={CustomPieLegend} />
        </PieChart>
    )
}

export default PlayerPieChart
