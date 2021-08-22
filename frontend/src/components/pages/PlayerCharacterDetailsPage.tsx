type PlayerCharacterDetailsPageProps = {
    id: string;
}

function PlayerCharacterDetailsPage(props: PlayerCharacterDetailsPageProps){
    return(
        <p>Character Details Page for id={props.id}</p>
    );
}

export default PlayerCharacterDetailsPage;