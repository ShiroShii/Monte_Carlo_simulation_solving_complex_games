import { Weapon } from "../../../_common";

interface IPlayerCharacter {
    id: String
    name: String
    dexterity: Number
    strength: Number
    speed: Number
    armorClass: Number
    characterLevel: String
    characterClass: String
    weapons: [keyof typeof Weapon]
}

export default IPlayerCharacter