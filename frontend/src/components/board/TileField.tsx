import { Button } from "@material-ui/core"
import { FieldArray } from 'react-final-form-arrays'
import ReachableTileField from "./ReachableTileField"
import TerrainFeatureField from "./TerrainFeatureField"

type TileFieldProps = {
    push: (...args: any[]) => any
}

function TileField(props: TileFieldProps) {
    return (
        <>
            <Button onClick={() => props.push('nodes', '')}>
                Add Tile
            </Button>
            <FieldArray name="nodes">
                {({ fields }) =>
                    fields.map((name, nodeIndex) => (
                        <div>
                            <label>Tile {nodeIndex + 1}.</label>
                            <TerrainFeatureField nodeIndex={nodeIndex} />
                            <ReachableTileField nodeFieldLength={fields.length as number} nodeIndex={nodeIndex} push={props.push} />
                            <span
                                onClick={() => fields.remove(nodeIndex)}
                                style={{ cursor: 'pointer' }}
                            >
                                ❌
                            </span>
                        </div>
                    ))
                }
            </FieldArray>
        </>
    )
}

export default TileField
