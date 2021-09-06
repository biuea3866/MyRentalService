import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import ArticleForm from '../components/home/ArticleForm';
import ArticleTemplate from '../components/home/ArticleTemplate';

const HomePage = () => {
    return(
        <>
            <HeaderTemplate />
            <ArticleTemplate>
                <ArticleForm />
            </ArticleTemplate>
        </>
    );
};

export default HomePage;