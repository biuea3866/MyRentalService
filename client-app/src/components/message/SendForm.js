import React, { useEffect } from 'react';
import styled from 'styled-components';
import InputBar from './common/InputBar';
import InputButton from './common/InpuButton';
import { useDispatch, useSelector } from 'react-redux';
import { changeField, sendChat } from '../../modules/send';
import { readChatList } from '../../modules/messageList';
import { withRouter } from 'react-router-dom';

const SendContainer = styled.div`
    width: inherit;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    overflow-y: auto;
    overflow-x: hidden;
`;

const StyledInputBar = styled(InputBar)`
    width: 100%;
`;

const SendForm = ({ match }) => {
    const dispatch = useDispatch();
    const {
        nickname,
        sender,
        receiver,
        content,
    } = useSelector(({
        send,
        user
    }) => ({
        nickname: user.user.nickname,
        sender: send.sender,
        receiver: send.receiver,
        content: send.content
    }));

    const onChange = e => {
        e.preventDefault();

        const { value, name } = e.target;

        dispatch(changeField({
            key: name,
            value
        }));
    };

    const onSend = e => {
        e.preventDefault();

        dispatch(sendChat({
            receiver,
            sender,
            content,
        }));

        dispatch(readChatList({
            sender,
            receiver,
        }));
    };

    // 메시지 아이콘 클릭 후 sender = writer로 변경
    useEffect(() => {
        dispatch(changeField({
            key: 'sender',
            value: nickname
        }));
    }, [dispatch, nickname]);

    useEffect(() => {
        dispatch(changeField({
            key: 'receiver',
            value: match.params.writer
        }))
    }, [dispatch, match]);

    return (
        <SendContainer>
            <StyledInputBar name="content"
                            placeholder="메시지를 입력해주세요" 
                            onChange={ onChange } />
            <InputButton text="보내기"
                         onClick={ onSend } />            
        </SendContainer>
    );
};

export default withRouter(SendForm);