import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import RequestListContainer from '../components/user/RequestListContainer';

const RequestPage = () => {
    return(
        <>
            <HeaderTemplate />
            <RequestListContainer />
        </>
    );
};

export default RequestPage