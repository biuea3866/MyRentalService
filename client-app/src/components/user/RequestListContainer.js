import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import RequestCard from './RequestCard';
import { requestRentals } from '../../modules/rental';

const Box = styled.div`
    width: 100%;
    height: 100vh;
    overflow-x: hidden;
    overflow-y: auto;
    background: ${palette.gray[2]};
    border-radius: 2px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`;

const NotData = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.5rem;
`;

const RequestListContainer = () => {
    const dispatch = useDispatch();
    const { 
        rentals,
        nickname 
    } = useSelector(({ 
        rental,
        user
    }) => ({ 
        rentals: rental.rentals,
        nickname: user.user.nickname,
    }));

    useEffect(() => {
        dispatch(requestRentals(nickname));
    }, [dispatch, nickname]);

    return(
        <Box>
            {
                rentals ?
                rentals.map((item, i) => {
                    return <RequestCard item={ item } />
                }) : 
                <NotData>
                    대여 요청이 존재하지 않습니다!
                </NotData>
            }
        </Box>
    );
};

export default RequestListContainer;