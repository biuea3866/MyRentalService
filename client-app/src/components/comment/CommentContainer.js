import React from 'react';
import styled from 'styled-components';
import CommentList from './CommentList';
import WriteContainer from './WriteContainer';

const CommentBlock = styled.div`
    margin-top: 3rem;
`;

const CommentContainer = () => {
    return(
        <CommentBlock>
            <WriteContainer />
            <CommentList />
        </CommentBlock>
    );
};

export default CommentContainer;