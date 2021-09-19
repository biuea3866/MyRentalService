import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router';
import { readPost, unloadPost } from '../../modules/post';
import { initialize } from '../../modules/writeComment';
import PostViewer from './PostViewer';

const PostViewerContainer = ({ match }) => {
    const { id } = match.params;
    const dispatch = useDispatch();
    const { post, error, loading } = useSelector(({ post, loading }) => ({
        post: post.post,
        error: post.error,
        loading: loading['post/READ_POST'],
    }));

    useEffect(() => {
        dispatch(readPost(id));

        return() => {
            dispatch(unloadPost());
            dispatch(initialize());
        };
    }, [dispatch, id]);

    return <PostViewer post={ post } 
                       loading={ loading } 
                       error={ error } 
            />;
};

export default withRouter(PostViewerContainer);