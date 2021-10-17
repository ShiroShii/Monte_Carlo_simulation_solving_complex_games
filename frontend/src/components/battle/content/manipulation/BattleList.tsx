import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { useState } from 'react'
import { IBattle } from '.'
import { LinkButton } from '../../../_common'
import { useBattleList } from './hook'

function BattleList() {
    const [loading, setLoading] = useState(true)
    const battleList = useBattleList(setLoading)

    const columns: GridColDef[] = [
        {
            field: 'name',
            headerName: 'Name',
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'center',
            flex: 1.4
        },
        {
            field: 'playerCount',
            headerName: 'Player Count',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'monsterCount',
            headerName: 'Monster Count',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'playerTotalHp',
            headerName: 'Player Total HP',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'monsterTotalHp',
            headerName: 'Monster Total HP',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'id',
            headerName: ' ',
            sortable: false,
            disableColumnMenu: true,
            flex: 0.7,
            align: 'center',
            renderCell: (params: GridCellParams) => {
                return <LinkButton to={`/battle/${params.value}`}>Details</LinkButton>;
            },
        },
    ];

    const transformData = (data: IBattle[]) => {
        return data.map(x => {
            const playerCharacterStates = x.tiles.flatMap(x => x.playerCharacterStates)
            const monsterStates = x.tiles.flatMap(x => x.monsterStates)

            return ({
                id: x.id,
                name: x.name,
                playerCount: playerCharacterStates.length,
                monsterCount: monsterStates.length,
                playerTotalHp: playerCharacterStates
                    .map(x => x.currentHp)
                    .reduce((sum, current) => sum + current, 0),
                monsterTotalHp: monsterStates
                    .map(x => x.currentHp)
                    .reduce((sum, current) => sum + current, 0)
            })
        }
        )
    }

    return (
        <DataGrid
            autoHeight
            loading={loading}
            rows={transformData(battleList)}
            columns={columns} />
    );
}

export default BattleList
