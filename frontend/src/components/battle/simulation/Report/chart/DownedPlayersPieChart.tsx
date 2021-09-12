import { Cell, Legend, Pie, PieChart, Tooltip } from "recharts";
import { NoMarginParagraph, Paragraph } from "../../../../_common";
import colorMixer from "../../../../_common/ColorMixer";
import IDownedPlayer from "../../interface/IDownedPlayer";
import CustomPieLabel from "./CustomPieLabel";
import { CustomPieLegend } from "./CustomPieLegend";
import CustomTooltip from "../../../../_common/CustomTooltip";
import { color1, color2 } from "./DownedPlayersColors";

function formatLegendItem(item: IDownedPlayer) {
    return (
        `${item.downedCount} downed (${item.downedPercentage}% of Party)`
    )
}

type DownedPlayersPieChartProps = {
    downedPlayers: IDownedPlayer[]
    initialPlayerCount: number
    simulationCount: number
}

function DownedPlayersPieChart({ downedPlayers, initialPlayerCount, simulationCount }: DownedPlayersPieChartProps) {
    const items = downedPlayers.map((entry, index) => (
        { color: colorMixer(color1, color2, index / downedPlayers.length), value: formatLegendItem(entry) }
    ))

    function formatPayload(payload: any) {
        return (
            <>
                <NoMarginParagraph><b>{payload[0].payload.simulationCount}</b> Simulations</NoMarginParagraph>
                <NoMarginParagraph>out of <b>{simulationCount}</b></NoMarginParagraph>
            </>
        )
    }

    return (
        <PieChart width={350} height={400} margin={{ top: 40 }}>
            <Tooltip content={<CustomTooltip formatPayload={formatPayload} />} />
            <Legend layout="vertical" verticalAlign="bottom" align="center" content={
                <CustomPieLegend items={items}>
                    <Paragraph>Simulation count: {simulationCount}</Paragraph>
                    <Paragraph>Initial player count: {initialPlayerCount}</Paragraph>
                </CustomPieLegend>} />
            <Pie
                data={downedPlayers}
                dataKey="simulationCount"
                label={CustomPieLabel}
                labelLine={false}
                innerRadius={30}
                outerRadius={100}
                paddingAngle={4}
            >
                {items.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                ))}
            </Pie>
        </PieChart >
    )
}

export default DownedPlayersPieChart
