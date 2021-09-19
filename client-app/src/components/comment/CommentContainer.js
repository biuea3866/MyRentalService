import React from 'react';
import styled from 'styled-components';
import CommentList from './CommentList';
import WriteContainer from './WriteContainer';

const CommentBlock = styled.div`
    margin-top: 3rem;
    min-height: 200px;
`;

const CommentContainer = ({ comments }) => {
    return(
        <CommentBlock>
            <WriteContainer />
            <CommentList comments={ comments }/>
        </CommentBlock>
    );
};

export default CommentContainer;