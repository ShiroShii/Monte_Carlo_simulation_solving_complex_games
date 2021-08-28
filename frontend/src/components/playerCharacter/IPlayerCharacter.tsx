interface IPlayerCharacter {
    id: String
    name: String
    dexterity: Number
    strength: Number
    walkingSpeed: Number
    armorClass: Number
    characterLevel: String
    characterClass: String
    weapons: [String]
}

export default IPlayerCharacter