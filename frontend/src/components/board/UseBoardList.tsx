import { GridRowsProp } from '@material-ui/data-grid';
import axios from 'axios';
import { useState, useEffect } from 'react';

function useBoardList(setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [boardList, setBoardList] = useState<GridRowsProp>([])

    useEffect(() => {
        axios.get('http://localhost:8080/board')
            .then((response) => {
                setBoardList(response.data);
                setLoading(false)
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, [setLoading]);

    return boardList;
}

export default useBoardList