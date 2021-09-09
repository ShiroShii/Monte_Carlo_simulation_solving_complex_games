import styled from "styled-components"
import { NoMarginParagraph } from "../../../../_common"

const PieLegendBlock = styled.div`
    text-align: left; 
    color: gray;
`
const HR = styled.hr`
    margin: 2px
`

const UL = styled.ul`
    padding: 0; 
    margin: 2px;
`

const LI = styled.li`
    list-style: none;
    display: flex;
    flex-direction: row;
`
const Square = styled.div`
    margin-right: 8px;
    width: 20px;
    height: 20px;
`

type ColoredSquareProps = {
    color: string
}

function ColoredSquare({ color }: ColoredSquareProps) {
    const ColoredSquare = styled(Square)`
        background-color: ${color}
    `

    return (
        <ColoredSquare />
    )
}

export type LegendItem = {
    color: string
    value: string
}

type CustomPieLegendProps = {
    items: LegendItem[]
    children: React.ReactNode
}

export function CustomPieLegend({ items, children }: CustomPieLegendProps) {
    return (
        <PieLegendBlock>
            <UL>
                {items.map((item, index) => {
                    return (
                        <LI>
                            <ColoredSquare color={item.color} />
                            <NoMarginParagraph>{item.value}</NoMarginParagraph>
                        </LI>
                    );
                })}
            </UL>
            <div>
                <HR />
                {children}
            </div>
        </PieLegendBlock>
    )
}