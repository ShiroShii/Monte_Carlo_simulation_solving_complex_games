import { GridRowsProp } from '@material-ui/data-grid';
import axios from 'axios';
import { useState, useEffect } from 'react';

function useBattleList(setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [battleList, setBattleList] = useState<GridRowsProp>([])

    useEffect(() => {
        axios.get('http://localhost:8080/battle')
            .then((response) => {
                setBattleList(response.data);
                setLoading(false)
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, [setLoading]);

    return battleList;
}

export default useBattleList