import { Button } from '@material-ui/core'
import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import { usePlayerCharacterList } from './hook'

export default function PlayerCharacterList() {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacterList(setLoading)
    const columns: GridColDef[] = [
        { field: 'name', headerName: 'Name', width: 200 },
        { field: 'dexterity', headerName: 'DEX', width: 120 },
        { field: 'strength', headerName: 'STR', width: 120 },
        { field: 'speed', headerName: 'SPD', width: 120 },
        { field: 'armorClass', headerName: 'AC', width: 120 },
        { field: 'characterLevel', headerName: 'LVL', width: 120 },
        { field: 'characterClass', headerName: 'Class', width: 120 },
        { field: 'armorClass', headerName: 'AC', width: 120 },
        { field: 'weapons', headerName: 'Weapons', width: 200 },
        {
            field: 'id', headerName: 'Details', width: 120,
            renderCell: (params: GridCellParams) => {
                return (
                    <Button
                        component={Link}
                        variant="contained"
                        to={`/character/${params.id}`}>
                        Details
                    </Button>
                )
            },
        },
    ];
    return (
        <DataGrid autoHeight loading={loading} rows={playerCharacter} columns={columns} />
    );
}
