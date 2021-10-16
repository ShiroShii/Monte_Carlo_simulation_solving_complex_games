import axios from 'axios';
import { useEffect, useState } from 'react';
import { IBattle } from '..';

function useBattleList(setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [battleList, setBattleList] = useState<IBattle[]>([])

    useEffect(() => {
        axios.get('http://localhost:8080/battle')
            .then((response) => {
                setBattleList(response.data);
                setLoading(false)
            });
    }, [setLoading]);

    return battleList;
}

export default useBattleList
