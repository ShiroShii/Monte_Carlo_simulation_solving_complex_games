type BattleDetailsPageProps = {
    id: string;
}

function BattleDetailsPage(props: BattleDetailsPageProps) {
    return (
        <p>Battle Details Page for id={props.id}</p>
    );
}

export default BattleDetailsPage;