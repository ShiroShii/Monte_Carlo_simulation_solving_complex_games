import { DataGrid, GridCellParams, GridColDef, GridRowsProp } from '@material-ui/data-grid'
import axios from 'axios'
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

function BoardList() {
    const [board, setBoard] = useState<GridRowsProp>([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        axios.get('http://localhost:8080/board')
            .then((response) => {
                setBoard(response.data);
                setLoading(false);
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, []);

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
                return <Link to={`/board/${params.id}`}>Details</Link>;
            },
        },
    ];

    return (
        <DataGrid autoHeight loading={loading} rows={board} columns={columns} />
    );
}

export default BoardList
