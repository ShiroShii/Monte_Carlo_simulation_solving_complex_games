import { GridRowsProp } from '@material-ui/data-grid';
import axios from 'axios';
import { useState, useEffect } from 'react';

function usePlayerCharacterList(setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [playerCharacterList, setPlayerCharacterList] = useState<GridRowsProp>([])

    useEffect(() => {
        axios.get('http://localhost:8080/player-character')
            .then((response) => {
                setPlayerCharacterList(response.data);
                setLoading(false)
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, [setLoading]);

    return playerCharacterList;
}

export default usePlayerCharacterList