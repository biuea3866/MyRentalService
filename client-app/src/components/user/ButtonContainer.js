import React from 'react';
import styled from 'styled-components';
import FullButton from '../common/FullButton';

const CustomFullButton = styled(FullButton)`
    margin-right: 25px;
    margin-left: 25px;
    width: 120px;
    &:hover {
        margin-right: 25px;
        margin-left: 25px;
        width: 120px;
    }
`;

const ButtonContainer = ({
    onAccept, 
    onDecline
}) => {
    return(
        <>
            <CustomFullButton onClick={ onAccept }>
                수락하기
            </CustomFullButton>
            <CustomFullButton red
                              onClick={ onDecline }                
            >
                거절하기
            </CustomFullButton>
        </>
    );
};

export default ButtonContainer;