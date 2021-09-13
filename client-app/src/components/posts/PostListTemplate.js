import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import { withRouter } from 'react-router';
import PostHeader from './PostHeader';

const PostListTemplateBlock = styled.div`
    background: ${ palette.gray[2] };
    padding-top: 130px;
    margin-top: 20px;
    display: flex;
    flex-direction: center;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
`;

const PostListTemplate = ({ history }) => {
    return(
        <>
            <PostHeader />
            <PostListTemplateBlock>
                {/* PostCard 추가 */}
            </PostListTemplateBlock>
        </>
    );
};

export default withRouter(PostListTemplate);