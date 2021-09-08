import axios from 'axios'
import arrayMutators from 'final-form-arrays'
import { Form } from "react-final-form"
import NameField from '../common/NameField'
import Tile from './Tile';
import TileField from './TileField'
function BoardCreationForm() {
    interface Values {
        name: String,
        List: [Tile]
    }

    const onSubmit = async (values: Values) => {
        axios.post('http://localhost:8080/board', values)
            .then((response) => {
                console.log(response);
                //TODO: redirect to details
            }).catch(response => {
                console.log(response);
                //TODO: toster error
            });
    };

    return (
        <Form
            onSubmit={onSubmit}
            mutators={{ ...arrayMutators }}
            render={({
                handleSubmit,
                form: {
                    mutators: { push }
                },
            }) => (
                <form onSubmit={handleSubmit}>
                    <NameField />
                    <TileField push={push} />
                    <button type="submit">Submit</button>
                </form>
            )}
        />
    )
}

export default BoardCreationForm
