import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const AuthTemplateBlock = styled.div`
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    background: ${ palette.gray[2] };
    padding-top: 130px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

const WhiteBox = styled.div`
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
    padding: 2rem;
    width: 80%;
    background: white;
    border-radius: 2px;
`;


const AuthTemplate = ({ children }) => {
    return(
        <AuthTemplateBlock>
            <WhiteBox>
                { children }
            </WhiteBox>
        </AuthTemplateBlock>
    );
};

export default AuthTemplate;