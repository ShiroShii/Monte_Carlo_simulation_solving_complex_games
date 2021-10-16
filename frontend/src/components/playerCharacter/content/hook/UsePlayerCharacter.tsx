import axios from 'axios';
import { useEffect, useState } from 'react';
import IPlayerCharacter from './IPlayerCharacter';

function usePlayerCharacter(id: string) {
    const [playerCharacter, setPlayerCharacter] = useState<IPlayerCharacter>()

    useEffect(() => {
        axios.get(`http://localhost:8080/player-character/${id}`)
            .then((response) => {
                setPlayerCharacter(response.data);
            });
    }, [id]);

    return playerCharacter;
}

export default usePlayerCharacter