import axios from 'axios';
import { useEffect, useState } from 'react';
import { IBattle } from '../form';

function useBattle(id: string, setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [battle, setBattle] = useState<IBattle>()

    useEffect(() => {
        axios.get(`http://localhost:8080/battle/${id}`)
            .then((response) => {
                setBattle(response.data);
                setLoading(false);
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, [setLoading, id]);

    return battle;
}

export default useBattle
