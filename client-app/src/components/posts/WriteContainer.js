import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField } from '../../modules/write';
import WriteForm from './WriteForm';

const WriteContainer = () => {
    const dispatch = useDispatch();
    const { user } = useSelector(({ user }) => ({
        user: user.user,
    }));
    const { postType } = useSelector(({ write }) => ({
        postType: write.postType,
    }));

    const onDrop = (pictures, urls) => {
        dispatch(changeField({
            key: "images",
            value: pictures
        }));
    };

    const onChangeField = e => {
        const { value, name } = e.target;

        dispatch(changeField({
            key: name,
            value
        }));
    };

    const onUpdate = (startDate, endDate) => {
        var start = splitString(startDate);
        var end = splitString(endDate);

        dispatch(changeField({
            key: "date",
            value: [start, end],
        }));
    };

    const onSelect = (value) => {
        dispatch(changeField({
            key: "category",
            value: value.value
        }))

        setOption(value);
    };

    const [option, setOption] = useState('');
    const options = [
        { value: '가전제품', label: '가전제품' },
        { value: '도서류', label: '도서류' },
        { value: '의류', label: '의류' },
        { value: '캠핑용품', label: '캠핑용품' },
    ];

    useEffect(() => {
        if(user) {
            dispatch(changeField({
                key: "writer",
                value: user.nickname,
            }));
        }
    }, [dispatch, user]);

    useEffect(() => {
        if(user) {
            dispatch(changeField({
                key: "userId",
                value: user.userId
            }));
        }
    }, [dispatch, user]);

    function splitString(str) {
        var _arr = `${ str }`.split(' ');

        return _arr[0] + ' ' + _arr[1] + ' ' + _arr[2] + ' ' + _arr[3];
    }

    return (
        <WriteForm onChangeField={ onChangeField }
                   onDrop={ onDrop }
                   onUpdate={ onUpdate }
                   onSelect={ onSelect }
                   options={ options } 
                   option={ option }
                   postType={ postType }
        />
    );
};

export default WriteContainer;