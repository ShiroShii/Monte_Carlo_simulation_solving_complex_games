import React from 'react';

type BoardDetailsPageProps = {
    id: string;
}

function BoardDetailsPage(props: BoardDetailsPageProps){
    return(
        <p>Board Details Page for id={props.id}</p>
    );
}

export default BoardDetailsPage;