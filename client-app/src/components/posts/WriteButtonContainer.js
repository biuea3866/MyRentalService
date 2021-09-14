import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router';
import { initialize, writePost } from '../../modules/write';
import WriteButton from './common/WriteButton';

const WriteButtonContainer = ({ history }) => {
    const dispatch = useDispatch();
    const {
        userId,
        type,
        category,
        rentalPrice,
        title,
        content,
        date,
        writer,
        images,
        post,
    } = useSelector(({ write }) => ({
        userId: write.userId,
        type: write.type,
        category: write.category,
        rentalPrice: write.rentalPrice,
        title: write.title,
        content: write.content,
        date: write.date,
        writer: write.writer,
        images: write.image,
        post: write.post,
    }));

    const onPublish = () => {
        dispatch(writePost({
            userId,
            type,
            category,
            rentalPrice,
            title,
            content,
            date,
            writer,
            images,
        }));
    };

    const onCancel = () => {
        dispatch(initialize());

        history.goBack();
    };

    useEffect(() => {
        if(post) {
            history.onPublish("/posts");
        }
    }, [history, post]);

    return (
        <WriteButton
            onPublish={ onPublish }
            onCancel={ onCancel }
        />
    );
};

export default withRouter(WriteButtonContainer);