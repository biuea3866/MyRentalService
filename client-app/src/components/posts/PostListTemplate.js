import React, { useEffect } from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import { withRouter } from 'react-router';
import PostHeader from './PostHeader';
import { useDispatch, useSelector } from 'react-redux';
import { readPostList } from '../../modules/postList';
import PostCard from './PostCard';
import PostLoading from './common/PostLoading';

const PostListTemplateBlock = styled.div`
    background: ${ palette.gray[2] };
    padding-top: 30px;
    display: flex;
    flex-direction: center;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
`;

const PostListTemplate = ({ history }) => {
    const dispatch = useDispatch();
    const { user } = useSelector(({ user }) => ({
        user: user.user
    }));
    const { 
        postList, 
        error, 
        loading 
    } = useSelector(({ 
        postList, 
        loading
    }) => ({
        postList: postList.postList,
        error: postList.error,
        loading: loading['posts/READ_POSTLIST']
    }));

    useEffect(() => {
        dispatch(readPostList());
    }, [dispatch]);

    const dummyData = [
        { "images": ["https://picsum.photos/id/0/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/1/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/2/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/3/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/4/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/5/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/6/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/7/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
        { "images": ["https://picsum.photos/id/8/1000/1000.jpg"], "title": "test-01", "nickname": "test-01", createdAt: "2020-01-01" },
    ];
    // useEffect(() => {
    //     if(!user) {
    //         history.push('/auth/login');
    //     }
    // }, [history, user]);
    
    return(
        <>
            <PostHeader />
            <PostListTemplateBlock>
                {
                    // postList !== null ?
                    // postList.PostListTemplate(
                    //     (item, i) => {
                    //         return  <PostCard item={ item }
                    //                           i={ i }
                    //                 />
                    //     }
                    // ) : <PostLoading />
                    dummyData.map((item, i) => {
                        return  <PostCard item={ item }
                                          i={ i }
                                />
                    })
                }
            </PostListTemplateBlock>
        </>
    );
};

export default withRouter(PostListTemplate);