import { DataGrid, GridColDef, GridRowsProp } from '@material-ui/data-grid';
import axios from 'axios';
import { useEffect, useState } from 'react';

function PlayerCharacterList() {
    const [playerCharacter, getPlayerCharacter] = useState<GridRowsProp>([])

    useEffect(() => {
        axios.get('http://localhost:8080/player-character')
            .then((response) => {
                getPlayerCharacter(response.data);
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, []);

    const columns: GridColDef[] = [
        { field: 'id', headerName: 'ID', width: 500 }
    ];

    return (
        <DataGrid autoHeight rows={playerCharacter} columns={columns} />
    );
}

export default PlayerCharacterList;