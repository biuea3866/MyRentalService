import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import PostViewerContainer from '../components/posts/PostViewerContainer';

const PostDetailPage = () => {
    return(
        <>
            <HeaderTemplate />
            <PostViewerContainer />
        </>
    );
};

export default PostDetailPage;