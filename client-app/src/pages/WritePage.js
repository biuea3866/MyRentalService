import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import WriteHeaderTemplate from '../components/posts/WriteHeaderTemplate';
import WriteTemplate from '../components/posts/WriteTemplate';
import WriteContainer from '../components/posts/WriteContainer';
import WriteButtonContainer from '../components/posts/WriteButtonContainer';

const WritePage = () => {
    return(
        <>
            <HeaderTemplate />
            <WriteTemplate>
                <WriteHeaderTemplate />
                <WriteContainer />
                <WriteButtonContainer />
            </WriteTemplate>
        </>
    );
};

export default WritePage;