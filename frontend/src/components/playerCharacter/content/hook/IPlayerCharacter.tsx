import { Weapon } from "../../../_common";

interface IPlayerCharacter {
    id: string
    name: string
    dexterity: number
    strength: number
    speed: number
    armorClass: number
    characterLevel: string
    characterClass: string
    weapons: [keyof typeof Weapon]
}

export default IPlayerCharacter