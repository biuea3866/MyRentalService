import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import MyPageForm from './MyPageForm';

const MyPageTemplateBlock = styled.div`
    background: ${ palette.gray[2] };
    padding-top: 130px;
    display: flex;
    justify-content: center;
    align-items: center;
`;

const WhiteBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 2rem;
    margin: 0;
    width: 100%;
`;

const MyPageTemplate = ({ childiren }) => {
    return(
        <MyPageTemplateBlock>
            <WhiteBox>
                <MyPageForm />
            </WhiteBox>
        </MyPageTemplateBlock>
    );
};

export default MyPageTemplate;