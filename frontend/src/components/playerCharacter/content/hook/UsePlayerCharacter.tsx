import axios from 'axios';
import { useEffect, useState } from 'react';
import IPlayerCharacter from './IPlayerCharacter';

function usePlayerCharacter(id: String, setLoading: (value: React.SetStateAction<boolean>) => void) {
    const [playerCharacter, setPlayerCharacter] = useState<IPlayerCharacter>()

    useEffect(() => {
        axios.get(`http://localhost:8080/player-character/${id}`)
            .then((response) => {
                setPlayerCharacter(response.data);
                setLoading(false);
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    }, [setLoading, id]);

    return playerCharacter;
}

export default usePlayerCharacter