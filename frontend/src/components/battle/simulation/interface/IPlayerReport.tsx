import IPlayerBoxPlot from "./IPlayerBoxPlot";

interface IPlayerReport {
    id: string
    name: string
    downCount: number
    playerBoxPlot: IPlayerBoxPlot
}

export default IPlayerReport
