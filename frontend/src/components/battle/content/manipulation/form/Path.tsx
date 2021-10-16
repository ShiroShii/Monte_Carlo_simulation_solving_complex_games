class Path {
    id: string
    tileIds: [string, string]

    constructor(id: string, tileId1: string, tileId2: string) {
        this.id = id
        this.tileIds = [tileId1, tileId2]
    }
}

export default Path
