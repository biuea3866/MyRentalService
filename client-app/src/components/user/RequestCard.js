import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField, completeRental, initialize, intialize } from '../../modules/rental';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import ButtonContainer from './ButtonContainer';
import { rollbackPost } from '../../modules/post';

const WhiteBox = styled.div`
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
    padding: 2rem;
    width: 40%;
    background: white;
    border-radius: 2px;
    margin-top: 2rem;
    margin-bottom: 2rem;
`;

const Content = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    font-size: 1.5rem;
    font-weight: bold;
    border-bottom: 1px solid ${palette.gray[2]};
    padding-bottom: 1.5rem;
`;

const Footer = styled.div`
    width: 100%;
    display: flex;
    justify-content: flex-end;
`;

const RequestCard = ({ item, i }) => {
    const dispatch = useDispatch()
    const {
        acceptance,
        rentalId,
        rental
    } = useSelector(({ rental }) => ({
        acceptance: rental.acceptance,
        rentalId: rental.rentalId,
        rental: rental.rental
    }));
    const onAccept = () => {
        dispatch(changeField({
            key: 'acceptance',
            value: true
        }));
    };

    const onDecline = () => {
        dispatch(changeField({
            key: 'acceptance',
            value: false
        }));
    };

    useEffect(() => {
        dispatch(changeField({
            key: 'rentalId',
            value: item.rentalId
        }));
    }, [dispatch, item]);

    useEffect(() => {
        if(acceptance === true) {
            dispatch(completeRental({
                acceptance,
                rentalId
            }));

            dispatch(initialize());
        }

        if(acceptance === false) {
            const postId = item.postId;

            dispatch(completeRental({
                acceptance,
                rentalId
            }));

            dispatch(initialize());

            dispatch(rollbackPost(postId));
        }
    }, [dispatch, acceptance, rentalId, item]);

    return(
        <WhiteBox>
            <Content>
                { item.borrower } 님이 대여 요청을 하였습니다.
            </Content>
            <Footer>
                <ButtonContainer onAccept={ onAccept }
                                 onDecline={ onDecline }
                />
            </Footer>
        </WhiteBox>
    );
};

export default RequestCard;