import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import usePlayerCharacterList from './UsePlayerCharacterList'
function PlayerCharacterList() {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacterList(setLoading)

    const columns: GridColDef[] = [
        {
            field: 'name',
            headerName: 'Name',
            width: 200
        },
        {
            field: 'id',
            headerName: 'Details',
            width: 150,
            renderCell: (params: GridCellParams) => {
                return <Link to={`/character/${params.id}`}>Details</Link>;
            },
        },
    ];

    return (
        <DataGrid autoHeight loading={loading} rows={playerCharacter} columns={columns} />
    );
}

export default PlayerCharacterList
