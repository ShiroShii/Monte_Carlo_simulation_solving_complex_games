import { DataGrid, GridColDef, GridRowsProp } from '@material-ui/data-grid';
import axios from 'axios';
import { useEffect, useState } from 'react';

function BattleList() {
    const [battle, getBattle] = useState<GridRowsProp>([])

    useEffect(() => {
        axios.get('http://localhost:8080/board')
            .then((response) => {
                getBattle(response.data);
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, []);

    const columns: GridColDef[] = [
        { field: 'id', headerName: 'ID', width: 500 }
    ];

    return (
        <DataGrid autoHeight rows={battle} columns={columns} />
    );
}

export default BattleList;