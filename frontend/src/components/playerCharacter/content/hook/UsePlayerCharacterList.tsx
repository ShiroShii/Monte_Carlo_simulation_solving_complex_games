import axios from 'axios';
import { useEffect, useState } from 'react';
import { IPlayerCharacter } from '.';

export default function usePlayerCharacterList() {
    const [list, setList] = useState<IPlayerCharacter[]>()

    useEffect(() => {
        axios.get('http://localhost:8080/player-character')
            .then((response) => {
                setList(response.data);
            });
    },[]);

    return list;
}
