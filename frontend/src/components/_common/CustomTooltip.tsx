import styled from 'styled-components'

const TooltipBlock = styled.div`
    background-color: white;
    border: 1px solid gray;
    padding: 10px 10px 10px 10px;
    border-radius: 2px;
`

const CustomTooltip = ({ active, payload, formatPayload }: any) => {
    if (active && payload && payload[0]) {
        return (
            <TooltipBlock>
                {formatPayload(payload)}
            </TooltipBlock >
        );
    }

    return null;
};

export default CustomTooltip