import { CharacterClass, CharacterLevel, Weapon } from "../../../_common";

interface IPlayerCharacter {
    id: string
    name: string
    dexterity: number
    strength: number
    speed: number
    armorClass: number
    characterLevel: keyof typeof CharacterLevel
    characterClass: keyof typeof CharacterClass
    weapons: [keyof typeof Weapon]
}

export default IPlayerCharacter