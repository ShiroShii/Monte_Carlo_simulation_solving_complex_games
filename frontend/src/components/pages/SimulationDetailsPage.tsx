import React from 'react';

type SimulationDetailsPageProps = {
    id: string;
}

function SimulationDetailsPage(props: SimulationDetailsPageProps) {
    return (
        <p>Simulation Details Page for id={props.id}</p>
    );
}

export default SimulationDetailsPage;