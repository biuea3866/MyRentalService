import React, { useState } from "react";
import Responsive from "../common/Responsive";
import styled from "styled-components";
import palette from "../../lib/styles/palettes";
import RadioForm from "../common/RadioForm";
import RadioItem from "../common/RadioItem";
import ImageUploader from 'react-images-upload'
import Select from 'react-select';
import Input from "../common/Input";
import { RangeDatePicker } from 'react-google-flight-datepicker';
import 'react-google-flight-datepicker/dist/main.css';
import WriteButtonContainer from "./WriteButtonContainer";

const WriteFormBlock = styled(Responsive)`
    padding-top: 5rem;
    padding-bottom: 5rem;
`;

const PostTypeArea = styled.div`
    width: 200px;
    margin-top: 20px;
    margin-bottom: 2rem;
`;

const TitleInput = styled.input`
    width: 100%;
    font-size: 1rem;
    padding-bottom: 0.5rem;
    border: none;
    border-bottom: 1px solid ${ palette.blue[2] };
    margin-top: 1rem;
    margin-bottom: 2rem;
    outline: none;
`;

const ContentInput = styled.textarea`
    width: 100%;
    height: 400px;
    font-size: 1rem;
    border: none;
    border-bottom: 1px solid ${ palette.blue[2] };
    margin-top: 1rem;
    margin-bottom: 1rem;
    outline: none;
    resize: none;
`;

const ErrorMessage = styled.div`
    color: red;
    text-align: center;
    font-size: 14px;
    margin-top: 1rem;
`;

const WriteForm = ({ 
    onChangeField,
    onDrop, 
    onUpdate, 
    onSelect, 
    options,
    option,
    postType 
}) => {
    const [error, setError] = useState('');

    return(
        <>
            { 
                postType === '빌려주세요' ? 
                <>
                    <WriteFormBlock>
                        <form>
                            <PostTypeArea>
                                <RadioForm>
                                    <RadioItem id="postBorrow"
                                               name="postType"
                                               value="빌려주세요"
                                               for="postBorrow"
                                               onChange={ onChangeField }
                                    />
                                    <RadioItem id="postRental"
                                               name="postType"
                                               value="빌려줄게요"
                                               for="postRental"
                                               onChange={ onChangeField }
                                    />
                                </RadioForm>
                            </PostTypeArea>
                            <TitleInput autoComplete="title"
                                        name="title"
                                        placeholder="게시글 제목을 작성해주세요"
                                        onChange={ onChangeField }
                            />
                            <ContentInput autoComplete="content"
                                          name="content"
                                          placeholder="게시글 내용을 작성해주세요"
                                          onChange={ onChangeField }
                            />
                        </form>
                        { error && <ErrorMessage>{ error }</ErrorMessage> }
                    </WriteFormBlock>
                    <WriteButtonContainer 
                        error={ error }
                        setError={ setError }
                    />
                </> :
                <>
                    <WriteFormBlock>
                        <form encType="multipart/form-data">
                            <PostTypeArea>
                                <RadioForm>
                                    <RadioItem id="postBorrow"
                                               name="postType"
                                               value="빌려주세요"
                                               for="postBorrow"
                                               onChange={ onChangeField }
                                    />
                                    <RadioItem id="postRental"
                                               name="postType"
                                               value="빌려줄게요"
                                               for="postRental"
                                               onChange={ onChangeField }
                                    />
                                </RadioForm>
                            </PostTypeArea>
                            <Select onChange={ onSelect }
                                    options={ options }
                                    value={ option }
                                    placeholder="카테고리를 정해주세요"
                            />
                            <TitleInput autoComplete="title"
                                        name="title"
                                        placeholder="게시글 제목을 작성해주세요"
                                        onChange={ onChangeField }
                            />
                            <ContentInput autoComplete="content"
                                          name="content"
                                          placeholder="게시글 내용을 작성해주세요"
                                          onChange={ onChangeField }
                            />
                            <Input autoComplete="rentalPrice"
                                   name="rentalPrice"
                                   placeholder="가격을 입력해주세요"
                                   onChange={ onChangeField }
                            />
                            <RangeDatePicker onChange={ onUpdate }
                                             startDatePlaceholder="시작 날짜"
                                             endDatePlaceholder="종료 날짜"
                                             disabled={false}
                            />
                            <ImageUploader withIcon={ true }
                                           buttonText='이미지를 선택해주세요'
                                           onChange={ onDrop }
                                           name="images"
                                           imgExtension={['.jpg', '.gif', '.png', '.gif']}
                                           maxFileSize={ 5242880 }
                                           withPreview={ true }
                            />
                        </form>
                        { error && <ErrorMessage>{ error }</ErrorMessage> }
                    </WriteFormBlock>
                    <WriteButtonContainer error={ error }
                                          setError={ setError }
                    />
                </>
            }
        </>
    );
};

export default WriteForm;