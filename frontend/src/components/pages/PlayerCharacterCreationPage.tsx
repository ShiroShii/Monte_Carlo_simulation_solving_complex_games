import { Form } from 'react-final-form'

function PlayerCharacterCreationPage() {
    function onSubmit(){
        console.log("Submitted");
    }

    return (
        <>
            <p>Player Character Creation Page</p>
            <Form onSubmit={onSubmit}>
                {props => (
                    <form onSubmit={props.handleSubmit}>

                        ... fields go here...

                        <button type="submit">Submit</button>
                    </form>
                )}
            </Form>

        </>
    );
}

export default PlayerCharacterCreationPage;