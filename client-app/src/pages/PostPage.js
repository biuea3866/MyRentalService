import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import PostListTemplate from '../components/posts/PostListTemplate';
import SearchHeaderTemplate from '../components/posts/SearchHeaderTemplate';

const PostPage = () => {
    return(
        <>
            <HeaderTemplate />
            <SearchHeaderTemplate />
            <PostListTemplate />
        </>
    );
};

export default PostPage;