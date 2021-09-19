import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router';
import { initialize, writePost } from '../../modules/write';
import WriteButton from './common/WriteButton';

const WriteButtonContainer = ({ history, error, setError }) => {
    const dispatch = useDispatch();
    const {
        userId,
        postType,
        category,
        rentalPrice,
        title,
        content,
        date,
        writer,
        images,
        post,
        postError,
    } = useSelector(({ write }) => ({
        userId: write.userId,
        postType: write.postType,
        category: write.category,
        rentalPrice: write.rentalPrice,
        title: write.title,
        content: write.content,
        date: write.date,
        writer: write.writer,
        images: write.images,
        post: write.post,
        postError: write.postError,
    }));

    const onPublish = () => {
        if(title === '') {
            setError('제목을 입력해주세요');

            return;
        }

        if(content === '') {
            setError('내용을 적어주세요');

            return;
        }

        if(category === '' && postType === '빌려줄게요') {
            setError('카테고리를 지정해주세요');

            return;
        }

        if(rentalPrice === null && postType === '빌려줄게요') {
            setError('가격을 입력해주세요');

            return;
        }

        if(category === '' && postType === '빌려줄게요') {
            setError('카테고리를 지정해주세요');

            return;
        }

        if(date === null && postType === '빌려줄게요') {
            setError('날짜를 정해주세요');

            return;
        }

        if(images === null && postType === '빌려줄게요') {
            setError('이미지를 넣어주세요');

            return;
        }

        // if(postError) {
        //     setError('에러 발생!');

        //     return;
        // }

        dispatch(writePost({
            userId,
            postType,
            category,
            rentalPrice,
            title,
            content,
            date,
            writer,
            images,
        }));

        dispatch(initialize());
        
        history.push('/posts');
    };

    const onCancel = () => {
        dispatch(initialize());

        history.goBack();
    };

    return (
        <WriteButton onPublish={ onPublish }
                     onCancel={ onCancel }
        />
    );
};

export default withRouter(WriteButtonContainer);