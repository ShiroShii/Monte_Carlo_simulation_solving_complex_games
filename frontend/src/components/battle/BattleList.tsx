import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import useBattleList from './UseBattleList'

function BattleList() {
    const [loading, setLoading] = useState(true)
    const battleList = useBattleList(setLoading)

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
                return <Link to={`/battle/${params.id}`}>Details</Link>;
            },
        },
    ];

    return (
        <DataGrid autoHeight loading={loading} rows={battleList} columns={columns} />
    );
}

export default BattleList
