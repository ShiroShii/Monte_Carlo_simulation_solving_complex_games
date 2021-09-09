import styled from "styled-components"

const TableHeader = styled.th`
    color: gray; 
    text-align: left;
    padding-right: 10px;
    border-right: 1px solid lightgray;
`

const TableData = styled.td`
    text-align: left;
    padding-left: 10px;
    background-color: gray;
    border-bottom: 5px solid white;
    width: 205px;
`

const Table = styled.table`
    margin: 60px 5px 5px 5px;
`

function DisabledPlayerOveriew() {
    return (
        <Table>
        <tbody>
            <tr>
                <TableHeader>Initial HP:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Strength:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Dexterity:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Armor Class:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Walking Speed:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Level:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Class:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Play Style:</TableHeader>
                <TableData/>
            </tr>
            <tr>
                <TableHeader>Targeting Style:</TableHeader>
                <TableData/>
            </tr>
        </tbody>
    </Table>
    )
}

export default DisabledPlayerOveriew
