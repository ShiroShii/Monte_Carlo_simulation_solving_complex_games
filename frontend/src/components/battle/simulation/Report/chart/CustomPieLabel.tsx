import styled from 'styled-components'

type RenderCustomPieLabelProps = {
    cx: number
    cy: number
    midAngle: number
    innerRadius: number
    outerRadius: number
    percent: number
}

const RADIAN = Math.PI / 180

const Bold = styled.text`
    font-weight: bold;
`

function CustomPieLabel({ cx, cy, midAngle, innerRadius, outerRadius, percent }: RenderCustomPieLabelProps) {
    const radius = innerRadius + (outerRadius - innerRadius) * 0.5
    const x = cx + radius * Math.cos(-midAngle * RADIAN)
    const y = cy + radius * Math.sin(-midAngle * RADIAN)

    return (
        <>{percent !== 0 &&
            <>
                <rect
                    x={x - 25}
                    y={y - 11}
                    width="50"
                    height="20"
                    rx="3"
                    fill="black"
                    opacity="0.4"
                />
                <Bold
                    x={x}
                    y={y}
                    fill="white"
                    textAnchor="middle"
                    dominantBaseline="middle"
                >
                    {`${(percent * 100).toFixed(1)}%`}
                </Bold>
            </>
        }
        </>
    );
};

export default CustomPieLabel