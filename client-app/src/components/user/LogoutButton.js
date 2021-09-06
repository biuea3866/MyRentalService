import React from 'react';
import styled from 'styled-components';
import palette from '../../../lib/styles/palettes';

const Button = styled.button`
    width: 100%;
    height: 40px;
    border-radius: 4px;
    background-color: ${palette.blue[2]};
    color: #ffffff;
    border: none;
    &:hover {
        width: 100%;
        height: 40px;
        border-radius: 4px;
        background-color: ${palette.blue[1]};
        color: #ffffff;
        border: none;
    }
`;

const LogoutButton = () => {
    return(
        <Button>
            Logout
        </Button>
    );
};

export default LogoutButton;