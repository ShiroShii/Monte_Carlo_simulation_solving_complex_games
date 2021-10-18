import { DataGrid, GridCellParams, GridColDef } from '@material-ui/data-grid'
import { CharacterClass, CharacterLevel, LinkButton, Weapon } from '../../_common'
import { IPlayerCharacter, usePlayerCharacterList } from './hook'

const transformData = (data: IPlayerCharacter[]) => {
    return data.map(x => ({
        id: x.id,
        name: x.name,
        dexterity: x.dexterity,
        strength: x.strength,
        speed: x.speed,
        armorClass: x.armorClass,
        characterLevel: CharacterLevel[x.characterLevel],
        characterClass: CharacterClass[x.characterClass],
        weapons: x.weapons.map(weapon => Weapon[weapon]).join(', ')
    }))
}

const columns: GridColDef[] = [
    {
        field: 'name',
        headerName: 'Name',
        flex: 1.3,
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
        flex: 0.6,
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
                <LinkButton
                    to={`/character/${params.id}`}>
                    Details
                </LinkButton>
            )
        },
    },
];

export default function PlayerCharacterList() {
    const list = usePlayerCharacterList()

    return (
        <DataGrid
            autoHeight
            loading={!list}
            rows={list ? transformData(list) : []}
            columns={columns}
        />
    );
}
