import { CircularProgress, MenuItem, TextField } from "@material-ui/core"
import { useState } from "react"
import { Field } from "react-final-form"
import useBoardList from "../board/UseBoardList"

function BoardField() {
    const [loading, setLoading] = useState(true)
    const battleList = useBoardList(setLoading)

    return (
        <>
            {loading ? <CircularProgress /> :
                <Field name="boardId">
                    {props => (
                        <div>
                            <TextField
                                name={props.input.name}
                                value={props.input.value}
                                onChange={props.input.onChange}
                                select
                                label="Class"
                                required
                            >
                                {battleList.map((option) => (
                                    <MenuItem key={option.id} value={option.id}>
                                        {option.name}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </div>
                    )}
                </Field>
            }
        </>
    )
}

export default BoardField
