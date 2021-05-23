type CharacterDetailsPageProps = {
    id: string;
}

function CharacterDetailsPage(props: CharacterDetailsPageProps){
    return(
        <p>Character Details Page for id={props.id}</p>
    );
}

export default CharacterDetailsPage;