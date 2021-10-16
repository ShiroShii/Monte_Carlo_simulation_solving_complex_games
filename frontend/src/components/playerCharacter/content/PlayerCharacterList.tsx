import { Button } from '@material-ui/core'
import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import { CharacterClass, CharacterLevel, Weapon } from '../../_common'
import { IPlayerCharacter, usePlayerCharacterList } from './hook'

export default function PlayerCharacterList() {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacterList(setLoading)
    const columns: GridColDef[] = [
        {
            field: 'name',
            headerName: 'Name',
            flex: 2,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'center'
        },
        {
            field: 'dexterity',
            headerName: 'DEX',
            flex: 0.5,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'strength',
            headerName: 'STR',
            flex: 0.5,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'speed',
            headerName: 'SPD',
            flex: 0.5,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'armorClass',
            headerName: 'AC',
            flex: 0.5,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'characterLevel',
            headerName: 'LVL',
            flex: 0.5,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'right'
        },
        {
            field: 'characterClass',
            headerName: 'Class',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'center'
        },
        {
            field: 'weapons',
            headerName: 'Weapons',
            flex: 1,
            hideSortIcons: true,
            headerAlign: 'center',
            align: 'center'
        },
        {
            field: 'id',
            headerName: ' ',
            flex: 0.6,
            sortable: false,
            disableColumnMenu: true,
            align: 'center',
            renderCell: (params: GridCellParams) => {
                return (
                    <Button
                        component={Link}
                        variant="contained"
                        to={`/character/${params.id}`}>
                        Details
                    </Button>
                )
            },
        },
    ];

    const transformData = (data: IPlayerCharacter[]) => {
        return data.map(x => {
            return ({
                id: x.id,
                name: x.name,
                dexterity: x.dexterity,
                strength: x.strength,
                speed: x.speed,
                armorClass: x.armorClass,
                characterLevel: CharacterLevel[x.characterLevel],
                characterClass: CharacterClass[x.characterClass],
                weapons: x.weapons.map(weapon => Weapon[weapon])
            })
        }
        )
    }

    return (
        <DataGrid
            autoHeight
            loading={loading}
            rows={transformData(playerCharacter)}
            columns={columns}
        />
    );
}
